/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Dev
 * 创建者:   高旭
 * 生成日期:     2020/6/19 0:44
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
 * @create 2020/6/19 0:44
 * @since 1.0.0
 */
public class Dev {

    private String id;
    private String category;
    private List<Item> item;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
