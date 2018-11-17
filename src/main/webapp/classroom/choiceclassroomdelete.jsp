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
		var cr_Id = document.querySelector(".classroom").value;
		var cmd = "update";
		if (confirm("確定選擇此班級? (提醒:刪除班級將會連同班級布告欄一同刪除)") ) {
			document.myform.action="<c:url value='choiceclassroomdelete.do?cmd="+cmd+"&cr_Id=" + cr_Id +"' />" ;
			document.myform.method="POST";
			document.myform.submit();
		}
	}
</script>
</head>
<body>
<Form action="<c:url value='choiceclassroomdelete.do' />" method="POST" name="myform">
<h2>請選擇要修改的教室</h2>
 <select name="Cr_Id" class="classroom">      
      	<c:forEach var="classroom" items="${loadclassroom}">
      		<option  value="${classroom.cr_Id}">${classroom.cr_Name}</option>
      	</c:forEach>
  </select>	
  <input type="submit" name="submit" value="確認送出" onclick="choiceclass();return false">
</form>
</body>
</html>