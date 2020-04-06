package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
	@Autowired
	private IAddressService addressService;
	@Test
	public void  create(){
		try{
			Address address = new Address();
			Integer uid =9;
			String username ="管理员"; 
			address.setPhone("13524781425");
			address.setName("幻光");
			addressService.create(uid, username, address);
			System.err.println("OK");
			} catch (Exception e){
				System.err.println(e.getClass().getName());
				System.err.println(e.getMessage());
			}
		
	}
	@Test
	public void getByUid(){
		List<Address> list = addressService.getByUid(6);
		System.err.println("count="+list.size());
		for (Address address : list) {
			System.err.println(address);
		}
	}
	@Test
	public void setDefault(){
		try{
			Integer aid = 15;
			Integer uid = 6;
			String username ="传奇";
			addressService.setDefault(aid, uid, username);
			System.err.println("OK");
		} catch (Exception e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		}
	
	@Test
	public void delete(){
		try{
			Integer aid = 17;
			Integer uid = 6;
			String username ="传奇";
			addressService.delete(aid, uid, username);
			System.err.println("OK");
		} catch (Exception e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
}
