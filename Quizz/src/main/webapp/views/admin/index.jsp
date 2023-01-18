<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin</title>
<style><%@include file="/views/admin/style.css" %></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"/>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/admin/layouts/header.jsp"%>
		<%@include file="/views/admin/layouts/menu.jsp"%>
		<section class="count row">
			<div class="row">
				<div>
					<p>User: ${countUser }</p>
				</div>
				<div>
					<p>Quizz: ${countQuizz }</p>
				</div>
				<div>
					<p>Question: ${countQuestion }</p>
				</div>
				<div>
					<p>Answer: ${countAnswer }</p>
				</div>
			</div>
		</section>
		<%@include file="/views/admin/layouts/footer.jsp"%>
	</div>
</body>
</html>