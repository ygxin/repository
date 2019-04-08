package com.nsc.pojo;

import java.util.Date;

public class DataBean {
	/**
	 * 唯一id
	 */
	private String id;
	
	/**
	 * 网站标识符（域名）
	 */
	private String baseurl;

	/**
	 * 项目国家
	 */
	private String country;

	/**
	 * 项目编号
	 */
	private String meta;

	/**
	 * 项目类别
	 */
	private String product;

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 项目详情链接
	 */
	private String href;

	/**
	 * 项目状态
	 */
	private String status;

	/**
	 * 核准日期
	 */
	private Date approvaldate;
	
	
	private String approval;

	/**
	 * 来源url
	 */
	private String url;
	
	private String projecttype;

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getApprovaldate() {
		return approvaldate;
	}

	public void setApprovaldate(Date approvaldate) {
		this.approvaldate = approvaldate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	@Override
	public String toString() {
		return "DataBean [id=" + id + ", baseurl=" + baseurl + ", country=" + country + ", meta=" + meta + ", product="
				+ product + ", name=" + name + ", href=" + href + ", status=" + status + ", approvaldate="
				+ approvaldate + ", approval=" + approval + ", url=" + url + ", projecttype=" + projecttype + "]";
	}
}
