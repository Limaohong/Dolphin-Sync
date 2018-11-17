package company.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import company.model.companyBean;
import company.service.companyService;
import init.util.GlobalService;
import init.util.SystemUtils2018;
import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;


//
//啟動檔案上傳的功能：
//1. <form>標籤的 method屬性必須是"POST", 而且
//    enctype屬性必須是"multipart/form-data"
//    注意：enctype屬性的預設值為"application/x-www-form-urlencoded"
//2. 定義可以挑選上傳檔案的表單欄位：
//   <input type='file' name='user-defined_name' />
//
//所謂 HTTP multipart request是指由Http客戶端(如瀏覽器)所建構的ㄧ種請求，
//用來上傳一般的表單資料(form data)與檔案。
//參考網頁：http://stackoverflow.com/questions/913626/what-should-a-multipart-http-request-with-multiple-files-look-like
//
//Servlet規格書一直到Servlet 3.0才提出標準API將檔案上傳的功能標準化。
//
//在Servlet 3.0中，若要能夠處理瀏覽器送來的HTTP multipart request, 
//我們撰寫的Servlet程式必須以註釋
//   『javax.servlet.annotation.MultipartConfig』來加以說明。
//
//MultipartConfig的屬性說明:
//location: 上傳之表單資料與檔案暫時存放在Server端之路徑，此路徑必須存在，否則Web Container將丟出例外。
//
//fileSizeThreshold: 上傳檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，
//                   否則存放在主記憶體。
//
//maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Web Container會丟出例外
//
//maxRequestSize: 上傳所有檔案之總長度限制，如果超過此數值，Web Container會丟出例外
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/setting/updateslide.do")
public class updateslideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		
		String fileName = "";		
		Blob Slide_1 = null;
		Blob Slide_2 = null;
		Blob Slide_3 = null;
		Blob Slide_4 = null;
		Blob Slide_5 = null;
		long sizeInBytes = 0;
		InputStream is = null;
		// 取出HTTP multipart request內所有的parts
		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
				for (Part p : parts) {
					String fldName = p.getName();
					String value = request.getParameter(fldName);
	
					// 1. 讀取使用者輸入資料
					if (p.getContentType()==null) { 
						
							
						}else if(p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/gif")){
							if(fldName.equals("Slide_1")) {					
								fileName = GlobalService.getFileName(p);
								fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
								if (fileName != null && fileName.trim().length() > 0) {
									sizeInBytes = p.getSize();
									is = p.getInputStream();
									try {
										Slide_1 = SystemUtils2018.fileToBlob(is, sizeInBytes);
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else {
										Slide_1 = null;
										}
							} else if(fldName.equals("Slide_2")) {					 
								fileName = GlobalService.getFileName(p);
								fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
								if(fileName != null && fileName.trim().length() >0) {
									sizeInBytes = p.getSize();
									is = p.getInputStream();
									try {
										Slide_2 = SystemUtils2018.fileToBlob(is,sizeInBytes);
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}else {
										Slide_2 = null;
									}
								
							 } else if(fldName.equals("Slide_3")) {					 
									fileName = GlobalService.getFileName(p);
									fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
									if(fileName != null && fileName.trim().length() >0) {
										sizeInBytes = p.getSize();
										is = p.getInputStream();
										try {
											Slide_3 = SystemUtils2018.fileToBlob(is,sizeInBytes);
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}else {
											Slide_3 = null;
										}
									
							} else if(fldName.equals("Slide_4")) {					 
								fileName = GlobalService.getFileName(p);
								fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
								if(fileName != null && fileName.trim().length() >0) {
									sizeInBytes = p.getSize();
									is = p.getInputStream();
									try {
										Slide_4 = SystemUtils2018.fileToBlob(is,sizeInBytes);
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}else {
										Slide_4 = null;
									}
								
							 }else if(fldName.equals("Slide_5")) {					 
									fileName = GlobalService.getFileName(p);
									fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
									if(fileName != null && fileName.trim().length() >0) {
										sizeInBytes = p.getSize();
										is = p.getInputStream();
										try {
											Slide_5 = SystemUtils2018.fileToBlob(is,sizeInBytes);
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}else {
											Slide_5 = null;
										}
									
								 }
						}
					}//for (Part p : parts)
			
			if(Slide_1==null&&Slide_2==null&&Slide_3==null&&Slide_4==null&&Slide_5==null) {
				errorMsg.put("errorslide","未選擇圖片");
			}	 


		}else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
		
		// 如果有錯誤
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息		
				RequestDispatcher rd = request.getRequestDispatcher("update_slide.jsp");
				rd.forward(request, response);
				
			return;
			}
			
		
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = 
					WebApplicationContextUtils.findWebApplicationContext(sc);
			companyService companyService = ctx.getBean(companyService.class);


			companyBean cb = new companyBean(Slide_1,Slide_2,Slide_3,Slide_4,Slide_5);
			userAccountBean ub = (userAccountBean) session.getAttribute("LoginOK");
			String UA_Acu = ub.getUA_Acu();
			Integer n = 0;
			n = companyService.updateslide(cb,ub);
			if(n==1) {
				msgOK.put("updateOk", "更新完成");
				RequestDispatcher rd = request.getRequestDispatcher("update_slide.jsp");
				rd.forward(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("update_slide.jsp");
			rd.forward(request, response);
		}
	}
}