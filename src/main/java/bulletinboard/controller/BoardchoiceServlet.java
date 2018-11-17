package bulletinboard.controller;

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

import bulletinboard.model.DemoboardBean;
import bulletinboard.model.bulletinboardBean;
import bulletinboard.service.bulletinboardService;
import classroom.model.classroomBean;
//給選擇班級轉頁面用

@WebServlet("/board/choiceclass.do")
public class BoardchoiceServlet extends HttpServlet {
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
		Map<String, String> errorMsg = new HashMap<String, String>();
		String cmd = request.getParameter("cmd");
		String cr_Id = request.getParameter("cr_Id");
		Integer Cr_Id = Integer.parseInt(cr_Id.trim());
		bulletinboardBean bb = null;
		DemoboardBean db = null;
		classroomBean cb = new classroomBean(Cr_Id,null,null);
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		bulletinboardService bs = ctx.getBean(bulletinboardService.class);		
		if (cmd.equalsIgnoreCase("update")) {
			bb = bs.querysingleclassboard(cb);
			db = bs.loadsingleboard(bb);
			session.setAttribute("singleboard", db);
			RequestDispatcher rd = request.getRequestDispatcher("/board/updateboard.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		
		
	}

}
