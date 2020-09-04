<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<style>
	.container {
		width: 1200px; margin: 30px auto; text-align: center; padding: 5px;
	}
	table {
		width: 1000px; border: 1px solid black;
		border-collapse: collapse;
	}
	th {
		border-bottom:1px solid black; padding: 10px;
		background-color: #03C75A;
	}
	td {
		text-align: right;
		padding: 10px;
	}
 	table .itemRow:hover {
 		background-color: #ecf0f1; cursor: pointer;
 	}
 	.nm {
 		font-weight: bold; font-size: 1.2em;
 	}
 	#now_page {
 		color: blue; font-size: 2em;
 	}
 	.pagingFont {
		font-size: 2em; color: red;
	}
	.pagingFont:not(:first-child) {
		margin-left: 13px;
	}
	.containerPImg {
		display: inline-block;	
		width: 30px;
		height: 30px;
	    border-radius: 50%;
	    overflow: hidden;
	}
	
	.pImg {
	
		 object-fit: cover;
		  max-width:100%;
	}
	.highlight {
		color: red;
		font-weight: bold;
	}
	#likeListContainer {			
		padding: 10px;		
		border: 1px solid #bdc3c7;
		position: absolute;
		left: 0px;
		top: 30px;
		width: 130px;
		height: 300px;
		overflow-y: auto;
		background-color: white !important;
		opacity: 0;
	}		
	
	
 		
	.profile {
		background-color: white !important;
		display: inline-block;	
		width: 25px;
		height: 25px;
	    border-radius: 50%;
	    overflow: hidden;
	}		
	
	.likeItemContainer {
		display: flex;
		width: 100%;
	}
	
	.likeItemContainer .nm {
		background-color: white !important;
		margin-left: 7px;
		font-size: 0.7em;
		display: flex;
		align-items: center;
</style>
</head>
<body>
	<div class="container">
		<h1>글목록</h1>
		<div><span class="nm">${loginUser.nm }</span>님 환영합니다.</div> <!-- 세션을 활용하는 법 -->
		<div>
			<form action="/board/list" method="get" id="selFrm">
				<input type="hidden" name="page" value="${page }">
				<input type="hidden" name="searchText" value="${param.searchText}">
				레코드 수 :
				<select id="record_cnt" name="record_cnt" onchange="changeRecordCnt()">
					<c:forEach begin="10" end="30" step="10" var="item"> 
						<c:choose>
							<c:when test="${param.record_cnt == item}" >
								<option value="${item}" selected>${item}개</option>
							</c:when>
							<c:otherwise>
								<option value="${item}">${item}개</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</form>
		</div>
		<div>
			<a href="regmod">글쓰기</a>
			<a href="/logout">로그아웃</a>
			<a href="/profile">프로필</a>
		</div>
		<table>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>좋아요</th>
				<th>작성 시간</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr class="itemRow">
					<td onclick="moveToDetail(${item.i_board})">${item.i_board }</td>
					<td onclick="moveToDetail(${item.i_board})">${item.title }[${item.cmt_cnt }]</td>
					<td>
						<div class="containerPImg">
							<c:choose>
								<c:when test="${item.profile_img != null}">
									<img class="pImg" src="/img/user/${item.i_user}/${item.profile_img}">
								</c:when>
								<c:otherwise>
									<img class="pImg" src="/img/default_profile.png">
								</c:otherwise>
							</c:choose>
						</div>
						${item.nm}
					</td>
					<td>${item.hits }</td>
					<td>
					<c:if test="${item.yn_like == 1 }">
						<span class="material-icons" style="color: red">favorite</span>
					</c:if>
					<c:if test="${item.yn_like == 0 }">
						<span class="material-icons" style="color: red">favorite_border</span>
					</c:if>
					<span onclick="getlikeList(${item.i_board}, ${item.like_cnt }, this)">(${item.like_cnt })</span></td>
					<td>${item.r_dt }</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<form action="/board/list" id="serFrm">
				<select name="searchType">
					<option value="a" ${searchType == 'a' ? 'selected' : '' }>제목</option>
					<option value="b" ${searchType == 'b' ? 'selected' : '' }>내용</option>
					<option value="c" ${searchType == 'c' ? 'selected' : '' }>제목+내용</option>
				</select>
				<input type="text" name="searchText" value="${param.searchText }">
				<input type="submit" value="검색">
			</form>
		</div>
		<c:forEach begin='1' end='${pagingCnt }' var="item">
			<c:choose>
				<c:when test="${page == item}">
					<span class="pagingFont pageSelected">${item}</span>
				</c:when>
				<c:otherwise>
					<span class="pagingFont">
						<a href="/board/list?page=${item}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&searchType=${searchType}">${item}</a>
					</span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<div id="likeListContainer">
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		let beforeI_board = 0 // 전역변수 - 값이 유지된다
		function getlikeList(i_board, cnt, span) {
			if(cnt == 0 ) {return} // 좋아요가 0이면 return 한다
			
			if(beforeI_board == i_board) {
				likeListContainer.style.display = 'none'
				return
			} else if(beforeI_board != i_board) {
				beforeI_board = i_board
				
				likeListContainer.style.display = 'unset'
			}
			
			const locationX = window.scrollX + span.getBoundingClientRect().left + 30
			const locationY = window.scrollY + span.getBoundingClientRect().top + 30
			
			likeListContainer.style.left = `\${locationX}px`
			likeListContainer.style.top = `\${locationY}px`

			likeListContainer.style.opacity = 1
			likeListContainer.innerHTML = ""
			
			axios.get('/board/like', {
				params: {
					i_board // 키값 : 벨류 - 쿼리스트링 대신 사용하는 법(두 값이 같다면 하나로 적어도 된다)
				}
			}).then(function(res) {
				if(res.data.length > 0) {
					//console.log(res)
					for(let i = 0; i < res.data.length; i++) {
						const result = makeLikeUser(res.data[i])
						console.log(result)
						likeListContainer.innerHTML += result
					}
				}
			})
		}
		
		function makeLikeUser(one) {
			const img = one.profile_img == null ?
					'<img class="pImg" src="/img/default_profile.png">'
					:
					`<img class="pImg" src="/img/user/\${one.i_user}/\${one.profile_img}">`
			
			const ele = `<div class="likeItemContainer">
				<div class="profileContainer">
					<div class="profile">
						\${img}
					</div>
				</div>
				<div class="nm">\${one.nm}</div>
			</div>`
			return ele
		}
	
		function changeRecordCnt() {
			selFrm.submit() 
		}
	
		function moveToDetail(i_board) {
			location.href = '/board/detail?page=${page}&record_cnt=${param.record_cnt}&i_board=' + i_board + '&searchText=${param.searchText}&searchType=${searchType}'
		}
	</script>
</body>
</html>