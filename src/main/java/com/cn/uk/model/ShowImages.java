package com.cn.uk.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询界面图片
 * @创建者: 高旭
 * @生成时间: 2019年11月20日 下午6:16:51
 */
public class ShowImages {
	
	private int id;
	private String alarm_name; //识别类型
	private String pic_url;//图片路径
	private Date run_time;//生成时间
	private String n_date;
	private String remark;//识别描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlarm_name() {
		return alarm_name;
	}
	public void setAlarm_name(String alarm_name) {
		this.alarm_name = alarm_name;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public Date getRun_time() {
		return run_time;
	}
	public void setRun_time(Date run_time) {
		
		SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		n_date = sb.format(run_time);
		
		this.run_time = run_time;
	}
	public String getN_date() {
		return n_date;
	}
	public void setN_date(String n_date) {
		this.n_date = n_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	

}
