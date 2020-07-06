/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jrt_station_rl
 * 创建者:   高旭
 * 生成日期:     2020/5/20 8:51
 * 描述: 变电站信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈变电站信息〉
 *
 * @author gaoxu
 * @create 2020/5/20 8:51
 * @since 1.0.0
 */
public class Jrt_station_rl {

    private String measure_time;
    private String        type;
    private String value;
    private String         value_show;

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
