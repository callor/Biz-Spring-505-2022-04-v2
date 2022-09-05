<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form method="POST">
		<div>
			<label>USERNAME</label>
			<input name="username">
		</div>
		<div>
			<label>PASSWORD</label>
			<input name="password" type="password"/>
		</div>
		<div>
			<button>회원가입</button>
		</div>
		<input name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html>