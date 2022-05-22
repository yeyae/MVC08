package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	// 인코딩 방식 (utf-8)
	private String encoding;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터 동작
		request.setCharacterEncoding(encoding);
		
		// 다음 단계로 넘어가기
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 인코딩 방식을 파라미터로 받아옴
		encoding = filterConfig.getInitParameter("encoding");
		if(encoding == null) {
			// 필터 설정파일에 encoding 정보가 없는 경우
			// UTF-8 방식으로 기본 설정
			encoding = "UTF-8";
		}
	}
	
}
