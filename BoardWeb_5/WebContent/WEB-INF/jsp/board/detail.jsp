<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세글</title>
<style>
	.container {
		width: 600px; margin: 30px auto;
	}
	div {
		border: 1px solid black;
	}
	#ctnt {
		
	}
</style>
</head>
<body>
	<div class="container">
		<div id="title">제목 : ${data.title }</div>
		<div id="nm">작성자 : ${data.nm }</div>
		<div id="ctnt">내용 : ${data.ctnt }</div>
		<div id="r_dt">작성일시 : ${data.r_dt }</div>
		<div id="hits">조회수 : ${data.hits }</div>
	</div>
</body>
</html>