<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<title>게시글 수정</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container" style="min-height: calc(100vh - 240px)">
	<h1 class="mt-3 mb-3">게시글 수정</h1>
	<form action="<c:url value="/post/update"/>" method="post">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="po_title" value="${post.po_title}">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" id="content" name="po_content">${post.po_content}</textarea>
		</div>
		<button type="submit" class="btn btn-outline-warning col-12"
			style="border : black 2px solid;">수정 완료</button>
		<input type="hidden" name="po_num" value="${post.po_num}">
	</form>
</div>

<script>
	$('#content').summernote({
		placeholder: '게시글을 작성해주세요.',
		tabsize: 2,
		height: 400
	});
</script>
</body>
</html>