package com.nsc.pojo;

import java.util.List;

public class PageUtil {
	private Integer total; //记录总数
	
	private List<?> list; //记录列表
	
	private int pageNo; //页码
	
	private int pageSize; //每页长度
	
	private int pageStart; //开始记录数
	
	private int pageEnd; //结束记录数
	
	public PageUtil() {
		// TODO Auto-generated constructor stub
	}
	public PageUtil(Integer total, List<?> list, int pageNo, int pageSize, int pageStart, int pageEnd) {
		super();
		this.total = total;
		this.list = list;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.pageStart = pageStart;
		this.pageEnd = pageEnd;
	}
	public PageUtil (Integer total ,List<?> list) {
		super();
		this.total = total;
		this.list = list;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	@Override
	public String toString() {
		return "PageUtil [total=" + total + ", list=" + list + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", pageStart=" + pageStart + ", pageEnd=" + pageEnd + "]";
	}
	 
	

}
