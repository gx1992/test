/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: XmlTool
 * 创建者:   高旭
 * 生成日期:     2020/5/22 15:17
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.config;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/5/22 15:17
 * @since 1.0.0
 */
import com.cn.uk.command.ConfigData;
import com.cn.uk.command.RealTimeData;
import com.cn.uk.command.RegisterData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XmlTool {
    private static final Logger logger = LoggerFactory.getLogger(XmlTool.class);


    public static RegisterData serializeModelData(String xmlStr, Class... cls) {
        try {
            JAXBContext context = JAXBContext.newInstance(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader sr = new StringReader(xmlStr);

            RegisterData registerData = (RegisterData) unmarshaller.unmarshal(sr);

            return registerData;
        } catch (JAXBException e) {
            logger.error("序列化XML失败", e);
            return null;
        }
    }

    public static ConfigData serializeConfigData(String xmlStr, Class... cls) {
        try {
            JAXBContext context = JAXBContext.newInstance(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader sr = new StringReader(xmlStr);

            ConfigData configData = (ConfigData) unmarshaller.unmarshal(sr);

            return configData;
        } catch (JAXBException e) {
            logger.error("序列化XML失败", e);
            return null;
        }
    }


    /**
     * 实时数据序列化
     * @param xmlStr
     * @param cls
     * @return
     */
    public static RealTimeData serializeRealTimeData(String xmlStr, Class... cls) {
        try {
            JAXBContext context = JAXBContext.newInstance(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader sr = new StringReader(xmlStr);

            RealTimeData realTimeData = (RealTimeData) unmarshaller.unmarshal(sr);

            return realTimeData;
        } catch (JAXBException e) {
            logger.error("序列化XML失败", e);
            return null;
        }
    }


}
