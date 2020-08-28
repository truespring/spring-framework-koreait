package com.koreait.pjt.board;

import com.koreait.pjt.vo.BoardVO;

public class BoardDomain extends BoardVO {
	private int like_cnt;
	private int cmt_cnt;
	private String nm;
	private int record_cnt; // 페이지 당 나온는 레코드 수
	private int sIdx;
	private int eIdx;
	private int page;
	private String searchText;
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getsIdx() {
		return sIdx;
	}

	public void setsIdx(int sIdx) {
		this.sIdx = sIdx;
	}

	public int geteIdx() {
		return eIdx;
	}

	public void seteIdx(int eIdx) {
		this.eIdx = eIdx;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getRecord_cnt() {
		return record_cnt;
	}

	public void setRecord_cnt(int record_cnt) {
		this.record_cnt = record_cnt;
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
