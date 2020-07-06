package com.cn.uk.model;

public class Door {
    private int doorID; //门的ID
    private String doorName; //门的名称
    private String isUsed; //是否使用 1-是 0-否
    private int inOutFlag; //进出标志 1-进 2-出 0-不分进出
    private int doorType; //1-刷卡 2-人脸 3-车辆刷卡

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public int getInOutFlag() {
        return inOutFlag;
    }

    public void setInOutFlag(int inOutFlag) {
        this.inOutFlag = inOutFlag;
    }

    public int getDoorType() {
        return doorType;
    }

    public void setDoorType(int doorType) {
        this.doorType = doorType;
    }
}
