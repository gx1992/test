/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jrobot
 * 创建者:   高旭
 * 生成日期:     2020/6/4 20:10
 * 描述: 机器人台账信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈机器人台账信息〉
 *
 * @author gaoxu
 * @create 2020/6/4 20:10
 * @since 1.0.0
 */
public class Jrobot {

    private String robot_name;
    private String      robot_mac;
    private String power_bureau;
    private String         substation;
    private String patrol_center;
    private String       patrol_substation;
    private String manufacturer;
    private String        device_type;
    private String manufacture_time;
    private String       manufacture_num;
    private String commissioning_date;
    private String       is_transport;
    private String battery_capacity;
    private String        navigation_type;
    private String type;
    private String        drive_type;

    public String getRobot_name() {
        return robot_name;
    }

    public void setRobot_name(String robot_name) {
        this.robot_name = robot_name;
    }

    public String getRobot_mac() {
        return robot_mac;
    }

    public void setRobot_mac(String robot_mac) {
        this.robot_mac = robot_mac;
    }

    public String getPower_bureau() {
        return power_bureau;
    }

    public void setPower_bureau(String power_bureau) {
        this.power_bureau = power_bureau;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public String getPatrol_center() {
        return patrol_center;
    }

    public void setPatrol_center(String patrol_center) {
        this.patrol_center = patrol_center;
    }

    public String getPatrol_substation() {
        return patrol_substation;
    }

    public void setPatrol_substation(String patrol_substation) {
        this.patrol_substation = patrol_substation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getManufacture_time() {
        return manufacture_time;
    }

    public void setManufacture_time(String manufacture_time) {
        this.manufacture_time = manufacture_time;
    }

    public String getManufacture_num() {
        return manufacture_num;
    }

    public void setManufacture_num(String manufacture_num) {
        this.manufacture_num = manufacture_num;
    }

    public String getCommissioning_date() {
        return commissioning_date;
    }

    public void setCommissioning_date(String commissioning_date) {
        this.commissioning_date = commissioning_date;
    }

    public String getIs_transport() {
        return is_transport;
    }

    public void setIs_transport(String is_transport) {
        this.is_transport = is_transport;
    }

    public String getBattery_capacity() {
        return battery_capacity;
    }

    public void setBattery_capacity(String battery_capacity) {
        this.battery_capacity = battery_capacity;
    }

    public String getNavigation_type() {
        return navigation_type;
    }

    public void setNavigation_type(String navigation_type) {
        this.navigation_type = navigation_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDrive_type() {
        return drive_type;
    }

    public void setDrive_type(String drive_type) {
        this.drive_type = drive_type;
    }
}
