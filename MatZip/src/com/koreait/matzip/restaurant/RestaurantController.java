package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;

public class RestaurantController {
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "음식점 찾기");
		request.setAttribute(Const.VIEW, "restaurant/restMap");
//		request.setAttribute()
		return ViewRef.TEMP_MENU_TEMP;
	}
}
