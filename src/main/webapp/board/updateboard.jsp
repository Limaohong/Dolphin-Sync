<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='boardupdate.do' />"  id="boardupdate.do" >
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <br>

      <input class="giris" type="text" name="BB_T1" placeholder="標題一" value="${singleboard.BB_T1}">
      <textarea cols="50" rows="5" name="BB_B1">${singleboard.BB_B1}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_F1"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <br>
      <input class="giris" type="text" name="BB_T2" placeholder="標題二" value="${singleboard.BB_T2}">
      <textarea cols="50" rows="5" name="BB_B2">${singleboard.BB_B2}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_F2"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <br>
	  <input class="giris" type="text" name="BB_T3" placeholder="標題三" value="${singleboard.BB_T3}">
      <textarea cols="50" rows="5" name="BB_B3">${singleboard.BB_B3}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_F3"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>　	  
	  <br>
	  <input class="giris" type="text" name="BB_T4" placeholder="標題四" value="${singleboard.BB_T4}">
      <textarea cols="50" rows="5" name="BB_B4">${singleboard.BB_B4}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_F4"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <br>
	  <input class="giris" type="text" name="BB_T5" placeholder="標題五" value="${singleboard.BB_T5}">
      <textarea cols="50" rows="5" name="BB_B5">${singleboard.BB_B5}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_F5"><BR>
      <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
      <br>
      <input type="submit" name="submit" value="確認送出">
      <input type="text"  name="BB_CrId" value="${singleboard.BB_CrId}" class="fieldWidth" style="display:none">
</form>

</body>
</html>