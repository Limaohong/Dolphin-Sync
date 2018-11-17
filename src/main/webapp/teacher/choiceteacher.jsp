<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function choiceclass(){
		var TP_Id = document.querySelector(".teacher").value;
		if (confirm("確定選擇此老師 ? ") ) {
			document.form2.action="<c:url value='choiceteacher.do?cmd=update&TP_Id=" + TP_Id +" ' />" ;
			document.form2.method="POST";
			document.form2.submit();
		}
	}
</script>
</head>
<body>
<Form action="<c:url value='choiceteacher.do' />" method="POST" name="form2">
 <select name="TP_Id" class="teacher">      
      	<c:forEach var="teacher" items="${DemoTeacher}">
      		<option  value="${teacher.TP_Id}">${teacher.TP_Name}</option>
      	</c:forEach>
  </select>	
  <input type="submit" name="submit" value="確認送出" onclick="choiceteacher();return false">
</form>
</body>
</html>