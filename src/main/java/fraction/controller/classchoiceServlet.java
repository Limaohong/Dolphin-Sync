package fraction.controller;

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

import classroom.service.classroomService;
import student.model.studentBean;
//給選擇班級轉頁面用
import student.service.StudentService;

@WebServlet("/fraction/choiceclassinsertfraction.do")
public class classchoiceServlet extends HttpServlet {
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
		Map<String,String> subject = new HashMap<String,String>();
		subject.put("subject1", "國文");
		subject.put("subject2", "英文");
		subject.put("subject3", "數學");
		String Cr_Id = request.getParameter("cr_Id");
		Integer cr_Id = Integer.parseInt(Cr_Id);
		String radiosValue = request.getParameter("radiosValue");
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		StudentService ss = ctx.getBean(StudentService.class);
		classroomService cs = ctx.getBean(classroomService.class);
		List<studentBean> list = ss.findstudents(cs.loadoneclassroom(cr_Id));
		session.setAttribute("Cr_Id", Cr_Id);
		session.setAttribute("studentBeanlist", list);
		session.setAttribute("subjectall", subject);
		if(radiosValue.equals("single")) {
			RequestDispatcher rd = request.getRequestDispatcher("/fraction/onestudentfractioninsert.jsp");
			rd.forward(request, response);
		}else if(radiosValue.equals("plural")) {
			RequestDispatcher rd = request.getRequestDispatcher("/fraction/studentsfractioninsert.jsp");
			rd.forward(request, response);
		}
			
			
		
		
		
		
	}

}
