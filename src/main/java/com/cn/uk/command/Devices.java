/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Devices
 * 创建者:   高旭
 * 生成日期:     2020/6/19 0:22
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/19 0:22
 * @since 1.0.0
 */
public class Devices {

    private String id;
    private List<Items> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
