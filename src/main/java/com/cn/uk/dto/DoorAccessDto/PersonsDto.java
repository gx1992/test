/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: PersonsDto
 * 创建者:   高旭
 * 生成日期:     2020/5/18 9:57
 * 描述: 所有人事数据
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.DoorAccessDto;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈所有人事数据〉
 *
 * @author gaoxu
 * @create 2020/5/18 9:57
 * @since 1.0.0
 */
public class PersonsDto {

    private String personId;
    private String personNo;
    private String personName;
    private int personGender; //0：女1：男
    private String deptId;
    private String deptName;
    private String remark;
    private String personPhoto;
    private String credentialType;
    private String identityNo;
    private String mobile;
    private String tel1;
    private String tel2;
    private String email;
    private String  roomNo;
    private String address;
    private int tenementType;

    private List<Vehicle> vehicleList;


    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonGender() {
        return personGender;
    }

    public void setPersonGender(int personGender) {
        this.personGender = personGender;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTenementType() {
        return tenementType;
    }

    public void setTenementType(int tenementType) {
        this.tenementType = tenementType;
    }
}
