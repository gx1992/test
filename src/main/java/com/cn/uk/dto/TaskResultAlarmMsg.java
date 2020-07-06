/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: TaskResultAlarmMsg
 * 创建者:   高旭
 * 生成日期:     2020/5/24 10:08
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/5/24 10:08
 * @since 1.0.0
 */
public class TaskResultAlarmMsg {

    private String       cameraId;
    private String msgType;
    private String       msgTypeCn;
    private String imageUrl;
    private String        imageTime;
    private String taskId;
    private String        content;
    private String exceptionFlag;
    private String         resultFlag;
    private String tourKey;

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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExceptionFlag() {
        return exceptionFlag;
    }

    public void setExceptionFlag(String exceptionFlag) {
        this.exceptionFlag = exceptionFlag;
    }

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getTourKey() {
        return tourKey;
    }

    public void setTourKey(String tourKey) {
        this.tourKey = tourKey;
    }
}
