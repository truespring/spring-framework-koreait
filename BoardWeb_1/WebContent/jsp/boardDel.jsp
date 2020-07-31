<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.web.BoardVO" %>
<%! 
	// 메시지 구성
	Connection getCon() throws Exception{
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userName = "hr";
	String password = "koreait2020";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url, userName, password); 
	System.out.println("접속 성공!");
	return con;
}
%> 
<%
		
	String strI_board = request.getParameter("i_board"); 
	int i_board = Integer.parseInt(strI_board); // strI_board에 null값이 들어오면 예외가 발생! 숫자로 바꿀 수 없는 값이기 때문, 이때는 try catch문을 사용해야한다.
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String sql = " DELETE FROM t_board WHERE i_board = ?";

	int result = -1; // 문법적 에러가 나면 -1 (데이터베이스 에러)
	try{
		con = getCon();
		ps = con.prepareStatement(sql); // 문장 완성 기능
		ps.setInt(1, i_board); 
		result = ps.executeUpdate(); // 실행은 되었으나 삭제를 하나도 못했다면 0이 넘어옴, 1이 넘어오면 삭제 성공
		
		if(rs.next()){ 
			String title = rs.getNString("title");
			String ctnt = rs.getNString("ctnt");
			int i_student = rs.getInt("i_student");
		}
	} catch (Exception e){
		e.printStackTrace();
	} finally {
		if(ps != null){	try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} }
	}
	
	System.out.println("result : " + result);
	switch(result){
	case -1:
		response.sendRedirect("/jsp/boardDetail.jsp?err=-1&i_board=" + i_board);
		break;
	case 0:
		response.sendRedirect("/jsp/boardDetail.jsp?err=0&i_board=" + i_board);
		break;
	case 1:
		response.sendRedirect("/jsp/boardList.jsp");
		break;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div><a href="/jsp/boardList.jsp">리스트로 가기</a></div>
</body>
</html>