<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
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

	if("".equals(title) || "".equals(ctnt) || "".equals(strI_student)) {
		response.sendRedirect("/jsp/boardWrite.jsp?err=10");
		return;
	} // 내용을 쓰지않고 글등록을 눌렀을 때 에러로 빠지게 하는 조건문
	
	int i_student = Integer.parseInt(strI_student); // strI_board에 null값 or 문자열이 들어오면 예외 발생! 숫자로 바꿀 수 없는 값이기 때문, 이때는 try catch문을 사용해야한다.

	Connection con = null;
	PreparedStatement ps = null;
	
	String sql = " INSERT INTO t_board(i_board, title, ctnt, i_student) " 
			+ " SELECT nvl(max(i_board),0) + 1, ?, ?, ? "
			+ " FROM t_board "; // 커리문이 여러개가 날아오면 섞일 수 있다
	int result = -1;
	try{
		con = getCon();
		ps = con.prepareStatement(sql); // 문장 완성 기능
		ps.setNString(1, title);
		ps.setNString(2, ctnt);
		// ps.setString(3, strI_student);
		ps.setInt(3, i_student);
		
		result = ps.executeUpdate(); // delete 혹은 insert에서 사용하자!(사용하지 않으면 ?자리에 ?가 들어간다)
		// 0 혹은 1이 넘어오고 0은 실패 1은 성공
	} catch (Exception e){
		e.printStackTrace();
	} finally {
		if(ps != null){	try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} }
	}
	
	int err = 0;
	switch(result) {
	case 1: 
		response.sendRedirect("/jsp/boardList.jsp");
		return; // 메소드를 종료시킨다 -> 맨 아래의 메소드를 실행시키지 않게 하기 위해서
	case 0:
		err = 10;
		break;
	case -1: // 입력 범위를 초과했을 때 발생한다 or 무결성을 위배했을 때
		err = 20;
		break;
	}
	response.sendRedirect("/jsp/boardWrite.jsp?err=" + err);
%>