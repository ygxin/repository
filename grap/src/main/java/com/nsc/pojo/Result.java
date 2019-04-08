package com.nsc.pojo;

public class Result {
	private Object object;
	private Integer code;
	private String msg;
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Result [object=" + object + ", code=" + code + ", msg=" + msg + "]";
	}
	public Result(Object object, Integer code, String msg) {
		super();
		this.object = object;
		this.code = code;
		this.msg = msg;
	}
	
	
}
