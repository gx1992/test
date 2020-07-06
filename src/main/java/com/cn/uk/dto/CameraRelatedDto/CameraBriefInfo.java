/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: CameraBriefInfo
 * 创建者:   高旭
 * 生成日期:     2020/4/16 15:12
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.CameraRelatedDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/4/16 15:12
 * @since 1.0.0
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CameraBriefInfo implements Serializable {

    private String code;
    private String name;
    private String deviceGroupCode;
    private String parentCode;
    private String domainCode;
    private String deviceModelType;
    private String vendorType;
    private int deviceFormType;
    private int type;
    private String cameraLocation;
    private int  cameraStatus;
    private int status;
    private int netType;
    private int isSupportIntelligent;
    private int enableVoice;
    private String nvrCode;
    private String deviceCreateTime;
    private int isExDomain;
    private String deviceIP;
    private String reserve;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeviceGroupCode(String deviceGroupCode) {
        this.deviceGroupCode = deviceGroupCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }

    public void setDeviceModelType(String deviceModelType) {
        this.deviceModelType = deviceModelType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public void setDeviceFormType(int deviceFormType) {
        this.deviceFormType = deviceFormType;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCameraLocation(String cameraLocation) {
        this.cameraLocation = cameraLocation;
    }

    public void setCameraStatus(int cameraStatus) {
        this.cameraStatus = cameraStatus;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNetType(int netType) {
        this.netType = netType;
    }

    public void setIsSupportIntelligent(int isSupportIntelligent) {
        this.isSupportIntelligent = isSupportIntelligent;
    }

    public void setEnableVoice(int enableVoice) {
        this.enableVoice = enableVoice;
    }

    public void setNvrCode(String nvrCode) {
        this.nvrCode = nvrCode;
    }

    public void setDeviceCreateTime(String deviceCreateTime) {
        this.deviceCreateTime = deviceCreateTime;
    }

    public void setIsExDomain(int isExDomain) {
        this.isExDomain = isExDomain;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public CameraBriefInfo(){}

    @Override
    public String toString() {
        return "CameraBriefInfo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", deviceGroupCode='" + deviceGroupCode + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", domainCode='" + domainCode + '\'' +
                ", deviceModelType='" + deviceModelType + '\'' +
                ", vendorType='" + vendorType + '\'' +
                ", deviceFormType=" + deviceFormType +
                ", type=" + type +
                ", cameraLocation='" + cameraLocation + '\'' +
                ", cameraStatus=" + cameraStatus +
                ", status=" + status +
                ", netType=" + netType +
                ", isSupportIntelligent=" + isSupportIntelligent +
                ", enableVoice=" + enableVoice +
                ", nvrCode='" + nvrCode + '\'' +
                ", deviceCreateTime='" + deviceCreateTime + '\'' +
                ", isExDomain=" + isExDomain +
                ", deviceIP='" + deviceIP + '\'' +
                ", reserve='" + reserve + '\'' +
                '}';
    }
}
