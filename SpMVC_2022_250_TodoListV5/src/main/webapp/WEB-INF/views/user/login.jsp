<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />    
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<style>
	form.w3-container {
		width: 50%;
		margin:10px auto;
	}
	
</style>
<form:form class="w3-container">
	<fieldset class="w3-padding-32">
		<legend class="w3-text-blue w3-center">로그인</legend>
		
		<c:if test="${error=='LOGIN_NEED'}">
			<div class='w3-text-red'>* 로그인이 필요한 서비스 입니다 *</div>
		</c:if>
		<label class="w3-text-blue">USER NAME</label>
		<input name="username" class="w3-input w3-border"/>	
	
		<label class="w3-text-blue">비밀번호</label>
		<input name="password" class="w3-input w3-border"/>
		
		<button class="w3-button w3-green w3-right">로그인</button>	
	</fieldset>
</form:form>