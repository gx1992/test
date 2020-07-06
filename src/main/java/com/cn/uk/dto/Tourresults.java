/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Messagetourresults
 * 创建者:   高旭
 * 生成日期:     2020/5/24 8:45
 * 描述: 巡检任务结果
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈巡检任务结果〉
 *
 * @author gaoxu
 * @create 2020/5/24 8:45
 * @since 1.0.0
 */
public class Tourresults {

    private String substation;
    private int code;
    private String message;
    private boolean success;

    private List<MessageTours> data;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<MessageTours> getData() {
        return data;
    }

    public void setData(List<MessageTours> data) {
        this.data = data;
    }
}
