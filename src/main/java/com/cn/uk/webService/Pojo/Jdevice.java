/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jdevice
 * 创建者:   高旭
 * 生成日期:     2020/5/20 9:36
 * 描述: 设备信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈设备信息〉
 *
 * @author gaoxu
 * @create 2020/5/20 9:36
 * @since 1.0.0
 */
public class Jdevice {

    private int jdevice_level;
    private String id;
    private String device_name;
    private String region_id;
    private String bay_id;
    private String main_power_device_id;
    private String device_type;
    private String dial_type;
    private String appearance_type;
    private String fever_type;
    private String phase;
    private String group_id;


    public int getJdevice_level() {
        return jdevice_level;
    }

    public void setJdevice_level(int jdevice_level) {
        this.jdevice_level = jdevice_level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getBay_id() {
        return bay_id;
    }

    public void setBay_id(String bay_id) {
        this.bay_id = bay_id;
    }

    public String getMain_power_device_id() {
        return main_power_device_id;
    }

    public void setMain_power_device_id(String main_power_device_id) {
        this.main_power_device_id = main_power_device_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDial_type() {
        return dial_type;
    }

    public void setDial_type(String dial_type) {
        this.dial_type = dial_type;
    }

    public String getAppearance_type() {
        return appearance_type;
    }

    public void setAppearance_type(String appearance_type) {
        this.appearance_type = appearance_type;
    }

    public String getFever_type() {
        return fever_type;
    }

    public void setFever_type(String fever_type) {
        this.fever_type = fever_type;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
