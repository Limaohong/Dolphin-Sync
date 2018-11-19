<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>${LoginCom.c_CN}</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/WOW-master/css/libs/animate.css">

<link href="https://fonts.googleapis.com/css?family=Indie+Flower|Open+Sans|Varela+Round" rel="stylesheet">
</head>

<body>
	<!-- 導航欄 -->
	<nav class="navbar navbar-expand-lg navbar-light"
		style="background-color: #e3f2fd;">
		<a class="navbar-brand" href="#"> 
			<img src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=0&type=company" width="30" height="30"
			class="d-inline-block align-top" alt=""> 
			<c:if test="${! empty LoginCom}">
				${LoginCom.c_CN}
            </c:if>                          
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end"
			id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="#">首頁</a> 
				<a class="nav-item nav-link" href="#">師資介紹</a> 
				<a class="nav-item nav-link" href="#">佈告欄</a> 
				<a class="nav-item nav-link" href="#">成績單</a> 
				<a class="nav-item nav-link" href="#">聯絡我們</a>
					<c:if test="${! empty LoginOK}">
						<img height='40px' width='30px'
						src='${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&type=UserAccount'>
						<a class="nav-item nav-link" href="#">${LoginOK.UA_Name}你好</a>					
					</c:if>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#setting">
					設定
					</button>
				
					<c:if test="${! empty LoginOK}">
					<a class="nav-item nav-link" href="login/logout.jsp"><span class="glyphicon glyphicon-log-in"></span> Logout</a>
					</c:if>					
			</div>
		</div>
	</nav>
	
	
	
	<!-- Modal設定欄位 -->
	<div class="modal fade" id="setting" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalCenterTitle">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	 <c:choose>
				<c:when test="${LoginOK.UA_PL == 0}">
					<a class="nav-item nav-link" href="setting/update_boss.jsp">老闆基本資料修改</a>	
					<a class="nav-item nav-link" href="teacher/teacherpresentinsert.jsp">師資新增</a>	
					<a class="nav-item nav-link" href="teacher/choiceteacherdelete.jsp">師資刪除</a>	
					<a class="nav-item nav-link" href="teacher/choiceteacher.jsp">師資修改</a>
					<a class="nav-item nav-link" href="board/insertboard.jsp">公司佈告欄新增</a>	
					<a class="nav-item nav-link" href="board/choicedeletecomboard.jsp">公司佈告欄刪除</a>	
					<a class="nav-item nav-link" href="board/updateboard.jsp">公司佈告欄修改</a>	
					<a class="nav-item nav-link" href="setting/update_slide.jsp">輪播圖修改</a>	
				</c:when>
				<c:when test="${LoginOK.UA_PL == 1}">
					<a class="nav-item nav-link" href="setting/update_teacher.jsp">老師基本資料修改</a>	
					<a class="nav-item nav-link" href="board/choiceinsertclass.jsp">班級佈告欄新增</a>	
					<a class="nav-item nav-link" href="board/choicedeleteclass.jsp">班級佈告欄刪除</a>	
					<a class="nav-item nav-link" href="board/choiceclass.jsp">班級佈告欄修改</a>	
					<a class="nav-item nav-link" href="classroom/classinsert.jsp">教室新增</a>	
					<a class="nav-item nav-link" href="classroom/choiceclassroom.jsp">教室名稱修改</a>	
					<a class="nav-item nav-link" href="classroom/choiceclassroomdelete.jsp">教室刪除</a>	
					<a class="nav-item nav-link" href="studentclass/choiceclassroom.jsp">教室狀況查詢</a>	
					<a class="nav-item nav-link" href="student/choiceclassroom.jsp">班級學員新增</a>	
				</c:when>
			</c:choose>	      	
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        
	      </div>
	    </div>
	  </div>
	</div>

	<!-- 輪播欄 -->
	<div class="container">
		<div id="carouselExampleControls" class="carousel slide bottom"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=1&type=company"
						alt="First slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=2&type=company"
						alt="Second slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=3&type=company"
						alt="Third slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=4&type=company"
						alt="Third slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&order=5&type=company"
						alt="Third slide">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>


<div style="height: 100px;width: 100px"></div>

	<div style="display: flex;flex-direction: column;align-items: center;">
	      <img class="logo" src="./IMAGES/dolphinlogo128.png" height="100" width="100">
	      <h2 class="slogan">全心全意的照顧</h2>
	      <h3 class="slogan">全心全意的照顧</h3>
	      <h4 class="slogan">全心全意的照顧</h4>
	</div>
	
