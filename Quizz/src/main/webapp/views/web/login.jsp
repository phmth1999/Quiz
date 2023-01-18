<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Đăng nhập</title>
<style><%@include file="/views/web/style.css" %></style>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/web/layouts/header.jsp"%>
		<section class="login">
			<div class="container">
				<div class="row justify-center">
					<form onsubmit="return submitLogin()" class="form-login" action="${pageContext.request.contextPath}/dang-nhap" method="post">
						<h2>Đăng nhập</h2>
						<div class="group-input">
							<label for="username">Tên đăng nhập *:</label> 
							<input onblur="checkUsername()" value="${userName }" id="username" name="username" type="text" placeholder="Tên đăng nhập" maxlength="16"/>
						</div>
						<div class="group-input">
							<label for="password">Mật khẩu *:</label> 
							<input onblur="checkPassword()" value="${password }" id="password" name="password" type="password" placeholder="Nhập mật khẩu" maxlength="16"/>
						</div>
						<div class="group-input">
							<p class="errMessage">${errMessage }</p>
						</div>
						<div class="btn">
							<button type="submit">Đăng nhập</button>
						</div>
						<div class="link">
							<a href="/dang-ki">Đăng kí</a>
							<p>|</p>
							<a href="/quen-mat-khau">Quên mật khẩu</a>
						</div>
					</form>
				</div>
			</div>
		</section>
		<%@include file="/views/web/layouts/footer.jsp"%>
	</div>
	<script>
		let userName = document.getElementById("username");
		let password = document.getElementById("password");
		let errMessage = document.querySelector(".errMessage");
		function checkUsername(){
			let valid = true;
			if(userName.value === "" || userName.value === null){
				errMessage.innerHTML = "Username not empty!";
				valid = false;
			}else{
				errMessage.innerHTML = "";
			}
			
			return valid;
		}
		function checkPassword(){
			let valid = true;
			if(password.value === "" || password.value === null){
				errMessage.innerHTML = "Password not empty!";
				valid = false;
			}else{
				errMessage.innerHTML = "";
			}
			
			return valid;
		}
		function submitLogin(){
			return checkUsername() && checkPassword();
		}
	</script>
</body>
</html>