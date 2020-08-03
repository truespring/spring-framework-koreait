<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
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
	#reset { 
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
		<form id="frm" action="/jsp/boardWriteProc.jsp" method="POST" onsubmit="return chk()"><%// form태그를 통해 값들을 전달하는데 그 방식은 POST방식과 GET방식이 존재한다 %>
			<% // 글쓰는 담당, DB에 insert하는 담당 %>
			<div id="title"><label>제목 : <input type="text" name="title"></label></div>
			<% // 속성(name)과 값(value)을 쓰는 이유는 DB에게 값을 날릴 때 키 값이 된다
			// id는 유일성으로 사용하고 class는 그룹의 의미로 사용한다. %>
			<div id="ctnt">
				<label>내용 : <textarea name="ctnt" cols="70" rows="10"></textarea></label>
			</div>
			<div id="author"><label>작성자 : <input type="text" name="i_student"></label></div>
			<div id="send"><input type="submit" value="글등록"></div>
			<div id="reset"><input type="reset" value="다시쓰기"></div>
		</form>
		<div id="back">
			<a href="/jsp/boardList.jsp"><button>뒤로가기</button></a>
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
			} // 위와 아래가 같다
			 
			/*
			if(frm.title.value == '') { // 각 주제에서 입력 값이 없을 때를 판단하기 위해서(유효성 검사)
				alert('제목을 입력해 주세요.');
				frm.title.focus();
				return false;
			} else if (frm.ctnt.value.length == 0) {
				alert('내용을 입력해 주세요.');
				frm.ctnt.focus();
				return false;
			} else if (frm.i_student.value == '') {
				alert('작성자를 입력해 주세요.');
				frm.i_student.focus();
				return false;
			}
			*/
		}
	</script>
</body>
</html>