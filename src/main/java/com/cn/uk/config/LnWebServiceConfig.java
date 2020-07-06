/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: WebServiceConfig
 * 创建者:   高旭
 * 生成日期:     2020/5/21 11:20
 * 描述: 配置文件实体类
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 〈功能简述〉<br> 
 * 〈鲁能：配置文件实体类〉
 *
 * @author gaoxu
 * @create 2020/5/21 11:20
 * @since 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "lunengwebservice")
public class LnWebServiceConfig {

    private String ip;
    private String port;
    private String childUrl;
    private boolean enabled;



    public String getChildUrl() {
        return childUrl;
    }

    public void setChildUrl(String childUrl) {
        this.childUrl = childUrl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
