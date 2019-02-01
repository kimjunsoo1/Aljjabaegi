<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>no-cache 설정</title>
</head>
<body>

<script>
	alert("보안 경고 !!\n 로그아웃 후 뒤로 가기 시 페이지 no-cache 처리가 되어 뒤로 가지지 않습니다.")
	window.location.replace("../index.jsp");
</script>

</body>
</html>