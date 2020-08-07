<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>JSP 입문</h2>
		<hr>
		<form id="frm" action="/boardList" method="POST" onsubmit="return chk()">
			<div id="title"><label>제목 : <input type="text" name="title"></label></div>
			<div id="ctnt">
				<label>내용 : <textarea name="ctnt" cols="70" rows="10"></textarea></label>
			</div>
			<div id="author"><label>작성자 : <input type="text" name="i_student"></label></div>
			<div id="send"><input type="submit" value="글등록"></div>
		</form>
	</div>
	<script>
		function eleValid(ele, nm) {
			if(ele.value.length == 0) {
				alert(nm + '을(를) 입력해 주세요.');
				ele.focus();
				return true;
			}
		}
		
		function chk() {
			console.log(`title : \${frm.title.value}`);
			
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