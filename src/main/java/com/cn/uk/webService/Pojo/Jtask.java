/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jtask
 * 创建者:   高旭
 * 生成日期:     2020/5/20 10:09
 * 描述: 任务信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈任务信息〉
 *
 * @author gaoxu
 * @create 2020/5/20 10:09
 * @since 1.0.0
 */
public class Jtask {
    private String  type;
    private String  task_id;
    private String  task_name;
    private String  quartz_enable;
    private String  quartz_disable;
    private String  robot_mac;

    private String create_time;
    private String creater;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setQuartz_enable(String quartz_enable) {
        this.quartz_enable = quartz_enable;
    }

    public void setQuartz_disable(String quartz_disable) {
        this.quartz_disable = quartz_disable;
    }

    public String getRobot_mac() {
        return robot_mac;
    }

    public void setRobot_mac(String robot_mac) {
        this.robot_mac = robot_mac;
    }

    public String getQuartz_enable() {
        return quartz_enable;
    }

    public String getQuartz_disable() {
        return quartz_disable;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
