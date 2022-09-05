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
	background-image: url("${rootPath}/static/image0002.jpg");
	background-repeat: no-repeat;
	background-position: left center;
	background-attachment: fixed;
	background-size: contain;
}

a {
	text-decoration: none;
}

#myform fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
#myform fieldset legend{
    text-align: right;
}
#myform input[type=radio]{
    display: none;
}
#myform label{
    font-size: 3em;
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}
#myform label:hover{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#reviewContents {
    width: 100%;
    height: 150px;
    padding: 10px;
    box-sizing: border-box;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    font-size: 16px;
    resize: none;
}

footer {
	background-color: orange;
	color: white;
	text-align: center;
	padding: 1rem;
}
</style>
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
		<section class="w3-container">
			<div>
				<table class="w3-table-all w3-margin">
					<tr>
						<th>급식일자</th>
						<td>${LUNCH.MLSV_YMD}</td>
					</tr>
					<tr>
						<th>요리명</th>
						<td>${LUNCH.DDISH_NM}</td>
					</tr>
					<tr>
						<th>원산지정보</th>
						<td>${LUNCH.ORPLC_INFO}</td>
					</tr>
					<tr>
						<th>칼로리정보</th>
						<td>${LUNCH.CAL_INFO}</td>
					</tr>
					<tr>
						<th>영양정보</th>
						<td>${LUNCH.NTR_INFO}</td>
					</tr>
					<tr>
						<th>알러지정보</th>
						<td>요리명에 표시된 번호를 확인하세요.<br>1.난류, 2.우유, 3.메밀, 4.땅콩, 5.대두,
							6.밀, 7.고등어, 8.게, <br>9.새우, 10.돼지고기, 11.복숭아, 12.토마토, 13.아황산염,
							<br>14.호두, 15.닭고기, 16.쇠고기, 17.오징어, 18.조개류(굴,전복,홍합 등)
						</td>
					</tr>
				</table>
			</div>
			<sec:authorize access="isAuthenticated()">
			 	<form class="mb-3" name="myform" id="myform" method="post">
					<fieldset>
						<span class="text-bold">식단의 별점을 선택해주세요</span>
						<input type="radio" name="reviewStar" value="5" id="rate1"><label
							for="rate1">★</label>
						<input type="radio" name="reviewStar" value="4" id="rate2"><label
							for="rate2">★</label>
						<input type="radio" name="reviewStar" value="3" id="rate3"><label
							for="rate3">★</label>
						<input type="radio" name="reviewStar" value="2" id="rate4"><label
							for="rate4">★</label>
						<input type="radio" name="reviewStar" value="1" id="rate5"><label
							for="rate5">★</label>
					</fieldset>
					<!-- 				
					<div>
						<textarea class="col-auto form-control" type="text" id="reviewContents"
								  placeholder="영양사님께 응원의 메세지를"></textarea>
					</div>
					 -->
				</form>	
			</sec:authorize>							

		</section>
	</div>
	<footer class="main">
		<address>CopyRight &copy; https://github.com/geunyang</address>
	</footer>
</body>
</html>