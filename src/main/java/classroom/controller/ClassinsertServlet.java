package classroom.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import classroom.model.classroomBean;
import classroom.service.classroomService;
import init.util.GlobalService;
import init.util.SystemUtils2018;
import teacherpresentation.model.DemoTeacher;
import teacherpresentation.model.teacherpresentationBean;
import teacherpresentation.service.teacherpresentationService;
import useraccount.model.userAccountBean;


@WebServlet("/classroom/classinsert.do")
public class ClassinsertServlet extends HttpServlet {
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
			RequestDispatcher rd = request.getRequestDispatcher("classinsert.jsp");
			rd.forward(request, response);
			return;
		}
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		classroomService cls = ctx.getBean(classroomService.class);
		userAccountBean ub = (userAccountBean) session.getAttribute("LoginOK");
		classroomBean clb = new classroomBean(Cr_Name,ub);
		
		
		Integer n = cls.classinsert(clb);
		
		if(n == 1) {
			MsgOk.put("insertclassOk", "教室新增完成");
			
		}
		List<classroomBean> list = null;		
		list = cls.loadclassroom_teacher(ub);
		if(list!=null) {
			session.setAttribute("loadclassroom", list);
			response.sendRedirect("../index.jsp");
			return;
		}else {
			errorMsgMap.put("errorInsert", "教室新增失敗");
			RequestDispatcher rd = request.getRequestDispatcher("classinsert.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
