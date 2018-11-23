package studentclass.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import student.model.studentBean;
import student.service.StudentService;
import studentclass.service.StudentClassService;
import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;


@WebServlet("/studentclass/checkclassroom.do")
public class CheckClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		StudentClassService scs = ctx.getBean(StudentClassService.class);
		classroomService cs = ctx.getBean(classroomService.class);
		StudentService ss = ctx.getBean(StudentService.class);
		userAccountService us = ctx.getBean(userAccountService.class);
		Map<String, String> roomstate = new HashMap<String, String>();
//		找出班級名稱及人數
		Integer num = 0;
		String cr_Id = request.getParameter("cr_Id");
		Integer Cr_Id = Integer.parseInt(cr_Id);
		String name = cs.findClassroomName(Cr_Id);
		classroomBean cb = cs.loadoneclassroom(Cr_Id);
		num= scs.numofstudent(cb);
		String num_s = num.toString();
		roomstate.put("numofstudent", num_s);
		roomstate.put("nameofclass", name);
		session.setAttribute("roomstate", roomstate);
//========================================================================
		//找出學生姓名與電話
		List<studentBean> studentBeanlist = ss.findstudents(cb);
		session.setAttribute("studentBeanlist", studentBeanlist);
		//找出家長姓名				
		List<userAccountBean> userbeanlist = us.findParentsName(studentBeanlist);
		session.setAttribute("ParentsNameList", userbeanlist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/studentclass/CheckClassState.jsp");
		rd.forward(request, response);
	}

}
