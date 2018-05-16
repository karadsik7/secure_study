package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoListServlet
 */
@WebServlet("/photo/list")
public class PhotoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PhotoDao photoDao = new PhotoDao();
		List<PhotoVo> list = photoDao.selectList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/photo/list.jsp").forward(request, response);
	}
}
