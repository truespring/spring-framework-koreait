<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
	<c:if test="${isAuth == false || isAuth == null }"> <!-- 현재 비밀번호 확인 -->
		<div>
			<form action="/changePw" method="post">
				<input type="hidden" name="type" value="1">
				<div>
					<label><input type="password" name="pw" placeholder="현재 비밀번호"></label>
					<p>${msg }</p>
				</div>
				<div>
					<label><input type="submit" value="확인"></label>
				</div>
			</form>
		</div>
	</c:if>
	<c:if test="${isAuth == true }"><!-- 비밀번호 변경 -->
		<div>
			<form action="/changePw" method="post" onsubmit="return chk()" id="changeFrm">
				<input type="hidden" name="type" value="2">
				<div>
					<label><input type='password' name="pw" placeholder='변경 비밀번호'></label>
				</div> 
				<div>
					<label><input type="password" name="repw" placeholder="변경 비밀번호 확인"></label>
				</div>
				<div>
					<input type="submit" value="확인">
				</div>
			</form>
		</div>
	</c:if>
	<script>
		function chk() {
			if(changeFrm.pw.value.length < 5) {
				alert('비밀번호를 5글자 이상 입력하세요.')
				changeFrm.pw.focus()
				return false
			} else if(changeFrm.pw.value != changeFrm.repw.value) {
				alert('비밀번호를 확인하세요')
				changeFrm.focus()
				return false
			}
		}
	</script>
</body>
</html>