package com.cn.uk.model;

public class TigCamera {
    private String camera_no;
    private String camera_name;
    private String camera_manufacturer;
    private String camera_model;
    private String camera_ip;
    private int sdk_port;
    private String rtsp_main_url;
    private String rtsp_sub_url;
    private String camera_user;
    private String camera_password;
    private byte type;
    private byte status;
    private byte net_type;
    private byte enable_voice;
    private String reamrk;
    private String nvr_no;


    public String getNvr_no() {
        return nvr_no;
    }

    public void setNvr_no(String nvr_no) {
        this.nvr_no = nvr_no;
    }

    public String getCamera_no() {
        return camera_no;
    }

    public void setCamera_no(String camera_no) {
        this.camera_no = camera_no;
    }

    public String getCamera_name() {
        return camera_name;
    }

    public void setCamera_name(String camera_name) {
        this.camera_name = camera_name;
    }

    public String getCamera_manufacturer() {
        return camera_manufacturer;
    }

    public void setCamera_manufacturer(String camera_manufacturer) {
        this.camera_manufacturer = camera_manufacturer;
    }

    public String getCamera_model() {
        return camera_model;
    }

    public void setCamera_model(String camera_model) {
        this.camera_model = camera_model;
    }

    public String getCamera_ip() {
        return camera_ip;
    }

    public void setCamera_ip(String camera_ip) {
        this.camera_ip = camera_ip;
    }

    public int getSdk_port() {
        return sdk_port;
    }

    public void setSdk_port(int sdk_port) {
        this.sdk_port = sdk_port;
    }

    public String getRtsp_main_url() {
        return rtsp_main_url;
    }

    public void setRtsp_main_url(String rtsp_main_url) {
        this.rtsp_main_url = rtsp_main_url;
    }

    public String getRtsp_sub_url() {
        return rtsp_sub_url;
    }

    public void setRtsp_sub_url(String rtsp_sub_url) {
        this.rtsp_sub_url = rtsp_sub_url;
    }

    public String getCamera_user() {
        return camera_user;
    }

    public void setCamera_user(String camera_user) {
        this.camera_user = camera_user;
    }

    public String getCamera_password() {
        return camera_password;
    }

    public void setCamera_password(String camera_password) {
        this.camera_password = camera_password;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getNet_type() {
        return net_type;
    }

    public void setNet_type(byte net_type) {
        this.net_type = net_type;
    }

    public byte getEnable_voice() {
        return enable_voice;
    }

    public void setEnable_voice(byte enable_voice) {
        this.enable_voice = enable_voice;
    }

    public String getReamrk() {
        return reamrk;
    }

    public void setReamrk(String reamrk) {
        this.reamrk = reamrk;
    }
}
