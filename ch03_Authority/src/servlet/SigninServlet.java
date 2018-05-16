package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import vo.UserVo;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/user/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		//입력값 새니타이징
		id.replaceAll("--", "");
		String password = request.getParameter("password");
		password.replaceAll("--", "");
		UserDao userDao = new UserDao();
		UserVo vo = userDao.selectOne(id, password);
		
		if(vo == null) {
			request.setAttribute("msg", "아이디 또는 비밀번호가 틀립니다.");
			request.setAttribute("url", "/main");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("vo", vo);
			response.sendRedirect("/main");
		}
		
	}
}
