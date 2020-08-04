<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
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
	String msg = ""; // 에러 메시지
	String err = request.getParameter("err"); // err 값을 받아서 어떤 에러인지 판단
	if(err != null){
		switch(err) {
		case "10":
			msg = "등록할 수 없습니다.";
			break;
		case "20":
			msg = "DB 에러 발생";
			break;
		}
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
	
	BoardVO vo = new BoardVO();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board = " + strI_board;
	String sql = " SELECT title, ctnt, i_student FROM t_board WHERE i_board = ? ";

	int i_board = Integer.parseInt(strI_board);

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
<title>Insert title here</title>
<style>
	.container {
		margin: 30px auto
	}
	h2 {
		text-align: center;
	}
	#msg {
		color: red;
	}
	#title {
		margin: 20px
	}
	#ctnt {
		margin: 20px
	}
	#author {
		margin: 20px
	}
	#send { 
		float:left; margin: 10px;
	}
	#back {
		float:left; margin: 10px;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>JSP 입문</h2>
		<hr>
		<div id="msg"><%=msg %></div>
		<form id="frm" action="/jsp/boardModProc.jsp?i_board=<%=i_board %>" method="POST" onsubmit="return chk()"><%// form태그를 통해 값들을 전달하는데 그 방식은 POST방식과 GET방식이 존재한다 %>
			<input type="hidden" name="i_board" value="<%=i_board %>">
			<div id="title"><label>제목 : <input type="text" name="title" value="<%=vo.getTitle() %>"></label></div>
			<% // 속성(name)과 값(value)을 쓰는 이유는 DB에게 값을 날릴 때 키 값이 된다.
			// id는 유일성으로 사용하고 class는 그룹의 의미로 사용한다. %>
			<div id="ctnt">
				<label>내용 : <textarea name="ctnt" cols="70" rows="10"><%=vo.getCtnt() %></textarea></label>
			</div>
			<div id="author"><label>작성자 : <input type="text" name="i_student" value="<%=vo.getI_student() %>"></label></div>
			<div id="send"><input type="submit" value="수정하기"></div>
		</form>
		<div id="back">
			<a href="/jsp/boardDetail.jsp?i_board=<%=i_board %>"><button>뒤로가기</button></a>
		</div>
	</div>
	<script>
		function eleValid(ele, nm) {
			if(ele.value.length == 0){
				alert(nm + '을(를) 입력해 주세요.');
				ele.focus();
				return true;
			}
		}
	
		function chk() {
			console.log(`title : \${frm.title.value}`); // \는 Javascript의 $를 쓰고 싶을때 
			// console.log('title : ' + frm.title.value); // 위 아래가 같은 문법
			if(eleValid(frm.title, '제목')) {
				return false;
			} else if(eleValid(frm.ctnt, '내용')) {
				return false;
			} else if(eleValid(frm.i_student, '작성자')) {
				return false;
			} 
			 
		}
	</script>
</body>
</html>