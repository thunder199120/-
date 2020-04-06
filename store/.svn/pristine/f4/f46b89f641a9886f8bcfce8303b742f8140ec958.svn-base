package cn.tedu.store.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;
@Service
public class CartServiceImpl implements ICartService {
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private IProductService productService;
	
	private void insert(Cart cart) {
		Integer rows = cartMapper.insert(cart);
		if(rows != 1){
			throw new InsertException("将商品添加到购物车失败!插入数据发生未知异常!");
		}
	}
	private void updateNum(Integer cid,Integer num,String username,Date modifiedTime){
		Integer rows = cartMapper.updateNum(cid, num, username, modifiedTime);
			if(rows != 1){
				throw new UpdateException("修改商品数量失败!修改数据发生未知异常!");
			}
	}
	private Cart findByUidAndPid(Integer uid,Integer pid){
		return cartMapper.findByUidAndPid(uid, pid);
	}
	
	
	private List<CartVO> findByUid(Integer uid){
		return cartMapper.findByUid(uid);
	}
	private Cart findByCid(Integer cid){
		return  cartMapper.findByCid(cid);
	}
	
	private void deleteByCid(Integer cid){
		Integer rows = cartMapper.deleteByCid(cid);
		if(rows!=1){
			throw new DeleteException("删除购物车失败!删除数据发生未知异常!");
		}
	}
	private List<CartVO> findByCids(Integer[] cids) {
	    return cartMapper.findByCids(cids);
	}
	
	private void deleteByCids(Integer[] cids){
		Integer  rows = cartMapper.deleteByCids(cids);
		if(rows < 1){
			throw new DeleteException("删除购物车失败!删除数据发生未知异常!");
		}
	}
	@Override
	public void addToCart(Integer uid, Integer pid, String username, Integer num)
			throws InsertException ,UpdateException{
		//依据用户id和商品id查询数据
		Cart result = findByUidAndPid(uid,pid);
		//判断查询结果是否为null
		if(result == null){
		//是,需要新增购物车数据
		//  自行创建Cart对象
			Cart cart = new Cart();
	    //  调用productService的getById()方法获取单价并封装到Cart对象
			Product product = productService.getById(pid);
			Long price = product.getPrice();
	    //  将uid、pid、num参数封装到Cart对象
			cart.setUid(uid);
			cart.setPid(pid);
			cart.setNum(num);
			cart.setPrice(price);
	    //  创建当前时间对象，将时间和username封装到Cart对象的日志属性
			cart.setCreatedUser(username);
			cart.setCreatedTime(new Date());
			cart.setModifiedUser(username);
			cart.setModifiedTime(new Date());
	    //  执行插入
			insert(cart);
		}else{
	    // 否：需要修改欲购物的商品的数量
	    //  从查询结果中获取当前数量num和数据的cid
			Integer oldNum = result.getNum();
			Integer cid = result.getCid();
	    //  将以上查询结果中的当前数量num和参数增量num相加，得到新的数量
			Integer newNum = oldNum + num; 
	    //  执行更新数量
			updateNum(cid, newNum, username, new Date());	
		}

	}
	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	}
	@Override
	public void addNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		Integer num = numCheck(cid,uid);
		//将原始数量递增得到新的数量
		num = num + 1;
		//执行修改操作
		updateNum(cid, num, username, new Date());
	}
	@Override
	public void reduceNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		Integer num = numCheck(cid,uid);
		//将原始数量递减得到新的数量
		Integer newNum;
		if(num == 1){
			return;
		}
		
		//执行修改操作
		updateNum(cid, num-1, username, new Date());
		
	}
	private Integer numCheck(Integer cid, Integer uid)
			throws CartNotFoundException, AccessDeniedException, UpdateException{
		//依据cid查询数据
		Cart result = findByCid(cid);
		//判断查询结果是否为null
		if(result == null){
		//是,抛出CartNotFoundException
			throw new CartNotFoundException("增加失败!尝试访问的购物车数据不存在!");
		}
		//依据查询结果中的uid与参数uid对比是否不一致
		if(!result.getUid().equals(uid)){
			//是,抛出AccessDeniedException
			throw new AccessDeniedException("增加失败!不允许操作他人数据!");
		}
		//根据查询结果获得原始数量
		Integer num = result.getNum();
		return num;
	}
	@Override
	public void delete(Integer cid,Integer uid)
			throws CartNotFoundException, DeleteException, AccessDeniedException {
		//依据cid查询数据
		Cart result = findByCid(cid);
		//判断查询结果是否为null
		if(result == null){
		//是,抛出CartNotFoundException
			throw new CartNotFoundException("删除失败!尝试访问的购物车数据不存在!");
		}
		//依据查询结果中的uid与参数uid对比是否不一致
		if(!result.getUid().equals(uid)){
			//是,抛出AccessDeniedException
			throw new AccessDeniedException("删除失败!不允许操作他人数据!");
		}
		//执行删除
		deleteByCid(cid);
	}
	@Override
	public List<CartVO> getByCids(Integer[] cids, Integer uid) {
		if (cids == null) {
	        return new ArrayList<>();
	    }

	    List<CartVO> result = findByCids(cids);

	    Iterator<CartVO> it = result.iterator();
	    while(it.hasNext()) {
	        CartVO cartVO = it.next();
	        if (cartVO.getUid() != uid) {
	            it.remove();
	        }
	    }
		return result;
	}
	@Override
	public void delete(Integer[] cids,Integer uid) {
		// 判断即将删除的数据是否存在，及数据归属是否正确
	    // 可以调用自身的：List<CartVO> getByCids(Integer[] cids, Integer uid)，得到cid有效，且归属正确的购物车数据
		List<CartVO> carts = getByCids(cids,uid);
		//判断以上查询结果的长度是否有效
		if(carts.size() == 0){
			throw new CartNotFoundException("删除购物车数据失败!尝试访问的数据不存在!");
		}
	    // 基于以上得到的List<CartVO>得到允许执行删除的cid的数组
		Integer[] ids = new Integer[carts.size()];
		for (int i = 0; i < carts.size(); i++) {
			ids[i] = carts.get(i).getCid();
		}

	    // 执行删除
	    deleteByCids(ids);
	}

}
