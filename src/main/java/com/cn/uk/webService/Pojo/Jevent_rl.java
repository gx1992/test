/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jevent_rl
 * 创建者:   高旭
 * 生成日期:     2020/6/5 13:57
 * 描述: 机器人本体告警信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈机器人本体告警信息〉
 *
 * @author gaoxu
 * @create 2020/6/5 13:57
 * @since 1.0.0
 */
public class Jevent_rl {

    private String robot_name;
    private String       robot_mac;
    private String alarm_level;
    private String         robot_state;
    private String  alarm_time;
    private String         alarm_info;
    private String review_state;
    private String         review_time;

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

    public String getAlarm_level() {
        return alarm_level;
    }

    public void setAlarm_level(String alarm_level) {
        this.alarm_level = alarm_level;
    }

    public String getRobot_state() {
        return robot_state;
    }

    public void setRobot_state(String robot_state) {
        this.robot_state = robot_state;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }

    public String getAlarm_info() {
        return alarm_info;
    }

    public void setAlarm_info(String alarm_info) {
        this.alarm_info = alarm_info;
    }

    public String getReview_state() {
        return review_state;
    }

    public void setReview_state(String review_state) {
        this.review_state = review_state;
    }

    public String getReview_time() {
        return review_time;
    }

    public void setReview_time(String review_time) {
        this.review_time = review_time;
    }
}
