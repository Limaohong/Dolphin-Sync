package useraccount.controller;

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

import org.springframework.stereotype.Controller;
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
@WebServlet(urlPatterns= {"/register/useraccount.do"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 
	// 設定密碼欄位必須由大寫字母、小寫字母、數字與 !@#$%!^'" 等四組資料組合而成，且長度不能小於八個字元
	// 
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	@SuppressWarnings({ "static-access", "unused" })
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

		String UA_Acu = "";
		String UA_Psw = "";
		String UA_Psw2 = "";
		String UA_Name = "";
		String UA_Phone = "";
		String UA_VC ="";
		String fileName = "";
		String UA_pl ="";
		String C_CN = "";
		String C_CP = "";
		companyBean UA_CVC = null;
		Blob C_CL = null;
		Blob UA_Avater = null;
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
				if (p.getContentType() == null) { //如果getContentType為未知，則返回值為null
					if (fldName.equals("UA_Acu")) {
						UA_Acu = value;
					} else if (fldName.equals("UA_Psw")) {
						UA_Psw = value;
					} else if (fldName.equals("UA_Psw2")) {
						UA_Psw2 = value;
					} else if (fldName.equalsIgnoreCase("UA_Name")) {
						UA_Name = value;
					} else if (fldName.equalsIgnoreCase("UA_Phone")) {
						UA_Phone = value;
					} else if(fldName.equalsIgnoreCase("UA_PL")) {
						UA_pl = value;
					} else if(fldName.equalsIgnoreCase("UA_VC")) {
						UA_VC = value;
					} else if (fldName.equals("C_CN")){
						C_CN = value;
					} else if(fldName.equals("C_CP")) {
						C_CP = value;
					}
				}else if (p.getContentType().equals("image/jpeg") || p.getContentType().equals("image/gif")) {
						if(fldName.equals("C_CL")) {					
							fileName = GlobalService.getFileName(p);
							fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
							if (fileName != null && fileName.trim().length() > 0) {
								sizeInBytes = p.getSize();
								is = p.getInputStream();
								try {
									C_CL = SystemUtils2018.fileToBlob(is, sizeInBytes);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							} else {
									errorMsg.put("errPicture", "必須挑選圖片檔");
									}
						} else if(fldName.equals("UA_Avater")) {					 
							fileName = GlobalService.getFileName(p);
							fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
							if(fileName != null && fileName.trim().length() >0) {
								sizeInBytes = p.getSize();
								is = p.getInputStream();
								try {
									UA_Avater = SystemUtils2018.fileToBlob(is,sizeInBytes);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}else {
								errorMsg.put("errPicture", "必須挑選圖片檔");
								}
							
						 }
					}
				}
			 
			// 2. 進行必要的資料轉換
			// (無)
			// 3. 檢查使用者輸入資料
			if (UA_Acu == null || UA_Acu.trim().length() == 0) {
				errorMsg.put("errorIDEmpty", "帳號欄必須輸入");
			}
			if (UA_Psw == null || UA_Psw.trim().length() == 0) {
				errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
			}
			if (UA_Psw2 == null || UA_Psw2.trim().length() == 0) {
				errorMsg.put("errorPassword2Empty", "密碼確認欄必須輸入");
			}
			if (UA_Psw.trim().length() > 0 && UA_Psw2.trim().length() > 0) {
				if (!UA_Psw.trim().equals(UA_Psw2.trim())) {
					errorMsg.put("errorPassword2Empty", "密碼欄必須與確認欄一致");
					errorMsg.put("errorPasswordEmpty", "*");
				}
			}

			if (UA_Name == null || UA_Name.trim().length() == 0) {
				errorMsg.put("errorName", "姓名欄必須輸入");
			}
			if (UA_Phone == null || UA_Phone.trim().length() == 0) {
				errorMsg.put("errorTel", "電話號碼欄必須輸入");
			}

		}else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
		// 如果都沒有錯誤才去最後檢查密碼欄的格式
		if (errorMsg.isEmpty()) {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(UA_Psw);
			if (!matcher.matches()) {
				errorMsg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
			}
		}
		// 如果有錯誤
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = 
					WebApplicationContextUtils.findWebApplicationContext(sc);
			//WebApplicationContext 目的為去尋找組態檔的位置'ApplicationContext'
			userAccountService service = ctx.getBean(userAccountService.class);
			companyService companyService = ctx.getBean(companyService.class);


			if (service.idExists(UA_Acu)) {
				errorMsg.put("errorIDDup", "此帳號已存在，請換新帳號");
			} else {
				if(UA_pl.equals("0")) {   //boss
					UA_VC = service.VerificationCode(UA_pl);
					Integer UA_PL = 0;
					UA_PL = UA_PL.parseInt(UA_pl);
					String C_VC = UA_VC;
					companyBean com = new companyBean(C_CL,C_CN,C_CP,C_VC);
					UA_CVC = com;
					userAccountBean mem = new userAccountBean(UA_PL, UA_Acu, UA_Psw, UA_Name, UA_Phone, UA_VC,UA_CVC,UA_Avater);

					int n = service.saveMember(mem);
					int c = companyService.savecompany(com);
					if (n == 1 && c ==1 ) {
						msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
						response.sendRedirect("../login.jsp");
						return;
					} else {
						errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
					}
					
				}else if(UA_pl.equals("1")){  //teacher
					userAccountBean bean = service.checkVC(UA_VC);
					
					UA_CVC = bean.getUA_CVC();					
					if(bean!=null) {
						UA_VC = service.VerificationCode(UA_pl);
						Integer UA_PL = 0;
						UA_PL = UA_PL.parseInt(UA_pl);
						
						userAccountBean mem = new userAccountBean(UA_PL, UA_Acu, UA_Psw, UA_Name, UA_Phone, UA_VC,UA_CVC,UA_Avater);
						int n = service.saveMember(mem);
						if (n == 1) {
							msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
							response.sendRedirect("../login.jsp");
							return;
						} else {
							errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
						}
					} else {
						errorMsg.put("errorBossVC", "查無此驗證碼，請重新輸入");
					}
				}

			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				if(UA_pl.equals("0")) {
					RequestDispatcher rd = request.getRequestDispatcher("register_boss.jsp");
					rd.forward(request, response);					
				} else if(UA_pl.equals("1")) {
					RequestDispatcher rd = request.getRequestDispatcher("register_teacher.jsp");
					rd.forward(request, response);				
				}
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			System.out.println(e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("../login.jsp");
			rd.forward(request, response);
		}
	}
}