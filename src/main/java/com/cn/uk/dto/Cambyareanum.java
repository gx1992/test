/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Cambyareanum
 * 创建者:   高旭
 * 生成日期:     2020/4/17 11:07
 * 描述: 4.1.1 根据区域编码查询摄像头列表接口
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈4.1.1 根据区域编码查询摄像头列表接口〉
 *
 * @author gaoxu
 * @create 2020/4/17 11:07
 * @since 1.0.0
 */
public class Cambyareanum {
    private String substation;
    private int code;
    private List<Map<String,Object>> data;

    private String message;
    private boolean success;



    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
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

}
