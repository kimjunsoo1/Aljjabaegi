<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script type="text/javascript" src="../script/boardScript.js"></script>
</head>
<body>
<form action="boardWrite.do" method="post" name="boardWriteForm">
<table border="1">
	<tr>
		<th width="50">제목</th>
		<td><input type="text" name="subject" size="59"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" rows="15" cols="45"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="글쓰기" onclick="checkBoardWrite()">
			<input type="reset" value="다시작성">
		</td>
	</tr>
</table>
</form>
</body>
</html>







