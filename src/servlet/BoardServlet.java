package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Board;
import service.BoardService;

public class BoardServlet extends HttpServlet {
	
	BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req, resp);
	}
	
	protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath() + "/board";
		// String contextPath = req.getContextPath() + "/member";
		
		System.out.println("uri : " + uri);
		System.out.println("contextPath : " + contextPath);
		
		if(uri.equals(contextPath + "/write")) {
			
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String name = (String) req.getSession().getAttribute("username");
			
			Board b = new Board();
			b.setTitle(title);
			b.setContent(content);
			b.setName(name);
			
			boolean result = service.writeBoard(b);
			if(result) {
				req.setAttribute("msg", "정상 등록되었습니다.");
			} else {
				req.setAttribute("msg", "등록 실패하였습니다.");
			}
			req.setAttribute("url", "boardList");
			req.getRequestDispatcher("/result.jsp").forward(req, resp);
			
		} else if (uri.equals(contextPath + "/boardList")) {
			int pageNumber = 1;
			String strPageNumber = req.getParameter("page");
			
			if(strPageNumber != null) {
				pageNumber = Integer.parseInt(strPageNumber);
			}
			
			Map<String, Object> viewData = service.getBoardList(pageNumber);
			
			req.setAttribute("viewData", viewData);
			req.getRequestDispatcher("/boardList.jsp").forward(req, resp);
		} else if (uri.equals(contextPath+ "/boardDelete")) {
			int boardId = Integer.parseInt(req.getParameter("id"));
			String userName = (String) req.getSession().getAttribute("username");
			
			boolean result = service.deleteBoard(boardId, userName);
			String msg = "";
			if(result) {
				msg = "삭제 성공하였습니다.";
			} else {
				msg = "삭제할수 없습니다.";
			}
			req.setAttribute("msg", msg);
			req.setAttribute("url", "boardList");
			
			req.getRequestDispatcher("/result.jsp").forward(req, resp);
			
		}
	
	}
}
