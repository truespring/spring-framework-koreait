package com.koreait.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.common.Utils;
import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;

@WebServlet("/boardDetail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// get방식은 주로 화면 띄우기 용으로 사용된다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		
		int i_board = Utils.parseStrToInt(strI_board);
		
		if(i_board == 0) {
			response.sendRedirect("/boardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		BoardVO data = BoardDAO.selBoard(param); // DB로 값 받기
		request.setAttribute("data", data); // 키값으로 주소값을 받아옴
		
		String jsp = "/WEB-INF/view/boardDetail.jsp";
		
//		RequestDispatcher rd = request.getRequestDispatcher(jsp);
//		rd.forward(request, response);
		
		request.getRequestDispatcher(jsp).forward(request, response);
	}
//	업무처리에 많이 사용된다. 업데이트, 크리에이트 등
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
