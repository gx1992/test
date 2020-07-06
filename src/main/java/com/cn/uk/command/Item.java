/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Items
 * 创建者:   高旭
 * 生成日期:     2020/6/19 0:24
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/19 0:24
 * @since 1.0.0
 */
public class Item {

    @XmlAttribute(name = "sigId")
    private long sigId;
    @XmlAttribute(name = "val")
    private float val;
    @XmlAttribute(name = "tm")
    private long tm;

    @XmlTransient
    public long getSigId() {
        return sigId;
    }

    public void setSigId(long sigId) {
        this.sigId = sigId;
    }

    @XmlTransient
    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    @XmlTransient
    public long getTm() {
        return tm;
    }

    public void setTm(long tm) {
        this.tm = tm;
    }
}
