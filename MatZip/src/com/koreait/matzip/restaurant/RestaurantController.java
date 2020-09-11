package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class RestaurantController {
	private RestaurantService service;
	
	public RestaurantController() {
		service = new RestaurantService();
	}
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "음식점 찾기");
		request.setAttribute(Const.VIEW, "restaurant/restMap");
//		request.setAttribute()
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	public String restReg(HttpServletRequest request) {
		final int I_M = 1; // 카테고리 코드
		request.setAttribute("categoryList", CommonDAO.selCodeList(I_M));
		
		request.setAttribute(Const.TITLE, "가게 등록");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	public String restRegProc(HttpServletRequest request) {
		String nm = request.getParameter("nm");
		String addr= request.getParameter("addr");
		String strLat = request.getParameter("lat");
		String strLng = request.getParameter("lng");
		String strCd_category = request.getParameter("cd_category");
		
		
		int i_user = SecurityUtils.getLoginUser(request).getI_user();
		
		double lat = Double.parseDouble(strLat);
		double lng = Double.parseDouble(strLng);
		int cd_category = Integer.parseInt(strCd_category);
		
		RestaurantVO param = new RestaurantVO();
		
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setCd_category(cd_category);
		param.setI_user(i_user);
		
		int result = service.restRegProc(param);
		
		return "redirect:/restaurant/restMap";
	}
}
