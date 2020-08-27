package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		int page = MyUtils.getIntParameter(request, "page");
		int i_board = MyUtils.getIntParameter(request, "i_board");
		page = page == 0 ? 1 : page;
		System.out.println("page : " + page);
		
		BoardDomain param = new BoardDomain();
		param.setRecode_cnt(Const.RECORD_CNT);
		param.setI_board(i_board);
		
		int eldx = page * Const.RECORD_CNT;
		int sldx = eldx - Const.RECORD_CNT;
		
		param.setEldx(eldx);
		param.setSldx(sldx);
		
		request.setAttribute("list", BoardDAO.selBoardList(param));
		request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(param));
		
		ViewResolver.forwardLoginChk("board/list", request, response);
		
	}
}
