<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if(${su > 0}) {
		alert("저장 성공");
		location.href="boardList.do?pg=1";
	} else {
		alert("저장 실패");
		location.href="boardWriteForm.do";
	}
</script>
</head>
<body>

</body>
</html>



