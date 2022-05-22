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
		// �� ���ʹ� login, loginForm, join, joinForm�� ������
		// ������ ��� ��û�����ؼ� Ȯ���ؼ� �α��� ���°� �ƴϸ� �α��� ȭ������ ������.
		System.out.println("�α��� ���Ͱ� �����մϴ�.");
		
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		
		HttpSession session = hreq.getSession();
		
		// ���� ���ǿ� �츮�� "userid" ��� �Ӽ��� �����صа� �ִٸ�
		// �α����� �� ������
		// ���ٸ� (null�̸�) �α����� �ȵ� ����
		
		if(session.getAttribute("userid") != null) {
			// �״������� �״�� �������� �Ӵϴ�.
			chain.doFilter(request, response);
		} else {
			// �α����� �ȵ� ����
			// �α��� ȭ������ ����������
			// ��� result.jsp�� Ȱ���ؼ� �α����� �϶�� �˷��ݽô�.
			hreq.setAttribute("msg", "�α����� �ʿ��� ����Դϴ�.");
			String loginPath = hreq.getRequestURI() + "loginForm";
			// �α��� ȭ������ ��� ����
			
			loginPath = loginPath.replace("board", "member");
			// /board/ �������� ��û ������� �α��� ȭ���� ó���ϴ� member ��������
			// ������ ��� ����
			
			hreq.setAttribute("url", loginPath);
			hreq.getRequestDispatcher("/result.jsp").forward(request, response);
			
		}
		
	}
}
