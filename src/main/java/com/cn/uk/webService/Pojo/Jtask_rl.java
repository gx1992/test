/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jtask_rl
 * 创建者:   高旭
 * 生成日期:     2020/5/20 10:25
 * 描述: 当前任务信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈当前任务信息〉
 *
 * @author gaoxu
 * @create 2020/5/20 10:25
 * @since 1.0.0
 */
public class Jtask_rl {


    private String task_name;
    private String task_id;
    private int jtask_state;
    private String  time;
    private String current_inspection_id;
    private String error_device_count;
    private String estimated_time;
    private String  finish_device_count;
    private String  pratrol_progress;
    private String  total_device_count;


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

    public int getJtask_state() {
        return jtask_state;
    }

    public void setJtask_state(int jtask_state) {
        this.jtask_state = jtask_state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCurrent_inspection_id() {
        return current_inspection_id;
    }

    public void setCurrent_inspection_id(String current_inspection_id) {
        this.current_inspection_id = current_inspection_id;
    }

    public String getError_device_count() {
        return error_device_count;
    }

    public void setError_device_count(String error_device_count) {
        this.error_device_count = error_device_count;
    }

    public String getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(String estimated_time) {
        this.estimated_time = estimated_time;
    }

    public String getFinish_device_count() {
        return finish_device_count;
    }

    public void setFinish_device_count(String finish_device_count) {
        this.finish_device_count = finish_device_count;
    }

    public String getPratrol_progress() {
        return pratrol_progress;
    }

    public void setPratrol_progress(String pratrol_progress) {
        this.pratrol_progress = pratrol_progress;
    }

    public String getTotal_device_count() {
        return total_device_count;
    }

    public void setTotal_device_count(String total_device_count) {
        this.total_device_count = total_device_count;
    }
}
