/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: CameraRtspURLDto
 * 创建者:   高旭
 * 生成日期:     2020/4/22 11:53
 * 描述: 返回视频URL地址
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.socketCommDto;


/**
 * 〈功能简述〉<br> 
 * 〈返回视频URL地址〉
 *
 * @author gaoxu
 * @create 2020/4/22 11:53
 * @since 1.0.0
 */
public class CameraRtspURLDto {

    private String substation;
    private int resultCode;
    private String rtspURL;

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

    public String getRtspURL() {
        return rtspURL;
    }

    public void setRtspURL(String rtspURL) {
        this.rtspURL = rtspURL;
    }

}
