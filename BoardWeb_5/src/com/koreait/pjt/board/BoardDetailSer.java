package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		// 단독으로 조회수 올리기 방지 시작
		ServletContext application = getServletContext(); // 어플리케이션 내장객체 얻어 오기, 부모에게서 가져온 메소드, 어플리케이션에서 가져옴
		Integer readI_user = (Integer)application.getAttribute("read_" + strI_board); // 키 값이 된다, Integer은 객체형이며 null을 담을 수 있다.
		// null은 한 번도 값을 읽은 적 없던 글(서버가 온 된 후로)
		if(readI_user == null || readI_user != loginUser.getI_user()) { // int형과 Integer형의 값비교 가능
			BoardDAO.addHits(i_board);
			application.setAttribute("read_" + strI_board, loginUser.getI_user()); // pk값을 해당 글에 저장되어 담김 -> null이 아니게 된다.
		}
		// 단독으로 조회수 올리기 방지 끝 - 혼자서는 조회수를 올리지 못하지만 두 명이서 핑퐁을 하면 조회수가 계속해서 올라간다. 
		
		BoardVO param = new BoardVO();
		param.setI_user(loginUser.getI_user());
		param.setI_board(i_board);

		BoardVO data = BoardDAO.selBoard(param);
		request.setAttribute("data", data);
		ViewResolver.forwardLoginChk("/board/detail", request, response);
	}
}
