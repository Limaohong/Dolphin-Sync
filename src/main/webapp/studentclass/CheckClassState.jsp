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
	<div class="row">
		<div class="col-3">
			<div class="nav flex-column nav-pills" id="v-pills-tab"
				role="tablist" aria-orientation="vertical">
				<a class="nav-link active" id="v-pills-home-tab" data-toggle="pill"
					href="#v-pills-home" role="tab" aria-controls="v-pills-home"
					aria-selected="true">基本資料</a> <a class="nav-link"
					id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile"
					role="tab" aria-controls="v-pills-profile" aria-selected="false">詳細資料</a>

			</div>
		</div>
		<div class="col-9">
			<div class="tab-content" id="v-pills-tabContent">
				<div class="tab-pane fade show active" id="v-pills-home"
					role="tabpanel" aria-labelledby="v-pills-home-tab">
					<table border=1 width=300px cellpadding=3 cellspacing=3>
						<tr>
							<td>班級</td>
							<td>人數</td>
						<tr>
						<tr>
							<td>${roomstate.nameofclass}</td>
							<td>${roomstate.numofstudent}</td>
						<tr>
					</table>
				</div>
				<div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
					aria-labelledby="v-pills-profile-tab">
					<table border=1 width=300px cellpadding=3 cellspacing=3>
						<tr>
							<td>學生姓名</td>
							<c:forEach var="studentlist" items="${studentBeanlist}">
								<td>${studentlist.s_Name}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>家長電話</td>
							<c:forEach var="studentlist" items="${studentBeanlist}">
								<td>${studentlist.s_Phone.UA_Phone}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>家長姓名</td>
							<c:forEach var="ParentsName" items="${ParentsNameList}">
								<td>${ParentsName.UA_Name}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>家長驗證碼</td>
							<c:forEach var="ParentsName" items="${ParentsNameList}">
								<td>${ParentsName.UA_VC}</td>
							</c:forEach>
						</tr>




					</table>
				</div>

			</div>
		</div>
	</div>
































	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
</body>
</html>