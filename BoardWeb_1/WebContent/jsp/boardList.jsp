<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
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
	List<BoardVO> boardList = new ArrayList();
	Connection con = null; // 연결담당 - DB와 연결하기 위함
	PreparedStatement ps = null; // 커리문 완성 
	ResultSet rs = null; // SELECT문의 결과를 담는다
	
	String sql = " SELECT i_board, title FROM t_board ";
	
	try{
		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery(); //SELECT 때만 사용하는 메소드 executeQuery는 crtl + enter 하는 것
		
		while(rs.next()){ // DB의 레코드가 있으면 true를 리턴하며 반복한다
			int i_board = rs.getInt("i_board"); // 컬럼명 입력
			String title = rs.getNString("title");
			
			BoardVO vo = new BoardVO();
			vo.setI_board(i_board);
			vo.setTitle(title); // 여기까지 파싱작업
			
			boardList.add(vo);
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally { // 닫을 때는 열 때의 역순
		if(rs != null){	try{ rs.close(); } catch(Exception e) {} }
		if(ps != null){	try{ ps.close(); } catch(Exception e) {} }
		if(con != null){ try{ con.close(); } catch(Exception e) {} }
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>
	<h2>JSP 입문</h2>
	<hr>
	<%="게시판 리스트" %>
	<div>게시판 리스트</div>
	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
		</tr>
		
		<% for(BoardVO vo : boardList){ %>
		<tr>
			<td><%=vo.getI_board() %></td>
			<td><%=vo.getTitle() %></td>
		</tr>
		<% } %>
	</table>
</body>
</html>