package useraccount.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
import classroom.service.classroomService;
import company.model.companyBean;
import company.service.companyService;
import teacherpresentation.model.DemoTeacher;
import teacherpresentation.service.teacherpresentationService;
import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;




@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String,String> errorMsgMap = new HashMap<String,String>();
	
		request.setAttribute("ErrorMsgKey", errorMsgMap);
	
		String UA_Acu =request.getParameter("UA_Acu");		
		String UA_Psw = request.getParameter("UA_Psw");
		String rm = request.getParameter("rememberMe");
		Integer UA_Id=0;
		Integer UA_pl=0;
		String UA_VC = "";
		String UA_CVC = "";
//		String requestURI = (String)session.getAttribute("requestURI");
		//將請求的路徑存進requestURI
		
				if(UA_Acu == null || UA_Acu.trim().length() == 0) {
					errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
					}
				if(UA_Psw == null || UA_Psw.trim().length() == 0) {
					errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
				}
				// **********Remember Me****************************
						Cookie cookieUser = null;
						Cookie cookiePassword = null;
						Cookie cookieRememberMe = null;
						// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
						if (rm != null) { 
							cookieUser = new Cookie("user", UA_Acu);
							cookieUser.setMaxAge(7 * 24 * 60 * 60);     // Cookie的存活期: 七天
							cookieUser.setPath(request.getContextPath());
							
							
							cookiePassword = new Cookie("password", UA_Psw);
							cookiePassword.setMaxAge(7 * 24 * 60 * 60);
							cookiePassword.setPath(request.getContextPath());
							
							cookieRememberMe = new Cookie("rm", "true");
							cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
							cookieRememberMe.setPath(request.getContextPath());
						} else {   // 使用者沒有對 RememberMe 打勾
							cookieUser = new Cookie("user", UA_Acu);
							cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
							cookieUser.setPath(request.getContextPath());
							
							
							cookiePassword = new Cookie("password", UA_Psw);
							cookiePassword.setMaxAge(0);//代表瀏覽器關掉及刪掉
							cookiePassword.setPath(request.getContextPath());//告訴主機要刪的Path
							
							cookieRememberMe = new Cookie("rm", "false");
							cookieRememberMe.setMaxAge(7 * 24  * 60 * 60);
							cookieRememberMe.setPath(request.getContextPath());
						}
						response.addCookie(cookieUser);
						response.addCookie(cookiePassword);
						response.addCookie(cookieRememberMe);
						// ********************************************
						if(!errorMsgMap.isEmpty()) {
							RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
							rd.forward(request, response);
							return;
							
						}
						userAccountBean ua = null;
						companyBean cb = null;
						List<classroomBean> clsb = null;
						List<bulletinboardBean> bb = null;
						List<DemoboardBean> Demoboard = null;
						List<DemoTeacher> DemoTeacher = null;
//						loginService ls = new loginServiceImpl();
						ServletContext sc = getServletContext();
						WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
						userAccountService ls = ctx.getBean(userAccountService.class);
						companyService cs = ctx.getBean(companyService.class);
						classroomService cls = ctx.getBean(classroomService.class);
						bulletinboardService bs = ctx.getBean(bulletinboardService.class);
						teacherpresentationService ts = ctx.getBean(teacherpresentationService.class);
						try {							
							ua = ls.checkIDPassword(UA_Acu, UA_Psw);
							if(ua!=null) {
								session.setAttribute("LoginOK", ua);
								}else {
									errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
									RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
									rd.forward(request, response);
									
									return;
								}
							
							UA_pl = ua.getUA_PL();
							UA_Id = ua.getUA_Id();						
							UA_VC = ua.getUA_VC();
							if(UA_pl.equals(0)) {
								cb = cs.loadcompany_boss(ua.getUA_VC());
								Demoboard = bs.loadbulletinboard_boss(cb);
								DemoTeacher = ts.loadteacher_boss(cb);
								}else if(UA_pl.equals(1)) {
									cb = cs.loadcompany_teacher(ua.getUA_CVC().getC_VC());
									Demoboard = bs.loadbulletinboard_teacher(cb);
									DemoTeacher = ts.loadteacher_teacher(cb);
									clsb = cls.loadclassroom_teacher(ua);
								}else if(UA_pl.equals(2)) {
									cb = cs.loadcompany_parent(ua.getUA_CVC().getC_VC());
									Demoboard = bs.loadbulletinboard_parent(cb);
									DemoTeacher = ts.loadteacher_parent(cb);
								}
							
							if(cb!=null) {
								session.setAttribute("LoginCom", cb);
								}else {
									System.out.println("沒抓到companyBean的資料");
								}
							if(Demoboard!=null) {
								session.setAttribute("Demoboard", Demoboard);
								}else {
									System.out.println("沒抓到boardDemolist的資料");
								}
							if(DemoTeacher!=null) {
								session.setAttribute("DemoTeacher",DemoTeacher);
								}else {
									System.out.println("沒抓到DemoTeacher的資料");
								}
							if(clsb!=null) {
								session.setAttribute("loadclassroom", clsb);
								}else {
									System.out.println("沒抓到loadclassroom的資料");
								}
						}catch(RuntimeException e) {
							errorMsgMap.put("LoginError", e.getMessage());
						}
						
						if(errorMsgMap.isEmpty()) {
							RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
							rd.forward(request, response);
							
							return;
						}else {
							RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
							rd.forward(request, response);
							
							return;
						}
	
	
	
	
	
	
	}

}
