package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/error/404")
public class Error404Servlet extends HttpServlet{
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("page not found");
		logger.error("존재하지 않는 페이지 요청");
		
		request.setAttribute("msg", "존재하지 않는 페이지입니다");
		request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		
	}
}
