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
@WebServlet("/comment/list")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentDao commentDao = new CommentDao();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CommentVo> list = commentDao.selectList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/comment/list.jsp").forward(request, response);
	}
}
