package com.cn.uk.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 机器人配置
 * @author 高旭
 *
 */
@XmlRootElement(name = "Item")
public class RobotConfigItem extends BaseItem {
	
	private String robot_name;
	private String robot_code; // 对应 robot_id
	private String manufacturer;
	private String istransport;
	private String type; // 对应 robot_type
	
	@XmlAttribute(name = "robot_name")
	public String getRobot_name() {
		return robot_name;
	}
	@XmlAttribute(name = "robot_code")
	public String getRobot_code() {
		return robot_code;
	}
	@XmlAttribute(name = "manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}
	@XmlAttribute(name = "istransport")
	public String getIstransport() {
		return istransport;
	}
	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}
	
	
	public void setRobot_name(String robot_name) {
		this.robot_name = robot_name;
	}
	public void setRobot_code(String robot_code) {
		this.robot_code = robot_code;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setIstransport(String istransport) {
		this.istransport = istransport;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
