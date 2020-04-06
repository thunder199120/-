package cn.tedu.store.service;



import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;

public interface ICartService {
	void addToCart(Integer uid,Integer pid,String username,Integer num)
				throws InsertException ,UpdateException;
	
	List<CartVO> getByUid(Integer uid);
	
	void addNum(Integer cid,Integer uid,String username)
				throws CartNotFoundException,AccessDeniedException,UpdateException;
	
	void reduceNum(Integer cid,Integer uid,String username)
			throws CartNotFoundException,AccessDeniedException,UpdateException;

	void delete(Integer cid,Integer uid)throws CartNotFoundException,DeleteException,AccessDeniedException;

	List<CartVO> getByCids(Integer[] cids, Integer uid);
	
	void delete(Integer[] cids,Integer uid);
}
