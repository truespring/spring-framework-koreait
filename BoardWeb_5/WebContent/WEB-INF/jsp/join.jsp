<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<div id="container">
		<div>
			<form action="/join" method="post">
				<div><label><input type="text" name="user_id" placeholder="아이디"></label></div>
				<div><label><input type="password" name="user_pw" placeholder="비밀번호"></label></div>
				<div><label><input type="password" name="user_pw" placeholder="비밀번호 확인"></label></div>
				
			
			</form>
		</div>
	</div>
</body>
</html>