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
		width: 800px; margin: 30px auto; background-color: #b0cac7;
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
	#cmt {
		width: 400px; margin: 20px;
	}
	.commentlist {
		margin-top: 10px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="list">
             <a href="/board/list?page=${param.page}&record_cnt=${param.record_cnt}">목록</a>
        </div>
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
			<p id="comment"></p>
		</div>
		<div>
			<form id="cmtFrm" action="/board/cmt" method="post">
				<input type="hidden" name="i_cmt" value="0">
				<input type="hidden" name="i_board" value="${data.i_board }">
				<div>
					<input type="text" id="cmt" name="cmt" placeholder="댓글내용">
					<input type="submit" id="cmtSubmit" value="작성완료">
					<input type="button" value="취소" onclick="clkCmtCancel()">
				</div>
			</form>
			<div class="commentlist">
				<table>
					<tr>
						<th>글쓴이</th>
						<th>내용</th>
						<th>등록일</th>
					</tr>
					<c:forEach items="${list }" var="item">
						<tr>
							<th>${item.nm }</th>
							<th>${item.cmt }</th>
							<th>${item.r_dt }</th>
							<c:if test="${item.i_user == loginUser.i_user}">
        						<th><button onclick="clkCmtDel(${item.i_cmt})">삭제</button></th>
        						<th><button onclick="clkCmtMod(${item.i_cmt}, '${item.cmt}')">수정</button></th>
        					</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script>
	function clkCmtDel(i_cmt) {
		if(confirm('삭제 하시겠습니까?')) {
			location.href = '/board/cmt?i_board=${data.i_board}&i_cmt=' + i_cmt
		}
	}
	
	function clkCmtCancel() {
		cmtFrm.i_cmt.value = 0
		cmtFrm.cmt.value = ''  //홑따옴표
		cmtSubmit.value = '작성완료'
	}

	//댓글 수정
	function clkCmtMod(i_cmt, cmt) {
		console.log('i_cmt : ' + i_cmt)
		
		cmtFrm.i_cmt.value = i_cmt
		cmtFrm.cmt.value = cmt
		
		cmtSubmit.value = '수정'
	}
	
		function submitDel() {
			delFrm.submit()
		}
		
		function toggleLike(yn_like) {
			location.href='/board/toggleLike?i_board=${data.i_board}&yn_like=' + yn_like
		}									// 키값		벨류값
		
		
	</script>
</body>
</html>