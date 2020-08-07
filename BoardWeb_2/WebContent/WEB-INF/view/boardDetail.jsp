<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>
	<div>상세 페이지</div>
	<div>글번호 : ${data.i_board}</div>
	<% // 이런 형식을 EL식이라고 하며 4가지의 내장 객체에서만 사용이 가능하다
	   // 그 중에서 제일 먼저 만나는 객체를 우선 순위로 두고 없다면 순차적으로 확인한다%>
	<div>제목 : ${data.title}</div>
	<div>내용 : ${data.ctnt}</div>
	<div>작성자: ${data.i_student}</div>
</body>
</html>