package com.koreait.pjt.board;

import com.koreait.pjt.vo.BoardVO;

public class BoardDomain extends BoardVO {
	private int like_cnt;
	private int cmt_cnt;
	private String nm;

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
