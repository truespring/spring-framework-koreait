package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class UserController {
	private UserService service;
	
	public UserController() {
		service = new UserService();
	}
	
	public String login(HttpServletRequest request) {
		String error = request.getParameter("error");
		
		if(error != null) {
			switch(error) {
			case "2":
				request.setAttribute("msg", "아이디를 확인해 주세요.");
				break;
			case "3":
				request.setAttribute("msg", "비밀번호를 확인해 주세요.");
				break;
			}
		}
//		request.setAttribute(Const.TEMPLATE, null); // 헤더, 사이드바, 풋터에 해당하는 항상 나오는 것들을 템플릿으로 정함
		request.setAttribute(Const.TITLE, "로그인");
		request.setAttribute(Const.VIEW, "user/login"); // 로그인 때만 필요한 정보들
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String loginProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		
		int result = service.login(param);
		
		if(result == 1) {
			return "redirect:/restaurant/restMap";
		} else {
			return "redirect:/user/login?login?user_id=" + user_id + "&error=" + result;
		}
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		
		int result = service.join(param); // 분기를 위해 result에 담아준다
		return "redirect:/user/login";
	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(""); // null값이 넘어오면 에러가 발생하기 때문
		
		int result = service.login(param);
	
		return String.format("ajax:{\"result\": %s}", result);
	}
	
}
