package bulletinboard.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bulletinboard.model.DemoboardBean;
import bulletinboard.model.bulletinboardBean;
import bulletinboard.service.bulletinboardService;
import classroom.model.classroomBean;
import classroom.service.classroomService;
import company.model.companyBean;
import init.util.GlobalService;
import init.util.SystemUtils2018;
import useraccount.model.userAccountBean;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/board/boardupdate.do")
public class BoardupdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String,String> errorMsgMap = new HashMap<String,String>();	
		Map<String, String> msgOK = new HashMap<String, String>();
		
		
		
		String Cr_Id = "";
		String BB_T1 = null;
		String BB_T2 = null;
		String BB_T3 = null;
		String BB_T4 = null;
		String BB_T5 = null;
		String BB_B1 = null;
		String BB_B2 = null;
		String BB_B3 = null;
		String BB_B4 = null;
		String BB_B5 = null;
		Clob BB_B1_C = null;
		Clob BB_B2_C = null;
		Clob BB_B3_C = null;
		Clob BB_B4_C = null;
		Clob BB_B5_C = null;
		String fileName = "";
		Blob BB_F1 = null;
		String BB_FN1 = "";
		Blob BB_F2 = null;
		String BB_FN2 = "";
		Blob BB_F3 = null;
		String BB_FN3 = "";
		Blob BB_F4 = null;
		String BB_FN4 = "";
		Blob BB_F5 = null;
		String BB_FN5 = "";
		long sizeInBytes = 0;
		InputStream is = null;
		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) {
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				
				if (p.getContentType() == null) {
					if (fldName.equals("BB_CrId")) {
						Cr_Id = value;
					} else if(fldName.equals("BB_T1")) {
						BB_T1 = value;
					} else if(fldName.equals("BB_T2")) {
						BB_T2 = value;
					} else if(fldName.equals("BB_T3")) {
						BB_T3 = value;
					} else if(fldName.equals("BB_T4")) {
						BB_T4 = value;
					} else if(fldName.equals("BB_T5")) {
						BB_T5 = value;
					} else if(fldName.equals("BB_B1")) {
						BB_B1 = value;
						try {
							BB_B1_C = new SerialClob(BB_B1.toCharArray());
						} catch (Exception e) {							
							e.printStackTrace();
							throw new RuntimeException("Clob1存取失敗"+e.getMessage());
						}
					} else if(fldName.equals("BB_B2")) {
						BB_B2 = value;
						try {
							BB_B2_C = new SerialClob(BB_B2.toCharArray());
						} catch (Exception e) {							
							e.printStackTrace();
							throw new RuntimeException("Clob2存取失敗"+e.getMessage());
						}
					} else if(fldName.equals("BB_B3")) {
						BB_B3 = value;
						try {
							BB_B3_C = new SerialClob(BB_B3.toCharArray());
						} catch (Exception e) {							
							e.printStackTrace();
							throw new RuntimeException("Clob3存取失敗"+e.getMessage());
						}
					} else if(fldName.equals("BB_B4")) {
						BB_B4 = value;
						try {
							BB_B4_C = new SerialClob(BB_B4.toCharArray());
						} catch (Exception e) {							
							e.printStackTrace();
							throw new RuntimeException("Clob4存取失敗"+e.getMessage());
						}
					} else if(fldName.equals("BB_B5")) {
						BB_B5 = value;
						try {
							BB_B5_C = new SerialClob(BB_B5.toCharArray());
						} catch (Exception e) {							
							e.printStackTrace();
							throw new RuntimeException("Clob5存取失敗"+e.getMessage());
						}
					}
					
				} else if (p.getContentType().equals("image/jpeg")  || p.getContentType().equals("image/gif") || p.getContentType().equals("image/png") || p.getContentType().equals("application/octet-stream") || p.getContentType().equals("application/x-zip-compressed")) {
					if(fldName.equals("BB_F1")) {					
						BB_FN1 = GlobalService.getFileName(p);
						BB_FN1 = GlobalService.adjustFileName(BB_FN1, GlobalService.IMAGE_FILENAME_LENGTH);
						if (BB_FN1 != null && BB_FN1.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
							try {
								BB_F1 = SystemUtils2018.fileToBlob(is, sizeInBytes);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							BB_F1 = null;
						}
					}else if(fldName.equals("BB_F2")) {					
						BB_FN2 = GlobalService.getFileName(p);
						BB_FN2 = GlobalService.adjustFileName(BB_FN2, GlobalService.IMAGE_FILENAME_LENGTH);
						if (BB_FN2 != null && BB_FN2.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
							try {
								BB_F2 = SystemUtils2018.fileToBlob(is, sizeInBytes);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							BB_F2 = null;
						}
					} else if(fldName.equals("BB_F3")) {					
						BB_FN3 = GlobalService.getFileName(p);
						BB_FN3 = GlobalService.adjustFileName(BB_FN3, GlobalService.IMAGE_FILENAME_LENGTH);
						if (BB_FN3 != null && BB_FN3.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
							try {
								BB_F3 = SystemUtils2018.fileToBlob(is, sizeInBytes);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							BB_F3 = null;
						}
					} else if(fldName.equals("BB_F4")) {					
						BB_FN4 = GlobalService.getFileName(p);
						BB_FN4 = GlobalService.adjustFileName(BB_FN4, GlobalService.IMAGE_FILENAME_LENGTH);
						if (BB_FN4 != null && BB_FN4.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
							try {
								BB_F4 = SystemUtils2018.fileToBlob(is, sizeInBytes);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							BB_F4 = null;
						}
					} else if(fldName.equals("BB_F5")) {					
						BB_FN5 = GlobalService.getFileName(p);
						BB_FN5 = GlobalService.adjustFileName(BB_FN5, GlobalService.IMAGE_FILENAME_LENGTH);
						if (BB_FN5 != null && BB_FN5.trim().length() > 0) {
							sizeInBytes = p.getSize();
							is = p.getInputStream();
							try {
								BB_F5 = SystemUtils2018.fileToBlob(is, sizeInBytes);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							BB_F5 = null;
						}
					}
				} 
				
				
				
				
			}
		}//if (parts != null)
		
		
		if (BB_T1.trim().length() != 0) {
			if(BB_B1 == null || BB_B1.trim().length() == 0) {
				errorMsgMap.put("errorInsertEmpty", "標題內文必須輸入");
			}
		} else if (BB_T2.trim().length() != 0) {
			if(BB_B2 == null || BB_B2.trim().length() == 0) {
				errorMsgMap.put("errorInsertEmpty", "標題內文必須輸入");
			}
		} else if (BB_T3.trim().length() != 0) {
			if(BB_B3 == null || BB_B3.trim().length() == 0) {
				errorMsgMap.put("errorInsertEmpty", "標題內文必須輸入");
			}
		} else if (BB_T4.trim().length() != 0) {
			if(BB_B4 == null || BB_B4.trim().length() == 0) {
				errorMsgMap.put("errorInsertEmpty", "標題內文必須輸入");
			}
		} else if (BB_T5.trim().length() != 0) {
			if(BB_B5 == null || BB_B5.trim().length() == 0) {
				errorMsgMap.put("errorInsertEmpty", "標題內文必須輸入");
			}
		}
		
		if(!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("insertboard.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.findWebApplicationContext(sc);
		bulletinboardService bs = ctx.getBean(bulletinboardService.class);
		classroomService cs = ctx.getBean(classroomService.class);
		HttpSession hs = request.getSession();
		userAccountBean ub = (userAccountBean) hs.getAttribute("LoginOK");
		companyBean cb = null;
		classroomBean clb = null;
		cb = (companyBean) ub.getUA_CVC();
		Integer CR_ID = Integer.parseInt(Cr_Id);
		clb = cs.loadoneclassroom(CR_ID);
		String BB_SN = cs.findClassroomName(CR_ID);
		
		bulletinboardBean bb = new bulletinboardBean(cb,clb,BB_SN,BB_T1,BB_B1_C,BB_F1,
				BB_FN1,BB_T2,BB_B2_C,BB_F2,BB_FN2,BB_T3,BB_B3_C,BB_F3,BB_FN3,BB_T4,BB_B4_C,
				BB_F4,BB_FN4,BB_T5,BB_B5_C,BB_F5,BB_FN5);
		
		
		int n = bs.updateboard(bb, sizeInBytes,clb);
		if(n == 1) {
			msgOK.put("updateOK", "<Font color='red'>更改成功</Font>");
			
			
		}
		//重新給予Demoboard值
		Integer UA_Id=0;
		Integer UA_pl=0;
		Integer UA_MGR=0;
		Integer UA_PC=0;
		String UA_CVC = "";
		List<DemoboardBean> Demoboard = null;
		UA_Id = ub.getUA_Id();
		if(UA_pl.equals(0)) {
				Demoboard = bs.loadbulletinboard_boss(cb);
			}else if(UA_pl.equals(1)) {
				Demoboard = bs.loadbulletinboard_teacher(cb);
			}else if(UA_pl.equals(2)) {
				Demoboard = bs.loadbulletinboard_parent(cb);
			}
		
		if(Demoboard!=null) {
			session.setAttribute("Demoboard",Demoboard);
		}
		if (!errorMsgMap.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("update_boss.jsp");
				rd.forward(request, response);							
			
			
			return;
		}
		
		response.sendRedirect("../index.jsp");
		
	}
}
