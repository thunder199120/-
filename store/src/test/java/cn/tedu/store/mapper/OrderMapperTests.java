package cn.tedu.store.mapper;



import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
	@Autowired
	private OrderMapper orderMapper;
	@Test
	public  void  insertOrder(){
		Order order = new Order();
		order.setUid(6);
		order.setRecvName("Tom");
		order.setRecvPhone("13625784125");
		order.setRecvProvince("广东省");
		order.setRecvCity("东莞市");
		order.setRecvArea("东城区");
		order.setRecvAddress("麻烦了快速的房间里");
		order.setTotalPrice(2880l);
		Integer rows = orderMapper.insertOrder(order);
		System.err.println(rows);
		
	}
	
	@Test
	public  void  findByCode(){
		OrderItem orderItem = new OrderItem();
		orderItem.setOid(6);
		orderItem.setPid(2);
		orderItem.setTitle("发了多少街坊邻居发了多少");
		orderItem.setPrice(2500L);
		orderItem.setImage("简单化股份控股和");
		Integer rows = orderMapper.insertOrderItem(orderItem);
		System.err.println(rows);
	}
	
	@Test
	public void updateStatus(){
		Integer oid = 2;
		Integer status = 3;
		String username = "管理员";
		Date modifiedTime = new Date();
		Integer rows = orderMapper.updateStatus(oid, status, username, modifiedTime);
		System.err.println(rows);
	}
	@Test
	public void findByOid(){
		Integer oid = 2;
		Order order = orderMapper.findByOid(oid);
		System.err.println(order);
	}
}
