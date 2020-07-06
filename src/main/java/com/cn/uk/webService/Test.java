/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Test
 * 创建者:   高旭
 * 生成日期:     2020/6/2 15:44
 * 描述: webservice调用测试
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.command.HisItem;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.service.QueryTaskService;
import com.cn.uk.webService.Pojo.Jstation_info;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈webservice调用测试〉
 *
 * @author gaoxu
 * @create 2020/6/2 15:44
 * @since 1.0.0
 */
public class Test {

    private final Logger logger = LoggerFactory.getLogger(Test.class);

    /**
     * 2：动态调用
     */
    public static void main2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();


    //    Client client = dcf.createClient("http://192.168.16.23:18888/ws/pasService?wsdl");

        Client client = dcf.createClient("http://127.0.0.1:8080/ws/ukService?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));

        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(30000);  //连接超时
        httpClientPolicy.setAllowChunking(false);    //取消块编码
        httpClientPolicy.setReceiveTimeout(180000);     //响应超时
        conduit.setClient(httpClientPolicy);

        try {

            String substation_id = "ZS_01";
            Jstation_info jstation_info1 = new Jstation_info();

            jstation_info1.setClientAddr("http://192.168.3.10:8000");
            jstation_info1.setDirection("2");
            jstation_info1.setID("ZS_01");
            jstation_info1.setVer("3.0");

            List<Jstation_info> list = new ArrayList<>();
            list.add(jstation_info1);
            String jstation_info = JSONObject.toJSONString(jstation_info1);


            Object [] result =  client.invoke("send_config_info", substation_id,jstation_info); //首个参数为方法名，后面参数为传入的参数


            Map<String,Object> map = JSON.parseObject(String.valueOf(result[0]));

            String   jalarm_data_rl_list = String.valueOf(map.get("jalarm_data_rl_list"));

            JSONArray jsonArray = JSONArray.parseArray(jalarm_data_rl_list);

            System.out.println(jsonArray);

            String res_conf = String.valueOf(result[0]);

            System.out.println(res_conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  main(String[] args) {
        // Test.main2();


    }
}
