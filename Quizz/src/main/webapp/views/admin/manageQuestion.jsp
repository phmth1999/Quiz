<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Manage Question</title>
<style><%@include file="/views/admin/style.css" %></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"/>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/admin/layouts/header.jsp"%>
		<%@include file="/views/admin/layouts/menu.jsp"%>
		<section class="manage-question">
			<form class="form-question" action="${pageContext.request.contextPath}/quan-tri/question" method="post">
				<div class="selectQuizz">
					<label>Select Quizz:</label> 
						<select name="quizzId" 
								id="quizzId" 
								onfocus="this.size=4" 
								onblur='this.size=1;'  
								onchange='this.size=1; this.blur(); selectQuizz()'>
							<option value="" hidden="">${quizzModel.name }</option>
							<c:forEach var="item" items="${listQuizz }">
								<option value="${item.id }">${item.id }.${item.name }</option>
							</c:forEach>
						</select>
				</div>
				<div class="questions">
					<div class="question-item question-item-0">
						<label>Question:</label>
						<div class="text-question">
							<input type="text" name="question" id="question" placeholder="Content question">
							<input name="count-answer" class="count-answer" value="1" hidden="" />
							<input name="questionId" value="0"  hidden=""/>
							<div class="social">
								<i onclick="addQuestion()" class="fa-solid fa-plus add"></i> 
								<i onclick="deleteQuestion(0)" class="fa-solid fa-minus delete"></i>
							</div>
						</div>
						<div class="answers">
							<div class="answer-item answer-item-0">
								<input name="checkboxAnswer" value="0:0" type="checkbox" /> 
								<label>Answer:</label>
								<input name="answerId" value="`+0+`"  hidden=""/> 
								<input class="input-answer" 
										type="text" 
										name="content-answer" 
										id="content-answer" 
										placeholder="Content answer" />
								<div class="social">
									<i onclick="addAnswer(0,0)" class="fa-solid fa-plus add"></i> 
									<i onclick="deleteAnswer(0,0)" class="fa-solid fa-minus delete"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input class="btnAdd" type="submit" value="Save"/>
			</form>
		</section>
		<%@include file="/views/admin/layouts/footer.jsp"%>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		const idQuizz = document.getElementById("quizzId");
		let questionId = 0;
		function selectQuizz() {
			let id = idQuizz.value;
			let path = location.pathname.split("/")[1];
			$.ajax({
						url : "/" + path + "/api/question?quizzId=" + id,
						async : false,
						type : "get",
						success : function(res) {
							if(res.length > 0){
								questionId = res.length-1;
							}
							let answerId = [];
							let questionItem = ``;
							$.each(res, function(i, item) {
								answerId[i] = item.listAnswer.length; 
												questionItem += `<div class="question-item question-item-`+i+`">`
												questionItem += `<label>Question:</label>`
												questionItem += `<div class="text-question">`
												questionItem += `<input type="text" name="question" id="question" value="` + item.content + `" placeholder="Content question"> `
												questionItem += `<input name="count-answer" class="count-answer" value="`+item.listAnswer.length+`"  hidden=""/>`
												questionItem += `<input name="questionId" value="`+item.id+`"  hidden=""/>`
												questionItem += `<div class="social">`
												questionItem += `<i onclick="addQuestion()" class="fa-solid fa-plus add"></i>`
												questionItem += `<i onclick="deleteQuestion(`+i+`)" class="fa-solid fa-minus delete"></i>`
												questionItem += `</div>`
												questionItem += `</div>`
												questionItem += `<div class="answers">`

												for (let j = 0; j < item.listAnswer.length; j++) {
													questionItem += `<div class="answer-item answer-item-`+j+`">`
													if(item.listAnswer[j].status == "true"){
														questionItem += `<input name="checkboxAnswer" value="`+j+`:`+i+`" checked="true" type="checkbox"/>`
													}else{
														questionItem += `<input name="checkboxAnswer" value="`+j+`:`+i+`" type="checkbox"/>`
													}
													questionItem += `<label>Answer:</label>`
													questionItem += `<input name="answerId" value="`+item.listAnswer[j].id+`"  hidden=""/>`
													questionItem += `<input class="input-answer" type="text" name="content-answer" id="content-answer" value="` + item.listAnswer[j].content + `" placeholder="Content answer"/>`
													questionItem += `<div class="social">`
													questionItem += `<i onclick="addAnswer(`+i+`,`+j+`, `+item.listAnswer.length+`)" class="fa-solid fa-plus add"></i>`
													questionItem += `<i onclick="deleteAnswer(`+i+`,`+j+`)" class="fa-solid fa-minus delete"></i>`
													questionItem += `</div>`
													questionItem += `</div>`
												}

												questionItem += `</div>`
												questionItem += `</div>`
							});
							if(res.length < 1){
								questionId = 0;
								$('.questions').empty();
								questionItem = `
									<div class="question-item question-item-0">
									<label>Question:</label>
									<div class="text-question">
										<input type="text" name="question" id="question" placeholder="Content question">
										<input name="count-answer" class="count-answer" value="1" hidden="" />
										<input name="questionId" value="0"  hidden=""/>
										<div class="social">
											<i onclick="addQuestion()" class="fa-solid fa-plus add"></i> 
											<i onclick="deleteQuestion(0)" class="fa-solid fa-minus delete"></i>
										</div>
									</div>
									<div class="answers">
										<div class="answer-item answer-item-0">
											<input name="checkboxAnswer" value="0:0" type="checkbox" /> 
											<label>Answer:</label>
											<input name="answerId" value="`+0+`"  hidden=""/> 
											<input class="input-answer" type="text" name="content-answer" id="content-answer" placeholder="Content answer" />
											<div class="social">
												<i onclick="addAnswer(0,0)" class="fa-solid fa-plus add"></i> 
												<i onclick="deleteAnswer(0,0)" class="fa-solid fa-minus delete"></i>
											</div>
										</div>
									</div>
								</div>
								`;
								$('.questions').append(questionItem);
							}else{
								$('.questions').empty();
								$('.questions').append(questionItem);
								for (let i = 0; i <= questionId; i++) {
									for (let j = 0; j < answerId[i]; j++) {
										if(j < answerId[i]-1){
											hiddenAnswerAdd(i,j);
											showAnswerDelete(i,j);
										}
									}
									if(i < questionId){
										hiddenQuestionAdd(i);
										showQuestionDelete(i);
									}
								}
							}
						}
					});
		}
		function addQuestion(){
			hiddenQuestionAdd(questionId);
			showQuestionDelete(questionId);
			questionId++;
			let questionItem = `
				<div class="question-item question-item-`+questionId+`">
					<label>Question:</label>
					<div class="text-question">
						<input type="text" name="question" id="question" value="" placeholder="Content question"> 
						<input name="count-answer" class="count-answer" value="1" hidden=""/>
						<input name="questionId" value="0"  hidden=""/>
						<div class="social">
							<i onclick="addQuestion(`+questionId+`)" class="fa-solid fa-plus add"></i>
							<i onclick="deleteQuestion(`+questionId+`)" class="fa-solid fa-minus delete"></i>
						</div>
					</div>
					<div class="answers">
						<div class="answer-item answer-item-0">
							<input name="checkboxAnswer" value="0:`+questionId+`" type="checkbox"/>
							<label>Answer:</label>
							<input name="answerId" value="`+0+`"  hidden=""/>
							<input class="input-answer" type="text" name="content-answer" id="content-answer" value="" placeholder="Content answer"/>
							<div class="social">
								<i onclick="addAnswer(`+questionId+`,0)" class="fa-solid fa-plus add"></i>
								<i onclick="deleteAnswer(`+questionId+`,0)" class="fa-solid fa-minus delete"></i>
							</div>
						</div>

					</div>
				</div>
			`;
			$('.questions').append(questionItem);
		}
		
		function addAnswer(questionId,answerId){
			hiddenAnswerAdd(questionId,answerId);
			showAnswerDelete(questionId,answerId);
			answerId++;
			document.querySelector(`.question-item-`+questionId+``).querySelector(".count-answer").value = answerId+1;
			const div = document.createElement('div');
			div.className = `answer-item answer-item-`+answerId+``;
			div.innerHTML = `
				<input name="checkboxAnswer" value="`+answerId+`:`+questionId+`" type="checkbox"/>
				<label>Answer:</label>
				<input name="answerId" value="`+0+`"  hidden=""/>
				<input class="input-answer" type="text" name="content-answer" id="content-answer" value="" placeholder="Content answer"/>
				<div class="social">
					<i onclick="addAnswer(`+questionId+`,`+answerId+`)" class="fa-solid fa-plus add"></i>
					<i onclick="deleteAnswer(`+questionId+`,`+answerId+`)" class="fa-solid fa-minus delete"></i>
				</div>
			`;
			document.querySelector(`.question-item-`+questionId+``).querySelector('.answers').appendChild(div);
		}
		
		function deleteAnswer(questionId,answerId){
			document.querySelector(`.question-item-`+questionId+``).querySelector(`.answer-item-`+answerId+``).remove(); 
		}
		function hiddenAnswerAdd(questionId,answerId){
			document.querySelector(`.question-item-`+questionId+``).querySelector(`.answer-item-`+answerId+``).querySelector(`.add`).style.display = "none";
		}
		function showAnswerDelete(questionId,answerId){
			document.querySelector(`.question-item-`+questionId+``).querySelector(`.answer-item-`+answerId+``).querySelector(`.delete`).style.display = "block";
		}
		function deleteQuestion(questionId){
			document.querySelector(`.question-item-`+questionId+``).remove();
		}
		function hiddenQuestionAdd(questionId){
			document.querySelector(`.question-item-`+questionId+``).querySelector(`.text-question`).querySelector(`.add`).style.display = "none";
		}
		function showQuestionDelete(questionId){
			document.querySelector(`.question-item-`+questionId+``).querySelector(`.text-question`).querySelector(`.delete`).style.display = "block";
		}
		
	</script>
</body>
</html>