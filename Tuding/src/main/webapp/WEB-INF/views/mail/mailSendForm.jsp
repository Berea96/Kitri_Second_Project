<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(() => {
		$(() => {
			$("#sendForm").submit();
		});
	});
</script>
</head>
<body>
	<form id="sendForm" action="${pageContext.request.contextPath}/mail/sending" method="post" style="display:none;">
		<input type="hidden" name="tomail" value="${member.email}">
		<input type="hidden" name="title" value="Tuding인증 메일입니다.">
	</form>
</body>
</html>
