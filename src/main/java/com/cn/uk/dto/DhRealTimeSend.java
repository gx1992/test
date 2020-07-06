/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: DhRealTimeSend
 * 创建者:   高旭
 * 生成日期:     2020/4/24 10:21
 * 描述: 动环实时推送
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈动环实时推送〉
 *
 * @author gaoxu
 * @create 2020/4/24 10:21
 * @since 1.0.0
 */
public class DhRealTimeSend {

    private Map<String,Object> sensorGateway;
    private String substation;
    private String type;


    public Map<String, Object> getSensorGateway() {
        return sensorGateway;
    }

    public void setSensorGateway(Map<String, Object> sensorGateway) {
        this.sensorGateway = sensorGateway;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DhRealTimeSend{" +
                "sensorGateway=" + sensorGateway +
                ", substation='" + substation + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
