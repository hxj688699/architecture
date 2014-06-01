package com.feinno.architecture.common.web;

public class BaseWebModel {

	private String queryJsonString;
	private int nowPage;
	private int pageSize = 10;
		
	public String getQueryJsonString() {
		return queryJsonString;
	}
	public void setQueryJsonString(String queryJsonString) {
		this.queryJsonString = queryJsonString;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "CustomerWebModel [queryString=" + queryJsonString + ", nowPage="
				+ nowPage + ", pageSize=" + pageSize + "]";
	}
}
