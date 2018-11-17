package useraccount.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import company.model.companyBean;
import company.service.companyService;
import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;

@SuppressWarnings("serial")
@WebServlet("/RegisterServletApp")
public class RegisterServletApp extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String UA_Acu = "";
		String UA_Psw = "";
		String UA_Name = "";
		String UA_Phone = "";
		String UA_pl = "";
		String UA_VC = "";
		String photoImage = "";
		companyBean UA_CVC = null;
		Blob UA_Avater = null;
		String C_CN = "";
		String C_CP = "";
		String logoImage = "";
		Blob C_CL = null;
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		UA_Acu = jsonObject.get("account").getAsString();
		UA_Psw = jsonObject.get("password").getAsString();
		UA_Name = jsonObject.get("name").getAsString();
		UA_Phone = jsonObject.get("phone").getAsString();
		photoImage = jsonObject.get("photoImage").getAsString();
		try {
			byte[] pi = Base64.getMimeDecoder().decode(photoImage);
			UA_Avater = new SerialBlob(pi);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		UA_pl = jsonObject.get("identity").getAsString();
		if (UA_pl.equals("1")) {
			UA_VC = jsonObject.get("verificationCode").getAsString();
		}
		if (UA_pl.equals("0")) {
			C_CN = jsonObject.get("companyName").getAsString();
			C_CP = jsonObject.get("companyPhone").getAsString();
			logoImage = jsonObject.get("logoImage").getAsString();
			try {
				byte[] li = Base64.getMimeDecoder().decode(logoImage);
				C_CL = new SerialBlob(li);
			} catch (SerialException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		boolean signUpOk = false;
		String signUpError = null;
		boolean accountError = false;
		boolean phoneError = false;
		boolean verificationCodeError = false;
		boolean companyNameError = false;
		boolean companyPhoneError = false;
		int error = 0;
		Integer UA_PL = Integer.parseInt(UA_pl);
		userAccountBean mem = null;
		userAccountService service = ctx.getBean(userAccountService.class);
		if (service.idExists(UA_Acu)) {
			error++;
			accountError = true;
		} else {
			accountError = false;
		}
		if (service.phoneExists(UA_Phone)) {
			error++;
			phoneError = true;
		} else {
			phoneError = false;
		}
		if (UA_pl.equals("0")) { // boss
			companyBean com = null;
			companyService companyService = ctx.getBean(companyService.class);
			if (companyService.CNExists(C_CN)) {
				error++;
				companyNameError = true;
			} else {
				companyNameError = false;
			}
			if (companyService.CPExists(C_CP)) {
				error++;
				companyPhoneError = true;
			} else {
				companyPhoneError = false;
			}
			int n = 0;
			int c = 0;
			try {
				if (companyNameError == false && companyPhoneError == false) {
					UA_VC = service.VerificationCode(UA_pl);
					String C_VC = UA_VC;
					com = new companyBean(C_CL, C_CN, C_CP, C_VC);
					mem = new userAccountBean(UA_PL, UA_Acu, UA_Psw, UA_Name, UA_Phone, UA_VC,com,UA_Avater);
					n = service.saveMember(mem);
					c = companyService.savecompany(com);
				}
				if (n == 1 && c == 1) {
					signUpOk = true;
				} else {
					error++;
					signUpOk = false;
					signUpError = "Boss sign up error";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (UA_pl.equals("1")) { // teacher
			userAccountBean bean = service.checkVC(UA_VC);
			UA_CVC = bean.getUA_CVC();
			if (bean != null) {
			} else {
				error++;
				signUpOk = false;
				verificationCodeError = false;
			}
			if (UA_CVC != null && accountError == false && phoneError == false && verificationCodeError == false) {
				try {
					UA_VC = service.VerificationCode(UA_pl);
					mem = new userAccountBean(UA_PL, UA_Acu, UA_Psw, UA_Name, UA_Phone, UA_VC, UA_CVC, UA_Avater);
					int n = service.saveMember(mem);
					if (n == 1) {
						signUpOk = true;
					} else {
						error++;
						signUpOk = false;
						signUpError = "Boss sign up error";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		jsonObject = new JsonObject();
		if (error <= 0) {
			jsonObject.addProperty("signUpOk", signUpOk);
		} else {
			jsonObject.addProperty("signUpOk", signUpOk);
			jsonObject.addProperty("signUpError", signUpError);
			jsonObject.addProperty("accountError", accountError);
			jsonObject.addProperty("phoneError", phoneError);
			jsonObject.addProperty("verificationCodeError", verificationCodeError);
			jsonObject.addProperty("companyNameError", companyNameError);
			jsonObject.addProperty("companyPhoneError", companyPhoneError);
		}
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());

	}
}

