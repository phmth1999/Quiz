<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quizzes</title>
<style><%@include file="/views/web/style.css" %></style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/web/layouts/header.jsp" %>
		<section class="quizz">
			<div class="container">
			 	<h1>danh sach</h1>
            	<div class="list-quizz">
            		<c:forEach var="item" items="${listQuizz }">
                		<div class="quizz-item">
                    		<div class="img">
                       			<img alt="" src="./views/web/images/${item.image }">
                        		<span><a href="./quizz-detail?quizzId=${item.id }">Lam ngay</a></span>
                    		</div>
                    		<p>${item.name }</p>
                    		<p>${item.description }</p>
                		</div>
                	</c:forEach>
           		</div>
        	</div>
		</section>
		<%@include file="/views/web/layouts/footer.jsp" %>
	</div>
</body>
</html>