package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/toggleLike")
public class ToggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		int i_board = MyUtils.getIntParameter(request, "i_board");
		int yn_like = MyUtils.getIntParameter(request, "yn_like");
		int page = MyUtils.getIntParameter(request, "page");
		int record_cnt = MyUtils.getIntParameter(request, "record_cnt");

		UserVO loginUser = MyUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		System.out.println("i_user : " + i_user);
		
		
		BoardDomain param = new BoardDomain();
		param.setI_board(i_board);
		param.setI_user(i_user);
		param.setPage(page);
		param.setRecord_cnt(record_cnt);
		
		if(yn_like == 0) {
			int result = BoardDAO.likeBoard(param);
			System.out.println("result : " + result);
		} else {
			int result = BoardDAO.dislikeBoard(param);
			System.out.println("result : " + result);
		}
		
//		String target = String.format("/board/detail?i_board=%s&yn_like=%s&");
		
		response.sendRedirect("/board/detail?i_board=" + i_board);
	}

}
