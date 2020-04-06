package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {
	@Autowired
	AddressMapper addressMapper;
	@Test
	public void addnew() {
		Address address = new Address();
		address.setUid(9);
		address.setName("黄泉");
		address.setPhone("13845879632");
		address.setProvinceCode("231524");
		address.setProvinceName("广东省");
		address.setCityCode("213255");
		address.setCityName("东莞市");
		address.setAreaCode("251100");
		address.setAreaName("东城区");
		address.setZip("");
		address.setTel("027561234");
		address.setTag("");
		address.setIsDefault(1);
		address.setCreatedUser("");
		address.setCreatedTime(new Date());
		address.setModifiedUser("");
		address.setAddress("");
		address.setModifiedTime(new Date());
		Integer rows = addressMapper.addnew(address);
		System.err.println(rows);
	}
	@Test
	public void deleteByAid(){
		Integer rows = addressMapper.deleteByAid(14);
		System.err.println(rows);
	}
	@Test
	public void countByUid(){
		Integer count = addressMapper.countByUid(9);
		System.err.println(count);
	}
	
	@Test
	public void findByUid(){
		List<Address> list = addressMapper.findByUid(6);
		System.err.println("count="+list.size());
		for (Address address : list) {
			System.err.println(address);
		}
	}
	@Test
	public void updateNonDefault(){
		Integer rows = addressMapper.updateNonDefault(6);
		System.err.println("rows="+rows);
	}
	@Test
	public void updateDefault(){
		Integer aid = 16;
		String username = "超级管理员";
		Integer rows = addressMapper.updateDefault(aid, username, new Date());
		System.err.println("rows="+rows);
	}
	@Test
	public void findByAid(){
		Address result = addressMapper.findByAid(22);
		System.err.println(result);
	}
	@Test
	public void findLastModified(){
		Address result = addressMapper.findLastModified(6);
		System.err.println(result);
	}
}
