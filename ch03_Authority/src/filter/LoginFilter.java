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

@WebFilter(value = {"/photo/*","/sayHello/*", "/comment/*", "/manage/*"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		UserVo vo = (UserVo)(request.getSession().getAttribute("vo"));
		if(vo == null) {
			request.setAttribute("msg", "로그인 후 이용하세요");
			request.setAttribute("url", "/main");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			return;
		}
		
		if(request.getRequestURI().indexOf("/manage") >= 0 && !vo.getId().equals("admin")) {
			request.setAttribute("msg", "잘못된 페이지입니다.");
			request.setAttribute("url", "/main");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			return;
		}
		
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}
