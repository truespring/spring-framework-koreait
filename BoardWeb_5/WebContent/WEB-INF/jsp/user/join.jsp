<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	#container {
		width: 600px; height: 600px; margin: 50px auto; text-align: center;
		border: 1px solid black; padding: 5px;
	}
	.err {
		color: red; font-size: 1.1em;
	}
	input {
		width: 350px; height: 30px; margin-bottom: 20px;
	}
	#btn_join {
		width: 360px; height: 40px; text-align: center; background-color: #03C75A;
		border: none; color: white; margin: 20px; 
	}
	#btn_join:hover {
		cursor: pointer;
	}
</style>
</head>
<body>
	<div id="container">
		<h1>회원가입</h1>
		<div class="err">${msg }</div>
		<div>
			<form id="frm" action="/join" method="post" onsubmit="return chk()">
				<div id="id"><label><input type="text" name="user_id" placeholder="아이디" required value="${data.user_id }"></label></div>
				<div id="pw"><label><input type="password" name="user_pw" placeholder="비밀번호" required></label></div>
				<div id="pwre"><label><input type="password" name="user_pwre" placeholder="비밀번호 확인"></label></div>
				<div id="nm"><input type="text" name="nm" placeholder="이름" required value="${data.nm }"></div>
				<div id="email"><input type="email" name="email" placeholder="이메일" required value="${data.email }"></div>
				<div id="btn"><input type="submit" value="회원가입" id="btn_join"></div>
			</form>
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
			} else if(frm.user_pw.value != frm.user_pwre.value) {
				alert('비밀번호를 확인하세요.')
				frm.user_pwre.focus()
				return false
			}
			if(frm.nm.value.length > 0) { // 이름에 대한 정규화
				const korean = /[^가-힣]/;
				// const result = korean.test(frm.nm.value)
				console.log('result : ' + result)
				if(korean.test(frm.nm.value)) {
					alert('이름은 한글만 입력하세요.')
					frm.nm.focus()
					return false
				}
			}
			if(frm.email.value.length > 0) {
				const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
				
				if(!email.test(frm.email.value)) {
					alert('이메일의 형식을 확인하세요.')
					frm.email.focus()
					return false
				}
			}
			
			return false
		}
	</script>
</body>
</html>