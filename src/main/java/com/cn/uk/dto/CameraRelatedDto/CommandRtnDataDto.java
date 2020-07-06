package com.cn.uk.dto.CameraRelatedDto;

public class CommandRtnDataDto {
    private String substation;
    private int resultCode;//0代表成功，1代表不成功
    private String lockStatus;//0未锁定 1已锁定


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

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

}
