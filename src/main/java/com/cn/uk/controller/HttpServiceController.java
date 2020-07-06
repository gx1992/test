/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: HttpServiceController
 * 创建者:   高旭
 * 生成日期:     2020/6/17 0:58
 * 描述: HTTPClient服务端
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * 〈功能简述〉<br> 
 * 〈HTTPClient服务端〉
 *
 * @author gaoxu
 * @create 2020/6/17 0:58
 * @since 1.0.0
 */
@RestController
public class HttpServiceController {


    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpServiceController.class);


    @RequestMapping(value = "/1111XMLReceiver.cgi", method = RequestMethod.POST)
    public String sendPostDataByMap(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("开始执行注册命令");

        Document document = null;
        try {
            // 获取HTTP请求的输入流
            // 已HTTP请求输入流建立一个BufferedReader对象
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), "UTF-8"));

            String buffer = null;
            // 存放请求内容
            StringBuffer xml = new StringBuffer();
            while ((buffer = br.readLine()) != null) {
                // 在页面中显示读取到的请求参数
                xml.append(buffer);
            }

            SAXReader reader = new SAXReader();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xml
                    .toString().getBytes());
            InputStreamReader ir = new InputStreamReader(inputStream);
//document已经获取到xml文件
            document = reader.read(ir);
//根据项目需求，解析xml文件
//。。。。略过

        } catch (Exception ex) {
            ex.printStackTrace();
            //resDoc为返回的xml文件
        } finally {
            OutputFormat format = OutputFormat.createCompactFormat();
            format.setEncoding("UTF-8");
            XMLWriter writer;
            try {
                writer = new XMLWriter(response.getOutputStream(), format);

                FileOutputStream fos = new FileOutputStream("D://emps.xml");
                writer.setOutputStream(fos);
                writer.write(document);
                System.out.println("写入完毕！");

                //    writer.write(document);

                //resDoc为通过http返回的xml文件
                //    writer.write(resDoc);
                writer.flush();
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        String result = "调用成功：数据是 " + "name:" + request.getParameter("name") + " city:" + request.getParameter("city");
        logger.info(result);
        return JSON.toJSONString(result);
    }


}
