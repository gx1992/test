package com.cn.uk.dto.CameraRelatedDto;

public class CameraTaskResDto {
    private String cameraId;
    private String msgType;
    private String msgType_cn;
    private String imageUrl;
    private String imageTime;
    private String content;


    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgType_cn() {
        return msgType_cn;
    }

    public void setMsgType_cn(String msgType_cn) {
        this.msgType_cn = msgType_cn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTime() {
        return imageTime;
    }

    public void setImageTime(String imageTime) {
        this.imageTime = imageTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
