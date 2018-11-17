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
	<form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='boardinsert.do' />"  id="boardinsert.do" >
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <input type="text" name="Cr_Id" value="${classroom.cr_Id}" class="fieldWidth" style="display: none;" >
 	  <c:if test="${ empty classroom}">
		公司名稱:<input type="text" name="C_CN" value="${LoginCom.c_CN}" class="fieldWidth"  readonly="readonly">
      </c:if> 
 	  <c:if test="${! empty classroom}">
		教室名稱:<input type="text" name="Cr_Name" value="${classroom.cr_Name}" class="fieldWidth"  readonly="readonly">
      </c:if>
 	  
	  <br>

      <input class="giris" type="text" name="BB_T1" placeholder="標題一" value="${param.BB_T1}">
      <textarea cols="50" rows="5" name="BB_B1">${param.BB_B1}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_S1"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <br>
      <input class="giris" type="text" name="BB_T2" placeholder="標題二" value="${param.BB_T2}">
      <textarea cols="50" rows="5" name="BB_B2">${param.BB_B2}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_S2"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <br>
	  <input class="giris" type="text" name="BB_T3" placeholder="標題三" value="${param.BB_T3}">
      <textarea cols="50" rows="5" name="BB_B3">${param.BB_B3}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_S3"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
　	  <br>
	  <input class="giris" type="text" name="BB_T4" placeholder="標題四" value="${param.BB_T4}">
      <textarea cols="50" rows="5" name="BB_B4">${param.BB_B4}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_S4"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
	  <br>
	  <input class="giris" type="text" name="BB_T5" placeholder="標題五" value="${param.BB_T5}">
      <textarea cols="50" rows="5" name="BB_B5">${param.BB_B5}</textarea>
	  <Input type="file" size="40" class="InputClass" style="width: 480px;"  name="BB_S5"><BR>
	  <p style="color: red;">${ErrorMsgKey.errorInsertEmpty}</p>
      <br>
      <input type="submit" name="submit" value="確認送出">
      
</form>

</body>
</html>