package com.cn.uk.model;

import com.alibaba.fastjson.annotation.JSONField;
import jdk.nashorn.internal.ir.EmptyNode;

import java.util.Date;

public class DoorAccessEmp {

    private String empNo;
    private String empName;
    private String empSex;
    private String dptNo;
    private String empStatusid;
    private String positionNo;
    private String positionName;
    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
    private Date empBirthDay;
    private String phone;
    private String certificateType;
    private String certificateNo;
    private String etGrpDay;

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

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public String getDptNo() {
        return dptNo;
    }

    public void setDptNo(String dptNo) {
        this.dptNo = dptNo;
    }

    public String getEmpStatusid() {
        return empStatusid;
    }

    public void setEmpStatusid(String empStatusid) {
        this.empStatusid = empStatusid;
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getEmpBirthDay() {
        return empBirthDay;
    }

    public void setEmpBirthDay(Date empBirthDay) {
        this.empBirthDay = empBirthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getEtGrpDay() {
        return etGrpDay;
    }

    public void setEtGrpDay(String etGrpDay) {
        this.etGrpDay = etGrpDay;
    }
}
