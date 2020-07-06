/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jtask_data_rl
 * 创建者:   高旭
 * 生成日期:     2020/5/20 10:35
 * 描述: 历史任务信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈历史任务信息〉
 *
 * @author gaoxu
 * @create 2020/5/20 10:35
 * @since 1.0.0
 */
public class Jtask_data_rl {

    private String id;
    private String         robot_name;
    private String robot_mac;
    private String         task_name;
    private String task_id;
    private String         task_finish_state;
    private String plan_start_time;
    private String         start_time;
    private String end_time;
    private int        all_device_count;
    private int normal_count;
    private int         alarm_device_count;
    private int recogn_error_count;
    private int         miss_count;
    private String temperature;
    private String        humidity;
    private String wind_speed;
    private String        total_mileage;
    private String  road_img_path;
    private String        review_state;
    private String review_time;
    private String        submit_suggestion;
    private String run_mode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_finish_state() {
        return task_finish_state;
    }

    public void setTask_finish_state(String task_finish_state) {
        this.task_finish_state = task_finish_state;
    }

    public String getPlan_start_time() {
        return plan_start_time;
    }

    public void setPlan_start_time(String plan_start_time) {
        this.plan_start_time = plan_start_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getAll_device_count() {
        return all_device_count;
    }

    public void setAll_device_count(int all_device_count) {
        this.all_device_count = all_device_count;
    }

    public int getNormal_count() {
        return normal_count;
    }

    public void setNormal_count(int normal_count) {
        this.normal_count = normal_count;
    }

    public int getAlarm_device_count() {
        return alarm_device_count;
    }

    public void setAlarm_device_count(int alarm_device_count) {
        this.alarm_device_count = alarm_device_count;
    }

    public int getRecogn_error_count() {
        return recogn_error_count;
    }

    public void setRecogn_error_count(int recogn_error_count) {
        this.recogn_error_count = recogn_error_count;
    }

    public int getMiss_count() {
        return miss_count;
    }

    public void setMiss_count(int miss_count) {
        this.miss_count = miss_count;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getTotal_mileage() {
        return total_mileage;
    }

    public void setTotal_mileage(String total_mileage) {
        this.total_mileage = total_mileage;
    }

    public String getRoad_img_path() {
        return road_img_path;
    }

    public void setRoad_img_path(String road_img_path) {
        this.road_img_path = road_img_path;
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

    public String getSubmit_suggestion() {
        return submit_suggestion;
    }

    public void setSubmit_suggestion(String submit_suggestion) {
        this.submit_suggestion = submit_suggestion;
    }

    public String getRun_mode() {
        return run_mode;
    }

    public void setRun_mode(String run_mode) {
        this.run_mode = run_mode;
    }
}
