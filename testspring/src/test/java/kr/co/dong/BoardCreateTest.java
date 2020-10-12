package kr.co.dong;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.dong.board.BoardDAO;
import kr.co.dong.board.BoardDTO;

// 현재 테스트 코드를 실행할 때 스프링이 로딩되도록 하는 부분.
@RunWith(SpringJUnit4ClassRunner.class)
//속성 경로에 xml파일을 이용해 스프링이 로딩된다.
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class BoardCreateTest {
	//위에서 스프링이 정상적으로 동작할 경우 스피링이 생성해서 주입해준다.
	@Inject
	private BoardDAO boardDAO;
	
	@Test
	public void testInsert() throws Exception{
		BoardDTO boardDTO;
		
		
		for(int i=1; i<1000; i++) {
			boardDTO = new BoardDTO();
			boardDTO.setTitle("Test Tiele [" + i+ "]");
			boardDTO.setContent("Test content" + i);
			boardDTO.setId("root");
			
			boardDAO.register(boardDTO);
			
			Thread.sleep(1000); //1초간 멈춤
		}
	}
}




