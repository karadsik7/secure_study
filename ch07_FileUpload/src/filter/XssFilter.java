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

import com.inc.util.XssRequestWrapper;

@WebFilter("/*")
public class XssFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resonse, FilterChain chain)
			throws IOException, ServletException {
		request = new XssRequestWrapper((HttpServletRequest)request);
		chain.doFilter(request, resonse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
