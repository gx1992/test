/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Equipment
 * 创建者:   高旭
 * 生成日期:     2020/6/4 19:19
 * 描述: 设备台账数据
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈设备台账数据〉
 *
 * @author gaoxu
 * @create 2020/6/4 19:19
 * @since 1.0.0
 */
public class Equipment {


    private String transformer;
    private String breaker;
    private String gis;
    private String disconnector;
    private String switchgear;
    private String ct;

    //..后续还有


    public String getTransformer() {
        return transformer;
    }

    public void setTransformer(String transformer) {
        this.transformer = transformer;
    }

    public String getBreaker() {
        return breaker;
    }

    public void setBreaker(String breaker) {
        this.breaker = breaker;
    }

    public String getGis() {
        return gis;
    }

    public void setGis(String gis) {
        this.gis = gis;
    }

    public String getDisconnector() {
        return disconnector;
    }

    public void setDisconnector(String disconnector) {
        this.disconnector = disconnector;
    }

    public String getSwitchgear() {
        return switchgear;
    }

    public void setSwitchgear(String switchgear) {
        this.switchgear = switchgear;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }
}
