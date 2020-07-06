/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: RealTimeProject
 * 创建者:   高旭
 * 生成日期:     2020/6/19 0:20
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈上送到主站时，规定的字段〉
 *
 * @author gaoxu
 * @create 2020/6/19 0:20
 * @since 1.0.0
 */
public class Project {

    private String sn;
    private  long createdTm;
    private List<Devices> devices;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public long getCreatedTm() {
        return createdTm;
    }

    public void setCreatedTm(long createdTm) {
        this.createdTm = createdTm;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }
}
