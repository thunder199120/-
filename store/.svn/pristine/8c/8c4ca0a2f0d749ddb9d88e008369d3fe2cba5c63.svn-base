package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests {
	@Autowired
	private DistrictMapper districtMapper;
	@Test
	public  void  findByParent(){
		String parent = "420000";
		List<District> district = districtMapper.findByParent(parent);
		for (District dt : district) {
			System.err.println(dt);
		}
		
	}
	
	@Test
	public  void  findByCode(){
		String code = "420000";
		District data = districtMapper.findByCode(code);
		System.err.println(data);
		
		
	}
}
