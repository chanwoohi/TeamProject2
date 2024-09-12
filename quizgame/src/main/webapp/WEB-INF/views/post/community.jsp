<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
*{
	box-sizing: border-box;
}
.container{
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 670px;
	margin-top: 60px;
	margin-bottom: 60px;
	background: #ffffff;
	border: 2px solid black;
	box-shadow: 7px 7px 39px rgba(0, 0, 0, 0.25);
	border-radius: 20px;
}
.btn{
	background-color: #ffd480;
}
.container.container-width{
	width: 40%;
}
h1{
	text-align: center;
}
.list-group-item{
	border-left: none;
	border-right: none;
	border-bottom-color: black;
}
.table td, .table th {
    padding: .75rem;
    vertical-align: top;
    border-top: 1px solid #000;
}
.table td.list-group-item-action{
	border: none;
	border-top: 1px solid #000;
}
.table tr:last-of-type td.list-group-item-action{
	border-bottom: 1px solid #000;
}
</style>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<title>메인</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container container-width" style="min-height: calc(100vh - 240px)">
<h1 class="mt-3 mb-3">커뮤니티 목록</h1>
<table class="table table-hover">
	<c:forEach items="${list}" var="co">
		<tr>
			<td class="list-group-item list-group-item-action">
				<a href="<c:url value="/post/list?co_num=${co.co_num}"/>">${co.co_name}</a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>