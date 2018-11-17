package init.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import company.model.companyBean;
import company.service.companyService;
import teacherpresentation.model.teacherpresentationBean;
import teacherpresentation.service.teacherpresentationService;
import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;



// 
// 程式功能：
// 本Servlet 類別會依據傳入的主鍵呼叫Service元件以讀取該主鍵所對應的紀錄，取出該紀錄內的BLOB欄，
// 進而讀取存放在BLOB欄內的圖片資料，然後傳回給提出請求的瀏覽器。

@WebServlet("/init/getImage")
public class RetrieveImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = null;
		InputStream is = null;
		Integer UA_PL ;
		try {
			// 讀取瀏覽器傳送來的主鍵
			String UA_Acu = request.getParameter("UA_Acu");
			System.out.println("讀取瀏覽器傳送來的UA_Acu: "+UA_Acu);
//			 讀取瀏覽器傳送來的type，以分辨要處理哪個表格
			String type = request.getParameter("type"); 
			System.out.println("讀取瀏覽器傳送來的type: "+type);
			ServletContext sc=getServletContext();
			WebApplicationContext ctx=
					WebApplicationContextUtils.getWebApplicationContext(sc);
			
			userAccountService uac= ctx.getBean(userAccountService.class);
			userAccountBean bean2 = uac.queryMember(UA_Acu);
			
			switch(type){
				case "UserAccount":
				try {
					is = bean2.getUA_Avater().getBinaryStream();
				} catch (Exception e1) {
					;
				}  
					break;
				case "company":
					String order = request.getParameter("order");
					companyService cs = ctx.getBean(companyService.class);
					UA_PL = bean2.getUA_PL();
					companyBean cb = null;
					if(UA_PL.equals(0)) {
						cb = cs.loadcompany_boss(bean2.getUA_CVC().getC_VC());						
					}else if(UA_PL.equals(1)) {
						cb = cs.loadcompany_teacher(bean2.getUA_CVC().getC_VC());
					}else if(UA_PL.equals(2)) {
						cb = cs.loadcompany_parent(bean2.getUA_CVC().getC_VC());
					}
					if(order.equals("0")) {
						try {
							is = cb.getC_CL().getBinaryStream();
						} catch (NumberFormatException ex) {
							;
						}
					}else if(order.equals("1")) {
						try {
							is = cb.getSlide_1().getBinaryStream();
						} catch (Exception e) {
							;
						}						
					}else if(order.equals("2")) {
						try {
							is = cb.getSlide_2().getBinaryStream();
						} catch (Exception e) {							
							;
						}					
					}else if(order.equals("3")) {
						try {
							is = cb.getSlide_3().getBinaryStream();
						} catch (Exception e) {
							;
						}						
					}else if(order.equals("4")) {
						try {
							is = cb.getSlide_4().getBinaryStream();
						} catch (Exception e) {
							;
						}						
					}else if(order.equals("5")) {
						try {
							is = cb.getSlide_5().getBinaryStream();
						} catch (Exception e) {
							;
						}							
					}
					break;
				case "teacher":
					String TP_Id = request.getParameter("TP_Id");
					Integer Tp_Id = Integer.parseInt(TP_Id);
					teacherpresentationService ts = ctx.getBean(teacherpresentationService.class);
					teacherpresentationBean tb = null;
					
					tb = ts.queryteacher(Tp_Id);
				
				try {
					is = tb.getTP_Avater().getBinaryStream();
				} catch (Exception e) {
					;
				}
					break;
			}		
			

			// 由圖片檔的檔名來得到檔案的MIME型態
//			String mimeType = getServletContext().getMimeType("image/jpeg");
			// 設定輸出資料的MIME型態
			response.setContentType("image/jpeg");
			// 取得能寫出非文字資料的OutputStream物件
						
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.jpg)	
			if (is == null) {
				is = getServletContext().getResourceAsStream(
							"/IMAGES/NoImage.jpg");
			}
			os = response.getOutputStream();	
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[8192];
			while ((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("init.util.RetrieveImageServlet#doGet()發生SQLException: " + ex.getMessage());
		} finally{
			is.close();
			os.close();
			
		}
		
	}
}