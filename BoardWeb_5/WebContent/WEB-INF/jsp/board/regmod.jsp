<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data == null ? '등록하기' : '수정하기' }</title>
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
			<input type="hidden" name="i_board" value="${data.i_board }">
			<div id="title">제목 <br><input type="text" name="title" value="${data.title }"></div>
			<div id="ctnt">내용 <br><textarea name="ctnt" cols="70" rows="10">${data.ctnt }</textarea></div>
			<div><input type="submit" id="btn_in" value='${data.i_board == null ? "등록하기" : "수정하기" }'></div>
		</form>
	</div>
</body>
</html>