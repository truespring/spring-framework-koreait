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

@WebServlet("/boardWrite")
public class BoardWriteSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	화면 열기
		String jsp = "/WEB-INF/view/boardRegMod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	처리
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		String strI_student = request.getParameter("i_student"); // HTML에서는 정수의 개념이 없기 때문에 String으로 받아야 한다. 
		
//		int i_student = Utils.parseStrToInt(strI_student);
		
		System.out.println("title : " + title);
		System.out.println("ctnt : " + ctnt);
		System.out.println("i_student : " + strI_student); // 단위 테스트 - 작업이 잘 되었나 확인을 위함
		
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
//		param.setI_student(i_student);
		param.setI_student(Utils.parseStrToInt(strI_student));
		
		int result = BoardDAO.instBoard(param);
		System.out.println("result : " + result);
		
		response.sendRedirect("/boardList");
		
		
	}

}
