package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.UserDao;
import vo.CommentVo;
import vo.UserVo;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/comment/write")
public class CommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentDao commentDao = new CommentDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_id = request.getParameter("u_id");
		String content = request.getParameter("content");
		
		CommentVo vo = new CommentVo();
		vo.setU_id(u_id);
		vo.setContent(content);
		commentDao.insert(vo);
		response.sendRedirect("/comment/list");
	}
}
