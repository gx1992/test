/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: ConfigData
 * 创建者:   高旭
 * 生成日期:     2020/6/18 14:41
 * 描述: 站点配置xml
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * 〈功能简述〉<br> 
 * 〈站点配置xml〉
 *
 * @author gaoxu
 * @create 2020/6/18 14:41
 * @since 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
public class ConfigData implements Serializable {


    private static final long serialVersionUID = 8932736808437155543L;

    @XmlAttribute(name = "data")
    private String data;

    @XmlElement(name = "prj")
    private ConPrjReg prj;

    @XmlTransient
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @XmlTransient
    public ConPrjReg getPrj() {
        return prj;
    }

    public void setPrj(ConPrjReg prj) {
        this.prj = prj;
    }

    @Override
    public String toString() {
        return "ConfigData{" +
                "data='" + data + '\'' +
                ", prj=" + prj +
                '}';
    }
}
