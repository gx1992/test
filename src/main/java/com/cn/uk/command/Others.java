/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Others
 * 创建者:   高旭
 * 生成日期:     2020/5/25 10:41
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/5/25 10:41
 * @since 1.0.0
 */
@XmlRootElement
public class Others extends BaseItem {

    @XmlElement
    private Prjcfg prjcfg;
    @XmlElement
    private Env env;


    @XmlTransient
    public Prjcfg getPrjcfg() {
        return prjcfg;
    }

    public void setPrjcfg(Prjcfg prjcfg) {
        this.prjcfg = prjcfg;
    }

    @XmlTransient
    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }
}
