/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Return_remote_control
 * 创建者:   高旭
 * 生成日期:     2020/6/5 14:18
 * 描述: 机器人控制返回信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈机器人控制返回信息〉
 *
 * @author gaoxu
 * @create 2020/6/5 14:18
 * @since 1.0.0
 */
public class Return_remote_control {


    private String res_conf;
    private String       robot_name;
    private String robot_mac;
    private String position_coordinate;//(abscissa 横坐标,ordinate 纵坐标)
    private String angular_velocity;
    private String        linear_velocity;
    private String robot_hori;
    private String        robot_verti;


    public String getRes_conf() {
        return res_conf;
    }

    public void setRes_conf(String res_conf) {
        this.res_conf = res_conf;
    }

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

    public String getPosition_coordinate() {
        return position_coordinate;
    }

    public void setPosition_coordinate(String position_coordinate) {
        this.position_coordinate = position_coordinate;
    }

    public String getAngular_velocity() {
        return angular_velocity;
    }

    public void setAngular_velocity(String angular_velocity) {
        this.angular_velocity = angular_velocity;
    }

    public String getLinear_velocity() {
        return linear_velocity;
    }

    public void setLinear_velocity(String linear_velocity) {
        this.linear_velocity = linear_velocity;
    }

    public String getRobot_hori() {
        return robot_hori;
    }

    public void setRobot_hori(String robot_hori) {
        this.robot_hori = robot_hori;
    }

    public String getRobot_verti() {
        return robot_verti;
    }

    public void setRobot_verti(String robot_verti) {
        this.robot_verti = robot_verti;
    }
}
