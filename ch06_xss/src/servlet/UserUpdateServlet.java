package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import vo.UserVo;

@WebServlet("/user/update")
public class UserUpdateServlet extends HttpServlet{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVo uvo = (UserVo)request.getSession().getAttribute("vo");
		
		String id = uvo.getId();
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		if("".equals(password)) {
			password = uvo.getPassword();
		}
		
		UserVo newUvo = new UserVo();
		newUvo.setId(id);
		newUvo.setPassword(password);
		newUvo.setName(name);
		
		UserDao userDao = new UserDao();
		userDao.update(newUvo);
		request.getSession().setAttribute("vo", newUvo);
		response.sendRedirect(request.getContextPath() + "/user/info");
	}
}
