/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jrt_data_rl
 * 创建者:   高旭
 * 生成日期:     2020/6/27 15:17
 * 描述: 机器人实时数据
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈机器人实时数据〉
 *
 * @author gaoxu
 * @create 2020/6/27 15:17
 * @since 1.0.0
 */
public class Jrt_data_rl {

    private String robot_name;
    private String      robot_mac;
    private String measure_time;
    private String         type;
    private String  value;
    private String       value_show;

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

    public String getMeasure_time() {
        return measure_time;
    }

    public void setMeasure_time(String measure_time) {
        this.measure_time = measure_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue_show() {
        return value_show;
    }

    public void setValue_show(String value_show) {
        this.value_show = value_show;
    }
}
