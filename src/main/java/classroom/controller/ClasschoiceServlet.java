package classroom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import classroom.model.classroomBean;
import classroom.service.classroomService;
//給選擇班級轉頁面用

@WebServlet("/classroom/choiceclassroom.do")
public class ClasschoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		
		String Cr_Id = request.getParameter("Cr_Id");
		Integer cr_Id = Integer.parseInt(Cr_Id.trim());
		classroomBean cb = null;
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		classroomService cs = ctx.getBean(classroomService.class);
			cb = cs.loadoneclassroom(cr_Id);
			
			session.setAttribute("singleclass", cb);
			RequestDispatcher rd = request.getRequestDispatcher("/classroom/classUpdate.jsp");
			rd.forward(request, response);
			return;
		
		
		
		
		
	}

}
