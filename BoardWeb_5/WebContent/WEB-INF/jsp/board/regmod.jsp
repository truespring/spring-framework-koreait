<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록/수정</title>
<style>
	.container {
		width: 600px; margin: 30px auto;
	}
	#title {
		margin: 20px;
	}
	#ctnt {
		margin: 20px;
	}
	#btn_in {
		width: 360px; height: 40px; text-align: center; background-color: #03C75A;
		border: none; color: white;
	}
	#msg {
		color: red;
	}
</style>
</head>
<body>
	<div class="container">
	<div id="msg">${msg }</div>
		<form id="frm" action="regmod" method="post">
			<input type="hidden" name="i_board" value="${data }">
			<div id="title">제목 <br><input type="text" name="title"></div>
			<div id="ctnt">내용 <br><textarea name="ctnt" cols="70" rows="10"></textarea></div>
			<div><input type="submit" id="btn_in" value="등록하기"></div>
		</form>
	</div>
</body>
</html>