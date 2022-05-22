package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.Test;

import model.Board;

public class BoardDaoTest {
	
	BoardDao dao = BoardDao.getInstance();
	
	@Test
	public void deleteTest() {
		
		List<Board> list = dao.selectList(1, 10);
		
		for(Board b : list) {
			System.out.println(b.toString());
		}
		
	}
	
}
