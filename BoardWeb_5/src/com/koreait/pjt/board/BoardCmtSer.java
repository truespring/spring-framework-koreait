package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 댓글 삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_cmt = MyUtils.getIntParameter(request, "i_cmt");
		int i_board = MyUtils.getIntParameter(request, "i_board");
		System.out.println("i_cmt : " + i_cmt);
		System.out.println("i_board : " + i_board);
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		BoardCmtVO param = new BoardCmtVO();
		
		int i_user = loginUser.getI_user();
		param.setI_user(i_user);
		param.setI_cmt(i_cmt);
		param.setI_board(i_board);
		
		int result = BoardCmtDAO.delCmt(param);
		System.out.println("삭제 성공 : " + result);
		response.sendRedirect("/board/detail?i_board="+i_board);
	}

	// 댓글 (등록 / 수정)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		String strI_board = request.getParameter("i_board");
		String cmt = request.getParameter("cmt");
		UserVO loginUser = MyUtils.getLoginUser(request);
		int page = MyUtils.getIntParameter(request, "page");
		int record_cnt = MyUtils.getIntParameter(request, "record_cnt");
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		System.out.println("1 : " + page + " 2 : " + record_cnt + " 3 : " + searchText + " 4 : " + searchType);
		int i_user = loginUser.getI_user();
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		BoardCmtVO param = new BoardCmtVO();
		param.setCmt(cmt);
		param.setI_board(i_board);
		param.setI_user(i_user);
		int result;
		
		switch(strI_cmt) {
		case "0": // 등록
			result = BoardCmtDAO.insCmt(param);
			System.out.println("댓글 등록 : " + result);
			break;
		default: // 수정
			int i_cmt = MyUtils.parseStrToInt(strI_cmt);
			param.setI_cmt(i_cmt);
			result = BoardCmtDAO.updCmt(param);
			System.out.println("댓글 수정 : " + result);
			break;
		}
		
		String target = String.format("/board/detail?i_board=%s&page=%s&record_cnt=%s&searchType=%s&searchText=%s"
				, strI_board, page, record_cnt, searchType, searchText);
		
		response.sendRedirect(target);
	}

}
