<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"/>
	<!-- 유효성 검사 !! 순서 주의 -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
	
	<!-- 다음 주소 시작  -->
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        function execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색된 주소를 'address' input 필드에 설정
                    document.getElementById('address').value = data.address;
                }
            }).open();
        }
    </script>
    <!-- 다음 주소 끝 -->
</head>
<style type="text/css">
.error{
	color:red;
}
*{
	box-sizing: border-box;
}
.container{
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 800px;
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
</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<form action="<c:url value="/mypage"/>" class="py-5" method="post" id="form2">
				<div class="header">
		          <div class="h3 mb-3">마이 페이지</div>
        		</div>
				<div class="form-group">
					<label for="id">* 아이디</label> <input type="text" class="form-control bg-white"
						id="id" name="me_id" value="${member.me_id}" readonly>
				</div>
				<div class="form-group">
					<label for="pw">* 비밀번호</label> <input type="password"
						class="form-control" id="pw" name="me_pw">
				</div>
				<div class="form-group">
					<label for="pw2">* 비밀번호 확인</label> <input type="password"
						class="form-control" id="pw2" name="me_pw2">
				</div>
				<div class="form-group">
					<label for="phoneNumber">* 연락처</label> 
					<div class="d-flex align-items-center">
						<input type="text"
							class="phoneNumber form-control bg-white w-33" readonly value="010">
						<span> - </span>
						<input type="text"
							class="phoneNumber form-control w-33" id="phoneNumberMid" name="me_phoneNumberMid" value="${member.me_phoneNumberMid}">
						<span> - </span>
						<input type="text"
							class="phoneNumber form-control w-33" id="phoneNumberEnd" name="me_phoneNumberEnd" value="${member.me_phoneNumberEnd}">
					</div>	
				</div>
				<div class="form-group">
					<label for="email">* 이메일</label> <input type="text"
						class="form-control" id="email" name="me_email" value="${member.me_email}">
				</div>
				<div>
					<label for="address">* 주소</label>
					<div class="clearfix">
						<input type="text" id="address" class="form-control bg-white col-9 float-left" name="me_address" name="me_address" placeholder="주소를 검색하세요" value="${member.me_address}" readonly>
				        <button type="button" class="btn col-3 mb-3" onclick="execDaumPostcode()">주소 검색</button>
				        <!-- <input type="submit" value="서블릿으로 전송"> -->
			        </div>
			    </div>
			    <div>
					<label for="addressDetail">* 상세주소</label>
					<input type="text" id="addressDetail" class="form-control" name="me_addressDetail" placeholder="상세 주소를 입력하세요" value="${member.me_addressDetail}">
			    </div>
				<button type="submit" class="btn col-12 mt-4">회원 정보 수정</button>
			</form>
	</div>
<script type="text/javascript">
	$('#form2').validate({
		rules : {
			me_id : {
				required : true,
				regex : /^\w{6,13}$/
			},
			me_pw : {
				required : true,
				regex : /^[a-zA-Z0-9!@#$]{6,15}$/
			},
			me_pw2 : {
				equalTo : pw
			},
			me_email : {
				required : true,
				email : true
			}
		},
		messages : {
			me_id : {
				required : '필수 항목입니다.',
				regex : '아이디는 영어, 숫자만 가능하며, 6~13자이어야 합니다.'
			},
			me_pw : {
				required : '필수 항목입니다.',
				regex : '아이디는 영어, 숫자, 특수문자(!@#$)만 가능하며, 6~15자이어야 합니다.'
			},
			me_pw2 : {
				equalTo : '비번과 일치하지 않습니다.'
			},
			me_email : {
				required : '필수 항목입니다.',
				email : '이메일 형식이 아닙니다'
			}
		},
		submitHandler : function() {
			return true;
		}
	});
	$.validator.addMethod('regex', function(value, element, regex) {
		var re = new RegExp(regex);
		return this.optional(element) || re.test(value);
	}, "정규표현식을 확인하세요.");
</script>
</body>
</html>