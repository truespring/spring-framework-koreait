<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<div id="container">
		<div>
			<form id="frm" action="/login" method="post" onsubmit="return chk()">
				<div><label>아이디<input type="text" name="user_id"></label></div>
				<div><label>비밀번호<input type="password" name="user_pw"></label></div>
				<div><input type="submit" value="로그인"></div>
			</form>
			<div><a href="/join"><button>회원가입</button></a></div>
		</div>
	</div>
</body>
</html>