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
	if(strI_board == null) {
%>
		<script>
			alert("잘 못 된 접근입니다.");
			location.href='/jsp/boardList.jsp';
		</script>
		
<%
		return; // 메소드를 종료시키기 위해 반드시 들어가야한다.
	}
	
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board = " + strI_board;
	String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board = ?";

	int i_board = Integer.parseInt(strI_board);
	BoardVO vo = new BoardVO();

	try{
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setInt(1, i_board); // 커리문에 있는 물음표의 순서, 물음표에 주입할 값(값이 그대로 들어감)
		// ps.setString(1, strI_board); 문자열이기 때문에 ''으로 값이 들어감
		rs = ps.executeQuery(); // 커리문을 완성하고 다음 단계로 진입해야 에러가 발생하지 않는다.
		
		if(rs.next()){ // 레코드가 없으면 실행이 되지 않기 때문에 반드시 필요하다.
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
	<div><a href="/jsp/boardList.jsp">리스트로 가기</a></div>
	<div>
	<a href="#" onclick="procDel(<%=i_board %>)">삭제</a>
	</div>
	<div>상세 페이지 : <%=strI_board %></div>
	<table>
		<tr>
			<th>제목</th>
			<th>내용</th>
			<th>학생번호</th>
		</tr>
		<tr>
			<td><%=vo.getTitle() %></td>
			<td><%=vo.getCtnt() %></td>
			<td><%=vo.getI_student() %></td>
		</tr>
	</table>
	<script>
		function procDel(i_board) {
			// alert('i_board : ' + i_board)
			var result = confirm('삭제하시겠습니까?');
			if(result){
				location.href = '/jsp/boardDel.jsp?i_board=' + i_board;
			}
		}
	</script>
</body>
</html>