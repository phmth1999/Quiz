<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>manage User</title>
<style><%@include file="/views/admin/style.css" %></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"/>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/admin/layouts/header.jsp"%>
		<%@include file="/views/admin/layouts/menu.jsp"%>
		<section class="list-user">
			<table>
				<tr>
					<th>STT</th>
					<th>UserName</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Role</th>
					<th>Action</th>
				</tr>
				<tr>
					<td>1</td>
					<td>A</td>
					<td>Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</td>
					<td>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</td>
					<td>a</td>
					<td>a</td>
					<td>a</td>
					<td>a</td>
					<td>
						<i class="fa-solid fa-lock"></i>
						<i class="fa-solid fa-lock-open"></i>
					</td>
				</tr>
				<tr>
					<td>1</td>
					<td>A</td>
					<td>Aaaaaaaaaaa</td>
					<td>aaaaaaaaaaaa</td>
					<td>a</td>
					<td>a</td>
					<td>a</td>
					<td>a</td>
					<td>
						<i class="fa-solid fa-lock"></i>
						<i class="fa-solid fa-lock-open"></i>
					</td>
				</tr>
			</table>
		</section>
		<%@include file="/views/admin/layouts/footer.jsp"%>
	</div>
</body>
</html>