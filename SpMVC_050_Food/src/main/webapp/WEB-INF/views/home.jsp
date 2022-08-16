<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />    
   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>나의 API App</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
* {
	box-sizing: border-box;
	margin:0;
	padding:0;
}

html {
	width: 100vw;
	height: 100vh;
}



body {
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;
}

section.main {
	flex:1;
}



header {
  padding: 1.2rem;
  text-align: center;
  background-color: rgb(193, 226, 237);
  color: rgb(47.41, 41);
  font-weight: 900;
}

nav {
  background-color: skyblue;
  color: white;
}

nav ul {
  list-style: none;
  display: flex;
}

nav li {
  padding: 3px 16px;
  margin: 15px;
  border-bottom: 5px solid transparent;
  font-weight: 900;
  font-size: 20px;

  transition: border 0.7s ease-out;
}

nav li:hover {
  cursor: pointer;
  border-bottom: 5px solid rgb(241, 241, 114);
}

nav li:nth-of-type(4) {
	margin-left:auto;
}

a {
  text-decoration: none;
}

nav a {
  color: inherit;
  white-space: nowrap;
}


footer{
	background-color: skyblue;
	text-align: center;
	padding:1rem;
}
</style>
<script>
	const rootPath = '${rootPath}'
</script>

</head>
<body>
	<header>
		<h1>MY Api</h1>
		<p>대한민국 공공 DB API 를 활용한 정보 서비스</p>
	</header>
	<nav>
		<ul>
			<li><a href="${rootPath}/">Home</a></li>
			<li><a href="${rootPath}/about">대하여</a></li>
			<li><a href="${rootPath}/food">맛집정보</a></li>
	
			<% // 로그인을 하지 않았을때 %>		
			<c:if test="${ empty USER}">
				<li><a href="${rootPath}/user/login">로그인</a></li>
				<li><a href="${rootPath}/user/join">회원가입</a></li>
			</c:if>
			
			<% // 로그인을 하였을때는 MEMBER 객체에 로그인한 사용자 정보가 담겨 있다 %>
			<c:if test="${not empty USER}">
				<li><a href="${rootPath}/user/mypage">My Page(${USER.nickname})</a></li>
				<li><a href="${rootPath}/user/logout">Logout</a></li>
			</c:if>
			
		</ul>
	</nav>
	<section class="main">
		<c:choose>
			<c:when test="${LAYOUT == 'JOIN' }">
				<% // 회원가입 폼 include %>
			</c:when>
			<c:when test="${LAYOUT == 'LOGIN' }">
				<% // 로그인 include %>
			</c:when>
			<c:when test="${LAYOUT == 'MYPAGE' }">
				<% // mypage include %>
			</c:when>
		</c:choose>
	</section>
	<footer class="main">
		<address>CopyRight &copy; callor@callor.com</address>	
	</footer>
</body>

</html>
