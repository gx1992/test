/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: sensorGateway
 * 创建者:   高旭
 * 生成日期:     2020/6/19 1:00
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
 * @create 2020/6/19 1:00
 * @since 1.0.0
 */
public class sensorGateway {

    private String data;
    private Project project;
    private String substation;
    private String type;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
