package classroom.controller;

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

import bulletinboard.model.DemoboardBean;
import bulletinboard.service.bulletinboardService;
import classroom.model.classroomBean;
import classroom.service.classroomService;
import company.model.companyBean;
import useraccount.model.userAccountBean;


@WebServlet("/classroom/classupdate.do")
public class ClassUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String,String> errorMsgMap = new HashMap<String,String>();
		Map<String,String> MsgOk = new HashMap<String,String>();
		String Cr_Name = request.getParameter("Cr_Name");
		request.setAttribute("errorMsgMap", errorMsgMap);
		
		if (Cr_Name.trim().length() == 0) {			
			errorMsgMap.put("errorName", "教室名稱必須填寫");			
		}
		
		if(!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("classUpdate.jsp");
			rd.forward(request, response);
			return;
		}
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		classroomService cs = ctx.getBean(classroomService.class);
		bulletinboardService bs = ctx.getBean(bulletinboardService.class);
		
		userAccountBean ub = (userAccountBean) session.getAttribute("LoginOK");
		classroomBean clbean = (classroomBean) session.getAttribute("singleclass");
		companyBean comb = (companyBean) session.getAttribute("LoginCom");
		Integer Cr_Id = clbean.getCr_Id();
		classroomBean clb = new classroomBean(Cr_Id,Cr_Name,ub);
		
		Integer n = cs.classUpdate(clb);
		
		if(n == 1) {
			MsgOk.put("updateteacherOk", "教室名稱修改完成");
			
		}
		
		List<classroomBean> list = null;
		List<DemoboardBean> Demoboard = null;
		Demoboard = bs.loadbulletinboard_teacher(comb);
		list = cs.loadclassroom_teacher(ub);
		if(list!=null && Demoboard!=null) {
			session.setAttribute("Demoboard", Demoboard);
			session.setAttribute("loadclassroom", list);
			response.sendRedirect("../index.jsp");
			return;
		}else {
			errorMsgMap.put("errorInsert", "教室新增失敗");
			RequestDispatcher rd = request.getRequestDispatcher("classUpdate.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
