/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jconn_re_info
 * 创建者:   高旭
 * 生成日期:     2020/6/5 11:41
 * 描述: 配置信息
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈配置信息〉
 *
 * @author gaoxu
 * @create 2020/6/5 11:41
 * @since 1.0.0
 */
public class Jconn_re_info {

    private Integer IsRlUploadFile;
    private ServerAddr ServerAddr;
    private FileAddr FileAddr;


    public Integer getIsRlUploadFile() {
        return IsRlUploadFile;
    }

    public void setIsRlUploadFile(Integer isRlUploadFile) {
        IsRlUploadFile = isRlUploadFile;
    }

    public com.cn.uk.webService.Pojo.ServerAddr getServerAddr() {
        return ServerAddr;
    }

    public void setServerAddr(com.cn.uk.webService.Pojo.ServerAddr serverAddr) {
        ServerAddr = serverAddr;
    }

    public com.cn.uk.webService.Pojo.FileAddr getFileAddr() {
        return FileAddr;
    }

    public void setFileAddr(com.cn.uk.webService.Pojo.FileAddr fileAddr) {
        FileAddr = fileAddr;
    }
}
