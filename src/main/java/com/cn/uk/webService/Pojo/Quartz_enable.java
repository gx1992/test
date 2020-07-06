/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Quartz_disable
 * 创建者:   高旭
 * 生成日期:     2020/6/4 16:31
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈周期不可用〉
 *
 * @author gaoxu
 * @create 2020/6/4 16:31
 * @since 1.0.0
 */
public class Quartz_enable {

    private List<lFixDate> lFixDates;
    private List<lWeekDate> lWeekDates;
    private List<lIntervalDate> lIntervalDates;

    public List<lFixDate> getlFixDates() {
        return lFixDates;
    }

    public void setlFixDates(List<lFixDate> lFixDates) {
        this.lFixDates = lFixDates;
    }

    public List<lWeekDate> getlWeekDates() {
        return lWeekDates;
    }

    public void setlWeekDates(List<lWeekDate> lWeekDates) {
        this.lWeekDates = lWeekDates;
    }

    public List<lIntervalDate> getlIntervalDates() {
        return lIntervalDates;
    }

    public void setlIntervalDates(List<lIntervalDate> lIntervalDates) {
        this.lIntervalDates = lIntervalDates;
    }
}
