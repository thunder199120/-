package cn.tedu.store.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.OrderNotFoundException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;
@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICartService cartService;
	@Autowired
	private IProductService productService;
	private void insertOrder(Order order){
		Integer rows =  orderMapper.insertOrder(order);
		if(rows!=1){
			throw new InsertException("创建订单失败!插入订单数据出现未知错误!");
		}
	}
	
	private void insertOrderItem(OrderItem orderItem){
		Integer rows =  orderMapper.insertOrderItem(orderItem);
		if(rows!=1){
			throw new InsertException("创建订单失败!插入订单数据出现未知错误!");
		}
	}
	
	private void updateStatus(Integer oid,Integer status,String username,Date modifiedTime){
		Integer rows = orderMapper.updateStatus(oid, status, username, modifiedTime);
		if(rows!=1){
			throw new UpdateException("修改资料失败!尝试访问的数据不存在!");
		}
	}
	private Order findByOid(Integer oid){
		return orderMapper.findByOid(oid);
	}
	@Override
	@Transactional
	public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
		// 创建当前时间对象
		Date now = new Date();
	    // 根据参数cids，通过cartService的getByCids()查询购物车数据，得到List<CartVO>类型的对象
		List<CartVO> list = cartService.getByCids(cids, uid);
	    // 遍历以上购物车数据集合对象以计算总价
		Long totalPrice = 0L;
		for (CartVO cart : list) {
			totalPrice += cart.getRealPrice()*cart.getNum();
		}
		
		//创建order对象
		Order order = new Order();
		
		order.setUid(uid);
		//根据参数aid，通过addressService的getByAid()查询收货地址数据
		Address address = addressService.getByAid(aid);
		//补全order所有属性
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		order.setRecvProvince(address.getProvinceName());
		order.setRecvCity(address.getCityName());
		order.setRecvArea(address.getAreaName());
		order.setRecvAddress(address.getAddress());
		order.setTotalPrice(totalPrice);
		order.setStatus(0);
		order.setOrderTime(now);
		order.setPayTime(null);
		order.setCreatedUser(username);
		order.setCreatedTime(now);
		order.setModifiedUser(username);
		order.setModifiedTime(now);
		//插入订单数据
		insertOrder(order);
		
		// 准备订单商品数据的集合，后续可用于归还商品库存
		List<OrderItem> orderItems = new ArrayList<>();
		// 遍历购物车数据集合对象
		for (CartVO cart : list) {
			// -- 创建OrderItem对象
			OrderItem orderItem = new OrderItem();
			//补全orderItem所有属性
			orderItem.setOid(order.getOid());
			orderItem.setPid(cart.getPid());
			orderItem.setTitle(cart.getTitle());
			orderItem.setImage(cart.getImage());
			orderItem.setPrice(cart.getRealPrice());
			orderItem.setNum(cart.getNum());
			orderItem.setCreatedUser(username);
			orderItem.setCreatedTime(now);
			orderItem.setModifiedUser(username);
			orderItem.setModifiedTime(now);
			
			// 将订单商品添加到集合中，后续可能用于归还库存
			orderItems.add(orderItem);
			
			//插入订单商品数据
			insertOrderItem(orderItem);
			// 销库存
			productService.reduceNum(cart.getPid(), cart.getNum());
		}
	    //删除购物车对应的数据
		cartService.delete(cids, uid);
		//开启倒计时任务,如果用户未在规定时间内未支付,则关闭订单,归还库存
		new Thread(){
			public void run(){
				System.err.println("OrderService：计划15分钟后检查订单状态，准备关闭订单");
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println("OrderService：准备关闭订单……");
				close(order.getOid(), orderItems, username); 
			}
		}.start();
		
		
		//返回成功创建的订单对象
		return order;
	}

	@Override
	public void changeStatus(Integer oid, Integer status, String username) {
		Order result = findByOid(oid);
		if(result == null){
			throw new OrderNotFoundException("修改订单状态失败!尝试访问的数据不存在!");
		}
		updateStatus(oid, status, username, new Date());
	}

	@Override
	@Transactional
	public void close(Integer oid, List<OrderItem> orderItems, String username) {
		// 检查订单状态是否是“未支付”
	    Order result = findByOid(oid);
	    // 检查查询结果是否不存在
	    if (result == null) {
	        throw new OrderNotFoundException(
	            "关闭订单失败！尝试访问的数据不存在！");
	    }

	    // 检查订单当前状态
	    if (result.getStatus() != Status.UNPAID) {
	        return;
	    }

	    // 将订单状态修改为已关闭
	    changeStatus(oid, Status.CLOSED, username);

	    // 归还订单中所有商品的库存
	    for (OrderItem orderItem : orderItems) {
	        productService.addNum(orderItem.getPid(), orderItem.getNum());
	    }
	    System.err.println("OrderService：订单已关闭！");
	}
	

}
