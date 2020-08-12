package kr.co.ca;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
	@Autowired //스프링컨테이너가 bean을 넣어줌 (null이 아니다)
	private DataSource dataFactory;
	
	@Test //무조건 public void
	public void testConnection() {
		Connection conn = null;
		try {
			conn = dataFactory.getConnection();
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
