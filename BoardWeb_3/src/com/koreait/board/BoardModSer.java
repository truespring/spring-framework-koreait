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

@WebServlet("/boardMod")
public class BoardModSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		jsp에서 값을 받아옴 - 화면을 열기위해
		String strI_board = request.getParameter("i_board");
		
		int i_board = Utils.parseStrToInt(strI_board);
		
		if(i_board == 0) {
			response.sendRedirect("/boardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board); // 받아온 값을 객체에 담는다
		BoardVO data = BoardDAO.selBoard(param); // selBoard 메소드를 통해 값을 담는다
		request.setAttribute("data", data); // 담은 값을 사용할 수 있게 한다
		
		String jsp = "/WEB-INF/view/boardRegMod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response); // 값을 가지고 해당 jsp로 이동한다.
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		jsp에서 post방식으로 보내온 값을 action에 해당되는 주소로 이동하여 값을 받는다
		String strI_board = request.getParameter("i_board");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		int i_board = Utils.parseStrToInt(strI_board);
		
		if(i_board == 0) {
			response.sendRedirect("/boardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setTitle(title);
		param.setCtnt(ctnt); // 받아온 값들을 객체에 담는다
		
		int result = BoardDAO.updBoard(param); // 객체를 통해 upBoard 메소드를 실행시킨다
		System.out.println("result : " + result);
		
		if(result == 1 ) {
			response.sendRedirect("/boardDetail?i_board=" + i_board);
		} else {
			request.setAttribute("msg", "에러가 발생했습니다.");
			doGet(request, response);
		}
	}

}
