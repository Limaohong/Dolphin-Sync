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
<script type="text/javascript">
	radios = document.getElementsByName("radio-stacked");
	radiosValue = null;
	function choiceclass(){
		var cr_Id = document.querySelector(".classroom").value;
		if (confirm("確定選擇此班級 ? ") ) {
			document.myform.action="<c:url value='choiceinsertstudentclass.do?cr_Id=" + cr_Id +"&radiosValue="+radiosValue+"' />" ;
			document.myform.method="POST";
			document.myform.submit();
		}
	}
	function getValue(){
        
        for(var i= 0;i<radios.length;i++){
            if(radios[i].checked){
                console.log(radios[i].value);
                radiosValue = radios[i].value;
                break;
            }
        }
    }
</script>
</head>
<body>
<Form action="<c:url value='choiceinsertstudentclass.do' />" method="POST" name="myform" class="was-validated">
<h2>請選擇要新增的學員班級</h2>
 <select name="Cr_Id" class="classroom">      
      	<c:forEach var="classroom" items="${loadclassroom}">
      		<option  value="${classroom.cr_Id}">${classroom.cr_Name}</option>
      	</c:forEach>
  </select>	
   <div class="custom-control custom-radio">
        <input type="radio" class="custom-control-input" id="single" name="radio-stacked" required value="single" onclick="getValue()">        
        <label class="custom-control-label" for="single">單一學員新增</label>
    </div>
    <div class="custom-control custom-radio mb-3">
        <input type="radio" class="custom-control-input" id="plural" name="radio-stacked" required value="plural "onclick="getValue()">        
        <label class="custom-control-label" for="plural">多位學員批次新增</label>
        <div class="invalid-feedback">請擇一選擇</div>
    </div>
  <input type="submit" name="submit" value="確認送出" onclick="choiceclass();return false">
</form>



<script src="${pageContext.request.contextPath}/resource/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
</body>
</html>