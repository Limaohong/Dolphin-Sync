package useraccount.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import useraccount.model.userAccountBean;
import useraccount.service.userAccountService;


@SuppressWarnings("serial")
@WebServlet("/GetPasswordApp")
public class GetPasswordApp extends HttpServlet {
	
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String password = null;
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String account = jsonObject.get("account").getAsString();
		boolean isUserValid = false;
		userAccountBean ua = null;
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		userAccountService ls = ctx.getBean(userAccountService.class);
		
		try {
			ua = ls.checkID(account);
			if (ua != null) {
				isUserValid=true;
				password = ua.getUA_Psw();
			} else {
				isUserValid = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jsonObject = new JsonObject();
		if (isUserValid == true ) {
			jsonObject.addProperty("isUserValid", isUserValid);
			jsonObject.addProperty("Password", password);
		}else {
			jsonObject.addProperty("isUserValid", isUserValid);
		}
		
		
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
		System.out.println("output: " + jsonObject.toString());
	}

}
