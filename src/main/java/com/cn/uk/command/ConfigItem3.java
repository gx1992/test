/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: ConfigItem3
 * 创建者:   高旭
 * 生成日期:     2020/6/20 16:17
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/20 16:17
 * @since 1.0.0
 */
public class ConfigItem3 {

    private String siteId;
    private String devId;
    private String name;
    private String typeId;
    private String category;
    private String subCateory;
    private String meaureUnit;
    private String no;
    private String iso;
    private String energyType1;
    private String energyType2;
    private String energyType3;
    private String energyType4;
    private String model;
    private String brand;
    private String sn;
    private String ratedCapacity;
    private String others;
    private String status;

    @XmlAttribute(name = "id")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    @XmlAttribute(name = "id")
    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }
    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "typeId")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    @XmlAttribute(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @XmlAttribute(name = "subCateory")
    public String getSubCateory() {
        return subCateory;
    }

    public void setSubCateory(String subCateory) {
        this.subCateory = subCateory;
    }
    @XmlAttribute(name = "meaureUnit")
    public String getMeaureUnit() {
        return meaureUnit;
    }

    public void setMeaureUnit(String meaureUnit) {
        this.meaureUnit = meaureUnit;
    }
    @XmlAttribute(name = "no")
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    @XmlAttribute(name = "iso")
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }
    @XmlAttribute(name = "energyType1")
    public String getEnergyType1() {
        return energyType1;
    }

    public void setEnergyType1(String energyType1) {
        this.energyType1 = energyType1;
    }
    @XmlAttribute(name = "energyType2")
    public String getEnergyType2() {
        return energyType2;
    }

    public void setEnergyType2(String energyType2) {
        this.energyType2 = energyType2;
    }
    @XmlAttribute(name = "energyType3")
    public String getEnergyType3() {
        return energyType3;
    }

    public void setEnergyType3(String energyType3) {
        this.energyType3 = energyType3;
    }
    @XmlAttribute(name = "energyType4")
    public String getEnergyType4() {
        return energyType4;
    }

    public void setEnergyType4(String energyType4) {
        this.energyType4 = energyType4;
    }
    @XmlAttribute(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @XmlAttribute(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    @XmlAttribute(name = "sn")
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    @XmlAttribute(name = "ratedCapacity")
    public String getRatedCapacity() {
        return ratedCapacity;
    }

    public void setRatedCapacity(String ratedCapacity) {
        this.ratedCapacity = ratedCapacity;
    }
    @XmlAttribute(name = "others")
    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
    @XmlAttribute(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
