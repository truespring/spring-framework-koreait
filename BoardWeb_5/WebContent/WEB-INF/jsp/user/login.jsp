<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	#container {
		width: 600px; height: 600px; margin: 50px auto; text-align: center;
		border: 1px solid black; padding: 5px;
	}
	.id {
		display: inline-block;
	}
	.id input {
		width: 350px; height: 30px;margin-top:40px; margin-bottom: 20px;
	}
	.pw {
		display: inline-block;
	}
	.pw input {
		width: 350px; height: 30px; margin-bottom: 20px;
	}
	.btn_login {
		margin-bottom: 20px;
	}
	#btn_login {
		width: 360px; height: 40px; text-align: center; background-color: #03C75A;
		border: none; color: white;
	}
	#btn_join {
		width: 360px; height: 40px; text-align: center; background-color: #03C75A;
		border: none; color: white;
	}
	.msg {
		color: red;
	}
</style>
</head>
<body>
	<div id="container">
		<h1>드루와</h1>
		<div>
			<form id="frm" action="/login" method="post" onsubmit="return chk()">
				<div class="id"><input type="text" id="user_id" name="user_id" value="${user_id }" placeholder="아이디"></div>
				<div class="pw"><input type="password" id="user_pw" name="user_pw" placeholder="비밀번호"></div>
				<div class="msg">${msg }</div>
				<div class="btn_login"><input type="submit" id="btn_login" value="로그인"></div>
			</form>
			<div class="btn_join"><a href="/join"><button id="btn_join">회원가입</button></a></div>
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