/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: RegisterData
 * 创建者:   高旭
 * 生成日期:     2020/5/22 15:24
 * 描述: 动环注册xml
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * 〈功能简述〉<br> 
 * 〈动环注册xml〉
 *
 * @author gaoxu
 * @create 2020/5/22 15:24
 * @since 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
public class RegisterData implements Serializable {

    private static final long serialVersionUID = 4426330496023400512L;

    @XmlAttribute(name = "data")
    private String data;

    @XmlElement(name = "prj")
   private PrjReg prj;

    @XmlTransient
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @XmlTransient
    public PrjReg getPrj() {
        return prj;
    }

    public void setPrj(PrjReg prj) {
        this.prj = prj;
    }

    @Override
    public String toString() {
        return "RegisterData{" +
                "data='" + data + '\'' +
                ", prj=" + prj +
                '}';
    }
}
