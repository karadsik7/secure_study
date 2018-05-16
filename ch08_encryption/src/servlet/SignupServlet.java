package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import encrypt.SHA256Encryptor;
import vo.UserVo;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/user/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/signup.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		//유효성 검사
		/*if(!Verifier.idVerify(id) || !Verifier.passwordVerify(password) ||
				!Verifier.nameVerify(name)) {
			request.setAttribute("msg", "올바르지 않은 요청입니다.");
			request.setAttribute("url", "/main");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			return;
		}*/
		
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setName(name);
		password = SHA256Encryptor.shaEncrypt(password);
		vo.setPassword(password);
		System.out.println(vo.getPassword());
		
		UserDao userDao = new UserDao();
		userDao.insert(vo);
		response.sendRedirect("/main");
	}
}
