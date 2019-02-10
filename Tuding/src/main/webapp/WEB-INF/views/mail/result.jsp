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
			if('${result}' == 'success') {
				alert("인증에 성공하였습니다. 로그인 해주세요.");
				location.href = '${pageContext.request.contextPath}/member/home'
			}
			else {
				alert("잘못된 행동입니다.");
				location.href = '${pageContext.request.contextPath}/member/home'
			}
		})
	});
</script>
</head>
<body>

</body>
</html>