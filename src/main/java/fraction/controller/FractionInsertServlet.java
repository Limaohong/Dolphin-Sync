package fraction.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import fraction.model.fractionBean;
import fraction.service.FractionService;
import student.model.studentBean;
import student.service.StudentService;


@WebServlet("/fraction/choiceinsertfractionclass.do")
public class FractionInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String,String> errorMsgMap = new HashMap<String,String>();
		Map<String,String> MsgOk = new HashMap<String,String>();
		request.setAttribute("errorMsgMap", errorMsgMap);
		
		String S_Id = request.getParameter("S_Id");
		Integer s_Id = Integer.parseInt(S_Id);
		String F_Exam = request.getParameter("F_Exam");
		String F_Subject = request.getParameter("subject");
		Integer f_subject = null;
		studentBean sb = null;
		if(F_Subject.equals("國文")) {
			f_subject=1;
		}else if(F_Subject.equals("英文")) {
			f_subject=2;
		}else if(F_Subject.equals("數學")) {
			f_subject=3;
		}
		String F_Fraction = request.getParameter("F_Fraction");
		BigDecimal f_fraction = new BigDecimal(F_Fraction);
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		StudentService ss = ctx.getBean(StudentService.class);
		FractionService fs = ctx.getBean(FractionService.class);
		sb = ss.queryStudent(s_Id);
		fractionBean fb = new fractionBean(sb,f_fraction,F_Exam,f_subject);
		fs.FractionInsert(fb);
		Integer n = 1;
		if(n==1) {
			MsgOk.put("insertclassOk", "成績新增完成");
			response.sendRedirect("../index.jsp");
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
