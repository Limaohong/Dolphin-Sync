package student.controller;

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

import bulletinboard.model.DemoboardBean;
import bulletinboard.model.bulletinboardBean;
import bulletinboard.service.bulletinboardService;
//給選擇班級轉頁面用

@WebServlet("/student/choiceinsertstudentclass.do")
public class StudentClassChoiceServlet extends HttpServlet {
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
		
		
		String Cr_Id = request.getParameter("cr_Id");
		String radiosValue = request.getParameter("radiosValue");

		session.setAttribute("Cr_Id", Cr_Id);
		if(radiosValue.equals("single")) {
			RequestDispatcher rd = request.getRequestDispatcher("/student/studentinsert.jsp");
			rd.forward(request, response);
		}else if(radiosValue.equals("plural")) {
			RequestDispatcher rd = request.getRequestDispatcher("/student/studentinsertplural.jsp");
			rd.forward(request, response);
		}
			
			
		
		
		
		
	}

}
