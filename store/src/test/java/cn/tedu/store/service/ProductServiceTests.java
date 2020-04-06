package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Product;
import cn.tedu.store.service.ex.ServiceException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {
	@Autowired
	private IProductService productService;
	@Test
	public void getHotList(){
		List<Product> list = productService.getHotList();
		for (Product product : list) {
			System.err.println(product);
		}
		System.err.println("OK");
	}
	
	@Test
	public void getById(){
		Product product = productService.getById(10000022);
		System.err.println(product);
	}
	@Test
	public void reduceNum() {
	    try {
	        Integer pid = 10000022;
	        Integer amount = 80;
	        productService.reduceNum(pid, amount);
	        System.err.println("OK");
	    } catch (ServiceException e) {
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
	
	@Test
	public void addNum() {
	    try {
	        Integer pid = 10000022;
	        Integer amount = 80;
	        productService.addNum(pid, amount);
	        System.err.println("OK");
	    } catch (ServiceException e) {
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
}
