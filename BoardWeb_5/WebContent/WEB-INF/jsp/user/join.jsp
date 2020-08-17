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
			<form id="frm" action="/join" method="post" onsubmit="return chk()">
				<div><label><input type="text" name="user_id" placeholder="아이디" required></label></div>
				<div><label><input type="password" name="user_pw" placeholder="비밀번호" required></label></div>
				<div><label><input type="password" name="user_pwre" placeholder="비밀번호 확인"></label></div>
				<div><input type="text" name="nm" placeholder="이름" required></div>
				<div><input type="email" name="email" placeholder="이메일" required></div>
				<div><input type="submit" value="회원가입"></div>
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