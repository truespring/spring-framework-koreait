package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		// 검색을 위한 변수
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "" : searchText);
		
		// 레코드 갯수
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		// 현재 페이지
		int page = MyUtils.getIntParameter(request, "page");
		page = page == 0 ? 1 : page;
		
		BoardDomain param = new BoardDomain();
		int i_board = MyUtils.getIntParameter(request, "i_board");

		param.setSearchText("%" + searchText + "%");
		param.setRecord_cnt(recordCnt);
		int pagingCnt = BoardDAO.selPagingCnt(param); // 페이지의 갯수(총 레코드/한 페이지 레코드)
		
		if(page > pagingCnt) {
			page = pagingCnt; // 현재 페이지가 바뀌게될 페이지의 갯수보다 큰 경우
		}
		
		request.setAttribute("page", page);
		System.out.println("page : " + page);
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		param.setI_board(i_board);
		param.seteIdx(eIdx);
		param.setsIdx(sIdx);
		
		request.setAttribute("list", BoardDAO.selBoardList(param));
		request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(param));
		
		ViewResolver.forwardLoginChk("board/list", request, response);
		
	}
}
