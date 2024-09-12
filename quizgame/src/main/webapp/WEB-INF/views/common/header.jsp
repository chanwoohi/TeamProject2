<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style type="text/css">
	   	@font-face {
		    font-family: 'establishRoomNo703OTF';
		    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2112@1.0/establishRoomNo703OTF.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}
	   	*{
	   		font-family: 'establishRoomNo703OTF';
	   	}
	   	.a{
	   		display: flex;
    		align-items: center;
	   		position: fixed; top: 0; bottom: 0; left: 0; right: 0;
	   		background: rgba(0,0,0,0.3); z-index: 100; display: none;
	   	}
	   	.login-popup{
	   		background: rgba(255, 255, 255, 1);
    		width: 500px;
    		padding: 30px;
    		display: flex;
		    flex-direction: column;
		    justify-content: center;
		    /* align-items: center; */
		    height: 400px;
	   	}
	   	.login-top{
	   		display: flex;
	   		justify-content: space-between;
            align-items: center;
	   	}
	   	nav{
			background-color: #deb887 !important;
		}
		
		.menu{
			color: black !important;
		}
		.logo img{
			width : 70px;
		}
	</style>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark" style="justify-content: space-between;">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="navbar-brand menu logo" href="<c:url value='/' />">
				   <img alt="quizlogo" src="/quizgame/resources/img/qlogo.png">
				</a>
			</li>
			<c:if test="${user ne null && user.me_authority eq 'ADMIN'}">
				<li class="nav-item">
					<a class="nav-link menu" href="<c:url value="/admin/quiz"/>">퀴즈 관리</a>
				</li>
			</c:if>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link menu" href="<c:url value="/community"/>">소통공간</a>
			</li>
			<li class="nav-item">
				<a class="nav-link menu" href="<c:url value="/ranking"/>">랭킹</a>
			</li>
			<c:if test="${user == null}">
				<li class="nav-item">
					<a class="nav-link menu" href="<c:url value="/signup"/>">회원가입</a>
				</li>
				<li class="nav-item btn-login">
					<a class="nav-link menu" href="javascript:void(0)">로그인</a>
				</li>
			</c:if>
			<c:if test="${user != null}">
				<li class="nav-item">
					<a class="nav-link menu" href="<c:url value="/logout"/>">로그아웃</a>
				</li>
				<li class="nav-item">
					<a class="nav-link menu" href="<c:url value="/mypage?me_id=${user.me_id}"/>">마이페이지</a>
				</li>
			</c:if>
		</ul>
	</nav>
	<div class="a">
		<div class="container login-popup">
			<div class="login-top">
				<h1>로그인</h1>
				<a href="javascript:void(0)" class="btn-close">X</a>
			</div>
			<form action="<c:url value="/login"/>" method="post" id="form">
				<div class="form-group">
					<label for="id">아이디:</label>
					<input type="text" class="form-control" name="me_id">
				</div>
				<div class="form-group">
					<label for="pw">비번:</label>
					<input type="password" class="form-control" name="me_pw">
				</div>
				<div class="form-check">
		  			<label class="form-check-label">
		    			<input type="checkbox" class="form-check-input" value="true" name="auto">자동로그인
		  			</label>
				</div>
				<button type="submit" class="btn btn-outline-warning col-12"
					style="border : solid, black, 2px;">로그인</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$('.btn-login').click(function(){
			$('.a').css('display', 'flex');
		});
		$('.btn-close').click(function(){
			$('.a').css('display', 'none');
		});
	</script>
</body>
</html>