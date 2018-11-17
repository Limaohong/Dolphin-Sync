package classroom.controller;

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

import bulletinboard.model.DemoboardBean;
import bulletinboard.service.bulletinboardService;
import classroom.model.classroomBean;
import classroom.service.classroomService;
import company.model.companyBean;
import studentclass.service.StudentClassService;
import useraccount.model.userAccountBean;
//給選擇班級轉頁面用

@WebServlet("/classroom/choiceclassroomdelete.do")
public class ClasschoicedeleteServlet extends HttpServlet {
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
		Integer n1 = 0;
		Integer n2 = 0;
		Integer n3 = 0;
		userAccountBean ub = null;
		classroomBean cbbean = null;
		companyBean combean = null;
		List<classroomBean> cb = null;
		List<DemoboardBean> Demoboard = null;
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		classroomService cs = ctx.getBean(classroomService.class);
		StudentClassService scs = ctx.getBean(StudentClassService.class);
		bulletinboardService bs = ctx.getBean(bulletinboardService.class);
		ub = (userAccountBean) session.getAttribute("LoginOK");
		combean = (companyBean) session.getAttribute("LoginCom");
		cbbean = cs.loadoneclassroom(cr_Id);
			n1 = scs.deleteStudentClass(cr_Id);
			n3 = bs.deleteBoard(cbbean);
			n2 = cs.classDelete(cr_Id);
			if(n1==1 && n2==1 && n3==1) {
				cb = cs.loadclassroom_teacher(ub);
				Demoboard = bs.loadbulletinboard_teacher(combean);
				session.setAttribute("loadclassroom", cb);
				session.setAttribute("Demoboard", Demoboard);
				response.sendRedirect("../index.jsp");;
				return;
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("choiceclassroomdelete.jsp");
				rd.forward(request, response);
			}
			
		
		
		
		
		
	}

}
