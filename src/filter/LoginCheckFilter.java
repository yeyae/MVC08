package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 이 필터는 login, loginForm, join, joinForm을 제외한
		// 나머지 모든 요청에대해서 확인해서 로그인 상태가 아니면 로그인 화면으로 보낸다.
		System.out.println("로그인 필터가 동작합니다.");
		
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		
		HttpSession session = hreq.getSession();
		
		// 만약 세션에 우리가 "userid" 라고 속성을 저장해둔게 있다면
		// 로그인이 된 상태임
		// 없다면 (null이면) 로그인이 안된 상태
		
		if(session.getAttribute("userid") != null) {
			// 그다음으로 그대로 지나가게 둡니다.
			chain.doFilter(request, response);
		} else {
			// 로그인이 안된 상태
			// 로그인 화면으로 보내버리기
			// 대신 result.jsp를 활용해서 로그인을 하라고 알려줍시다.
			hreq.setAttribute("msg", "로그인이 필요한 기능입니다.");
			String loginPath = hreq.getRequestURI() + "loginForm";
			// 로그인 화면으로 경로 지정
			
			loginPath = loginPath.replace("board", "member");
			// /board/ 서블릿으로 요청 보낸경우 로그인 화면을 처리하는 member 서블릿으로
			// 가도록 경로 변경
			
			hreq.setAttribute("url", loginPath);
			hreq.getRequestDispatcher("/result.jsp").forward(request, response);
			
		}
		
	}
}
