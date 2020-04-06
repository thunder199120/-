package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	@Autowired
	private ICartService cartService;
	@Test
	public void addToCart(){
		 try {
		        Integer uid = 100;
		        Integer pid = 10000017;
		        Integer num = 3;
		        String username = "土豪";
		        cartService.addToCart(uid, pid, username, num);
		        System.err.println("OK.");
		    } catch (ServiceException e) {
		        System.err.println(e.getClass().getName());
		        System.err.println(e.getMessage());
		    }
	}
	
	@Test
	public void getByUid(){
		List<CartVO> result = cartService.getByUid(6);
		for (CartVO cartVO : result) {
			System.err.println(cartVO);
		}
		System.err.println("OK");
	}
	
	@Test
	public void addNum(){
		try{
			cartService.addNum(6, 6, "传奇");
			System.err.println("OK");
		} catch (ServiceException e) {
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
	@Test
	public void aaa(){
		Integer[] cids = {10,12,15};
		cartService.delete(cids, 6);
		System.err.println("OK");
	}
	@Test
	public void deleteByCids() {
	    try {
	        Integer[] cids = {13,15,23,25,27,29,31};
	        Integer uid = 6;
	        cartService.delete(cids, uid);
	        System.err.println("OK.");
	    } catch (ServiceException e) {
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
}
