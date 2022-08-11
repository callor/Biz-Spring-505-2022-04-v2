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
	
	div.todo_content {
		cursor:pointer; 
		
	}
	
	div.complete {
		text-decoration:line-through wavy;
		color:green;
	}

</style>
<script>
document.addEventListener("DOMContentLoaded",()=>{
    const div_body = document.querySelector("div.todo_body")
    div_body?.addEventListener("click",(e)=>{
        const target = e.target
        if(target.tagName == "DIV" 
        		&& target.classList?.contains("todo_content")) {
        	const seq = target?.dataset.seq
        	// if(seq == false)
        	if(!seq) {
        		alert("완료된 항목은 수정할수 없음")
        		return false
        	}
        	document.location.href = "${rootPath}/todo/update?t_seq=" + seq
        			
        } else if (target.tagName == "SPAN" 
        		&& target.classList?.contains("todo_content")) {
        	
        	const parentDiv = target.closest("DIV")
        	console.log(parentDiv)
        	const seq = parentDiv?.dataset.seq
        	document.location.href = "${rootPath}/todo/comp?t_seq=" + seq 
        }
    })
})
</script>

<div class="todo_body w3-card-4">
	<sec:authorize access="isAuthenticated()">
		<h1 class="w3-text-blue">[ <sec:authentication property="principal.username" /> ] 님의 TODO LIST</h1>
		<c:forEach items="${TODOS}" var="TODO">
			<div 
				<c:if test='${empty TODO.t_edate}'> data-seq="${TODO.t_seq}" </c:if>
				title="시작 : ${TODO.t_sdate}, ${TODO.t_stime}" 
				class="todo_content w3-border w3-padding-16 w3-margin w3-tooltip
				<c:if test='${not empty TODO.t_edate}'> complete </c:if>
				">
				${TODO.t_content}
				<span class="todo_content w3-text">
					( 시작 : ${TODO.t_sdate }  ${TODO.t_stime } )
				</span>
				
				<c:if test="${not empty TODO.t_edate}">
					<span class="todo_content">완료</span>
					<span class="todo_content">${TODO.t_edate}</span>
					<span class="todo_content">${TODO.t_etime}</span>
				</c:if>

				<c:if test="${TODO.t_complete}">
					<span class="todo_content">완료</span>
					<span class="todo_content">${TODO.t_edate}</span>
					<span class="todo_content">${TODO.t_etime}</span>
				</c:if>
				
			</div>
		</c:forEach>
		<form:form>
			<input name="t_content"
					value="${TODO.t_content}" 
					placeholder="TODO Insert" 
					class="w3-input w3-border"/>
		</form:form>
	</sec:authorize>
</div>

