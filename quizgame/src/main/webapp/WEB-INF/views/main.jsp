<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
    <style>
        .list-community{
			list-style: none;
			margin-top: 25px;
			margin-bottom: 70px;
			padding: 0;
		}
		.item-community{
			width: 100%; 
			height: 100px; 
			box-sizing: border-box; 
			padding: 10px;
			margin-bottom: 20px;
		}
		.link-community{
			width: 100%;
            height: 100px;
            background-color: #DEB887;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: black;
            font-size: 20px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
		}
		.link-community:hover{
			background-color: #DEB887;
            transform: scale(1.05);
            color: black;
   			text-decoration: none;
		}
		.link-community:active{
			background-color: #DEB887;
            transform: scale(0.95);
		}
		.container-sides {
			display: flex;
			gap: 20px;
			margin-top: 50px;
		}
		.side1, .side2 {
			flex: 1;
			height: auto;
			border-radius: 10px;
			padding: 20px;
			box-sizing: border-box;
			margin-bottom: 70px;
		}
		#wrap{
			min-height: calc(100vh - 10rem);
		}
		.thead-th{
			border-top: 1px solid black !important; 
			border-bottom: 2px solid black !important;
		}
		.tbody-td{
			border-top: 1px solid gray !important;
			border-bottom: 1px solid gray !important;
		}
		.side-h2{
			text-align: center;
			margin-bottom: 25px;
		}
    </style>
    
	<jsp:include page="/WEB-INF/views/common/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container" id="wrap">
		<ul class="list-community">
			<c:forEach items="${quizList}" var="qt">
				<li class="item-community">
					<a href="#" class="link-community" data-url="<c:url value='/post/quiz?qt_num=${qt.qt_num}'/>">${qt.qt_name}</a>
				</li>
			</c:forEach>
		</ul>
		<hr style="border: 2px solid black;">
		<div class="container-sides">
			<div class="side1">
				<h2 class="side-h2">랭킹 TOP 5</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="thead-th">순위</th>
							<th class="thead-th">아이디</th>
							<th class="thead-th">점수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${memberList}" var="member" begin="0" end="4" varStatus="vs">
						<tr>
							<td class="tbody-td">${vs.index + 1}</td>
							<td class="tbody-td">${member.me_id}</td>
							<td class="tbody-td">${member.me_point}</td>
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
			<div class="side2">
				<h2 class="side-h2">커뮤니티 TOP 5</h2>
				<table class="table table-hover" style="border-collapse: collapse;">
					<thead>
						<tr>
							<th class="thead-th">번호</th>
							<th class="thead-th">제목</th>
							<th class="thead-th">작성자</th>
							<th class="thead-th">작성일</th>
							<th class="thead-th">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${postList}" var="post" begin="0" end="4">
							<tr>
								<td class="tbody-td">${post.po_num}</td>
								<td class="tbody-td">
									<a href="<c:url value="/post/detail?po_num=${post.po_num}"/>">${post.po_title}</a>
								</td>
								<td class="tbody-td">
									<a href="<c:url value="/post/list?type=writer&search=${post.po_me_id}&co_num=${post.po_co_num}"/>" >${post.po_me_id}</a> 
								</td>
								<td class="tbody-td">
									<fmt:formatDate value="${post.po_date}" pattern="yyyy-MM-dd"/>
								</td>
								<td class="tbody-td">${post.po_view}</td>
							</tr>
						</c:forEach>
						<c:if test="${PostList.size() == 0}">
							<tr>
								<th colspan="5" class="text-center">등록된 게시글이 없습니다.</th>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script type="text/javascript">
	$('.link-community').click(function(e){
		e.preventDefault();

		if(!checkLogin()){
			return;
		}
		
		var url = $(this).data('url');
		location.href = url;
	});
	
	function checkLogin(){
		 if('${user.me_id}' == ''){
			 if(confirm('로그인이 필요한 서비스입니다.\n로그인 페이즈로 이동하시겠습니까?')){
				 location.href = '<c:url value="/login"/>';
				 return false;
			 }else{
				 return false;
			 }
		 }
		 return true;
	}
	</script>
</body>
</html>