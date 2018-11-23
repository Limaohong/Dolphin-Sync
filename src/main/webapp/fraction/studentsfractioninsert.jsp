<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/css/bootstrap.min.css">

</head>
<body>
<Form action="<c:url value='insertfractionplural.do' />" method="POST" name="myform" class="was-validated">
<h2>請選擇要新增的學員班級</h2>
 
      	
  
  <label class="fontSize" >考試項目名稱：</label>
      <input type="text"  name="F_Exam" value="${param.F_Exam}" class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorTel}</font>
   <br/>
  <label class="fontSize" >考試科目：</label>
      <select name="subject" class="subject">     	
      	<option  value="${subjectall.subject1}">${subjectall.subject1}</option>      	
      	<option  value="${subjectall.subject2}">${subjectall.subject2}</option>      	
      	<option  value="${subjectall.subject3}">${subjectall.subject3}</option>      	
  	  </select>
   <br/>
  

   <br/>
   <c:forEach var="list" items="${studentBeanlist}" varStatus="status">
       <input type="text" name="S_Id${status.index}" value="${list.s_Id}" class="fieldWidth" style="display: none">
       <input type="text" name="S_Name${status.index}" value="${list.s_Name}" class="fieldWidth" readonly="readonly">
      考試成績：<input type="text"  name="F_Fraction${status.index}" value="${param.F_Fraction}" class="fieldWidth" style="width: 120px;">
   <hr>
   </c:forEach>
   
  <input type="submit" name="submit" value="確認送出">
</form>



<script src="${pageContext.request.contextPath}/resource/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function choiceclass(){
		var cr_Id = document.querySelector(".subject").value;
		if (confirm("確定送出 ? ") ) {
			document.myform.action="<c:url value='insertfractionplural.do?subject="+ subject "' />" ;
			document.myform.method="POST";
			document.myform.submit();
		}
	}
</script>
</body>
</html>