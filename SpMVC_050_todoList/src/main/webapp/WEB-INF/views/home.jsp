<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />   
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%> 
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Insert title here</title>
</head>
<body>
<h1>반갑습니다</h1>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username"/>
	<sec:authentication property="principal.email"/>
	<sec:authentication property="principal.realname"/>
</sec:authorize>

<form:form class="logout" action="${rootPath}/logout">
	<button>로그아웃</button>
</form:form>

</body>
</html>