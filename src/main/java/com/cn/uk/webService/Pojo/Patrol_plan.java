/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Patrol_plan
 * 创建者:   高旭
 * 生成日期:     2020/6/9 10:37
 * 描述: 巡视计划结果返回
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈巡视计划结果返回〉
 *
 * @author gaoxu
 * @create 2020/6/9 10:37
 * @since 1.0.0
 */
public class Patrol_plan {

    private String plan_num;
    private String       plan_type;
    private String plan_state;
    private String         work_type;
    private String workplace;
    private String        work_content;
    private String refined_type;
    private String        department;
    private String workgroup;
    private String        founder;
    private String work_mode;
    private String        voltage_level;
    private String principal;
    private String        work_member;
    private String  plan_source;
    private String         planned_start_time;
    private String planned_end_time;
    private String        actual_start_time;
    private String actual_end_time;


    public String getPlan_num() {
        return plan_num;
    }

    public void setPlan_num(String plan_num) {
        this.plan_num = plan_num;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public String getPlan_state() {
        return plan_state;
    }

    public void setPlan_state(String plan_state) {
        this.plan_state = plan_state;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getWork_content() {
        return work_content;
    }

    public void setWork_content(String work_content) {
        this.work_content = work_content;
    }

    public String getRefined_type() {
        return refined_type;
    }

    public void setRefined_type(String refined_type) {
        this.refined_type = refined_type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkgroup() {
        return workgroup;
    }

    public void setWorkgroup(String workgroup) {
        this.workgroup = workgroup;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getWork_mode() {
        return work_mode;
    }

    public void setWork_mode(String work_mode) {
        this.work_mode = work_mode;
    }

    public String getVoltage_level() {
        return voltage_level;
    }

    public void setVoltage_level(String voltage_level) {
        this.voltage_level = voltage_level;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getWork_member() {
        return work_member;
    }

    public void setWork_member(String work_member) {
        this.work_member = work_member;
    }

    public String getPlan_source() {
        return plan_source;
    }

    public void setPlan_source(String plan_source) {
        this.plan_source = plan_source;
    }

    public String getPlanned_start_time() {
        return planned_start_time;
    }

    public void setPlanned_start_time(String planned_start_time) {
        this.planned_start_time = planned_start_time;
    }

    public String getPlanned_end_time() {
        return planned_end_time;
    }

    public void setPlanned_end_time(String planned_end_time) {
        this.planned_end_time = planned_end_time;
    }

    public String getActual_start_time() {
        return actual_start_time;
    }

    public void setActual_start_time(String actual_start_time) {
        this.actual_start_time = actual_start_time;
    }

    public String getActual_end_time() {
        return actual_end_time;
    }

    public void setActual_end_time(String actual_end_time) {
        this.actual_end_time = actual_end_time;
    }
}
