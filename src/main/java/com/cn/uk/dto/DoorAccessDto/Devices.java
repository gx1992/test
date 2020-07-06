/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Devices
 * 创建者:   高旭
 * 生成日期:     2020/5/18 10:59
 * 描述: 设备信息表
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.DoorAccessDto;

/**
 * 〈功能简述〉<br> 
 * 〈设备信息表〉
 *
 * @author gaoxu
 * @create 2020/5/18 10:59
 * @since 1.0.0
 */
public class Devices {

    private String deviceGuid;
    private String deviceId;
    private String deviceName;
    private String deviceIp;
    private String deviceGateway;
    private String deviceNetmask;
    private int deviceIoType;
    private String remark;
    private String parentId;

    public String getDeviceGuid() {
        return deviceGuid;
    }

    public void setDeviceGuid(String deviceGuid) {
        this.deviceGuid = deviceGuid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getDeviceGateway() {
        return deviceGateway;
    }

    public void setDeviceGateway(String deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    public String getDeviceNetmask() {
        return deviceNetmask;
    }

    public void setDeviceNetmask(String deviceNetmask) {
        this.deviceNetmask = deviceNetmask;
    }

    public int getDeviceIoType() {
        return deviceIoType;
    }

    public void setDeviceIoType(int deviceIoType) {
        this.deviceIoType = deviceIoType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
