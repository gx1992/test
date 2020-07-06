/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: FileAddr
 * 创建者:   高旭
 * 生成日期:     2020/6/5 11:47
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
 * @create 2020/6/5 11:47
 * @since 1.0.0
 */
public class FileAddr {


    private String IP;
    private String Port;
    private String UserName;
    private String PassWord;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
