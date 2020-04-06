package cn.tedu.store;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sound.midi.SysexMessage;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {
	@Autowired
	private DataSource dataSource;
	@Test
	public void contextLoads() {
	}
	@Test
	public void getConnection() throws SQLException{
		Connection conn = dataSource.getConnection();
		System.err.println("conn="+conn);
	}
	@Test
	public void messageDigest(){
		String str = "好嗨呦";
		String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
		System.err.println(md5);
	}
}
