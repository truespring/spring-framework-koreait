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
	<div class="msg">${msg }</div>
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
	<script>
		function chk() {
			if(frm.user_id.value.length < 5) {
				alert('아이디를 5글자 이상 입력하세요.')
				frm.user_id.focus()
				return false
			} else if(frm.user_pw.value.length < 5) {
				alert('비밀번호를 5글자 이상 입력하세요.')
				frm.user_pw.focus()
				return false
			}
		}
	</script>
</body>
</html>