<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Manage Quizz</title>
<style><%@include file="/views/admin/style.css" %></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"/>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/admin/layouts/header.jsp"%>
		<%@include file="/views/admin/layouts/menu.jsp"%>
		<section class="menu">
			<div class="container">
				<div class="menu-title">
					<div class="menu-button active" data-title="#add">Manage Quizzes</div>
					<div class="menu-button" data-title="#edit">Update Q/A Quizzes</div>
					<div class="menu-button" data-title="#assign">Assign to User</div>
				</div>

				<div class="menu-item-content active" id="add">
					<form class="form-add" 
						  action="${pageContext.request.contextPath}/quan-tri/quizz" 
						  enctype="multipart/form-data" 
						  method="post">
						<p>Add new Quizz</p>
						<div class="form-group">
							<input type="text" name="name" id="name"> 
							<label>Name</label>
						</div>
						<div class="form-group">
							<input type="text" name="description">
							<label>Description</label>
						</div>
						<div class="form-group">
							<input class="file" type="file" name="image" value="D:\cv\PhamMinhThien.jpg"/>
							<label>Upload Image</label>
						</div>
						<input type="submit" value="Save" name="add" id="btn-save">
					</form>
					<p>List Quizzes</p>
					<div class="list-quizz">
					<table>
						<thead>
							<tr>
								<th>STT</th>
								<th>Name</th>
								<th>Description</th>
								<th>Image</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody class="quizzItems">
							<tr>
							</tr>
						</tbody>
					</table>
					<div class="pagination">
  						<a hidden="" class="btnPre" href="#">&laquo;</a>
  						<div class="btnPage">
  						</div>
  						<a class="btnNext" href="#">&raquo;</a>
					</div>
					</div>
				</div>
				<div class="menu-item-content" id="edit">
					<form class="form-add" action="${pageContext.request.contextPath}/quan-tri/quizz" method="post" enctype="multipart/form-data">
						<p>Edit new Quizz</p>
						<div class="form-group">
							<input type="text" name="id" id="id" value="${quizzModel.id }" readonly="readonly"/> 
							<label>Id</label>
						</div>
						<div class="form-group">
							<input type="text" name="name" id="name" value="${quizzModel.name }"/> 
							<label>Name</label>
						</div>
						<div class="form-group">
							<input type="text" name="description" value="${quizzModel.description }"/> 
							<label>Description</label>
						</div>
						<div class="form-group">
							<input class="file" type="file" name="image" value="D:\cv\PhamMinhThien.jpg"/>
							<label>Upload Image</label>
						</div>
						<input type="submit" value="Edit" name="edit" id="btn-save">
					</form>
				</div>
				<div class="menu-item-content" id="assign">
					<form class="form-add" action="">
						<div class="select">
							<div>
								<label>Select Quizz:</label>
								<select name="quizzId" id="quizzId" >
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</div>
							<div>
								<label>Select User:</label>
								<select name="userId" id="userId" >
									<option value="1">1</option>
									<option value="2">2</option>
								</select>
							</div>
						</div>
						<input type="submit" value="Assign" id="btn-save">
					</form>
				</div>
			</div>
			
		</section>
		<%@include file="/views/admin/layouts/footer.jsp"%>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		const menuTitle = document.querySelector(".menu-title");
		let valid = <%=request.getAttribute("edit") != "" && request.getAttribute("edit") != null ?true:false%>;
		if(valid){
			let currentPage = <%=request.getAttribute("currentPage")%>
			menuTitle.querySelector(".active").classList.remove("active");
			menuTitle.querySelectorAll('.menu-button')[1].classList.add("active");
			const menuItem = document.querySelector(".menu");
			menuItem.querySelector(".menu-item-content.active").classList.remove("active");
			menuItem.querySelector('#edit').classList.add("active");
		}
		menuTitle.addEventListener("click", function(x) {
			if (x.target.classList.contains("menu-button")) {
				const Target = x.target.getAttribute("data-title");
				console.log(Target)
				menuTitle.querySelector(".active").classList.remove("active");
				x.target.classList.add("active");

				const menuItem = document.querySelector(".menu");
				menuItem.querySelector(".menu-item-content.active").classList
						.remove("active");
				menuItem.querySelector(Target).classList.add("active");
			}
		});
		
			let path = location.pathname.split("/")[1];
			let data = {};
			
			$.ajax({
						url : "/" + path + "/api/quizz",
						async : false,
						type : "get",
						success : function(res) {
							data = res;
						}
			});
			let perPage = 1;
			let currentPage = 1;
			let start = 0;
			let end = perPage;
			const totalPage = Math.ceil(data.length / perPage);
			const btnNext = document.querySelector(".btnNext");
			const btnPre = document.querySelector(".btnPre");
			let validDelete = <%=request.getAttribute("delete") != "" && request.getAttribute("delete") != null ?true:false%>;
			if(validDelete){
				currentPage = <%=request.getAttribute("currentPage")%>
				start = (currentPage-1) * perPage;
				end = currentPage * perPage;
				renderListQuizz();
				
				showhidenBtnNextPre(currentPage,totalPage);
				
			}
			function renderListQuizz(){
				let html = ``;
				const content = data.map((item, index) => {
					if(index >= start && index < end){
						html += `
							<tr>
								<td>`+(index+1)+`</td>
								<td>`+item.name+`</td>
								<td>`+item.description+`</td>
								<td>`+item.image+`</td>
								<td>
									<a href="?quizzId=`+item.id+`"><i class="fa-solid fa-pen-to-square"></i></a>
									<a href="?deleteQuizzId=`+item.id+`&&currentPage=`+currentPage+`"><i class="fa-solid fa-trash"></i></a>
								</td>
							</tr>
						`;
						return html;
					}
				});
				document.querySelector('.quizzItems').innerHTML = html;
			}
			
			function renderListPage(){
				let html = ``;
				if(currentPage === 1 && totalPage === 1){
					html += `<a href="#" class="active">1</a>`;
				}
				if(currentPage === 1 && totalPage === 2){
					html += `<a href="#" class="active">1</a>`;
					html += `<a href="#">2</a>`;
				}
				if(totalPage > 2){
					if(currentPage <= totalPage-2){
						if(currentPage <= 2){
							html += `<a href="#" class="active">1</a>`;
							for(let i = 2; i <= 3; i++){
								html += `<a href="#">`+i+`</a>`;
							}
						}
						if(currentPage > 2){
							for(let i = currentPage - 2; i <= currentPage + 2; i++){
								html += `<a href="#">`+i+`</a>`;
							}
						}
					}
					if(currentPage > totalPage-2){
						for(let i = totalPage - 4; i <= totalPage; i++){
							if(i > 0){
								html += `<a href="#">`+i+`</a>`;
							}
						}
					}
				}
				document.querySelector('.btnPage').innerHTML = html;
			}
			
			function changePage(){
				const currentPages = document.querySelectorAll(".btnPage a");
				for(let i = 0; i < currentPages.length; i++){
					currentPages[i].addEventListener('click', () => {
						$('.btnPage a').removeClass('active');
						currentPages[i].classList.add('active');
						currentPage = parseInt(currentPages[i].innerHTML);
						start = (currentPage-1) * perPage;
						end = currentPage * perPage;
						renderListQuizz();
						showhidenBtnNextPre(currentPage,totalPage);
					});
				}
			}
			function showhidenBtnNextPre(currentPage,totalPage){
				btnPre.style.display = 'block';
				btnNext.style.display = 'block';
				if(currentPage === 1){
					btnPre.style.display = 'none';
				}
				if(currentPage === totalPage){
					btnNext.style.display = 'none';
				}
				if(currentPage === 1){
					renderListPage();
					$('.btnPage a').removeClass('active');
					document.querySelectorAll(".btnPage a")[0].classList.add('active');
					changePage();
				}
				if(currentPage === 2){
					renderListPage();
					$('.btnPage a').removeClass('active');
					document.querySelectorAll(".btnPage a")[1].classList.add('active');
					changePage();
				}
				if(currentPage >= 3){
					if(totalPage - currentPage === 1){
						renderListPage();
						document.querySelectorAll(".btnPage a")[3].classList.add('active');
						changePage();
					}else
					if(totalPage - currentPage === 0){
						renderListPage();
						document.querySelectorAll(".btnPage a")[4].classList.add('active');
						changePage();
					}else{
						renderListPage();
						document.querySelectorAll(".btnPage a")[2].classList.add('active');
						changePage();
					}
				}
				}
			btnNext.addEventListener('click', () => {
				currentPage++;
				if(currentPage > totalPage){
					currentPage = totalPage;
				}
				start = (currentPage-1) * perPage;
				end = currentPage * perPage;
				renderListQuizz();
				renderListPage();
				btnPre.style.display = 'block';
				if(currentPage === totalPage){
					btnNext.style.display = 'none';
				}
				pageActive();
			});
			btnPre.addEventListener('click', () => {
				currentPage--;
				if(currentPage <= 1){
					currentPage = 1;
				}
				start = (currentPage-1) * perPage;
				end = currentPage * perPage;
				console.log(start,end);
				renderListQuizz();
				renderListPage();
				btnNext.style.display = 'block';
				if(currentPage === 1){
					btnPre.style.display = 'none';
				}
				pageActive();
			});
			function pageActive(){
				$('.btnPage a').removeClass('active');
				if(currentPage <= 3){
					document.querySelectorAll('.btnPage a')[currentPage-1].classList.add('active');
				}
				if(currentPage > 3){
					if(currentPage <= totalPage - 2){
						document.querySelectorAll('.btnPage a')[2].classList.add('active');
					}
					if(currentPage > totalPage - 2){
						if(totalPage - currentPage === 1){
							document.querySelectorAll('.btnPage a')[3].classList.add('active');
						}
						if(totalPage - currentPage === 0){
							document.querySelectorAll('.btnPage a')[4].classList.add('active');
						}
					}
				}
			}
			renderListQuizz();
			renderListPage();
			changePage();
	</script>
</body>
</html>