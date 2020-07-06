package com.cn.uk.model;

public class DoorAccessInfo {

    private String doorID;
    private String doorName;
    private String isUsed;
    private byte inOutFlag;
    private byte doorType;

    public String getDoorID() {
        return doorID;
    }

    public void setDoorID(String doorID) {
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

    public byte getInOutFlag() {
        return inOutFlag;
    }

    public void setInOutFlag(byte inOutFlag) {
        this.inOutFlag = inOutFlag;
    }

    public byte getDoorType() {
        return doorType;
    }

    public void setDoorType(byte doorType) {
        this.doorType = doorType;
    }
}
