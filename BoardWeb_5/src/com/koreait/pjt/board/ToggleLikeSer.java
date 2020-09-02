package com.koreait.pjt.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/toggleLike")
public class ToggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		String strYn_like = request.getParameter("yn_like");
		int page = MyUtils.getIntParameter(request, "page");
		int record_cnt = MyUtils.getIntParameter(request, "record_cnt");
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");

		searchText = URLEncoder.encode(searchText, "UTF-8");
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		int yn_like = MyUtils.parseStrToInt(strYn_like, 3);	
		System.out.println("i_user : " + i_user);
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user()); //로그인한 사람의 i_user
		
		if(yn_like == 0) {
			int result = BoardDAO.likeBoard(param);
			System.out.println("result : " + result);
		} else {
			int result = BoardDAO.dislikeBoard(param);
			System.out.println("result : " + result);
		}
		
		String target = String.format("/board/detail?i_board=%s&page=%s&record_cnt=%s&searchType=%s&searchText=%s&yn_like=%s"
				, strI_board, page, record_cnt, searchType, searchText, yn_like);
		
		response.sendRedirect(target);
	}

}
