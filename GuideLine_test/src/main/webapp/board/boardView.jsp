<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
	<link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
	

	
</head>
<body>
<div class="container">
	<div>		
			<h4 class="my-0 font-weight-normal">${boardDTO.subject}</h4>				
	</div>
	<div>
		<ul class="list-unstyled mt-3 mb-4">
			<li>글번호 : ${boardDTO.seq}</li>
			<li>작성자 : ${boardDTO.name}</li>
			<li>조회수 : ${boardDTO.hit}</li>
		</ul>
	</div>
	<div>
		<ul class="list-unstyled mt-3 mb-4">
			<pre>${boardDTO.content}</pre>
		</ul>
	</div>
</div>
<input type="button" value="목록" 
	onclick="location.href='boardList.do?pg=${pg}'">
<input type="button" value="삭제" 
	onclick="location.href='boardDelete.do?seq=${seq}'">



</body>
</html>







