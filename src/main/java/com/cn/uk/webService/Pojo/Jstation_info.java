/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jstation_info
 * 创建者:   高旭
 * 生成日期:     2020/6/8 19:22
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/8 19:22
 * @since 1.0.0
 */
public class Jstation_info {

    private String ID;
    private String         Direction;
    private String ClientAddr;
    private String         Ver;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getClientAddr() {
        return ClientAddr;
    }

    public void setClientAddr(String clientAddr) {
        ClientAddr = clientAddr;
    }

    public String getVer() {
        return Ver;
    }

    public void setVer(String ver) {
        Ver = ver;
    }
}
