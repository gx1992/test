/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Env
 * 创建者:   高旭
 * 生成日期:     2020/5/25 11:01
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/5/25 11:01
 * @since 1.0.0
 */
@XmlRootElement
public class Env {

    @XmlElement
    private String CO2Unit;
    @XmlElement
    private String tempUnit;
    @XmlElement
    private String areaUnit;
    @XmlElement
    private String waterUnit;
    @XmlElement
    private String treesConv;
    @XmlElement
    private String CO2Conv;
    @XmlElement
    private String bulbsConv;
    @XmlElement
    private String cupOfTea;
    @XmlElement
    private String glassOfWine;
    @XmlElement
    private String numOfHamb;
    @XmlElement
    private String currency;
    @XmlElement
    private String energyConv;


    @XmlTransient
    public String getCO2Unit() {
        return CO2Unit;
    }

    public void setCO2Unit(String CO2Unit) {
        this.CO2Unit = CO2Unit;
    }

    @XmlTransient
    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    @XmlTransient
    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    @XmlTransient
    public String getWaterUnit() {
        return waterUnit;
    }

    public void setWaterUnit(String waterUnit) {
        this.waterUnit = waterUnit;
    }

    @XmlTransient
    public String getTreesConv() {
        return treesConv;
    }

    public void setTreesConv(String treesConv) {
        this.treesConv = treesConv;
    }

    @XmlTransient
    public String getCO2Conv() {
        return CO2Conv;
    }

    public void setCO2Conv(String CO2Conv) {
        this.CO2Conv = CO2Conv;
    }

    @XmlTransient
    public String getBulbsConv() {
        return bulbsConv;
    }

    public void setBulbsConv(String bulbsConv) {
        this.bulbsConv = bulbsConv;
    }

    @XmlTransient
    public String getCupOfTea() {
        return cupOfTea;
    }

    public void setCupOfTea(String cupOfTea) {
        this.cupOfTea = cupOfTea;
    }

    @XmlTransient
    public String getGlassOfWine() {
        return glassOfWine;
    }

    public void setGlassOfWine(String glassOfWine) {
        this.glassOfWine = glassOfWine;
    }

    @XmlTransient
    public String getNumOfHamb() {
        return numOfHamb;
    }

    public void setNumOfHamb(String numOfHamb) {
        this.numOfHamb = numOfHamb;
    }

    @XmlTransient
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlTransient
    public String getEnergyConv() {
        return energyConv;
    }

    public void setEnergyConv(String energyConv) {
        this.energyConv = energyConv;
    }
}
