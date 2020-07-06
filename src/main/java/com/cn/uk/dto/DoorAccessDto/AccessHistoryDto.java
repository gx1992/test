package com.cn.uk.dto.DoorAccessDto;

public class AccessHistoryDto {
    private String empNo; //人员编号
    private String empName; //姓名
    private int doorID; // 门ID
    private String doorName;//门的名称
    private String cardDay;//刷卡时间（格式 yyyy-MM-dd HH:mm:ss）
    private int inOutFlag;//进出记录（0-不分进出 1-进 2-出）
    private int empType;//人员类型（1-内部人员 2-访客）

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

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

    public String getCardDay() {
        return cardDay;
    }

    public void setCardDay(String cardDay) {
        this.cardDay = cardDay;
    }

    public int getInOutFlag() {
        return inOutFlag;
    }

    public void setInOutFlag(int inOutFlag) {
        this.inOutFlag = inOutFlag;
    }

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }
}
