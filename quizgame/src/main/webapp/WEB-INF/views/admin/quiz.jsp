<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>퀴즈 관리</title>
    <jsp:include page="/WEB-INF/views/common/head.jsp"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 20px;
            padding: 20px;
            border-radius: 8px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: black;
            margin-bottom: 20px;
        }
        .list-quiz {
            list-style: none;
            padding: 0;
        }
        .list-quiz .item-community {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #fafafa;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .link-quiz {
            display: flex;
            align-items: center;
        }
        .inner-quiz {
            flex: 1;
            margin-right: 15px;
        }
        .btn {
            margin-left: 5px;
        }
        .btn-update {
            background-color: #ffc107;
            color: #000;
        }
        .btn-del {
            background-color: #dc3545;
            color: #fff;
        }
        .btn-outline-success {
            border-color: #28a745;
            color: #28a745;
        }
        .btn-outline-success:hover {
            background-color: #28a745;
            color: #fff;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container">
         <h1>퀴즈 목록</h1>
        <ul class="list-quiz">
            <c:forEach items="${list}" var="qt">
                <li class="item-community">
                    <span class="inner-quiz">${qt.qt_name}</span>
                    <span class="link-quiz">
                        <a class="btn btn-outline-primary" href="<c:url value='/quiz2/quizlist?qt_num=${qt.qt_num}' />">퀴즈 목록</a>
                        <button class="btn btn-update" data-num="${qt.qt_num}">수정</button>
                        <a class="btn btn-del" href="<c:url value='/admin/quiz/delete?qt_num=${qt.qt_num}'/>">삭제</a>
                    </span>
                </li>
            </c:forEach>
        </ul>
        <div class="mt-4">
            <form class="input-group mb-3" action="<c:url value='/admin/quiz/insert'/>" method="post" id="form_insert">
                <input type="text" name="qt_name" class="form-control" placeholder="퀴즈 이름 입력">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-outline-success">등록</button>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
$('.btn-del').click(function(e){
	if(!confirm('해당 퀴즈를 삭제하시겠습니까?')){
		e.preventDefault();
		return;
	}
});
$('.btn-update').click(function(){
	$('#form_update').remove();
	var num = $(this).data('num');
	var name = $(this).prev().text();
	var str = `
	<form class="input-group mb-3" action="<c:url value="/admin/quiz/update"/>" method="post" id="form_update">
		<input type="text" name="qt_name" class="form-control">
		<div class="input-group-append">
			<button type="submit" class="btn btn-outline-success">수정</button>
		</div>
		<input type="hidden" name="qt_num" value="\${num}">
	</form>
	`;
	$('#form_insert').hide();
	$('#form_insert').after(str);
	
});

</script>
</body>
</html>