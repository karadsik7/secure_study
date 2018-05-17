package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error/500")
public class Error500Servlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("msg", "서버 오류입니다. 관리자에게 문의하세요.");
		request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		
	}

	
	
}
