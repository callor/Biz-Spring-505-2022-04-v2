<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SOME LUNCH</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

html {
	width: 100vw;
	height: 100vh;
}

body {
	width: 100%;
	height: 100%;
}

nav {
	background-color: orange;
	color: white;
}

nav ul {
	list-style: none;
	display: flex;
}

nav li {
	padding: 16px 12px;
}

nav a, .logout {
	text-decoration: none;
	color: inherit;
	magin: 5px 0;
	padding: 0 12px;
	border: none;
	border-bottom: 3px solid transparent;
	transition: 1s;
}

nav a {
	padding-bottom: 2px;
}

nav a:hover, .logout:hover {
	border-bottom: 3px solid yellow
}

nav li:nth-of-type(2) {
	margin-left: auto;
}

nav li:nth-of-type(1) {
	margin-left: 20px;
}

nav li:last-of-type {
	margin-right: 30px;
}

.logout {
	display: inline-block;
	box-sizing: border-box !important;
	background-color: inherit;
	cursor: pointer;
	margin-bottom: -4px;
}

.rows {
	/* 	width: inherit;
	height: inherit; */
	display: flex;
	flex-direction: row;
}

section.main {
	flex: 1;
	overflow: hidden;
}

header {
	padding: 7.2rem;
	text-align: center;
	background-color: none;
	color: white;
	font-weight: 900;
	background-image: url("${rootPath}/static/image0001.jpg");
	background-repeat: no-repeat;
	background-position: left center;
	background-attachment: fixed;
	background-size: contain;
}

a {
	text-decoration: none;
}

footer {
	background-color: orange;
	color: white;
	text-align: center;
	padding: 1rem;
}
</style>
<script src="${rootPath}/static/menu.js?ver=2022-08-25-001"></script>
<script>
	const rootPath = '${rootPath}'
</script>
</head>
<body>
	<nav>
		<ul>
			<li><a href="${rootPath}/">식단표</a></li>

			<sec:authorize access="isAnonymous()">
				<li><a href="${rootPath}/user/login">로그인</a></li>
				<li><a href="${rootPath}/user/join">회원가입</a></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<li><form:form action="${rootPath}/logout">
						<button class="logout">로그아웃</button>
					</form:form></li>
				<li><a href="${rootPath}/user/mypage">MYPAGE</a></li>
			</sec:authorize>
		</ul>
	</nav>
	<div class="rows">
		<header>
			<h1>어떤급식</h1>
		</header>

		<section class="main w3-container">
			<c:choose>
				<c:when test="${LAYOUT == 'JOIN' }">
					<%@ include file="/WEB-INF/views/user/join.jsp"%>
				</c:when>
				<c:when test="${LAYOUT == 'LOGIN' }">
					<%@ include file="/WEB-INF/views/user/login.jsp"%>
				</c:when>



				<c:otherwise>
					<article class="welcome">
						<h1>운암중학교 2022년 2학기 중식 식단표</h1>
						<sec:authorize access="isAnonymous()">
							<p>로그인 후 메뉴에 별점을 남길수 있습니다</p>
						</sec:authorize>

						<form method="POST">
							<input name="${_csrf.parameterName}" 
									value="${_csrf.token}" />
							<div>
								<label>교육청코드</label> 
								<input name="s_ofcode" value="F10">
							</div>
							<div>
								<label>학교명</label> 
								<input name="s_scname">
							</div>
							<div>
								<label>조회 일자</label> <input name="sdate"> ~ <input
									name="edate">
							</div>
							<button>검색</button>
						</form>

						<table class="LUNCHS w3-table-all w3-margin">
							<tr>
								<th>날짜</th>
								<th>메뉴</th>
							</tr>
							<c:forEach items="${LUNCHS}" var="LUNCH">
								<tr data-mlsv_ymd="${LUNCH.MLSV_YMD}">
									<td>${LUNCH.MLSV_YMD}</td>
									<td>${LUNCH.DDISH_NM}</td>
								</tr>
							</c:forEach>
						</table>
					</article>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
	<footer class="main">
		<address>CopyRight &copy; https://github.com/geunyang</address>
	</footer>
</body>
</html>