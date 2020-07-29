<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.web.BoardVO" %>
<%!
	public Connection getCon() throws Exception{
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userName = "hr";
	String password = "koreait2020";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url, userName, password); // getConnection은 static메소드이고 매개변수를 받는 메소드는 주로 static으로 한다
	System.out.println("접속 성공!");
	return con;
}
%> 
<%
	String strI_board = request.getParameter("i_board"); // 요청 정보, request는 내장(톰캣) 객체이다
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board = " + strI_board;

	BoardVO vo = new BoardVO();
	
	try{
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		if(rs.next()){
			String title = rs.getNString("title");
			String ctnt = rs.getNString("ctnt");
			int i_student = rs.getInt("i_student");
			
			vo.setTitle(title);
			vo.setCtnt(ctnt);
			vo.setI_student(i_student);
		}
	} catch (Exception e){
		e.printStackTrace();
	} finally {
		if(rs != null){	try{ rs.close(); } catch(Exception e) {} } 
		if(ps != null){	try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} }
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<style>
	table {
		border:1px solid black; border-collapse:collapse;
		width: 300px; heigth: 80px;
	}
	th, td {
		border: 1px solid black
	}
</style>
</head>
<body>
	<div>상세 페이지 : <%=strI_board %></div>
	<table>
		<tr>
			<th>제목</th>
			<th>컨텐츠</th>
			<th>학생번호</th>
		</tr>
		<tr>
			<td><%=vo.getTitle() %></td>
			<td><%=vo.getCtnt() %></td>
			<td><%=vo.getI_student() %></td>
		</tr>
	</table>
</body>
</html>