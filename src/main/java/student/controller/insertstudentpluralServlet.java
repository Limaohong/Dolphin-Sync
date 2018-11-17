package student.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
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

import org.apache.commons.io.input.BOMInputStream;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import classroom.model.classroomBean;
import classroom.service.classroomService;
import company.model.companyBean;
import init.util.GlobalService;
import student.model.studentBean;
import student.service.StudentService;
import studentclass.model.studentclassBean;
import studentclass.service.StudentClassService;
import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;


@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/student/insertstudentplural.do")
public class insertstudentpluralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		StudentService ss = ctx.getBean(StudentService.class);
		userAccountService us = ctx.getBean(userAccountService.class);
		StudentClassService scs = ctx.getBean(StudentClassService.class);
		classroomService cls = ctx.getBean(classroomService.class);
//		String filename = request.getParameter("filename");		
		String fileName = "";
		String Cr_Id = "";
		String str = null;
		String strall[] = null;
		Clob file = null;
		InputStream is = null;
		Integer parentInsert = 0;
		Integer studentclassInsert = 0;
		Integer studentInsert = 0;
		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		if (parts != null) {
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				if (p.getContentType() == null) {
					if(fldName.equals("Cr_Id")) {
						Cr_Id = value;
					}
				}else if(p.getContentType().equals("application/vnd.ms-excel")){
					if(fldName.equals("csvfile")) {
						fileName = GlobalService.getFileName(p);
						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
						is = p.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is,"Big5"));
						while((str = reader.readLine())!=null) {							
							str = new String( str.getBytes("UTF-8"));
							strall = str.split(",");
							System.out.println(strall.length);
							if(strall.length==0) {
								
							}else {

								if(strall[0].equals("學生")) {
									
								}else if(strall[1].equals("電話")) {
									
								}else if(strall[2].equals("家長")) {
									
								}else{
									Integer UA_PL = 2;
									String UA_VC = us.VerificationCode("2");
									String UA_Acu = strall[1];
									String UA_Psw = UA_VC;
									String UA_Phone = strall[1];
									String UA_Name = strall[2];
									String S_Name = strall[0];
									String S_Phone = strall[1];
									userAccountBean ua = (userAccountBean) session.getAttribute("LoginOK");
									companyBean UA_CVC = ua.getUA_CVC();
									userAccountBean ub = new userAccountBean(UA_PL,UA_Acu,UA_Psw,UA_Name,UA_Phone,UA_VC,UA_CVC);
									
									parentInsert = us.saveMember(ub);
									if(parentInsert==1) {
										msgOK.put("insertOk", "家長新增成功");
									}
									//新增學生table data									
									studentBean sb = new studentBean(S_Name,ub);
									
									studentInsert = ss.insertStudent(sb);
									if(studentInsert==1) {
										msgOK.put("insertOk", "學生新增成功");
									}
									//新增studentclass table data
									studentBean singleStudent = ss.queryStudent(ub);
									//找到student ID									
									Integer S_Id = singleStudent.getS_Id();
									Integer SC_CI = Integer.parseInt(Cr_Id);
									classroomBean clb = cls.loadoneclassroom(SC_CI);
									studentclassBean studentclassbean = new studentclassBean(SC_CI,clb.getCr_Name(),S_Id,singleStudent.getS_Name());
									
									studentclassInsert = scs.saveStudentClass(studentclassbean);
									if(studentclassInsert==1) {
										msgOK.put("insertOk", "學生班級關係建立");
									}
									
									
									
									
									
								}
							//}for(int i = 0;i<strall.length;i++)
							}	
						}//while((str = reader.readLine())!=null)
					}	
				}
			}
		}
		
		
		
		if(studentInsert+parentInsert+studentclassInsert == 3) {
			session.setAttribute("msgOK", msgOK);
			response.sendRedirect(response.encodeRedirectURL("studentinsertplural.jsp"));
		} else {
			errorMsg.put("errorInsert", "學員新增失敗");
			session.setAttribute("errorMsg", errorMsg);
			RequestDispatcher rd = request.getRequestDispatcher("studentinsertplural.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
		
	}

}
