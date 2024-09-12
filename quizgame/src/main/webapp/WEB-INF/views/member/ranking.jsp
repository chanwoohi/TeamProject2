<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랭킹</title>
	<style type="text/css">
		.thead-th{
			border-top: 1px solid black !important; 
			border-bottom: 2px solid black !important;
		}
		.tbody-td{
			border-top: 1px solid gray !important;
			border-bottom: 1px solid gray !important;
		}
		#wrap{
			min-height: calc(100vh - 10rem);
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
		.container.container-width{
   			 width: 40%;
		}
	</style>
	<jsp:include page="/WEB-INF/views/common/head.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container container-width" id="wrap">
		<h1 style="text-align: center; margin-top: 30px;">랭킹</h1>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="thead-th">순위</th>
					<th class="thead-th">아이디</th>
					<th class="thead-th">점수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="member" varStatus="vs">
					<c:choose>
						<c:when test="${member.me_ranking == 1}">
							<tr>
								<td class="tbody-td">🥇</td>
								<td class="tbody-td">${member.me_id}</td>
								<td class="tbody-td">${member.me_point}</td>
							</tr>
						</c:when>
						<c:when test="${member.me_ranking == 2}">
							<tr>
								<td class="tbody-td">🥈</td>
								<td class="tbody-td">${member.me_id}</td>
								<td class="tbody-td">${member.me_point}</td>
							</tr>
						</c:when>
						<c:when test="${member.me_ranking == 3}">
							<tr>
								<td class="tbody-td">🥉</td>
								<td class="tbody-td">${member.me_id}</td>
								<td class="tbody-td">${member.me_point}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="tbody-td">${member.me_ranking}</td>
								<td class="tbody-td">${member.me_id}</td>
								<td class="tbody-td">${member.me_point}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${list.size() == 0}">
					<tr>
						<th colspan="6" class="text-center tbody-td">등록된 회원이 없습니다.</th>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>