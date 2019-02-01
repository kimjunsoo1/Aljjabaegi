<%@page import="java.net.URLEncoder"%>
<%@page import="com.junshae.security.member.bean.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%

response.setHeader("Cache-Control", "no-cache, no-store"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", -1); // Proxies.

%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A-Team 게시판</title>

	 <link href="../css/bootstrap.css" rel="stylesheet" type="text/css">

	 
	<script type="text/javascript">
		function isLogin(seq) {
			if(${memId == null}) {
				alert("먼저 로그인 하세요.");
			} else {
				location.href="boardView.do?seq=" + seq + "&pg=" + ${pg};
			}		
		} 
	</script>
</head>
<body>

<%@ include file="../include/header.jsp" %>		

<!--  19.01.31 no cache 테스트
	<input type="button" value="로그아웃" onclick="location.href='../login/logout.do'">
 -->


<div class="card-deck mb-3 text-center">
	<h4 class="my-0 font-weight-normal">A-Team Board</h4>	
</div>
<%--
<%
MemberDTO memberDTO = new MemberDTO();

%>

 <div>

	<strong><%= memberDTO.getName() %></strong>
    <a href="<c:url value="/logout.do" />">Logout</a>
	
</div> --%>

<br><br>



<table class="table table-hover">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
<c:forEach var="boardDTO" items="${list}">
	<tr>
		<td>${boardDTO.seq}</td>
		<td><a id="subjectA" href="#" onclick="isLogin(${boardDTO.seq})">
			${boardDTO.subject}</a></td>
		<td>${boardDTO.id}</td>
		<td>${boardDTO.logtime}</td>
		<td>${boardDTO.hit}</td>
		<td>${test}</td>
	</tr>
</c:forEach>	

</table>	
	<!-- 페이징 -->
	
<a href="boardWriteForm.do" class="btn btn-default pull-right">글쓰기</a>
	
	<div class="text-center">
	<ul>
		<li class="pagination">
		<c:if test="${startPage > 5}">
			[<a id="paging" href="boardList.do?pg=${startPage-1}">이전</a>]
		</c:if>		
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1"> 
			<c:if test="${pg == i}">
				[<a id="currentPaging" href="boardList.do?pg=${i}">${i}</a>] 
			</c:if>
			<c:if test="${pg != i}">
				[<a id="paging" href="boardList.do?pg=${i}">${i}</a>]
			</c:if>		
		</c:forEach>
		
		<c:if test="${endPage < totalP}">
			[<a id="paging" href="boardList.do?pg=${endPage+1}">다음</a>]
		</c:if>			
		</li>
	</ul>
	</div>

<%@ include file="../include/footer.jsp" %>

</body>
</html>



















