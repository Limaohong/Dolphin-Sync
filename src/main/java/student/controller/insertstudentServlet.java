package student.controller;

import java.io.IOException;
import java.util.HashMap;
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
import company.model.companyBean;
import student.model.studentBean;
import student.service.StudentService;
import studentclass.model.studentclassBean;
import studentclass.service.StudentClassService;
import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;


@WebServlet("/student/insertstudent.do")
public class insertstudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		HttpSession session = request.getSession();
		
		String S_Name = request.getParameter("S_Name");
		String UA_Name = request.getParameter("UA_Name");
		String S_Phone = request.getParameter("S_Phone");
		String Cr_Id = request.getParameter("Cr_Id");
		
		if(S_Name.trim().length()==0) {
			errorMsg.put("errorNameS", "學生姓名不可空白");
		}
		if(UA_Name.trim().length()==0) {
			errorMsg.put("errorNameP", "家長姓名不可空白");
		}
		if(S_Phone.trim().length()==0) {
			errorMsg.put("errorTel", "家長電話不可空白");
		}
		
		
		if(!errorMsg.isEmpty()) {
			session.setAttribute("errorMsg", errorMsg);
			RequestDispatcher rd = request.getRequestDispatcher("studentinsert.jsp");
			rd.forward(request, response);
			
		}
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		StudentService ss = ctx.getBean(StudentService.class);
		userAccountService us = ctx.getBean(userAccountService.class);
		StudentClassService scs = ctx.getBean(StudentClassService.class);
		classroomService cls = ctx.getBean(classroomService.class);
		
		//新增家長table data
		userAccountBean ua = (userAccountBean) session.getAttribute("LoginOK");//老師帳號
		Integer UA_PL = 2;
		String UA_VC = us.VerificationCode("2");
		companyBean UA_CVC = ua.getUA_CVC();
		String UA_Acu = S_Phone;
		String UA_Psw = UA_VC;
		String UA_Phone = S_Phone;
		userAccountBean ub = new userAccountBean(UA_PL,UA_Acu,UA_Psw,UA_Name,UA_Phone,UA_VC,UA_CVC);
		Integer parentInsert = 0;
		//先判斷是否有新增過這筆資料
		if(us.checkID(UA_Acu)!=null) {
			
		}else {
			parentInsert = us.saveMember(ub);
		}
		
		if(parentInsert==1) {
			msgOK.put("insertOk", "家長新增成功");
		}
		//新增學生table data
		studentBean sb = new studentBean(S_Name,ub);
		Integer studentInsert = 0;
		if(ss.queryStudent(ub)!=null) {
			
		}else {
			studentInsert = ss.insertStudent(sb);
			
		}
		if(studentInsert==1) {
			msgOK.put("insertOk", "學生新增成功");
		}
		//新增studentclass table data
		studentBean singleStudent = ss.queryStudent(ub);
		//找到student ID
		Integer S_Id = singleStudent.getS_Id();
		Integer SC_CI = Integer.parseInt(Cr_Id);
		classroomBean cb = cls.loadoneclassroom(SC_CI);
		//String classname = cls.findClassroomName(SC_CI);
		//classroomBean cb = new classroomBean(classname,ub);
		//studentclassBean studentclassbean = new studentclassBean(cb,classname,singleStudent,S_Name);
		studentclassBean studentclassbean = new studentclassBean(SC_CI,cb.getCr_Name(),singleStudent.getS_Id(),S_Name);
		Integer studentclassInsert = 0;
		
		studentclassInsert = scs.saveStudentClass(studentclassbean);
		if(studentclassInsert==1) {
			msgOK.put("insertOk", "班級新增成功");
		}
		
		
		
		
		
		if(studentInsert+parentInsert+studentclassInsert == 3) {
			session.setAttribute("msgOK", msgOK);
			response.sendRedirect("studentinsert.jsp");
		} else {
			errorMsg.put("errorInsert", "學員新增失敗");
			session.setAttribute("errorMsg", errorMsg);
			RequestDispatcher rd = request.getRequestDispatcher("studentinsert.jsp");
			rd.forward(request, response);
		}
		
	}

}
