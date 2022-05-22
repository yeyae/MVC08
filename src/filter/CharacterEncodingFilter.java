package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	// ���ڵ� ��� (utf-8)
	private String encoding;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ���� ����
		request.setCharacterEncoding(encoding);
		
		// ���� �ܰ�� �Ѿ��
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// ���ڵ� ����� �Ķ���ͷ� �޾ƿ�
		encoding = filterConfig.getInitParameter("encoding");
		if(encoding == null) {
			// ���� �������Ͽ� encoding ������ ���� ���
			// UTF-8 ������� �⺻ ����
			encoding = "UTF-8";
		}
	}
	
}
