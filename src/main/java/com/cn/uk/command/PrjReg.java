/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Prj
 * 创建者:   高旭
 * 生成日期:     2020/5/22 16:44
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;


import javax.xml.bind.annotation.*;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/5/22 16:44
 * @since 1.0.0
 */
public class PrjReg {


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

    @XmlElement(name = "reg")
    private Reg reg;

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
    public Reg getReg() {
        return reg;
    }

    public void setReg(Reg reg) {
        this.reg = reg;
    }

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

    @Override
    public String toString() {
        return "PrjReg{" +
                "deadline='" + deadline + '\'' +
                ", dailySeq='" + dailySeq + '\'' +
                ", totalSeq='" + totalSeq + '\'' +
                ", sn='" + sn + '\'' +
                ", createdTm='" + createdTm + '\'' +
                ", reg=" + reg +
                '}';
    }
}
