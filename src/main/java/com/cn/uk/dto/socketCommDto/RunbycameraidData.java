/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: RunbycameraidData
 * 创建者:   高旭
 * 生成日期:     2020/4/14 17:33
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.socketCommDto;

import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/4/14 17:33
 * @since 1.0.0
 */
public class RunbycameraidData {

    private String cameraId;
    private String msgType;
    private String msgTypeCn;
    private String imageUrl;
    private long imageTime;
    private Map<String,Object> content;

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


    public long getImageTime() {
        return imageTime;
    }

    public void setImageTime(long imageTime) {
        this.imageTime = imageTime;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}
