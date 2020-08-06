<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.koreait.board.vo.BoardVO" %>
<%
	List<BoardVO> list = (List)request.getAttribute("data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
 .container {
 	margin: 30px auto; text-align: center;
 }
 #write {
 	margin: 20px;
 }
 #write button {
 	width: 100px; 
 }
 table td a {
 	text-decoration: none; color: black;
 }
 table {
 	border: 1px solid black; border-collapse: collapse; width: 300px;
 	margin: 0 auto; 
 }
 table .itemRow:hover {
 	background-color: #ecf0f1; cursor: pointer;
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
		<div id="write">
			게시판 리스트
			<a href="/boardWrite.jsp"><button>글쓰기</button></a>
		</div>
		<table>
			<tr>
				<th>No</th>
				<th>제목</th>
			</tr>
			<% for(BoardVO vo : list){ %>
			<tr class="itemRow" onclick="moveToDetail(<%=vo.getI_board() %>)">
				<td><%=vo.getI_board() %></td>
				<td><%=vo.getTitle() %></td>
			</tr>
			<% } %>
		</table>
	</div>
	<script>
		function moveToDetail(i_board){
				console.log("moveToDetail - i_board : " + i_board);
				location.href = "boardDetail?i_board=" + i_board;
		}
	</script>
</body>
</html>