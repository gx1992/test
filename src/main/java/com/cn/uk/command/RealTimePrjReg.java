/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: RealTimePrjReg
 * 创建者:   高旭
 * 生成日期:     2020/6/20 9:41
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/20 9:41
 * @since 1.0.0
 */
public class RealTimePrjReg {

    @XmlAttribute(name = "deadline")
    private String deadline;
    @XmlAttribute(name = "dailySeq")
    private String dailySeq;
    @XmlAttribute(name = "totalSeq")
    private String totalSeq;
    @XmlAttribute(name = "sn")
    private String sn;
    @XmlAttribute(name = "createdTm")
    private String createdTm;

    @XmlElement(name = "dev")
    private List<RealTimeDev> dev;

    @XmlTransient
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    @XmlTransient
    public String getDailySeq() {
        return dailySeq;
    }

    public void setDailySeq(String dailySeq) {
        this.dailySeq = dailySeq;
    }
    @XmlTransient
    public String getTotalSeq() {
        return totalSeq;
    }

    public void setTotalSeq(String totalSeq) {
        this.totalSeq = totalSeq;
    }
    @XmlTransient
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    @XmlTransient
    public String getCreatedTm() {
        return createdTm;
    }

    public void setCreatedTm(String createdTm) {
        this.createdTm = createdTm;
    }
    @XmlTransient
    public List<RealTimeDev> getDev() {
        return dev;
    }

    public void setDev(List<RealTimeDev> dev) {
        this.dev = dev;
    }
}
