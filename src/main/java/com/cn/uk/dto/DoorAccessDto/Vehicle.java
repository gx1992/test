/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Vehicle
 * 创建者:   高旭
 * 生成日期:     2020/5/18 10:41
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.DoorAccessDto;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/5/18 10:41
 * @since 1.0.0
 */
public class Vehicle {

    private String plateNumber;
    private String plateColor;
    private String vehicleBrand;
    private Number vehicleColor;
    private int vehicleStatus;
    private String remark;


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

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public Number getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(Number vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public int getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(int vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
