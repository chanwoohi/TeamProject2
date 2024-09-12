<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<style>
    .container {
        margin-top: 20px;
    }
    .table {
        background-color: #ffffff;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-collapse: collapse;
        border : #ffe1c4;
    }
    .table thead {
        background-color: #007bff;
        color: #ffffff;
    }
    .table th, .table td {
        text-align: center;
        padding: 12px;
        border: 1px solid #ddd;
    }
    .table th {
        font-weight: bold;
        background-color : #fff93d;
        color : black;
    }
    .table tbody tr:nth-of-type(odd) {
        background-color: #f2f2f2;
    }
    .table tbody tr:hover {
        background-color: #e2e6ea;
    }
    h1 {
        color: black;
    }
    .btn {
        display: inline-block;
        padding: 10px 20px;
        font-size: 14px;
        font-weight: 600;
        text-align: center;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s, box-shadow 0.3s;
    }
    .btn-primary{
    	background-color : #58abff;
    	}
    .btn-primary:hover {
        background-color: #58abff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    .btn-danger {
        background-color: #dc3545;
        color: #000000;
    }
    .btn-danger:hover {
        background-color: #c82333;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    .btn-secondary {
        background-color: #dc3545;
        color: #000000;
    }
    .btn-secondary:hover {
        background-color: #dc3545;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    .float-right {
        float: right;
        margin-left: 10px;
    }
    .checkbox-container {
        width: 30px;
        text-align: center;
        display: none;
    }
    .show-checkbox .checkbox-container {
        display: table-cell;
    }
    .select-all-container {
        text-align: center;
        display: none;
    }
    .show-checkbox .select-all-container {
        display: table-cell;
    }
    .checkbox-container input[type="checkbox"] {
        margin: 0;
        transform: scale(0.8);
    }
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <h1 class="mt-3 mb-3">${qt.qt_name} 퀴즈 목록</h1>
    <form id="delete-form" action="<c:url value='/quiz2/quizdelete'/>" method="post">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="select-all-container"><input type="checkbox" id="select-all" class="select-all" /></th>
                    <th>퀴즈</th>
                    <th>답1</th>
                    <th>답2</th>
                    <th>답3</th>
                    <th>답4</th>
                    <th>정답</th>
                </tr>
            </thead>
            <tbody id="quiz-table-body">
                <c:forEach items="${list}" var="quiz">
                    <tr>
                        <td class="checkbox-container"><input type="checkbox" name="qu_num" value="${quiz.qu_num}" /></td>
                        <td>${quiz.qu_content}</td>
                        <td>${quiz.qu_answer1}</td>
                        <td>${quiz.qu_answer2}</td>
                        <td>${quiz.qu_answer3}</td>
                        <td>${quiz.qu_answer4}</td>
                        <td>${quiz.qu_correct_answer}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <button type="submit" class="btn btn-danger float-right" id="delete-button" style="display: none;">선택 삭제</button>
    </form>

    <button class="btn btn-danger float-right" id="delete-mode-button">삭제</button>
    <a href="<c:url value='/quiz2/quizinsert?qt_num=${qt.qt_num}'/>" class="btn btn-primary float-right" style="color: #ffffff;">퀴즈 추가</a>

    <script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('delete-mode-button').addEventListener('click', function() {
            var tableBody = document.getElementById('quiz-table-body');
            tableBody.classList.toggle('show-checkbox');
            
            var deleteButton = document.getElementById('delete-button');
            var selectAllContainer = document.querySelector('.select-all-container');

            deleteButton.style.display = tableBody.classList.contains('show-checkbox') ? 'inline-block' : 'none';
            selectAllContainer.style.display = tableBody.classList.contains('show-checkbox') ? 'table-cell' : 'none';

            this.textContent = this.textContent === '삭제' ? '취소' : '삭제';
            this.classList.toggle('btn-danger');
            this.classList.toggle('btn-secondary');
        });

        document.getElementById('select-all').addEventListener('click', function() {
            var checkboxes = document.querySelectorAll('input[name="qu_num"]');
            for (var checkbox of checkboxes) {
                checkbox.checked = this.checked;
            }
        });
    });
    </script>
</body>
</html>