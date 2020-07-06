/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: HisItem
 * 创建者:   高旭
 * 生成日期:     2020/6/20 16:35
 * 描述: 历史数据item
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 〈功能简述〉<br> 
 * 〈历史数据item〉
 *
 * @author gaoxu
 * @create 2020/6/20 16:35
 * @since 1.0.0
 */
public class HisItem {

    private String date;
    private String time;
    private String sigId;
    private String val;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSigId() {
        return sigId;
    }

    public void setSigId(String sigId) {
        this.sigId = sigId;
    }
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
