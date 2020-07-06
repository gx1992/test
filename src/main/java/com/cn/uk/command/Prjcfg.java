/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Prjcfg
 * 创建者:   高旭
 * 生成日期:     2020/5/25 10:44
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
 * @create 2020/5/25 10:44
 * @since 1.0.0
 */
@XmlRootElement
public class Prjcfg {

    @XmlElement
    private String name;
    @XmlElement
    private String tz;
    @XmlElement
    private String lang;
    @XmlElement
    private String license;
    @XmlElement
    private String logFreq;
    @XmlElement
    private String logDuration;
    @XmlElement
    private String install;
    @XmlElement
    private String country;
    @XmlElement
    private String city;
    @XmlElement
    private String location;
    @XmlElement
    private String longitude;
    @XmlElement
    private String latitude;

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    @XmlTransient
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @XmlTransient
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @XmlTransient
    public String getLogFreq() {
        return logFreq;
    }

    public void setLogFreq(String logFreq) {
        this.logFreq = logFreq;
    }

    @XmlTransient
    public String getLogDuration() {
        return logDuration;
    }

    public void setLogDuration(String logDuration) {
        this.logDuration = logDuration;
    }

    @XmlTransient
    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    @XmlTransient
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlTransient
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
