package com.koreait.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardList") // 주소 맵핑
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListSer() {
        super(); // 부모의 기본생성자를 먼저 호출 그 후에 자신의 생성자 호출 가능
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String strI_board = request.getParameter("i_board");
    	System.out.println("Servlet i_board : " + strI_board);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardList.jsp");
    	rd.forward(request, response);
	  // get 방식 자동으로 jsp컨테이너가 실행해준다.
	} // request는 고객이 요청한 모든 정보가 담겨있다.

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response); // post 방식 자동으로 jsp컨테이너가 실행해준다.
	}

}
