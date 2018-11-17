<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
	<title>Document</title>
	<link href="login/Login_CSS/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="login/Login_JS/bootstrap.min.js"></script>
	<script src="login/Login_JS/jquery.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->
	<link rel="stylesheet" href="login/Login_CSS/main.css">
	<style type="text/css">
		.register{
			color: aqua;
		    font-size: 50px;
		    font-family: 微軟正黑體;
		}
	</style>
</head>
<body>


<section style="height: 50vh;">
	    <div style="background-image: url(); background-attachment: fixed; background-size: cover; width: 100%; height: 100vh; position: relative;"  >
	    <div class="baslik">
	        <b style="font-size: 101px; text-align: center; margin-bottom: -21px; display: block;">LOGO</b>
	        <span style="font-size: 26px; text-align: center; display: block; margin-bottom: 25px;">Hello!World</span>
	    </div>
	    <section>
	    <form method="POST" action="<c:url value='login.do' />">
	        <div class="arkalogin">
	            <div class="loginbaslik">使用者登入</div>
	            <Font color='red' size="-3">${ErrorMsgKey.LoginError}</Font>
	            <hr style="border: 1px solid #ccc;">
	            <input class="giris" type="text" name="UA_Acu" placeholder="User" value="${requestScope.user}">
	            &nbsp;<small><Font color='red' size="-3">${ErrorMsgKey.AccountEmptyError}
             	</Font></small><br>
	            <input class="giris" type="password" name="UA_Psw" placeholder="*****" value="${requestScope.password}">
	            &nbsp;<small><Font color='red'  size="-3">${ErrorMsgKey.PasswordEmptyError}
             	</Font></small><br>
	            <input type="checkbox" name="rememberMe" 
					<c:if test="${requestScope.rememberMe==true}">
					checked='checked'
					</c:if>
               value="true">
               <span>記住帳密</span>
	            <input type="submit" name="submit" value="Login">
	        </div>
	    </form>
	    </section><br>
	    <a href="register/register.jsp" class="register">帳號註冊</a>
	    <span style="font-size: 24px; text-align: center; display: block; color: #fff; font-weight: bold; margin-bottom: 34px;
	    ">登入頁面</span>
	    <span style="font-size: 17px; text-align: center; display: block; color: #fff;
	    ">www.12345678.com</span>
	    </div>
	    </section>


</body>
</html>