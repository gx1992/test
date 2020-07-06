/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Camera
 * 创建者:   高旭
 * 生成日期:     2020/4/16 11:36
 * 描述: 摄像机表
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.CameraRelatedDto;

/**
 * 〈功能简述〉<br> 
 * 〈摄像机表〉
 *
 * @author gaoxu
 * @create 2020/4/16 11:36
 * @since 1.0.0
 */
public class Camera {

    private long camera_no;
    private String camera_name;
    private String camera_manufacturer;
    private String camera_model;
    private String camera_ip;
    private int sdk_port;
    private String rtsp_main_url;
    private String rtsp_sub_url;
    private String camera_user;
    private String camera_password;
    private int type;
    private int status;
    private int net_type;
    private int enable_voice;
    private String remark;
    private int act_no;


    public long getCamera_no() {
        return camera_no;
    }

    public void setCamera_no(long camera_no) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNet_type() {
        return net_type;
    }

    public void setNet_type(int net_type) {
        this.net_type = net_type;
    }

    public int getEnable_voice() {
        return enable_voice;
    }

    public void setEnable_voice(int enable_voice) {
        this.enable_voice = enable_voice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAct_no() {
        return act_no;
    }

    public void setAct_no(int act_no) {
        this.act_no = act_no;
    }
}
