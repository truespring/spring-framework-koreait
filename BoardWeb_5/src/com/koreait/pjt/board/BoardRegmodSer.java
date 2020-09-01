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
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		String strI_board = request.getParameter("i_board");
		if(strI_board != null) {
			int i_board = MyUtils.parseStrToInt(strI_board);
			BoardVO param = new BoardVO();
			param.setI_board(i_board);
			BoardVO vo = new BoardVO();
			vo = BoardDAO.selBoard(param);
			request.setAttribute("data", vo);
		}
		ViewResolver.forwardLoginChk("board/regmod", request, response);
	} // 화면 띄우기 ( 등록창 / 수정창 )
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		String filterCtnt1 = scriptFilter(ctnt);
		String filterCtnt2 = swearWordFilter(filterCtnt1);
		HttpSession hs = request.getSession();
		UserVO uvo = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		int i_user = uvo.getI_user();
		System.out.println("title : " + title);
		System.out.println("ctnt : " + filterCtnt2);
		System.out.println("i_user : " + i_user);
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(filterCtnt2);
		param.setI_user(i_user);
		
		if("".equals(strI_board)) { // 글등록 분기
			int result = BoardDAO.insBoard(param);
			System.out.println("result : " + result); // 단위 테스트
			response.sendRedirect("/board/list");
//			return;
		} else { // 글수정 분기
			int i_board = Integer.parseInt(strI_board);
			param.setI_board(i_board);
			int result = BoardDAO.updBoard(param);
			System.out.println("result : " + result); // 단위 테스트
			request.setAttribute("data", param);
			response.sendRedirect("/board/detail?i_board=" + strI_board);
//			response.sendRedirect("/board/detail"); // detail로 적어도 같다
//			return;
		}
	} // 로직 처리 ( DB에 등록 / 수정 ) 실시
	
//	욕필터
	private String swearWordFilter(final String ctnt) {
		String[] filters = {"개새끼", "미친년", "ㄱㅐㅅㅐㄲㅣ"};
		String result = ctnt;
		for(int i = 0; i < filters.length; i++) {
			result = result.replace(filters[i], "***");
		}
		return result;
	}
	
//	스크립트 필터 - 스크립트 무력화
	private String scriptFilter(final String ctnt) {
		String[] filters = {"<script>", "</script>"};
		String[] filterResult = {"&lt;script&gt;", "&lt;/script&gt"};
//		html에서 확인해 보면 <script></script>로 보이지만 문자열로 바뀐 것이기 때문에 실질적인 작동은 하지 않는다.
		
		String result = ctnt;
		for(int i = 0; i < filters.length; i++) {
			result = result.replace(filters[i], filterResult[i]);
		}
		return result;
	}
}
