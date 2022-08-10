<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<style>
	div.todo_body {
		width: 60%;
		margin:10px auto;
		padding:2rem;
	}

</style>
<div class="todo_body w3-card-4">
	<sec:authorize access="isAuthenticated()">
		<h1 class="w3-text-blue">[ <sec:authentication property="principal.username" /> ] 님의 TODO LIST</h1>
		<c:forEach items="${TODOS}" var="TODO">
			<div class="todo_content">${TODO.t_content}</div>
		</c:forEach>
		<form:form action="${rootPath}/todo/insert">
			<input name="t_content" placeholder="TODO Insert" 
					class="w3-input w3-border"/>
		</form:form>
	</sec:authorize>
</div>

