package fraction.controller;

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

import classroom.model.classroomBean;
import classroom.service.classroomService;
import fraction.model.fractionDemo;
import fraction.service.FractionService;
import student.model.studentBean;
//給選擇班級轉頁面用
import student.service.StudentService;
import studentclass.model.studentclassBean;
import studentclass.service.StudentClassService;

@WebServlet("/fraction/teacherchoiceclasslook.do")
public class teacherclasschoiceServlet extends HttpServlet {
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
		String F_exam = request.getParameter("F_exam");
		Integer cr_Id = Integer.parseInt(Cr_Id);
		List<studentclassBean> scb = null;
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		StudentService ss = ctx.getBean(StudentService.class);
		StudentClassService scs = ctx.getBean(StudentClassService.class);
		FractionService fs = ctx.getBean(FractionService.class);
		classroomService cs = ctx.getBean(classroomService.class);
		List<fractionDemo> fd = null;
		classroomBean cb = cs.loadoneclassroom(cr_Id);
		scb = scs.querystudentclass(cb);
		fd = fs.loadFractionlook_teacher(scb, F_exam);
		List<studentBean> list = ss.findstudents(cs.loadoneclassroom(cr_Id));
		session.setAttribute("Cr_Id", Cr_Id);
		session.setAttribute("studentBeanlist", list);
		session.setAttribute("fractionDemo", fd);
		
		RequestDispatcher rd = request.getRequestDispatcher("/fraction/teacherfractionlook.jsp");
		rd.forward(request, response);	
		return;	
		
		
		
		
	}

}
