<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<style>
	.container {
		margin: 30px auto;
	}
	.container h2 {
		text-align: center;
	}
	table {
	 	border: 1px solid black; border-collapse: collapse; width: 300px;
	 	margin: 0 auto; 
	}
	table th {
	 	border: 1px solid black; padding: 10px; background-color: #ccc;
	}
	table td {
	 	border: 1px solid black; padding: 10px; text-align: justify;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>JSP 입문</h2>
		<hr>
		<div>상세 페이지</div>
		<div>글번호 : ${data.i_board}</div>
		<% // 이런 형식을 EL식이라고 하며 4가지의 내장 객체에서만 사용이 가능하다
		   // 그 중에서 제일 먼저 만나는 객체를 우선 순위로 두고 없다면 순차적으로 확인한다%>
		<div>제목 : ${data.title}</div>
		<div>내용 : ${data.ctnt}</div>
		<div>작성자: ${data.i_student}</div>
		<div><button onclick="doDel(${data.i_board})">삭제</button></div>
	</div>
	<script>
		function doDel(i_board) { // 객체 밖에 있으면 함수, 객체 안에 있으면 메소드
			if(confirm('삭제하시겠습니까?')){
				location.href='/boardDel?i_board=' + i_board;
			}
		}
	</script>
</body>
</html>