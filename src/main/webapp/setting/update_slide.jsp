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
<form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='updateslide.do' />"  id="updateslide.do" >
      <font color="red" size="-1">${MsgMap.errorIDDup}</font> 
      <font color="red" size="-1">${MsgMap.errTitle}</font> 
      <font color="red" size="-1">${MsgMap.errorslide}</font> 
            
      <label class="fontSize" >輪播圖1：</label>
      <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="Slide_1"><BR>
      <img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=1&type=company" alt="輪播圖1" height='120px' width='180px'>
      <font color="red" size="-1">${MsgMap.errPicture}</font>
      <br/>
      <label class="fontSize" >輪播圖2：</label>
      <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="Slide_2"><BR>
      <img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=2&type=company" alt="輪播圖2" height='120px' width='180px'>
      <font color="red" size="-1">${MsgMap.errPicture}</font>
      <br/>
      <label class="fontSize" >輪播圖3：</label>
      <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="Slide_3"><BR>
      <img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=3&type=company" alt="輪播圖3" height='120px' width='180px'>
      <font color="red" size="-1">${MsgMap.errPicture}</font>
      <br/>
      <label class="fontSize" >輪播圖4：</label>
      <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="Slide_4"><BR>
      <img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=4&type=company" alt="輪播圖4" height='120px' width='180px'>
      <font color="red" size="-1">${MsgMap.errPicture}</font>
      <br/>
      <label class="fontSize" >輪播圖5：</label>
      <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="Slide_5"><BR>
      <img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=5&type=company" alt="輪播圖5" height='120px' width='180px'>
      <font color="red" size="-1">${MsgMap.errPicture}</font>
      <br/>
       
<!--       <input type="text"  name="UA_PL" value="0" class="fieldWidth" style="display:none"> -->
	  <br>      
     
         <input type="submit" name="submit" value="儲存"/>
         
    
      <br/>
</form>
</body>
</html>