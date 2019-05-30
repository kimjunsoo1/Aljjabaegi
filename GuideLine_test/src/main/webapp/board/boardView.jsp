<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
	<link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
	
<script>
function download(filename) {
	var form = document.getElementById("downForm")
	form.filename.value = filename;
	form.submit();
}

</script>
	
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
	
	<div>
		<p id="file-list" style="text-align: right;">
			<c:forEach var="file" items="${attachFileList}" varStatus="status">
				<a href="javascript:download('${file.filename}')">${file.filename}</a>
			<input type="button" value="xxxxx" onclick="deleteAttachFile('${file.attachFileNo }')" />
			<br />
			</c:forEach>	
		</p>
	</div>
		



<form id="downForm" action="download.do" method="post">
		<p>
			<input type="hidden" name="filename">
		<p>		
	</form>
	<form id="listForm" action="./list" method="get">
		<p>
			<input type="hidden" name="boardCd" value="${param.boardCd }" />
			<input type="hidden" name="curPage" />
			<input type="hidden" name="searchWord" value="${param.searchWord }" />
		</p>
</form>
</div>

<input type="button" value="목록" 
	onclick="location.href='boardList.do?pg=${pg}'">
<input type="button" value="삭제" 
	onclick="location.href='boardDelete.do?seq=${seq}'">



</body>
</html>







