<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<style>
	table {
		width: 500px; border: 1px solid black;
		border-collapse: collapse;
	}
	th {
		border: 1px solid black; padding: 10px;
		background-color: #03C75A
	}
	td {
		border: 1px solid black; text-align: right;
		padding: 10px;
	}
</style>
</head>
<body>
	<h1>글목록</h1>
	<div>${loginUser.nm }님 환영합니다.</div> <!-- 세션을 활용하는 법 -->
	<div>
		<a href="/regmod">글쓰기</a>
	</div>
	<div class="container">
		<table>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성 시간</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.i_board }</td>
					<td>${item.title }</td>
					<td>${item.i_user }</td>
					<td>${item.hits }</td>
					<td>${item.r_dt }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>