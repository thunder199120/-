package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
	@Autowired
	private UserMapper userMapper;
	@Test
	public void addnew(){
		User user = new User();
		user.setUsername("Tom");
		user.setPassword("258541");
		Integer rows = userMapper.addnew(user);
		System.err.println("rows="+rows);		
	}
	
	@Test
	public void updatePassword(){
		Date date = new Date();
		Integer rows = userMapper.updatePassword(8, "1234", "管理员", date);
		System.err.println(rows);
	}
	
	@Test
	public void updateAvatar(){
		Date date = new Date();
		Integer rows = userMapper.updateAvatar(9, "user/l/1", "超级", date);
		System.err.println(rows);
	}
	@Test
	public void updateInfo(){
		User user = new User();
		user.setUid(8);
		user.setPhone("15625474512");
		user.setEmail("dskjf@147");
		user.setGender(1);
		user.setModifiedUser("超级管理员");
		user.setModifiedTime(new Date());
		Integer rows = userMapper.updateInfo(user);
		System.err.println(rows);
	}
	@Test
	public void findByUsername(){
		User result = userMapper.findByUsername("Spring");
		System.err.println(result);
	}
	@Test
	public void findByUid(){
		User result = userMapper.findByUid(8);
		System.err.println(result);
	}
}
