<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/member/home">Study
					Anywhere</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
			</div>
		</div>
	</nav>
	<!-- 내용 부분 -->
	<div class="container" style="max-width:560px">
		<div class="alert alert-success mt-4" role="alert">
			이메일 주소 인증메일이 전송 되었습니다. 가입시 입력한 이메일을 확인해 주세요.
		</div>
	</div>
	
	<footer class="container-fluid text-center">
		<p>KITRI 디지털 컨버젼스 28기</p>
	</footer>
</body>
</html>