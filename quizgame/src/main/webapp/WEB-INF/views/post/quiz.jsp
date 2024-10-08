<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${qt.qt_name}</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
    <style>
        .quiz-box {
            width: 1000px;
            padding: 20px;
            border: 3px solid #f7ac1b;
            background-color: white;
            border-radius: 10px;
            margin: 0 auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: absolute; 
		    top: 50%;
		    left: 50%;
		    transform: translate(-50%, -50%);
        }
        .quiz-question {
            font-size: 23px;
            margin-bottom: 20px;
        }
        .quiz-options label {
            display: block;
            font-size: 18px;
            margin-bottom: 10px;
            cursor: pointer;
        }
        .quiz-options input[type="radio"] {
            margin-right: 40px;
        }
        .btn:active{
        	background-color: #f7ac1b;
            transform: scale(0.95);
        }
	    h1{
	    	text-align: center;
	    }
		.end-msg {
			margin-bottom: 10px;
		    text-align: center;
		}
		.result-row {
		    display: flex;
		    justify-content: center;
		    font-size: 25px;
		    margin-bottom: 10px;
		}
		.result-label {
		    width: 120px;
		    text-align: left;
		    font-size: 25px;
		}
		.result-value {
		    width: 200px;
		    text-align: right;
		    font-size: 25px;
		}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="bg">
		<h1 class="mt-3 mb-3">${qt.qt_name}</h1>
		<form name="quizForm" action="<c:url value="/post/quiz"/>" method="post" onsubmit="checkAnswer(event)">
			<div class="quiz-box">
			    <c:if test="${index < list.size()}">
				    <div class="quiz-question">
				        <span style="font-size: 15px;">다음 문제의 정답을 고르세요!!</span>
				        <p>${quiz.qu_content}</p>
				    </div>
				    <hr>
				    <br>
				    <div class="quiz-options">
				        <label><input type="radio" name="answer" value="1"> ${quiz.qu_answer1}</label>
				        <label><input type="radio" name="answer" value="2"> ${quiz.qu_answer2}</label>
				        <label><input type="radio" name="answer" value="3"> ${quiz.qu_answer3}</label>
				        <label><input type="radio" name="answer" value="4"> ${quiz.qu_answer4}</label>
				    </div>
				    <input type="hidden" name="qu_num" value="${quiz.qu_num}">
				    <br>
				    <hr>
				    <button type="submit" class="btn btn-outline-warning col-12">제출!!</button>
			    </c:if>
			    <c:if test="${index == list.size()}">

			    	<div style="font-size: 50px; text-align: center;">GAME END!!!!!!</div>
			    	<hr style="margin: 10px">
			    	<p class="end-msg"><span style="font-size: 25px;">${user.me_id} 님의 결과!!</span></p>
	
					<p class="result-row"><span class="result-label" style="color: blue;">정답 갯수</span>:<span class="result-value">${count} 개</span></p>
					<p class="result-row"><span class="result-label" style="color: red;">오답 갯수</span>:<span class="result-value">${10 - count} 개</span></p>
					<c:choose>
					    <c:when test="${score > 0}">
					        <p class="result-row"><span class="result-label">점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;수</span>:<span class="result-value">${user.me_point} 점 (+${score})</span></p>
					    </c:when>
					    <c:otherwise>
					        <p class="result-row"><span class="result-label">점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;수</span>:<span class="result-value">${user.me_point} 점 (${score})</span></p>
					    </c:otherwise>
					</c:choose>
					<p style="text-align: right; font-size: 18px;">엄청 똑똑하시네여~!!</p>
					<hr>
					<a href="<c:url value="/main"/>" class="btn btn-outline-warning col-12">메뉴로 돌아가기</a>

				</c:if>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	 function checkAnswer(event) {
         event.preventDefault();
         
         const userAnswer = document.querySelector('input[name="answer"]:checked');
         var test = $("input[name='answer']").is(":checked");
         const quizAnswer = ${quiz.qu_correct_answer};
         if (userAnswer) {
             const userValue = userAnswer.value;
             if (userValue == quizAnswer) {
                 alert("정답입니다!");
             } else {
                 alert("오답입니다. 정답은 " + quizAnswer + "번입니다.");
             }
             if (${index} < ${list.size()}) {
                 document.quizForm.submit();
             }
         } else {
             alert("답을 선택해주세요!");
         }
     }
	 </script>
	
</body>
</html>