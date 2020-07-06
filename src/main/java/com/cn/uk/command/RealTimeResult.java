/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: sensorGateway
 * 创建者:   高旭
 * 生成日期:     2020/6/19 0:27
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/19 0:27
 * @since 1.0.0
 */
public class RealTimeResult {

    private String data;
    private Prj prj;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Prj getPrj() {
        return prj;
    }

    public void setPrj(Prj prj) {
        this.prj = prj;
    }
}
