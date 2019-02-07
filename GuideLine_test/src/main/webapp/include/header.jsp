<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

response.setHeader("Cache-Control", "no-cache, no-store"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", -1); // Proxies.

%> 
<h1>
	<a href="/main/main.jsp"><img src="../img/A-Team-logo.png" alt="A-Team logo" /></a>
</h1>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">   
    <ul class="nav navbar-nav">
      <li class="active"><a href="/main/main.jsp">Home</a></li>
      <li><a href="../board/boardList.do?pg=1">A-Team Board</a></li>
      <li><a href="#">Security</a></li>
      <li><a href="#">Network</a></li>
      <li><a href="#">Develop</a></li>
      <li><a href="#">Picture</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    
    	<c:choose>
		<c:when test="${empty memId}">		
		      <li><a href="#"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
		      <li><a href="../index.jsp"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
      	</c:when>
			<c:otherwise>
		      <li><a href="#"><span class="glyphicon glyphicon-user">${memName} </span> </a></li>
		      <li><a href="/logout.do"><span class="glyphicon glyphicon-log-out"></span> 로그아웃 </a></li>		
			</c:otherwise>
		</c:choose>
    </ul>
  </div>
</nav>
