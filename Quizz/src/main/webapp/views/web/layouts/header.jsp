<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<header class="header">
	<div class="logo">
		<a href="${pageContext.request.contextPath}/trang-chu"><img alt="" src="./views/web/images/logo.jpg"></a>
	</div>
	<div class="nav">
		<ul>
			<li><a class="active" href="${pageContext.request.contextPath}/trang-chu">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/quizz">Quizz</a></li>
			<li><a href="#contact">Contact</a></li>
			<li><a href="#about">About</a></li>
		</ul>
	</div>
	<div class="login-logout">
		<c:if test="${userLogin == null }">
			<a href="${pageContext.request.contextPath}/dang-nhap">Login</a>
			<p>|</p>
			<a>Register</a>
		</c:if>
		<c:if test="${userLogin != null }">
			<a href="#">${userLogin.firstName } ${userLogin.lastName }</a>
			<p>|</p>
			<a href="${pageContext.request.contextPath}/dang-xuat">Logout</a>
		</c:if>
	</div>
</header>