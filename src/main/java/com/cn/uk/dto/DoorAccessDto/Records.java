/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Records
 * 创建者:   高旭
 * 生成日期:     2020/5/18 11:16
 * 描述: 查询车辆停车场内的数据信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.DoorAccessDto;

/**
 * 〈功能简述〉<br> 
 * 〈查询车辆停车场内的数据信息〉
 *
 * @author gaoxu
 * @create 2020/5/18 11:16
 * @since 1.0.0
 */
public class Records {

    private String inRecordId;
    private String parkId;
    private String inDeviceId;
    private String inDeviceName;
    private String inTime;
    private String inImage;
    private String plateNumber;
    private String plateColor;
    private String stationOperator;
    private String sealName;

    public String getInRecordId() {
        return inRecordId;
    }

    public void setInRecordId(String inRecordId) {
        this.inRecordId = inRecordId;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getInDeviceId() {
        return inDeviceId;
    }

    public void setInDeviceId(String inDeviceId) {
        this.inDeviceId = inDeviceId;
    }

    public String getInDeviceName() {
        return inDeviceName;
    }

    public void setInDeviceName(String inDeviceName) {
        this.inDeviceName = inDeviceName;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getInImage() {
        return inImage;
    }

    public void setInImage(String inImage) {
        this.inImage = inImage;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getStationOperator() {
        return stationOperator;
    }

    public void setStationOperator(String stationOperator) {
        this.stationOperator = stationOperator;
    }

    public String getSealName() {
        return sealName;
    }

    public void setSealName(String sealName) {
        this.sealName = sealName;
    }
}
