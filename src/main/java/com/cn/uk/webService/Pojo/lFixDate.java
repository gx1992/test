/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: lFixDate
 * 创建者:   高旭
 * 生成日期:     2020/6/4 16:34
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
 * @create 2020/6/4 16:34
 * @since 1.0.0
 */
public class lFixDate {

    private int Type;
    private String ID;
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

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
}
