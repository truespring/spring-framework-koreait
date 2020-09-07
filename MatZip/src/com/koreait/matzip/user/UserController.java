package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;

public class UserController {
	public String login(HttpServletRequest request) {
//		request.setAttribute(Const.TEMPLATE, null); // 헤더, 사이드바, 풋터에 해당하는 항상 나오는 것들을 템플릿으로 정함
		request.setAttribute(Const.TITLE, "로그인");
		request.setAttribute(Const.VIEW, "user/login"); // 로그인 때만 필요한 정보들
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
}
