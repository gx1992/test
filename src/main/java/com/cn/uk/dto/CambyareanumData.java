/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: CambyareanumData
 * 创建者:   高旭
 * 生成日期:     2020/4/17 14:04
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/4/17 14:04
 * @since 1.0.0
 */
public class CambyareanumData {

    private String camBrands;
    private String camModel;
    private String camType;

    private List<CameraPointList> cameraPointList;
    private List<CameraPresettingList> cameraPresettingListList;
    private String channelAlias;
    private int channelId;
    private String devAddress;
    private int devPort;
    private String devPwd;
    private String devUser;
    private String openTasks;
    private String orgName;
    private String status;
    private String remark;
    private String cameraCode;
    private String powerName;
    private String powerNum;


    public String getCamBrands() {
        return camBrands;
    }

    public void setCamBrands(String camBrands) {
        this.camBrands = camBrands;
    }

    public String getCamModel() {
        return camModel;
    }

    public void setCamModel(String camModel) {
        this.camModel = camModel;
    }

    public String getCamType() {
        return camType;
    }

    public void setCamType(String camType) {
        this.camType = camType;
    }

    public List<CameraPointList> getCameraPointList() {
        return cameraPointList;
    }

    public void setCameraPointList(List<CameraPointList> cameraPointList) {
        this.cameraPointList = cameraPointList;
    }

    public List<CameraPresettingList> getCameraPresettingListList() {
        return cameraPresettingListList;
    }

    public void setCameraPresettingListList(List<CameraPresettingList> cameraPresettingListList) {
        this.cameraPresettingListList = cameraPresettingListList;
    }

    public String getChannelAlias() {
        return channelAlias;
    }

    public void setChannelAlias(String channelAlias) {
        this.channelAlias = channelAlias;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getDevAddress() {
        return devAddress;
    }

    public void setDevAddress(String devAddress) {
        this.devAddress = devAddress;
    }

    public int getDevPort() {
        return devPort;
    }

    public void setDevPort(int devPort) {
        this.devPort = devPort;
    }

    public String getDevPwd() {
        return devPwd;
    }

    public void setDevPwd(String devPwd) {
        this.devPwd = devPwd;
    }

    public String getDevUser() {
        return devUser;
    }

    public void setDevUser(String devUser) {
        this.devUser = devUser;
    }

    public String getOpenTasks() {
        return openTasks;
    }

    public void setOpenTasks(String openTasks) {
        this.openTasks = openTasks;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCameraCode() {
        return cameraCode;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerNum() {
        return powerNum;
    }

    public void setPowerNum(String powerNum) {
        this.powerNum = powerNum;
    }

    public class CameraPointList{

        private int cameraId;
        private String deviceId;
        private int discernType;
        private int id;
        private int meterType;
        private String pointName;

    }

    public class CameraPresettingList{
        private int cameraId;
        private String presettingCode;
        private String presettingName;
        private int id;
        private int discernType;
    }
}
