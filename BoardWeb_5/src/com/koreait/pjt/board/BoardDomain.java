package com.koreait.pjt.board;

import com.koreait.pjt.vo.BoardVO;

public class BoardDomain extends BoardVO {
	private int like_cnt;
	private int cmt_cnt;
	private String nm;
	private int recode_cnt; // 페이지 당 나온는 레코드 수
	private int sldx;
	private int eldx;
	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSldx() {
		return sldx;
	}

	public void setSldx(int sldx) {
		this.sldx = sldx;
	}

	public int getEldx() {
		return eldx;
	}

	public void setEldx(int eldx) {
		this.eldx = eldx;
	}

	public int getRecode_cnt() {
		return recode_cnt;
	}

	public void setRecode_cnt(int recode_cnt) {
		this.recode_cnt = recode_cnt;
	}

	public int getCmt_cnt() {
		return cmt_cnt;
	}

	public void setCmt_cnt(int cmt_cnt) {
		this.cmt_cnt = cmt_cnt;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	
}
