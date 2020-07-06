/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Operation_command
 * 创建者:   高旭
 * 生成日期:     2020/6/4 19:42
 * 描述: 调度倒闸操作历史
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈调度倒闸操作历史〉
 *
 * @author gaoxu
 * @create 2020/6/4 19:42
 * @since 1.0.0
 */
public class Operation_command {

    private String power_supply_bureau;
    private String        voltage_level;
    private String substation;
    private String         device_type;
    private String device_name;
    private String         function_position;
    private String operation_time;


    public String getPower_supply_bureau() {
        return power_supply_bureau;
    }

    public void setPower_supply_bureau(String power_supply_bureau) {
        this.power_supply_bureau = power_supply_bureau;
    }

    public String getVoltage_level() {
        return voltage_level;
    }

    public void setVoltage_level(String voltage_level) {
        this.voltage_level = voltage_level;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getFunction_position() {
        return function_position;
    }

    public void setFunction_position(String function_position) {
        this.function_position = function_position;
    }

    public String getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(String operation_time) {
        this.operation_time = operation_time;
    }
}
