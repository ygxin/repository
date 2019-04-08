package com.nsc.pojo;

import java.sql.Date;

public class User {
	private Integer id;
	private String userName;
	private Integer age;
	private Date ctm;
	private String sex;
	private String address;
	private boolean isredis;
	private Date createDate;
	private Date updateTime;
	private Date brithday;
	private int sort;
	private String password;
	private String salt;
	private String name;
	
	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", age=" + age + ", ctm=" + ctm + ", sex=" + sex
				+ ", address=" + address + ", isredis=" + isredis + ", createDate=" + createDate + ", updateTime="
				+ updateTime + ", brithday=" + brithday + ", sort=" + sort + ", password=" + password + ", salt=" + salt
				+ ", name=" + name + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCtm() {
		return ctm;
	}

	public void setCtm(Date ctm) {
		this.ctm = ctm;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isIsredis() {
		return isredis;
	}

	public void setIsredis(boolean isredis) {
		this.isredis = isredis;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(Integer id, String userName, Integer age, Date ctm, String sex, String address, boolean isredis,
			Date createDate, Date updateTime, Date brithday, int sort, String password, String salt, String name) {
		super();
		this.id = id;
		this.userName = userName;
		this.age = age;
		this.ctm = ctm;
		this.sex = sex;
		this.address = address;
		this.isredis = isredis;
		this.createDate = createDate;
		this.updateTime = updateTime;
		this.brithday = brithday;
		this.sort = sort;
		this.password = password;
		this.salt = salt;
		this.name = name;
	}

	
	

}
