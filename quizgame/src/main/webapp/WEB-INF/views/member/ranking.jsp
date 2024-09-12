<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랭킹</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<h1>랭킹</h1>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>순위</th>
					<th>아이디</th>
					<th>점수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="member" varStatus="vs">
					<tr>
						<td>${member.me_ranking}</td>
						<td>${member.me_id}</td>
						<td>${member.me_point}</td>
					</tr>
				</c:forEach>
				<c:if test="${list.size() == 0}">
					<tr>
						<th colspan="6" class="text-center">등록된 회원이 없습니다.</th>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>