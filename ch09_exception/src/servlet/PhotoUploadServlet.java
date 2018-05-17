package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.PhotoVo;
@WebServlet("/photo/upload")
public class PhotoUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PhotoDao photoDao = new PhotoDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/upload/");
		System.out.println(realPath);
		MultipartRequest mr = new MultipartRequest(request, realPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());
		String description = mr.getParameter("description");
		if(description.equals("")) {
			description = "사진";
		}
		File file = mr.getFile("photo");
		String filename = "nofile";		
		if(file != null) {
			filename = file.getName();
			if(!filename.endsWith(".jpg") && !filename.endsWith(".gif") && !filename.endsWith(".png")) {
				file.delete();
				request.setAttribute("msg", "개수작부리지 ㄴㄴ");
				request.setAttribute("url", "/photo/list");
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
				return;
			}
			
		}
		
		PhotoVo vo = new PhotoVo();
		vo.setDescription(description);
		vo.setUrl(request.getContextPath()+"/upload/"+filename);
		photoDao.insert(vo);
		response.sendRedirect("/photo/list");
	}

}
