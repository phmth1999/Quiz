<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quizz Detail</title>
<style><%@include file="/views/web/style.css" %></style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"/>
</head>
<body>
	<div id="wrapper">
		<%@include file="/views/web/layouts/header.jsp" %>
		<section class="quizzDetail">
			<div class="container">
				<div class="row">
					<div class="quizz-content">
						<div class="question">
							<c:forEach var="item" items="${listQuestion }" varStatus="myIndexQ">
							<c:set var="indexQ" value="${myIndexQ.index}" />
								<div class="question-item" id="Question${myIndexQ.index }">
									<img alt="" src="./views/web/images/${item.image }">
									<h1>${item.content }</h1>
									<div class="answers">
										<c:forEach var="items" items="${item.listAnswer }" varStatus="myIndexA">
										<c:set var="indexA" value="${myIndexA.index}" />
											<div class="answer-item">
												<c:if test="${myIndexA.index == 0}">
													<input onclick="clickInput(<c:out value="${indexA}"/>,<c:out value="${indexQ}"/>)" value="${items.status }" type="checkbox"/>
													<p>A. ${items.content }</p>
													<i class="fa-solid fa-check check"></i>
													<i class="fa-sharp fa-solid fa-xmark x"></i>
												</c:if>
												<c:if test="${myIndexA.index == 1}">
													<input onclick="clickInput(<c:out value="${indexA}"/>,<c:out value="${indexQ}"/>)" value="${items.status }" type="checkbox"/>
													<p>B. ${items.content }</p>
													<i class="fa-solid fa-check check"></i>
													<i class="fa-sharp fa-solid fa-xmark x"></i>
												</c:if>
												<c:if test="${myIndexA.index == 2}">
													<input onclick="clickInput(<c:out value="${indexA}"/>,<c:out value="${indexQ}"/>)" value="${items.status }" type="checkbox"/>
													<p>C. ${items.content }</p>
													<i class="fa-solid fa-check check"></i>
													<i class="fa-sharp fa-solid fa-xmark x"></i>
												</c:if>
												<c:if test="${myIndexA.index == 3}">
													<input onclick="clickInput(<c:out value="${indexA}"/>,<c:out value="${indexQ}"/>)" value="${items.status }" type="checkbox"/>
													<p>D. ${items.content }</p>
													<i class="fa-solid fa-check check"></i>
													<i class="fa-sharp fa-solid fa-xmark x"></i>
												</c:if>
											</div>
										</c:forEach>
									</div>
									<div class="btn">
										<button onclick="clickPre(<c:out value="${indexQ}"/>,${listQuestion.size() })">Previous</button>
										<button onclick="clickNext(<c:out value="${indexQ}"/>,${listQuestion.size() })">Next</button>
										<button class="finishBtn" onclick="clickFinish()">Finish</button>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="quizz-content">
						<div class="question">
							<div class="row">
								<div class="menu-title">
									<div class="timeOut"></div>
									<div class="resolve"></div>
									<c:forEach var="item" items="${listQuestion }" varStatus="myIndex">
                    					<button class="menu-button" data-title="#Question${myIndex.index }">Question ${myIndex.index + 1 }</button>
                    				</c:forEach>
                    				
                				</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<%@include file="/views/web/layouts/footer.jsp" %>
	</div>
	<script>
	const buttonPre = document.querySelector(".pre");
	const buttonNext = document.querySelector(".next");
	const menuButton = document.querySelectorAll(".menu-button");
	const menuQuestion = document.querySelectorAll(".question-item");
	const menuAnswer = document.querySelector(".answers").querySelectorAll(".answer-item");
	const menuTitle = document.querySelector(".menu-title");
	const resolve = document.querySelector(".resolve");
	const timeOut = document.querySelector(".timeOut");
	
	menuButton[0].classList.add("active");
	menuQuestion[0].classList.add("active");
	
	let hours = 0;
    let minutes = 10;
	let seconds = 0;
    function countdown(){
    	seconds--;
    	if(seconds <= 0 ){
    		minutes--;
    		seconds = 60;
    	}
    	if(minutes === 0){
    		hours--;
    		minutes = 60;
    	}
    	if(hours < 0){
    		hours = 0;
    		minutes = 0;
    		seconds =0;
    	}
        if((seconds == 0 && minutes == 0 && hours == 0) || hours < 0 || minutes < 0){
        	timeOut.innerHTML = "Out of time!";
        	clickFinish();
        }else{
        	if(hours < 10){
        		hour = "0" + hours;
        	}else{
        		hour = hours;
        	}
        	if(minutes < 10){
        		minute = "0" + minutes;
        	}else{
        		minute = minutes;
        	}
        	if(seconds < 10){
        		second = "0" + seconds;
        	}else{
        		second = seconds;
        	}
        	timeOut.innerHTML = hour + ":" + minute + ":" + second;
            setTimeout("countdown()",1000);
        }
    }
    countdown();
	
	function clickInput(idA, idQ){
		for(let j = 0; j < menuAnswer.length; j++){
			menuQuestion[idQ].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector("input").checked = false;
		}
		menuQuestion[idQ].querySelector(".answers").querySelectorAll(".answer-item")[idA].querySelector("input").checked = true;
		//
		for(let i = 0; i < menuQuestion.length; i++){
			for(let j = 0; j < menuAnswer.length; j++){
				const input = menuQuestion[i].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector("input");
				const value = input.value;
				if(input.checked === true){
					menuButton[i].classList.add("active-answer");
					break;
				}else{
					menuButton[i].classList.remove("active-answer");
				}
			}
		}
	}
	
	menuTitle.addEventListener("click",function(x){
	    if(x.target.classList.contains("menu-button")){
	        const Target = x.target.getAttribute("data-title");
	        for(let i = 0; i < menuQuestion.length; i++){
				for(let j = 0; j < menuAnswer.length; j++){
					const input = menuQuestion[i].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector("input");
					const value = input.value;
					if(input.checked === true){
						menuButton[i].classList.add("active-answer");
						menuButton[i].classList.remove("active");
						break;
					}else{
						menuButton[i].classList.remove("active-answer");
						menuButton[i].classList.remove("active");
					}
				}
			}
	        x.target.classList.add("active");
	        const menuItem = document.querySelector(".question");
	        menuItem.querySelector(".question-item.active").classList.remove("active");
	        menuItem.querySelector(Target).classList.add("active");
	    }
	});
	function clickNext(id, size){
		if(id < size - 1){
				for(let j = 0; j < menuAnswer.length; j++){
					const input = menuQuestion[id].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector("input");
					const value = input.value;
					if(input.checked === true){
						menuButton[id].classList.add("active-answer");
						break;
					}
				}
			menuButton[id].classList.remove("active");
			menuQuestion[id].classList.remove("active");
		
			menuButton[id + 1].classList.add("active");
			menuQuestion[id + 1].classList.add("active");
		}
	}
	function clickPre(id, size){
		if(id > 0){
			for(let j = 0; j < menuAnswer.length; j++){
				const input = menuQuestion[id].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector("input");
				const value = input.value;
				if(input.checked === true){
					menuButton[id].classList.add("active-answer");
					break;
				}
			}
			menuButton[id].classList.remove("active");
			menuQuestion[id].classList.remove("active");
		
			menuButton[id - 1].classList.add("active");
			menuQuestion[id - 1].classList.add("active");
		}
	}
	function clickFinish(){
		hours = 0;
	    minutes = 0;
		seconds = 0;
		let countQuestionTrue = 0;
		let countQuestionFalse = 0;
		
		for(let i = 0; i < menuQuestion.length; i++){
			for(let j = 0; j < menuAnswer.length; j++){
				const input = menuQuestion[i].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector("input");
				const value = input.value;
				const finishBtn = menuQuestion[i].querySelector(".finishBtn");
				input.disabled = true;
				finishBtn.disabled = "disabled";
				if(input.checked === true){
					if(value == 'true'){
						menuButton[i].classList.remove("active-answer");
						menuButton[i].classList.add("active-answer-true");
						menuQuestion[i].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector(".check").style.display = 'block';
						countQuestionTrue++;
					}else{
						menuButton[i].classList.remove("active-answer");
						menuButton[i].classList.add("active-answer-false");
						menuQuestion[i].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector("input").checked = true;
						menuQuestion[i].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector(".x").style.display = 'block';
						countQuestionFalse++;
					}
				}else{
					if(value == 'true'){
						menuQuestion[i].querySelector(".answers").querySelectorAll(".answer-item")[j].querySelector(".check").style.display = 'block';
					}
				}
			}
		}
		const countQuestionEmpty = menuQuestion.length - (countQuestionTrue + countQuestionFalse);
		const string = "Question true: " + countQuestionTrue + "; Question false: "+countQuestionFalse + "; Question empty: " + countQuestionEmpty;
		resolve.innerHTML = string;
	}
	</script>
</body>
</html>