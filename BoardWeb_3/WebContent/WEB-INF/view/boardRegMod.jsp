<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data == null ? "글등록" : "글수정" }</title>
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
	.err {
		color: red;
	}
	
</style>
</head>
<body>
	<div class="container">
		<h2>JSP 입문</h2>
		<hr>
		<div class="err">${msg }</div>
		<form id="frm" action="/${data == null ? 'boardWrite' : 'boardMod' }" method="POST" onsubmit="return chk()">
			<input type="hidden" name="i_board" value="${data.i_board }">
			<div id="title"><label>제목 : <input type="text" name="title" value="${data.title }"></label></div>
			<div id="ctnt"><label>내용 : <textarea name="ctnt" cols="70" rows="10">${data.ctnt }</textarea></label></div>
			<div id="author"><label>작성자 : <input type="text" name="i_student" value="${data.i_student }"></label></div>
			<div id="send"><input type="submit" value="${data == null ? '글등록' : '글수정' }"></div>
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