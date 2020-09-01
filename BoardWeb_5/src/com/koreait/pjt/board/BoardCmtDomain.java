package com.koreait.pjt.board;

import com.koreait.pjt.vo.BoardCmtVO;

public class BoardCmtDomain extends BoardCmtVO {
	private String nm;
	private String profile_img;

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
}
