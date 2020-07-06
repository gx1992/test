/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Reg
 * 创建者:   高旭
 * 生成日期:     2020/5/22 16:48
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
 * @create 2020/5/22 16:48
 * @since 1.0.0
 */
public class Reg {
    private String type;
    private String name;
    private String tz;
    private String lang;
    private String license;
    private String logFreq;
    private String logDuration;
    private String install;
    private String country;
    private String city;
    private String location;
    private String longitude;
    private String latitude;

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "tz")
    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    @XmlAttribute(name = "lang")
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @XmlAttribute(name = "license")
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @XmlAttribute(name = "logFreq")
    public String getLogFreq() {
        return logFreq;
    }

    public void setLogFreq(String logFreq) {
        this.logFreq = logFreq;
    }

    @XmlAttribute(name = "logDuration")
    public String getLogDuration() {
        return logDuration;
    }

    public void setLogDuration(String logDuration) {
        this.logDuration = logDuration;
    }

    @XmlAttribute(name = "install")
    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    @XmlAttribute(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlAttribute(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlAttribute(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlAttribute(name = "longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @XmlAttribute(name = "latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
