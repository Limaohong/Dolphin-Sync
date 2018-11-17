package teacherpresentation.controller;

import java.io.IOException;
import java.util.List;

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

import company.model.companyBean;
import teacherpresentation.model.DemoTeacher;
import teacherpresentation.model.teacherpresentationBean;
import teacherpresentation.service.teacherpresentationService;
//給選擇班級轉頁面用

@WebServlet("/teacher/choiceteacherdelete.do")
public class TeacherchoicedeleteServlet extends HttpServlet {
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
		
		List<DemoTeacher> DemoTeacher = null;
		companyBean cb = null;
		Integer n = 0;
		String TP_Id = request.getParameter("TP_Id");
		Integer Tp_Id = Integer.parseInt(TP_Id.trim());
		cb = (companyBean) session.getAttribute("LoginCom");
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		teacherpresentationService ts = ctx.getBean(teacherpresentationService.class);
		n = ts.deleteteacherpresentation(Tp_Id);
		if(n==1) {
			DemoTeacher = ts.loadteacher_teacher(cb);
			session.setAttribute("DemoTeacher", DemoTeacher);
			response.sendRedirect("../index.jsp");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("choiceteacherdelete.jsp"); 
			rd.forward(request, response);
			return;
		}
		
			
		
		
		
	}

}
