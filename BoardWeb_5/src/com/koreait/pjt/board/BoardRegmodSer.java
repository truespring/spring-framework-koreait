package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		request.setAttribute("data", i_board);
		ViewResolver.forwardLoginChk("board/regmod", request, response);
	} // 화면 띄우기 ( 등록창 / 수정창 )
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		HttpSession hs = request.getSession();
		UserVO uvo = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		int i_user = uvo.getI_user();
		System.out.println("title : " + title);
		System.out.println("ctnt : " + ctnt);
		System.out.println("i_user : " + i_user);
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(i_user);
		
		int result = BoardDAO.insBoard(param);
		System.out.println("result : " + result);
		if(result != 1) {
			request.setAttribute("msg", "에러가 발생하였습니다.");
			request.setAttribute("data", param);
			doGet(request, response);
			return;
		}
		response.sendRedirect("/board/list");
	} // 로직 처리 ( DB에 등록 / 수정 ) 실시

}
