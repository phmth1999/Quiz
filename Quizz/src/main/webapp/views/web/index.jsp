<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Trang chủ</title>
<style><%@include file="/views/web/style.css" %></style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/web/layouts/header.jsp" %>
		<section class="home">
        	<div class="container">
            	<div class="row">
                	<div class="about-item">
                    	<h2>Có rất nhiều cái để hỏi...</h2>
                    	<p>và Cách dễ nhất là thử làm cái bài quizz...</p>
                    	<a href="./quizz" class="btn">Bắt đầu ngay. Hoàn toàn miễn phí.</a>
                	</div>
                	<div class="about-item">                 
                    	<div class="about-item-img">
                        	<img src="./views/web/images/logo.jpg" alt="">
                    	</div> 
                	</div>
            	</div>
        	</div>
		</section>
		<%@include file="/views/web/layouts/footer.jsp" %>
	</div>
</body>
</html>