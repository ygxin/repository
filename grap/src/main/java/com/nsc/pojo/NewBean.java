package com.nsc.pojo;

import java.sql.Date;

public class NewBean {
	// 主键
	private String ANNOUNCE_ID;
	// 通知标题
	private String ANNOUNCE_TITLE;
	// 通知副标题
	private String ANNOUNCE_SUB_TITLE;
	// 来源
	private String ANNOUNCE_SOURCE;
	// 作者
	private String ANNOUNCE_AUTHOR;
	// 日期
	private Date ANNOUNCE_DATE;
	// 字体
	private String ANNOUNCE_FONT_SIZE;
	// 内容
	private String ANNOUNCE_CONTENT;
	// 备用字段
	private String CUSTOM1;
	// 数据状态
	private String datastatus;
	public NewBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NewBean(String aNNOUNCE_ID, String aNNOUNCE_TITLE, String aNNOUNCE_SUB_TITLE, String aNNOUNCE_SOURCE,
			String aNNOUNCE_AUTHOR, Date aNNOUNCE_DATE, String aNNOUNCE_FONT_SIZE, String aNNOUNCE_CONTENT,
			String cUSTOM1, String datastatus) {
		super();
		ANNOUNCE_ID = aNNOUNCE_ID;
		ANNOUNCE_TITLE = aNNOUNCE_TITLE;
		ANNOUNCE_SUB_TITLE = aNNOUNCE_SUB_TITLE;
		ANNOUNCE_SOURCE = aNNOUNCE_SOURCE;
		ANNOUNCE_AUTHOR = aNNOUNCE_AUTHOR;
		ANNOUNCE_DATE = aNNOUNCE_DATE;
		ANNOUNCE_FONT_SIZE = aNNOUNCE_FONT_SIZE;
		ANNOUNCE_CONTENT = aNNOUNCE_CONTENT;
		CUSTOM1 = cUSTOM1;
		this.datastatus = datastatus;
	}
	public String getANNOUNCE_ID() {
		return ANNOUNCE_ID;
	}
	public void setANNOUNCE_ID(String aNNOUNCE_ID) {
		ANNOUNCE_ID = aNNOUNCE_ID;
	}
	public String getANNOUNCE_TITLE() {
		return ANNOUNCE_TITLE;
	}
	public void setANNOUNCE_TITLE(String aNNOUNCE_TITLE) {
		ANNOUNCE_TITLE = aNNOUNCE_TITLE;
	}
	public String getANNOUNCE_SUB_TITLE() {
		return ANNOUNCE_SUB_TITLE;
	}
	public void setANNOUNCE_SUB_TITLE(String aNNOUNCE_SUB_TITLE) {
		ANNOUNCE_SUB_TITLE = aNNOUNCE_SUB_TITLE;
	}
	public String getANNOUNCE_SOURCE() {
		return ANNOUNCE_SOURCE;
	}
	public void setANNOUNCE_SOURCE(String aNNOUNCE_SOURCE) {
		ANNOUNCE_SOURCE = aNNOUNCE_SOURCE;
	}
	public String getANNOUNCE_AUTHOR() {
		return ANNOUNCE_AUTHOR;
	}
	public void setANNOUNCE_AUTHOR(String aNNOUNCE_AUTHOR) {
		ANNOUNCE_AUTHOR = aNNOUNCE_AUTHOR;
	}
	public Date getANNOUNCE_DATE() {
		return ANNOUNCE_DATE;
	}
	public void setANNOUNCE_DATE(Date aNNOUNCE_DATE) {
		ANNOUNCE_DATE = aNNOUNCE_DATE;
	}
	public String getANNOUNCE_FONT_SIZE() {
		return ANNOUNCE_FONT_SIZE;
	}
	public void setANNOUNCE_FONT_SIZE(String aNNOUNCE_FONT_SIZE) {
		ANNOUNCE_FONT_SIZE = aNNOUNCE_FONT_SIZE;
	}
	public String getANNOUNCE_CONTENT() {
		return ANNOUNCE_CONTENT;
	}
	public void setANNOUNCE_CONTENT(String aNNOUNCE_CONTENT) {
		ANNOUNCE_CONTENT = aNNOUNCE_CONTENT;
	}
	public String getCUSTOM1() {
		return CUSTOM1;
	}
	public void setCUSTOM1(String cUSTOM1) {
		CUSTOM1 = cUSTOM1;
	}
	public String getDatastatus() {
		return datastatus;
	}
	public void setDatastatus(String datastatus) {
		this.datastatus = datastatus;
	}
	@Override
	public String toString() {
		return "NewBean [ANNOUNCE_ID=" + ANNOUNCE_ID + ", ANNOUNCE_TITLE=" + ANNOUNCE_TITLE + ", ANNOUNCE_SUB_TITLE="
				+ ANNOUNCE_SUB_TITLE + ", ANNOUNCE_SOURCE=" + ANNOUNCE_SOURCE + ", ANNOUNCE_AUTHOR=" + ANNOUNCE_AUTHOR
				+ ", ANNOUNCE_DATE=" + ANNOUNCE_DATE + ", ANNOUNCE_FONT_SIZE=" + ANNOUNCE_FONT_SIZE
				+ ", ANNOUNCE_CONTENT=" + ANNOUNCE_CONTENT + ", CUSTOM1=" + CUSTOM1 + ", datastatus=" + datastatus
				+ "]";
	}
	
}
