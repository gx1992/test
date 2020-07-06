/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: lWeekDate
 * 创建者:   高旭
 * 生成日期:     2020/6/4 16:35
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
 * @create 2020/6/4 16:35
 * @since 1.0.0
 */
public class lWeekDate {

    private int Type;
    private String ID;
    private Integer [] lMonth;
    private Integer [] lWeek;
    private String StartTime;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer[] getlMonth() {
        return lMonth;
    }

    public void setlMonth(Integer[] lMonth) {
        this.lMonth = lMonth;
    }

    public Integer[] getlWeek() {
        return lWeek;
    }

    public void setlWeek(Integer[] lWeek) {
        this.lWeek = lWeek;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
}
