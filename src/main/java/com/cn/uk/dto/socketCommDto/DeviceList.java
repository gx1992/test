/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: DeviceList
 * 创建者:   高旭
 * 生成日期:     2020/4/15 12:14
 * 描述: 获取子设备列表
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.socketCommDto;


import com.cn.uk.dto.CameraRelatedDto.CameraBriefInfos;

import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈获取子设备列表〉
 *
 * @author gaoxu
 * @create 2020/4/15 12:14
 * @since 1.0.0
 */
public class DeviceList {


    private String substation;
    private int resultCode;
    private CameraBriefInfos cameraBriefInfos;
    private String audioBriefInfos;
    private String alarmBriefInfos;


    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public CameraBriefInfos getCameraBriefInfos() {
        return cameraBriefInfos;
    }

    public void setCameraBriefInfos(CameraBriefInfos cameraBriefInfos) {
        this.cameraBriefInfos = cameraBriefInfos;
    }

    public String getAudioBriefInfos() {
        return audioBriefInfos;
    }

    public void setAudioBriefInfos(String audioBriefInfos) {
        this.audioBriefInfos = audioBriefInfos;
    }

    public String getAlarmBriefInfos() {
        return alarmBriefInfos;
    }

    public void setAlarmBriefInfos(String alarmBriefInfos) {
        this.alarmBriefInfos = alarmBriefInfos;
    }

}