<!-- 師資欄 -->	
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseAllOne" role="button" aria-expanded="false" aria-controls="collapseExample">
    	師資欄
    </a>
	<div class="card-deck collapse" id="collapseAllOne" aria-expanded="false">
	 <c:forEach var="teacher" items="${DemoTeacher}">
	  <div class="card">  
	    <img class="card-img-top" src="${pageContext.request.contextPath}/init/getImage?UA_Acu=${LoginOK.UA_Acu}&TP_Id=${teacher.TP_Id}&type=teacher" alt="Card image cap"> <!-- card image -->
	    
	    <div class="card-body">  <!-- card content -->
	      <h5 class="card-title">${teacher.TP_Name}</h5>
	      <p class="card-text">${teacher.TP_TI}</p>
	    </div>
	  </div>
	 </c:forEach>
	</div>
<!-- 師資欄 end-->

		<!-- 公佈欄 -->		
		<nav>
				<div class="nav nav-tabs" id="nav-tab" role="tablist">
					<c:forEach var="board" items="${Demoboard}">
						<c:choose>
							<c:when test="${board.BB_CrId!=null}">
								<a class="nav-item nav-link" id="nav-class${board.BB_Id}-tab" data-toggle="tab" 
									href="#nav-class${board.BB_Id}" role="tab" aria-controls="nav-class${board.BB_Id}" 
									aria-selected="false">${board.BB_SN}</a>	 								
							</c:when>
							<c:otherwise>
								<a class="nav-item nav-link active" id="nav-class${board.BB_Id}-tab"
									data-toggle="tab" href="#nav-class${board.BB_Id}" role="tab"
									aria-controls="nav-class${board.BB_Id}" aria-selected="true">${board.BB_SN}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>			
		</nav>
		
		<div class="tab-content" id="nav-tabContent">
			<c:forEach varStatus="stVar"  var="board"  items="${Demoboard}" >
				<c:choose>
					<c:when test="${board.BB_CrId==null}">
						<div class="tab-pane fade show active" id="nav-class${board.BB_Id}" role="tabpanel"
							aria-labelledby="#nav-class${board.BB_Id}-tab">
							${board.BB_T1}<hr>
							${board.BB_B1}<hr>
							${board.BB_B2}<hr>
							${board.BB_B3}<hr>
							${board.BB_B4}<hr>
							${board.BB_B5}<hr>
					
						</div>
					</c:when>
					<c:otherwise>
						<div class="tab-pane fade" id="nav-class${board.BB_Id}" role="tabpanel"
							aria-labelledby="#nav-class${board.BB_Id}-tab">
							${board.BB_T1}<hr>
							${board.BB_B1}<hr>
							${board.BB_B2}<hr>
							${board.BB_B3}<hr>
							${board.BB_B4}<hr>
							${board.BB_B5}<hr>
						</div>
					</c:otherwise>			
				</c:choose>		
			</c:forEach>
		</div>
		
		<!-- 成績單 -->
		<div style="background-color: red;">成績單</div>
		<!-- 聯絡我們 -->
		<form class="needs-validation contack-bgcolor" novalidate>
			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label for="validationTooltip01">請輸入姓名</label> <input type="text"
						class="form-control" id="validationTooltip01" placeholder="王小明"
						value="" required>
					<div class="valid-tooltip">Looks good!</div>
				</div>
				<div class="col-md-4 mb-3">
					<label for="validationTooltip02">Last name</label> <input
						type="text" class="form-control" id="validationTooltip02"
						placeholder="Last name" value="Otto" required>
					<div class="valid-tooltip">Looks good!</div>
				</div>
				<div class="col-md-4 mb-3">
					<label for="validationTooltipUsername">Username</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"
								id="validationTooltipUsernamePrepend">@</span>
						</div>
						<input type="text" class="form-control"
							id="validationTooltipUsername" placeholder="Username"
							aria-describedby="validationTooltipUsernamePrepend" required>
						<div class="invalid-tooltip">Please choose a unique and
							valid username.</div>
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-6 mb-3">
					<label for="validationTooltip03">City</label> <input type="text"
						class="form-control" id="validationTooltip03" placeholder="City"
						required>
					<div class="invalid-tooltip">Please provide a valid city.</div>
				</div>
				<div class="col-md-3 mb-3">
					<label for="validationTooltip04">State</label> <input type="text"
						class="form-control" id="validationTooltip04" placeholder="State"
						required>
					<div class="invalid-tooltip">Please provide a valid state.</div>
				</div>
				<div class="col-md-3 mb-3">
					<label for="validationTooltip05">Zip</label> <input type="text"
						class="form-control" id="validationTooltip05" placeholder="Zip"
						required>
					<div class="invalid-tooltip">Please provide a valid zip.</div>
				</div>
			</div>
			<button class="btn btn-primary" type="submit">Submit form</button>
		</form>
	</div>
	
	<script src="${pageContext.request.contextPath}/resource/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/bootstrap-4.1.1-dist/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/WOW-master/dist/wow.min.js"></script>
	
	
</body>

</html>