/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: lSpecialDate
 * 创建者:   高旭
 * 生成日期:     2020/6/4 16:51
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/4 16:51
 * @since 1.0.0
 */
public class lSpecialDate {

    private Integer Type;
    private String ID;
    private Integer TimeType;
    private String StartTime;
    private String EndTime;


    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getTimeType() {
        return TimeType;
    }

    public void setTimeType(Integer timeType) {
        TimeType = timeType;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
