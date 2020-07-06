/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jdevice_w
 * 创建者:   高旭
 * 生成日期:     2020/5/19 19:53
 * 描述: 告警确认信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈告警确认信息〉
 *
 * @author gaoxu
 * @create 2020/5/19 19:53
 * @since 1.0.0
 */
public class Jdevice_w {

    private String patrol_point_id;
    private String         alarm_level;
    private String alarm_type;
    private String        recognition_time;
    private String recognition_type;
    private String         coordinate;
    private String task_run_his_id;
    private String         review_state;
    private String recogn_state;
    private String         review_value;
    private String  review_time;


    public String getPatrol_point_id() {
        return patrol_point_id;
    }

    public void setPatrol_point_id(String patrol_point_id) {
        this.patrol_point_id = patrol_point_id;
    }

    public String getAlarm_level() {
        return alarm_level;
    }

    public void setAlarm_level(String alarm_level) {
        this.alarm_level = alarm_level;
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

    public String getRecognition_time() {
        return recognition_time;
    }

    public void setRecognition_time(String recognition_time) {
        this.recognition_time = recognition_time;
    }

    public String getRecognition_type() {
        return recognition_type;
    }

    public void setRecognition_type(String recognition_type) {
        this.recognition_type = recognition_type;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getTask_run_his_id() {
        return task_run_his_id;
    }

    public void setTask_run_his_id(String task_run_his_id) {
        this.task_run_his_id = task_run_his_id;
    }

    public String getReview_state() {
        return review_state;
    }

    public void setReview_state(String review_state) {
        this.review_state = review_state;
    }

    public String getRecogn_state() {
        return recogn_state;
    }

    public void setRecogn_state(String recogn_state) {
        this.recogn_state = recogn_state;
    }

    public String getReview_value() {
        return review_value;
    }

    public void setReview_value(String review_value) {
        this.review_value = review_value;
    }

    public String getReview_time() {
        return review_time;
    }

    public void setReview_time(String review_time) {
        this.review_time = review_time;
    }
}
