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
			<tr>
				<td><%=vo.getI_board() %></td>
				<td>
					<a href="/boardDetail.jsp?i_board=<%=vo.getI_board() %>">
						<%=vo.getTitle() %>
					</a>
				</td>
			</tr>
			<% } %>
		</table>
	</div>
</body>
</html>