<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>퀴즈 등록</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <jsp:include page="/WEB-INF/views/common/head.jsp"/>
    <style>
        .container {
            max-width: 900px;
            margin: 40px auto;
            padding: 30px;
            border-radius: 8px;
            border: 2px solid black;
			box-shadow: 7px 7px 39px rgba(0, 0, 0, 0.25);
        }
        h1 {
            margin-bottom: 20px;
            color: #007bff;
        }
        .form-group {
            margin-bottom: 20px;
          
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
        }
        .form-control {
            width: 100%;
            padding: 12px;
            border-radius: 4px;
            border: 1px solid #ced4da;
            font-size: 16px;
            box-sizing: border-box;
        }
        .form-control:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
        }
        .btn {
            padding: 12px 20px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
            display: inline-block;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn:focus {
            outline: none;
            box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
        }
        .form-control-file {
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ced4da;
            background-color: #f8f9fa;
        }
        .form-container{
        	text-align : center;
        	margin:auto;
        }
    </style>
   
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container">
        <h1>${qt.qt_name} 추가</h1>
        <form action="<c:url value='/quiz2/quizinsert'/>" method="post">
            <div class="form-group">
                <label for="content">퀴즈 :</label>
                 <textarea class="form-control form-control-textarea" id="content" name="content" placeholder="퀴즈 문제를 입력하세요"></textarea>
            </div>
            <div class="form-group">
                <label for="answer1">답 1:</label>
                <input type="text" class="form-control" id="answer1" name="answer1" placeholder="첫 번째 답 입력">
            </div>
            <div class="form-group">
                <label for="answer2">답 2:</label>
                <input type="text" class="form-control" id="answer2" name="answer2" placeholder="두 번째 답 입력">
            </div>
            <div class="form-group">
                <label for="answer3">답 3:</label>
                <input type="text" class="form-control" id="answer3" name="answer3" placeholder="세 번째 답 입력">
            </div>
            <div class="form-group">
                <label for="answer4">답 4:</label>
                <input type="text" class="form-control" id="answer4" name="answer4" placeholder="네 번째 답 입력">
            </div>
            <div class="form-group">
                <label for="answer">정답 선택:</label>
                <select class="form-control" id="answer" name="answer" required>
                    <option value="1">1번</option>
                    <option value="2">2번</option>
                    <option value="3">3번</option>
                    <option value="4">4번</option>
                </select>
            </div>
            <div class="form-container">
	            <button type="submit" class="btn btn-info">추가</button>
            </div>
            <input type="hidden" name="qt_num" value="${qt_num}">
        </form>
    </div>
</body>
</html>