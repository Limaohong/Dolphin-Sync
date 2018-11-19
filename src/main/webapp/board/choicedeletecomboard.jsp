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
		var bB_Id = document.querySelector(".classroom").value;
		if (confirm("確定選擇刪除此公司布告欄 ? ") ) {
			document.myform.action="<c:url value='choicedeletecom.do?bB_Id=" + bB_Id +"' />" ;
			document.myform.method="POST";
			document.myform.submit();
		}
	}
</script>
</head>
<body>
<Form action="<c:url value='choicedeletecom.do' />" method="POST" name="myform">
 <select class="classroom">      
      	<c:forEach var="Demoboardlist" items="${Demoboard}">
      		<c:if test="${Demoboardlist.BB_CrId == null}">      		
	      		<option  value="${Demoboardlist.BB_Id}">${Demoboardlist.BB_SN}</option>
      		</c:if>
      	</c:forEach>
  </select>	
  <font color="red" size="-1">${errorMsg.errorchoice}</font>
  <input type="submit" name="submit" value="確認送出" onclick="choiceclass();return false">
</form>
</body>
</html>