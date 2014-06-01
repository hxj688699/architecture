package com.feinno.architecture.pageutil;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Page<E> implements Serializable {

	private int pageSize = 10;
	private int totalPage;
	private int totalCount;
	private int start;
	private int nowPage;
	private List<E> result = Collections.emptyList();
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return (int)Math.ceil(totalCount * 1.0 / pageSize);
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStart() {
		start = (getNowPage() -1) * getPageSize();
		if (start < 0) {
			start = 0;
		}
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getNowPage() {
		if (nowPage <= 0) {
			nowPage = 1;
		}
		if (nowPage > getTotalPage()) {
			nowPage = getTotalPage();
		}
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public List<E> getResult() {
		return result;
	}
	public void setResult(List<E> result) {
		this.result = result;
	}
}
