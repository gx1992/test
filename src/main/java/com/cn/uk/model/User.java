package com.cn.uk.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @创建者: 高旭
 * @生成时间: 2019年11月1日 下午2:03:54
 */
public class User {
	
	private BigDecimal musr_no;
	private String musr_code;
	private String musr_pwd;
	private Date login_time;
	private int token_valid_minutes; 
	private String token;
	private String remark;
	public BigDecimal getMusr_no() {
		return musr_no;
	}
	public void setMusr_no(BigDecimal musr_no) {
		this.musr_no = musr_no;
	}
	public String getMusr_code() {
		return musr_code;
	}
	public void setMusr_code(String musr_code) {
		this.musr_code = musr_code;
	}
	public String getMusr_pwd() {
		return musr_pwd;
	}
	public void setMusr_pwd(String musr_pwd) {
		this.musr_pwd = musr_pwd;
	}

	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}
	
	
	public int getToken_valid_minutes() {
		return token_valid_minutes;
	}
	public void setToken_valid_minutes(int token_valid_minutes) {
		this.token_valid_minutes = token_valid_minutes;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}
