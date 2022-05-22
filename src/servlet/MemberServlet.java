package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member;
import service.MemberService;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService service;

	public MemberServlet() {
		service = new MemberService();
		// ���񽺸� ���� db�� ���������� ����
		// ���񽺴� ����� ������ �и�
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		System.out.println("URI : " + request.getRequestURI());
		System.out.println("Context : " + request.getContextPath());

		// ��û�� �����ϴ� ���
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath() + "/member";

		if (uri.equals(contextPath + "/join")) {
			// ȸ������ ��û�̴�.
			Member member = new Member();
			// �����ͺ��̽��� ������ Member �����
			// ��û �Ķ���Ϳ��� �Է¿� �ʿ��� ���� ��������
			member.setId(request.getParameter("userid"));
			member.setPw(request.getParameter("userpw"));
			member.setName(request.getParameter("name"));
			member.setEmail(request.getParameter("email"));

			boolean result = service.join(member);
			// ȸ������ �����ϸ� loginForm ��û
			// ȸ������ �����ϸ� joinForm ��û
			if (result) {
				response.sendRedirect("loginForm");
			} else {
				response.sendRedirect("joinForm");
			}

		} else if (uri.equals(contextPath + "/loginForm")) {
			// /loginForm
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/joinForm")) {
			// /joinForm
			request.getRequestDispatcher("/joinForm.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/login")) {
			// ���� Ŭ������ login �޼ҵ带 �̿��ؼ� ó��
			String id = request.getParameter("userid");
			String pw = request.getParameter("userpw");
			
			// �α��� ���
			boolean result = service.login(id, pw);
			
			String url = null; // �α��� ����� ���� ������ ������ �ּ�
			String msg = null; // �α��� ����� ���� ����ڿ��� ������ �޽���
			if(result) {
				// �α��� ����
				// ����ȭ�� �����ֱ� : main ��û������
				msg = "�α��� ����!";
				url = "main";
				
				// ���� �����ͼ� session �� userid �Ӽ�
				request.getSession().setAttribute("userid", id);
				
				Member m = service.getMember(id);
				request.getSession().setAttribute("username", m.getName());
				
			} else {
				// �α��� ����
				// �α���ȭ�� �����ֱ� : loginForm ��û������
				msg = "�α��� ����";
				url = "loginForm";
			}
			// ���� �������� �ٷ� �������ʰ� ������������ ���� �����ֱ�
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/main")) {
			// main ��û ó��
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			
		} else if (uri.equals(contextPath + "/memberList")) {
			
			// memberList ��û ó��
			List<Member> memberList = service.getAllMembers();
			// ������ ��� ����� memberList.jsp�� ����
			request.setAttribute("memberList", memberList );
			request.getRequestDispatcher("/memberList.jsp").forward(request, response);
			
		} else if (uri.equals(contextPath + "/modifyForm")) {
			// modifyForm ��û ó��
			// ȸ������ ���� �������� �����ش�.
			// ������ ȸ�� �����Ͱ� ���޵Ǿ�� �Ѵ�.
			
			// �α����Ҷ� ���ǿ� �����ߴ� ����� ������ �����´�.
			String userid = (String)request.getSession().getAttribute("userid");
			// ���ǿ� ����Ǿ��ִ� ����� id�� ���ؼ�
			// service�� getMember() �޼ҵ带 ���
			Member member = service.getMember(userid);
			
			// ������ ������ request�� ����ְ�
			request.setAttribute("member", member);
			// �� request�� ������ ���·� �������� �̵�
			request.getRequestDispatcher("/modifyForm.jsp").forward(request, response);
		} else if (uri.equals(contextPath + "/logout")) {
			// �α׾ƿ� ��û ó��
			// session���� ����� ���� �����ϰ� loginForm���� ���ư���
			// ������ attribute (�Ӽ� ����)
			request.getSession().removeAttribute("userid");
			response.sendRedirect("loginForm");
		} else if (uri.equals(contextPath + "/modify")) {
			// ��û�� ����ִ� �Ķ���͸� �����ͼ� �ش� ������ ������
			// �����ͺ��̽��� ����� ������ ������Ʈ
			String userid = request.getParameter("userid");
			String userpw = request.getParameter("userpw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			Member member = new Member();
			member.setId(userid);
			member.setPw(userpw);
			member.setName(name);
			member.setEmail(email);
			
			boolean result = service.modify(member);
			// result �ȿ��� ������Ʈ�� ���
			// ������ true, ���н� false
			
			String msg = "";
			String url = "main"; // �����ϴ� �����ϴ� �Ȱ��� main���� ������
			if(result) {
				// ����
				msg = "���� �����Ͽ����ϴ�.";
			} else {
				// ����
				msg = "���� �����Ͽ����ϴ�.";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			// result.jsp �� ������ �̵�
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		}
	}

}
