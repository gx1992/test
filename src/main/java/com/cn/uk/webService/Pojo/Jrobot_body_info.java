/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jrobot_body_info
 * 创建者:   高旭
 * 生成日期:     2020/6/16 18:37
 * 描述: 机器人自身模块信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈机器人自身模块信息〉
 *
 * @author gaoxu
 * @create 2020/6/16 18:37
 * @since 1.0.0
 */
public class Jrobot_body_info {

    private String robot_name;
    private String       robot_mac;
    private String running_mileage;
    private String         running_time;
    private String number_device;
    private String         number_fault;

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

    public String getRunning_mileage() {
        return running_mileage;
    }

    public void setRunning_mileage(String running_mileage) {
        this.running_mileage = running_mileage;
    }

    public String getRunning_time() {
        return running_time;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }

    public String getNumber_device() {
        return number_device;
    }

    public void setNumber_device(String number_device) {
        this.number_device = number_device;
    }

    public String getNumber_fault() {
        return number_fault;
    }

    public void setNumber_fault(String number_fault) {
        this.number_fault = number_fault;
    }
}
