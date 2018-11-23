package fraction.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import classroom.model.classroomBean;
import classroom.service.classroomService;
import fraction.model.fractionDemo;
import fraction.service.FractionService;
import student.model.studentBean;
//給選擇班級轉頁面用
import student.service.StudentService;
import studentclass.model.studentclassBean;
import studentclass.service.StudentClassService;

@WebServlet("/fraction/loadfractionexam.do")
public class loadfractionexam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		Map<String,String> subject = new HashMap<String,String>();
		String Cr_Id = request.getParameter("cr_Id");
//		System.out.println("Cr_Id===================="+Cr_Id);
		Integer cr_Id = Integer.parseInt(Cr_Id);		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		StudentService ss = ctx.getBean(StudentService.class);
		StudentClassService scs = ctx.getBean(StudentClassService.class);
		FractionService fs = ctx.getBean(FractionService.class);
		classroomService cs = ctx.getBean(classroomService.class);
		classroomBean cb = cs.loadoneclassroom(cr_Id);
		List<studentBean> studentlist = ss.findstudents(cs.loadoneclassroom(cr_Id));
		List<studentclassBean> studenclasstlist = scs.querystudentclass(cb);
		List<fractionDemo> fractionlist = fs.loadFraction_teacher(studenclasstlist);		
		Set<String> set = new HashSet<String>();	
		for(fractionDemo fd:fractionlist) {
			set.add(fd.getF_Exam());
		}
		List<String> demolist = new ArrayList<String>();
		Iterator<String> iterator=set.iterator();
		while(iterator.hasNext()){
			demolist.add(iterator.next());
		}
		String json = new Gson().toJson(demolist);
		System.out.println(json);
		response.getWriter().print(json);
		
		
		
		
		
		
		
	}

}
