package com.cn.uk.dto;

import com.alibaba.fastjson.JSON;

public class RtnData {
	private int code;
	private boolean success;
	private String message;
	private Object data;

	//新增两个参数
	private String substation;
	private String msg;


	public String getSubstation() {
		return substation;
	}

	public void setSubstation(String substation) {
		this.substation = substation;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static RtnData ok(Object data) {
		RtnData rtnData = new RtnData();
		rtnData.setCode(0);
		rtnData.setMessage("成功");
		rtnData.setSuccess(true);
		rtnData.setData(data);
		
		return rtnData;
	}
	
	public static RtnData fail(int code, String msg, Object data) {
		RtnData rtnData = new RtnData();
		rtnData.setCode(code);
		rtnData.setMessage(msg);
		rtnData.setSuccess(false);
		rtnData.setData(data);
		
		return rtnData;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
