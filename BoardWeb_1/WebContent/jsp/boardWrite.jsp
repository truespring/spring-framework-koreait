<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<div>
		<form action="/jsp/boardWriteProc.jsp" method="POST"><%// form태그를 통해 값들을 전달하는데 그 방식은 POST방식과 GET방식이 존재한다 %>
			<% // 글쓰는 담당, DB에 insert하는 담당 %>
			<div><label>제목 : <input type="text" name="title"></label></div>
			<% // 속성(name)과 값(value)을 쓰는 이유는 DB에게 값을 날릴 때 키 값이 된다
			// id는 유일성으로 사용하고 class는 그룹의 의미로 사용한다. %>
			<div><label>내용 : <textarea name="ctnt"></textarea></label></div>
			<div><label>작성자 : <input type="text" name="i_student"></label></div>
			<div><input type="submit" value="글등록"></div>
		</form>
	</div>
</body>
</html>