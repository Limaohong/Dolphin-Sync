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
		if (confirm("確定選擇刪除此公司布告欄 ? ") ) {
			document.myform.action="<c:url value='choicedeleteclass.do?cmd=update&cr_Id=" + cr_Id +"' />" ;
			document.myform.method="POST";
			document.myform.submit();
		}
	}
</script>
</head>
<body>
<Form action="<c:url value='choicedeleteclass.do' />" method="POST" name="myform">
 <select class="classroom">      
      	<c:forEach var="Demoboardlist" items="${Demoboard}">
      		<c:if test="${empty Demoboardlist.bB_CrId}">
	      		<option  value="${Demoboardlist.bB_Id}">${Demoboardlist.bB_SN}</option>		
      		</c:if>
      	</c:forEach>
  </select>	
  <font color="red" size="-1">${errorMsg.errorchoice}</font>
  <input type="submit" name="submit" value="確認送出" onclick="choiceclass();return false">
</form>
</body>
</html>