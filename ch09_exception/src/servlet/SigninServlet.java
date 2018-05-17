package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
@WebServlet("/user/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		//입력값 새니타이징
		id.replaceAll("--", "");
		String password = request.getParameter("password");
		password.replaceAll("--", "");
		password = SHA256Encryptor.shaEncrypt(password);
		System.out.println(password);
		UserDao userDao = new UserDao();
		int login_count = userDao.getLoginCount(id);
		
		if(login_count == 3) {
			if(userDao.isLockRealeased(id)) {
				userDao.setLoginFailCount(id, 2);
			}else {
				request.setAttribute("msg", "3회 연속으로 로그인에 실패하였습니다. 5분 후에 다시 시도하세요.");
				request.setAttribute("url", "/main");
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
				return;
			}
		}
		
		UserVo vo = userDao.selectOne(id, password);
		
		if(vo == null) {
			userDao.setLoginFailTime(id);
			userDao.plusLoginCount(id);
			login_count = userDao.getLoginCount(id);
			if(login_count < 3) {
				request.setAttribute("msg", "아이디 또는 비밀번호가 틀립니다."+" " + login_count + "회 실패");
			}else {
				request.setAttribute("msg", "3회 이상 로그인에 실패하여 계정 로그인을 5분간 중지합니다.");
			}
			request.setAttribute("url", "/main");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
		}else {
			//로그인 실패 횟수를 0으로 변경
			//경우의 수 : 로그인 성공했을 때, 자연적으로 5분이 지났을 때
			
			userDao.setLoginFailCount(id, 0);
			//세션 재발급
			request.getSession().invalidate();
			request.getSession().setAttribute("vo", vo);
			response.sendRedirect("/main");
		}
		
	}
}
