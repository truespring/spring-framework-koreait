<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.title }</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<style>
	.container {
		width: 600px; margin: 30px auto; background-color: #b0cac7;
		padding: 20px;
	}
	#title {
		margin: 20px; height: 40px;
		line-height: 40px; font-size: 2em;
	}
	#nm {
		margin: 20px;
	}
	#ctnt {
		margin: 30px; padding: 10px; width: 600px;
	}
	.list, .upd, .del {
		border-radius: 50%; width: 50px; height: 50px;
		display: inline-block; width: 50px; height: 50px; text-align: center;
	}
	#list, #upd, #del {
		text-decoration: none; color: white; line-height: 20px;
	}
	#delFrm {
		display: inline-block;
	}
	.pointerCursor:hover {
		 cursor: pointer;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="list"><a href="/board/list" id="list">목록</a></div>
		<c:if test="${loginUser.i_user == data.i_user }">
			<div class="upd"><a href="/board/regmod?i_board=${data.i_board }" id="upd">수정</a></div>
			<form action="/board/del" id="delFrm" method="post">
				<input type="hidden" name="i_board" value="${data.i_board }">
				<div class="del"><a href="#" onclick="submitDel()" id="del">삭제</a></div>
			</form>
		</c:if>
		<div id="title">${data.title }</div>
		<div id="nm">${data.nm }</div>
		<div id="ctnt">${data.ctnt }</div>
		<div id="r_dt">작성일시 : ${data.r_dt }</div>
		<div id="hits">조회수 : ${data.hits }</div>
		<div id="yn_like">
			<span onclick="toggleLike(${data.yn_like})" class="pointerCursor">
				<c:if test="${data.yn_like == 1 }">
					<span class="material-icons" style="color: red">favorite</span>
				</c:if>
				<c:if test="${data.yn_like == 0 }">
					<span class="material-icons" style="color: red">favorite_border</span>
				</c:if>
			</span>
			<span>${data.like_cnt }</span>
		</div>
	</div>
	<script>
		function submitDel() {
			delFrm.submit()
		}
		
		function toggleLike(yn_like) {
			location.href="/board/toggleLike?i_board=${data.i_board}&yn_like=" + yn_like
		}									// 키값		벨류값
	</script>
</body>
</html>