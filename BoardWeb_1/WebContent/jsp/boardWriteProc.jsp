<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.web.BoardVO" %>
<% 	// 글쓰기 이후 실질적으로 작동하는 페이지 %>
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
	// request.setCharacterEncoding("UTF-8"); // post 방식의 utf-8 설정
	String title = request.getParameter("title");
	String ctnt = request.getParameter("ctnt");
	String strI_student = request.getParameter("i_student");
	
	Connection con = null;
	PreparedStatement ps = null;
	
	String sql = " INSERT INTO t_board(i_board, title, ctnt, i_student) " 
			+ " SELECT nvl(max(i_board),0) + 1, ?, ?, ? "
			+ " FROM t_board ";
	int i_student = Integer.parseInt(strI_student); // strI_board에 null값이 들어오면 예외가 발생! 숫자로 바꿀 수 없는 값이기 때문, 이때는 try catch문을 사용해야한다.
	
	try{
		con = getCon();
		ps = con.prepareStatement(sql); // 문장 완성 기능
		ps.setString(1, title);
		ps.setString(2, ctnt);
		// ps.setString(3, strI_student);
		ps.setInt(3, i_student);
		ps.executeUpdate(); // delete 혹은 insert에서 사용하자!
		
	} catch (Exception e){
		e.printStackTrace();
	} finally {
		if(ps != null){	try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} }
	}

%>

<div>title : <%= title %></div>
<div>ctnt : <%= ctnt %></div>
<div>strI_student : <%= strI_student %></div>
