<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>      
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="/login.do" name="loginForm" method="post">
<input type="text" name="returnURL" value="http://www.naver.com" />
<div class="container">
	<div class="row">					  			
	  			<div class="col-md-3"></div>
	  			<div class="col-md-6 col-xs-12">
	  				<div class="jumbotron">		  			
		  				<h1 class="text-center">A-Team</h1>					
		  				<br>		  				
		  				<p class="text-denger">${error}</p>
		  				<div class="form-group">
		  					<label class="control-lable" for="username">아이디</label>
		  					<input type="text" name="id" class="form-control" placeholder="id">
		  				</div>
		  				
		  				<div class="form-group">
		  					<label class="control-label" for="password">비밀번호</label>
		  					<input type="password" name="pwd" class="form-control" placeholder="password">		  					
		  				</div>
		  					<input type="checkbox" name="remember">아이디 기억하기?
		  				<br><br>	  				
	  					<div class="pull-right">	  					
	  						<button type="submit" class="btn btn-outline.warning">로그인</button>
	  						<button type="reset" class="btn btn-outline-dark">취소</button>
	  					</div>
	  					<br><br>	  					
	  				</div> 
				</div>								
	</div>
</div>  
</form>

<div class="text-center">
	
<a href="https://kauth.kakao.com/oauth/authorize?client_id=b066f3a7f97bd5032787a1af9ee29a5c&redirect_uri=http://localhost:8080/login/kakaologin.do&response_type=code"> 
<img src="./img/kakao_icon.png" class="img-fluid" alt="Responsive image" /> 
</a>

</div>


<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
</body>
</html>
