<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>Insert title here</title>
</head>
<body>
	<form method="POST">
		<div>
			<label>교육청 코드</label>
			<input name="sc_code">
		</div>
		<div>
			<label>학교코드</label>
			<input name="sc_num">
		</div>
		<div>
			<label>조회 일자</label>
			<input name="sdate"> ~ <input name="edate">
		</div>
		<button>검색</button>
	</form>
</body>
</html>