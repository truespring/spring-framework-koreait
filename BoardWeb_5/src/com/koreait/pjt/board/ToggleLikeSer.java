package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/toggleLike")
public class ToggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		String strI_board = request.getParameter("i_board"); // 키값 입력
		String strYn_like = request.getParameter("yn_like");
		UserVO loginUser = MyUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		System.out.println("i_user : " + i_user);
		
		int i_board = MyUtils.parseStrToInt(strI_board);
		int yn_like = MyUtils.parseStrToInt(strYn_like);
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(i_user);
		
		if(yn_like == 0) {
			int result = BoardDAO.likeBoard(param);
			System.out.println("result : " + result);
		} else {
			int result = BoardDAO.dislikeBoard(param);
			System.out.println("result : " + result);
		}
		response.sendRedirect("/board/detail?i_board="+i_board);
	}

}
