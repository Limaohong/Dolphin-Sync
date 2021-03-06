package bulletinboard.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bulletinboard.model.DemoboardBean;
import bulletinboard.model.bulletinboardBean;
import bulletinboard.service.bulletinboardService;
import classroom.model.classroomBean;
//給選擇班級轉頁面用
import classroom.service.classroomService;
import company.model.companyBean;

@WebServlet("/board/choicedeletecom.do")
public class BoarddeletecomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	HttpSession session;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}    
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		String bB_Id = request.getParameter("bB_Id");
		Integer BB_Id = Integer.parseInt(bB_Id.trim());
		bulletinboardBean bb = null;
		companyBean combean = null;
		List<DemoboardBean> Demoboard = null;
		DemoboardBean db = null;		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		bulletinboardService bs = ctx.getBean(bulletinboardService.class);
		classroomService cs = ctx.getBean(classroomService.class);
		combean = (companyBean) request.getAttribute("LoginCom");
		Integer n = 0;
		n = bs.deletecomBoard(BB_Id);
		if(n==1) {
			Demoboard = bs.loadbulletinboard_boss(combean);
			session.setAttribute("Demoboard", Demoboard);
			response.sendRedirect("../index.jsp");;
			return;
		}else {			
			RequestDispatcher rd = request.getRequestDispatcher("choicedeletecomboard.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
	}

}
