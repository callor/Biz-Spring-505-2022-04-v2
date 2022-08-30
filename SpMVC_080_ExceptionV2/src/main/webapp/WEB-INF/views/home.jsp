<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>반갑습니다</h1>
	<%
	/*
	현재 home.jsp 는 HomeController의 home() method 에 의해서
	사용자(Web Browser)에게 보여진다
	
	사용자는
	http://localhost:8080/exp/ 라고 Request를 Server에 보냈을때
	Tomcat 에 의해서 실행되고 있는 우리의 프로젝트(exp)를 찾고
	
	우리의 프로젝트에 설정된 RequestMapping 중에서 / 로 설정된
	Controller 또는 method 를 찾는다
	
	그리고 결과 home.jsp 를 Response 한 것이다
	
	즉 사용자 입장에서는
	http://localhost:8080/exp/ 라는 Request(요청)에 대해서
	home.jsp 에 설정된 html 화면을 Response(응답) 받게 된 것이다
	
	이 파일(home.jsp) 에 form tag 로 입력 box 를 만들었고
	여기에 method 는 POST 지정을 했다
	하지만 actioon 부분을 설정하지 않았기 때문에
	
	이 폼에서 로그인 버튼을 클릭하게 되면
	http://localhost:8080/exp/ Request에 
	POST 로 데이터를 보내게 된다
	헌재 input box 에 데이터를 담아서 보내니 이 데이터를 서버에서
	받아서(수신해서) 적당히 처리해 달라
	*/
	%>
	<form method="POST">
		<div>
			<label>USER NAME</label>
			<input name="username"/>
		</div>
		
		<div>
			<label>PASSWORD</label>
			<input name="password" type="password" />
		</div>
		<div>
			<button>로그인</button>
		</div>
	</form>

</body>
</html>