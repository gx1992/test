/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jtask_plan_info
 * 创建者:   高旭
 * 生成日期:     2020/5/20 10:32
 * 描述: 计划任务信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈计划任务信息〉
 *
 * @author gaoxu
 * @create 2020/5/20 10:32
 * @since 1.0.0
 */
public class Jtask_plan_info {

    private String id;
    private String      robot_name;
    private String robot_mac;
    private String         task_name;
    private String task_id;
    private String        task_finish_state;
    private String plan_start_time;


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
}
