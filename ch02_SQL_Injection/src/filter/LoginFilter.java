package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.UserVo;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(value={"/photo/*","/bbs/*","/manage/*", "/comment/*"})
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest h_request = (HttpServletRequest)request;
		HttpServletResponse h_response = (HttpServletResponse)response;
		UserVo vo = (UserVo)h_request.getSession().getAttribute("vo");
		if(vo == null) {
			h_request.setAttribute("msg", "로그인 후 이용가능한 페이지 입니다.");
			h_request.setAttribute("url", "/main");
			h_request.getRequestDispatcher("/views/error.jsp").forward(h_request, h_response);
			return;
		}
		if(h_request.getRequestURI().indexOf("/manage")>=0 && !vo.getId().equals("admin")) {
			h_request.setAttribute("msg", "관리자만 접근가능한 페이지 입니다.");
			h_request.setAttribute("url", "/main");
			h_request.getRequestDispatcher("/views/error.jsp").forward(h_request, h_response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
