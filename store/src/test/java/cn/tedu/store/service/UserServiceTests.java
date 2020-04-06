package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	@Autowired
	IUserService userService;
	@Test
	public void reg(){
		try{
		User user = new User();
		user.setUsername("spring");
		user.setPassword("789124");
		userService.reg(user);
		System.err.println("OK");
		} catch (Exception e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void login(){
		try {
			User user = userService.login("spring", "789124");
			System.err.println(user);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void changePassword(){
		try {
			Integer uid = 9;
			String oldPassword = "1234";
			String newPassword = "789124";
			String modifiedUser = "管理员";
			userService.changePassword(uid, oldPassword, newPassword, modifiedUser);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeAvatar(){
		try {
			Integer uid = 9;
			String avatar = "g/hui/2";
			String modifiedUser = "管理员";
			userService.changeAvatar(uid, avatar, modifiedUser);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeInfo(){
		try {
			Integer uid = 9;
			String username = "spring";
			User  user = new User();
			user.setPhone("13625457814");
			user.setEmail("root@147");
			user.setGender(1);
			userService.changeInfo(uid, username, user);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void getByUid(){
		try {
			Integer uid = 9;
			User user = userService.getByUid(uid);
			System.err.println(user);
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
}
