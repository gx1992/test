package com.cn.uk.dto.DoorAccessDto;

import java.util.List;

public class AccessSignDto {
    private String appId;
    private String key;
    private List<AccessParkInfoDto> parkInfo;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<AccessParkInfoDto> getParkInfo() {
        return parkInfo;
    }

    public void setParkInfo(List<AccessParkInfoDto> parkInfo) {
        this.parkInfo = parkInfo;
    }
}
