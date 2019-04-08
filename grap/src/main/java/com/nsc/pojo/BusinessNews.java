package com.nsc.pojo;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class BusinessNews {
	private String newsId; // 主键
	private String newsTitle;// 国际业务新闻标题
	private String newsSubTitle;// 新闻副标题
	private String newsSource;// 来源
	private String newsAuthor;// 作者
	private Date newsDate;// 日期
	private String newsFontSize;// 字体
	private String newsContent;// 内容
	private String custom3;// 备用字段
	private String datastatus; // 数据状态

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsSubTitle() {
		return newsSubTitle;
	}

	public void setNewsSubTitle(String newsSubTitle) {
		this.newsSubTitle = newsSubTitle;
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public String getNewsFontSize() {
		return newsFontSize;
	}

	public void setNewsFontSize(String newsFontSize) {
		this.newsFontSize = newsFontSize;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getCustom3() {
		return custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	public String getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(String datastatus) {
		this.datastatus = datastatus;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public BusinessNews() {
		// TODO Auto-generated constructor stub
	}

	public BusinessNews(String newsId, String newsTitle, String newsSubTitle, String newsSource, String newsAuthor,
			Date newsDate, String newsFontSize, String newsContent, String custom3, String datastatus) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsSubTitle = newsSubTitle;
		this.newsSource = newsSource;
		this.newsAuthor = newsAuthor;
		this.newsDate = newsDate;
		this.newsFontSize = newsFontSize;
		this.newsContent = newsContent;
		this.custom3 = custom3;
		this.datastatus = datastatus;
	}

	@Override
	public String toString() {
		return "BusinessNews [newsId=" + newsId + ", newsTitle=" + newsTitle + ", newsSubTitle=" + newsSubTitle
				+ ", newsSource=" + newsSource + ", newsAuthor=" + newsAuthor + ", newsDate=" + newsDate
				+ ", newsFontSize=" + newsFontSize + ", newsContent=" + newsContent + ", custom3=" + custom3
				+ ", datastatus=" + datastatus + "]";
	}

}
