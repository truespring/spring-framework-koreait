<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<style>
	.container {
		width: 800px; margin: 30px auto; text-align: center; padding: 5px;
	}
	table {
		width: 700px; border: 1px solid black;
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
 	table .itemRow:hover {
 		background-color: #ecf0f1; cursor: pointer;
 	}
 	.nm {
 		font-weight: bold; font-size: 1.2em;
 	}
</style>
</head>
<body>
	<div class="container">
		<h1>글목록</h1>
		<div><span class="nm">${loginUser.nm }</span>님 환영합니다.</div> <!-- 세션을 활용하는 법 -->
		<div>
			<a href="regmod">글쓰기</a>
			<a href="/logout">로그아웃</a>
		</div>
		<table>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성 시간</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr class="itemRow" onclick="moveToDetail(${item.i_board})">
					<td>${item.i_board }</td>
					<td>${item.title }[${item.cmt_cnt }]</td>
					<td>${item.nm }</td>
					<td>${item.hits }</td>
					<td>${item.r_dt }</td>
				</tr>
			</c:forEach>
		</table>
		<!--<c:forEach items="${pagingCnt }" var="page">
			<a href="${page.recode_cnt }"></a>
		</c:forEach> -->
	</div>
	<script>
		function moveToDetail(i_board) {
			location.href = "/board/detail?i_board=" + i_board;
		}
	</script>
</body>
</html>