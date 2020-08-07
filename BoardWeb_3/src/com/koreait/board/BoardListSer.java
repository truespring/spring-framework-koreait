package com.koreait.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;

@WebServlet("/boardList") // 주소값 맵핑 web.xml에도 작성해서 여기서 작성하지 않을 수 있지만 길게 적어야 한다
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L; // session 관련 중요한 변수
       
//    public BoardListSer() {
//        super(); // 부모의 기본생성자를 먼저 호출 그 후에 자신의 생성자 호출 가능
//    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	List<BoardVO> list = BoardDAO.selBoardList();
    	request.setAttribute("data", list);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardList.jsp"); // 값을 그대로 가지고 전달할 수 있는 메소드이다. sendRedirect와의 차이를 알아야 한다.
    	rd.forward(request, response); // 주소값이 변하지 않고 sendRedirect는 주소값이 변한다.
	  // get 방식 자동으로 jsp컨테이너가 실행해준다.
	} // request는 고객이 요청한 모든 정보가 담겨있다.

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response); // post 방식 자동으로 jsp컨테이너가 실행해준다.
	}

}
