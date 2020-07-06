/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: HisDevices
 * 创建者:   高旭
 * 生成日期:     2020/6/20 16:42
 * 描述: 历史数据的xml_device
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈历史数据的xml_device〉
 *
 * @author gaoxu
 * @create 2020/6/20 16:42
 * @since 1.0.0
 */
public class HisDevices {

    private String id;
    private List<HisItem> item;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<HisItem> getItem() {
        return item;
    }

    public void setItem(List<HisItem> item) {
        this.item = item;
    }
}
