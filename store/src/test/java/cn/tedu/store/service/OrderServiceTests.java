package cn.tedu.store.service;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
	@Autowired
	private IOrderService orderService;
	@Test
	public void create(){
		Integer aid = 28;
		Integer[] cids = {18,19,20,21};
		Integer uid = 6;
		String username = "管理员";
		Order order = orderService.create(aid, cids, uid, username);
		System.err.println(order);
		   
	}
	@Test
	public void changeStatus(){
		try {
			Integer oid = 9;
			Integer status = 2;
			String username = "管理员";
			orderService.changeStatus(oid, status, username);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
}
