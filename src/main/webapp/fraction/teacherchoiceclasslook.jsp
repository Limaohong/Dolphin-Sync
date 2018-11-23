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
<script src="${pageContext.request.contextPath}/resource/jquery-3.3.1.min.js"></script>	
<script src="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>

</head>
<body>
<Form action="<c:url value='teacherchoiceclasslook.do' />" method="POST" name="myform" class="was-validated">
<h2>請選擇要查看的學員班級</h2>
 <select name="Cr_Id" class="classroom">
 		<option  value="0">--請選擇--</option>      
      	<c:forEach var="classroom" items="${loadclassroom}">
      		<option  value="${classroom.cr_Id}">${classroom.cr_Name}</option>
      	</c:forEach>
 </select>	
  <select name="F_Exam" class="F_Exam">
  	<option  value="0">--請選擇--</option>
  </select>
  <input type="submit" name="submit" value="確認送出" onclick="choiceclass();return false">
</form>



<script type="text/javascript">	
	function choiceclass(){
		var cr_Id = document.querySelector(".classroom").value;
		var F_exam = document.querySelector(".F_Exam").value;
		if (confirm("確定選擇此班級 ? ") ) {
			document.myform.action="<c:url value='teacherchoiceclasslook.do?cr_Id=" + cr_Id +"&F_exam="+F_exam+"' />" ;
			document.myform.method="POST";
			document.myform.submit();
		}
	}
	$(".classroom").change(function(){
		var cr_Id = document.querySelector(".classroom").value;
		if(cr_Id == 0){
			console.log("none");
		}else{			
		$.ajax({ 
		      type:"POST",    //請求類型
		      url: "loadfractionexam.do", 
		      dataType:"json",//服務器返回的數據類型		      
		      data:{             //這裏需要注意的是，這個data是做為參數傳值到後台的，因為是post,所以可以定義多個
		          "cr_Id" : cr_Id		        
		         },
		     success:function(data){      //回調結果，如果成功
				for(var i=0;i<data.length;i++){
					$(".F_Exam").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
                     console.log(i);
                 }
 			     
		         
		    }
		    
		   });//$.ajax
		}
    })
	
</script>
</body>
</html>