/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: GManualTask
 * 创建者:   高旭
 * 生成日期:     2020/4/12 13:35
 * 描述: 手动任务返回格式数据
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

/**
 * 〈功能简述〉<br> 
 * 〈手动任务返回格式数据〉
 *
 * @author gaoxu
 * @create 2020/4/12 13:35
 * @since 1.0.0
 */
public class GManualTask {

    private String substation;
    private int code;
    private ManualTaskData data;


    private int resultCode;
    private String rtspURL;


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getRtspURL() {
        return rtspURL;
    }

    public void setRtspURL(String rtspURL) {
        this.rtspURL = rtspURL;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ManualTaskData getData() {
        return data;
    }

    public void setData(ManualTaskData data) {
        this.data = data;
    }

    class ManualTaskData{

        private String cameraId;
        private String msgType;
        private String msgTypeCn;
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

        public String getMsgTypeCn() {
            return msgTypeCn;
        }

        public void setMsgTypeCn(String msgTypeCn) {
            this.msgTypeCn = msgTypeCn;
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


}
