<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
<!--
body {
	background-attachment: fixed;
	background-color: #EBFFEB;
	background-repeat: no-repeat;
	background-position: 20px 50px;
}
.myBorder {
	color:#FFFF99;
	border: thin dotted #FFFFFF;
}
h1 {
	font-family: "標楷體", "新細明體", sans-serif;
	font-size: 24px;
}
.formBkgnd {
	color: #FFFFFF;
	background-color: #666666;
}
label {
	float:left;
	width:8em;
	font-weight:bold;
	color:#000000;
	margin-top:10px;
	margin-bottom:2px;
	margin-right:10px;
	text-align: right;
}

br {
	clear:both;
}
.fieldWidth {
    margin-top:10px;
	margin-bottom: 2px;
	width: 200px;
	background:#F6E497;
	font-size:1.1em;
}
/* 設定字體大小 */
.fontSize {
	font-size:1.1em;
}

#main {
    position:relative;
	left:70px;
	width:600px;
	height:543px;	
	top: 0px;
	z-index:2;
	font-size:0.9em; 
}
/* 主要內容的區塊 */
#content {
  width: 700px ;
  margin-left: auto ;
  margin-right: auto ;
}
/* 設定傳送鈕的樣式 */
#submit {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#FFFFFF;
	margin-right:1.5em;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#A9A9A9;
}
/* 設定取消鈕的樣式 */
#cancel {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#ffffff;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#a9a9a9;
}

#errorMsg {
    position:relative;
    top:0px; 
    left:0px;    
	color:#FF0000;
	font-size:0.8em;
}
-->
</style>
</head>
<body>
<form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='useraccount.do' />"  id="useraccount.do" >
      <label class="fontSize" >帳號：</label>
      <input type="text" name="UA_Acu" value="${param.UA_Acu}" class="fieldWidth" style="width: 180px;">
      <!-- 
         注意value屬性的內容以及顯示錯誤訊息的寫法
      -->
      <font size="-1" color="#FF0000">${MsgMap.errorIDEmpty}${MsgMap.errorIDDup}</font>
      <br/>
      <label class="fontSize" >密碼：</label>
      <input type="password" name="UA_Psw" value="${param.UA_Psw}" class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPasswordEmpty}${MsgMap.passwordError}</font>      
      <br/>
      
      <label class="fontSize" >密碼確認：</label>
      <input type="password" name="UA_Psw2" value="${param.UA_Psw2}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPassword2Empty}${MsgMap.passwordError}</font>            
      <br/>
      
      <label class="fontSize" >老闆驗證碼：</label>
      <input type="text" name="UA_VC" value="${param.UA_VC}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorBossVC}</font>            
      <br/>
      
      <label class="fontSize" >姓名：</label>
      <input type="text" name="UA_Name" value="${param.UA_Name}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorName}</font>
      <br/>      
     
      
      <label class="fontSize" >手機電話：</label>
      <input type="text"  name="UA_Phone" value="${param.UA_Phone}" class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorTel}</font>
      <br/>     
      
      
      <label class="fontSize" >個人大頭貼：</label>
      <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="UA_Avater"><BR>
      <font color="red" size="-1">${MsgMap.errPicture}</font>
      <br/>
      <input type="text"  name="UA_PL" value="1" class="fieldWidth" style="display:none">
	  <br>      
      <div id="btnArea" align="center">
         <input type="submit" name="submit" id="submit" value="儲存"/>
         <input type="reset" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
</body>
</html>