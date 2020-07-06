/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: RealTimePrj
 * 创建者:   高旭
 * 生成日期:     2020/6/19 0:29
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈动环上送来的字段〉
 *
 * @author gaoxu
 * @create 2020/6/19 0:29
 * @since 1.0.0
 */
public class Prj {

    private String sn;
    private  long createdTm;
    private String deadline;
    private String dailySeq;
    private String totalSeq;

    private List<Dev> dev;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public long getCreatedTm() {
        return createdTm;
    }

    public void setCreatedTm(long createdTm) {
        this.createdTm = createdTm;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDailySeq() {
        return dailySeq;
    }

    public void setDailySeq(String dailySeq) {
        this.dailySeq = dailySeq;
    }

    public String getTotalSeq() {
        return totalSeq;
    }

    public void setTotalSeq(String totalSeq) {
        this.totalSeq = totalSeq;
    }

    public List<Dev> getDev() {
        return dev;
    }

    public void setDev(List<Dev> dev) {
        this.dev = dev;
    }
}

