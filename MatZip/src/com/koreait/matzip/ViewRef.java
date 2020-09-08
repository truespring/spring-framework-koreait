package com.koreait.matzip;

public class ViewRef { // 둘 중의 하나의 파일을 열고 거기에서 include를 통해서 처리한다. 
	public static final String URI_USER = "user";
	
	public static final String TEMP_DEFAULT = "/WEB-INF/view/template/default.jsp";
	public static final String TEMP_MENU = "/WEB-INF/view/template/menuTemp.jsp"; // 상위(헤더), 하위(풋터) 구분
}
