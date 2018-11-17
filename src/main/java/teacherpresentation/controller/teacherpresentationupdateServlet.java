package teacherpresentation.controller;

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

import company.model.companyBean;
import init.util.GlobalService;
import init.util.SystemUtils2018;
import teacherpresentation.model.DemoTeacher;
import teacherpresentation.model.teacherpresentationBean;
import teacherpresentation.service.teacherpresentationService;
import useraccount.model.userAccountBean;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/teacher/teacherpresentupdate.do")
public class teacherpresentationupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String,String> errorMsgMap = new HashMap<String,String>();
		Map<String,String> MsgOk = new HashMap<String,String>();
		String TP_Name = null;
		String TP_TI = null;
		String TP_Id = null;
		Clob TP_TI_C = null;
		Blob TP_Avater = null;
		long sizeInBytes = 0;
		InputStream is = null;
		String fileName = "";
	
		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		if(parts!=null) {
			for(Part p:parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				
				if(p.getContentType() == null) {
					if(fldName.equals("TP_Name")) {
						TP_Name = value;
					}else if(fldName.equals("TP_Id")) {
						TP_Id = value;
					}else if(fldName.equals("TP_TI")) {
						TP_TI = value;
						try {
							TP_TI_C = new SerialClob(TP_TI.toCharArray());
						} catch (SerialException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
							throw new RuntimeException("TP_TI_C存取失敗"+e.getMessage());
						}
					}
				} else if(p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/gif")) {
					if(fldName.equals("TP_Avater")) {
						fileName = GlobalService.getFileName(p);
						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
						if (fileName != null && fileName.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
							try {
								TP_Avater = SystemUtils2018.fileToBlob(is, sizeInBytes);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							TP_Avater = null;
						}
					
					}
				}
				
				
			}//for(Part p:parts)
			
		}//if(parts!=null)
		
		
		if (TP_Name.trim().length() != 0) {
			if(TP_Name == null || TP_Name.trim().length() == 0) {
				errorMsgMap.put("errorName", "老師姓名必須填寫");
			}
		}
		if (TP_TI.trim().length() != 0) {
			if(TP_TI == null || TP_TI.trim().length() == 0) {
				errorMsgMap.put("errorContext", "老師介紹必須填寫");
			}
		}
		if(!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("teacherpresentupdate.jsp");
			rd.forward(request, response);
			return;
		}
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		teacherpresentationService ts = ctx.getBean(teacherpresentationService.class);
		HttpSession hs = request.getSession();
		List<DemoTeacher> DemoTeacher = null;
		userAccountBean ua = (userAccountBean) hs.getAttribute("LoginOK");		

		Integer Tp_Id = Integer.parseInt(TP_Id);

		companyBean cb =(companyBean) hs.getAttribute("LoginCom");

		teacherpresentationBean tb = new teacherpresentationBean(Tp_Id,cb,TP_Name,TP_Avater,TP_TI_C);
		
		Integer n = ts.updateteacher(tb, sizeInBytes);
		
		if(n == 1) {
			MsgOk.put("insertteacherOk", "師資介紹新增完成");
			
		}
		
		if(!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("teacherpresentupdate.jsp");
			rd.forward(request, response);
			return;
		}
		
		Integer UA_pl = ua.getUA_PL();
		if(UA_pl.equals(0)) {
				DemoTeacher = ts.loadteacher_boss(cb);
			}else if(UA_pl.equals(1)) {
				DemoTeacher = ts.loadteacher_teacher(cb);
			}else if(UA_pl.equals(2)) {
				DemoTeacher = ts.loadteacher_parent(cb);
			}
		
		
		if(DemoTeacher!=null) {
			session.setAttribute("DemoTeacher",DemoTeacher);
			response.sendRedirect("../index.jsp");
			return;
		}else {
			System.out.println("沒抓到DemoTeacher的資料");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
