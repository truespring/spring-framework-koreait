package com.koreait.matzip.restaurant;

public class RestaurantService {
	private RestaurantDAO dao;
	
	public RestaurantService() {
		dao = new RestaurantDAO();
	}
	
	public int restRegProc(RestaurantVO param) {

		return dao.restReg(param);
	}
}
