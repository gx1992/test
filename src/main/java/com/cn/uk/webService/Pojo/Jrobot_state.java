/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: 机器人状态数据
 * 创建者:   高旭
 * 生成日期:     2020/6/27 15:07
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈机器人状态数据〉
 *
 * @author gaoxu
 * @create 2020/6/27 15:07
 * @since 1.0.0
 */
public class Jrobot_state {

    private String robot_name;
    private String robot_mac;
    private String robot_state;

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

    public String getRobot_state() {
        return robot_state;
    }

    public void setRobot_state(String robot_state) {
        this.robot_state = robot_state;
    }
}
