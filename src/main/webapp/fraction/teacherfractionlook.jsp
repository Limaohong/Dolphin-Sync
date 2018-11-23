<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <title>Document</title>

</head>
<body>
    <div style="display: flex;justify-content: center;font-size: 26px;font-family: '微軟正黑體'">
        <ul class="list-group"  style="width:900px;">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                <span class="badge badge-primary badge-pill flex-fill" style="width: 120px">日期</span>
                <span class="badge badge-primary badge-pill flex-fill" style="width: 120px">學員姓名</span>
	            <span class="badge badge-primary badge-pill flex-fill" style="width: 165px">考試類型</span>
	            <span class="badge badge-primary badge-pill flex-fill">科目</span>
	            <span class="badge badge-primary badge-pill flex-fill">分數</span>
            </li>
            <c:forEach var="fraction" items="${fractionDemo}">
	            <li class="list-group-item" style="display:flex;align-items: center;justify-content: center;text-align: center;">                    
	                <span class="badge badge-primary badge-pill d-flex justify-content-center flex-fill" style="width: 200px">${fraction.f_RD}</span>
	                <span class="badge badge-primary badge-pill d-flex justify-content-center flex-fill" style="width: 200px">${fraction.f_SId.s_Name}</span>
	                <span class="badge badge-primary badge-pill d-flex justify-content-center flex-fill" style="width: 250px;height: 30px;">${fraction.f_Exam}</span>
	                <span class="badge badge-primary badge-pill d-flex justify-content-center flex-fill" style="width: 150px">${fraction.f_Subject}</span>
	                <span class="badge badge-primary badge-pill d-flex justify-content-center flex-fill" style="width: 150px">${fraction.f_Fraction}</span>
	            </li>
           </c:forEach>
        </ul>
    </div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>