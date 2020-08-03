<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.koreait.web.BoardVO" %>
<%!
	Connection getCon() throws Exception{
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
	List<BoardVO> boardList = new ArrayList(); // 여러 개의 레코드를 가져오기 위해 List를 사용한다 ex) List는 서랍장, BoardVO는 서랍
	Connection con = null; // 연결 담당 - DB와 연결하기 위함
	PreparedStatement ps = null; // 커리문 실행 담당 - 문장 완성 기능도 있다. 
	ResultSet rs = null; // SELECT문의 결과를 담는다(SELECT문에서만 사용한다) 선택한 결과를 담는다
	
	String sql = " SELECT i_board, title FROM t_board ORDER BY i_board DESC "; // SELECT문에서만 사용
	
	try{
		con = getCon(); // 연결된 상태
		ps = con.prepareStatement(sql); // 객체화된 커리문을 리턴하여 받는다.
		rs = ps.executeQuery(); //SELECT 때만 사용하는 메소드 executeQuery는 crtl + enter 하는 것
		
		while(rs.next()){ // DB의 레코드가 있으면 true를 리턴하며 반복한다
			int i_board = rs.getInt("i_board"); // 테이블에 존재하는 컬럼명 입력
			String title = rs.getNString("title"); // 정수형이 문자형으로 넘어오고 문자형은 정수형으로 넘어올 수 없다.
			
			BoardVO vo = new BoardVO(); // 굉장히 중요함! 반드시 while문 안에서 객체화 해야한다. 밖에서 생성하면 마지막 레코드만 출력학게 된다.
			vo.setI_board(i_board);
			vo.setTitle(title); // 여기까지 파싱작업
			
			boardList.add(vo);
		}
	} catch(Exception e){ // 예외처리가 굉장히 중요하다 공부하는 것이 좋다.
		e.printStackTrace();
	} finally { // 닫을 때는 열 때의 역순
		if(rs != null){	try{ rs.close(); } catch(Exception e) {} } // 각자 처리하고 싶은 것이 달라서 나눴다. try하나에 3개 모두 담으면 rs에서 에러가 발생했을 떄 ps, con은 실행하지 않고 닫지 않는다.
		if(ps != null){	try{ ps.close(); } catch(Exception e) {} } // 객체가 null인 상태에서 close()를 하면 에러가 발생한다.
		if(con != null){ try{ con.close(); } catch(Exception e) {} } // 닫아주는 이유는 리소스를 줄이기 위해서, http서버는 한번 뿌리고나서 연결을 끊어버린다.
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
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
 table th {
 	border: 1px solid black; padding: 10px; background-color: #ccc;
 }
 table td {
 	border: 1px solid black; padding: 10px; text-align: justify;
 }
</style>
<body>
	<div class="container">
		<h2>JSP 입문</h2>
		<hr>
		<div id="write">
			게시판 리스트
			<a href="/jsp/boardWrite.jsp"><button>글쓰기</button></a>
		</div>
		<table>
			<tr>
				<th>No</th>
				<th>제목</th>
			</tr>
			<% for(BoardVO vo : boardList){ %>
			<tr>
				<td><%=vo.getI_board() %></td>
				<td>
					<a href="/jsp/boardDetail.jsp?i_board=<%=vo.getI_board() %>">
						<%=vo.getTitle() %>
					</a>
				</td>
			</tr>
			<% } %>
		</table>
	</div>
</body>
</html>