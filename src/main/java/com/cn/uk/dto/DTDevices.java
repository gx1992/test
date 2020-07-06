/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: DTDevices
 * 创建者:   高旭
 * 生成日期:     2020/4/24 10:47
 * 描述: 动环推送Devices
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈动环推送Devices〉
 *
 * @author gaoxu
 * @create 2020/4/24 10:47
 * @since 1.0.0
 */
public class DTDevices {

    private int id;
    private List<DTDevicesItems> items;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DTDevicesItems> getItems() {
        return items;
    }

    public void setItems(List<DTDevicesItems> items) {
        this.items = items;
    }
}
