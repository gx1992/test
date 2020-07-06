package com.cn.uk.model;

public class TigCameraPreset {

    private long camera_no;
    private int preset_no;
    private long devicepoint_id;
    private String preset_name;
    private String remark;

    public long getCamera_no() {
        return camera_no;
    }

    public void setCamera_no(long camera_no) {
        this.camera_no = camera_no;
    }

    public int getPreset_no() {
        return preset_no;
    }

    public void setPreset_no(int preset_no) {
        this.preset_no = preset_no;
    }

    public long getDevicepoint_id() {
        return devicepoint_id;
    }

    public void setDevicepoint_id(long devicepoint_id) {
        this.devicepoint_id = devicepoint_id;
    }

    public String getPreset_name() {
        return preset_name;
    }

    public void setPreset_name(String preset_name) {
        this.preset_name = preset_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
