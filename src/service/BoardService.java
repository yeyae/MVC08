package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDao;
import model.Board;

public class BoardService {
	
	private BoardDao dao;
	private static final int NUM_OF_BOARD_PER_PAGE = 10;
	private static final int NUM_OF_NAVI_PAGE = 10;
	
	public BoardService() {
		dao = BoardDao.getInstance();
	}
	
	public List<Board> getAllBoards() {
		return dao.selectAll();
	}
	
	public Board getBoard(int id) {
		return dao.selectOne(id);
	}
	
	public boolean writeBoard(Board board) {
		
		int result = dao.insertBoard(board);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean modifyBoard(Board board) {
		
		int result = dao.updateBoard(board);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteBoard(int id, String name) {
		int result = dao.deleteBoard(id, name);
		
		if(result > 0) {
			return true;
		} else {
			return false;
		}
 		
	}
	
	public Map<String, Object> getBoardList(int pageNumber) {
		Map<String, Object> viewData = new HashMap<String, Object>();
		
		int firstRow = 0;
		int endRow = 0;
		int totalCount = dao.selectCount();
		
		firstRow = (pageNumber-1)*NUM_OF_BOARD_PER_PAGE + 1;
		endRow = pageNumber*NUM_OF_BOARD_PER_PAGE;
		
		List<Board> boardList = dao.selectList(firstRow, endRow);
		
		viewData.put("currentPage", pageNumber);
		viewData.put("boardList", boardList);
		viewData.put("pageTotalCount", calPageTotalCount(totalCount));
		viewData.put("startPage", getStartPage(pageNumber) );
		viewData.put("endPage", getEndPage(pageNumber));
		
		return viewData;
	}
	
	private int calPageTotalCount(int totalCount) {
		int pageTotalCount = 0;
		if(totalCount != 0) {
			pageTotalCount = (int)Math.ceil(((double)totalCount / NUM_OF_BOARD_PER_PAGE));
		}
		return pageTotalCount;
	}
	
	private int getStartPage(int pageNum) {
		int startPage = ((pageNum-1)/NUM_OF_NAVI_PAGE)*NUM_OF_NAVI_PAGE + 1;
		return startPage;
	}
	
	private int getEndPage(int pageNum) {
		int endPage = (((pageNum-1)/NUM_OF_NAVI_PAGE)+1) * NUM_OF_NAVI_PAGE;
		return endPage;
	}
}
