<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/css/bootstrap.min.css">
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
<form method="POST" action="<c:url value='insertstudent.do' />"  id="insertstudent.do" >
     
      
      <label class="fontSize" >學生姓名：</label>
      <input type="text" name="S_Name" value="${param.S_Name}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorName}</font>
      <br/>      
     
      <label class="fontSize" >家長姓名：</label>
      <input type="text" name="UA_Name" value="${param.UA_Name}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorName}</font>
      <br/> 
      
      <label class="fontSize" >家長手機：</label>
      <input type="text"  name="S_Phone" value="${param.S_Phone}" class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorTel}</font>
      <br/>           
      
       
      <input type="text"  name="Cr_Id" value="${Cr_Id}" class="fieldWidth" style="display:none">
	  <br>      
      <div id="btnArea" align="center">
         <input type="submit" name="submit" id="submit" value="儲存"/>
         <input type="reset" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
<script type="text/javascript">

// 	function doFirst() {
// 		var ok = "${msgOK.insertOk}"
// 		if (ok.length != 0) {
// 			alert("學員成功新增");
// 		}
// 	}
// 	window.addEventListener('load', doFirst);
</script>
<script src="${pageContext.request.contextPath}/resource/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
</body>
</html>