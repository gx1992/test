/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: CxfWebServiceConfig
 * 创建者:   高旭
 * 生成日期:     2020/6/3 15:54
 * 描述:  服务发布
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * 〈功能简述〉<br> 
 * 〈 服务发布〉
 *
 * @author gaoxu
 * @create 2020/6/3 15:54
 * @since 1.0.0
 */
@Configuration
public class CxfServiceConfig {

    /**
     *
     * */
    @Bean("cxfServletRegistration")
    public ServletRegistrationBean dispatcherServlet(){

        return new ServletRegistrationBean(new CXFServlet(),"/ws/*");
    }
    /**
     * 鲁能：申明业务处理类 当然也可以直接 在实现类上标注 @Service
     */
   /* @Bean
    public CentralPlatformService centralPlatformService() {
        return new CentralPlatformServiceImpl();
    }*/

    /**
     * 朗驰：申明业务处理类 当然也可以直接 在实现类上标注 @Service
     */
    @Bean
    public CentralPlatformServiceImplService centralPlatformServiceImplService() {
        return new CentralPlatformServiceImplServiceImpl();
    }

    /*
     * 非必要项
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        return springBus;
    }

    /*
     * 发布 鲁能 endpoint
     */
    @Bean
    public Endpoint another_endpoint( ) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), centralPlatformServiceImplService());
        endpoint.publish("/uklnService");//发布地址

        return endpoint;
    }

    /*
     * 发布 朗驰  endpoint
     */
    /*@Bean
    public Endpoint endpoint( ) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), centralPlatformService());
        endpoint.publish("/ukService");//发布地址

        return endpoint;
    }
*/


}
