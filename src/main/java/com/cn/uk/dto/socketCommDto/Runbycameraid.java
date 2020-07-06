/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Runbycameraid
 * 创建者:   高旭
 * 生成日期:     2020/4/14 15:38
 * 描述: 4.2.1 手动任务启动接口
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.socketCommDto;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈4.2.1 手动任务启动接口〉
 *
 * @author gaoxu
 * @create 2020/4/14 15:38
 * @since 1.0.0
 */
public class Runbycameraid {

    private String substation;
    private int code;
    private List<RunbycameraidData> data;


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

    public List<RunbycameraidData> getData() {
        return data;
    }

    public void setData(List<RunbycameraidData> data) {
        this.data = data;
    }

}
