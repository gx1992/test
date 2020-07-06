/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: HttpServerInboundHandler
 * 创建者:   高旭
 * 生成日期:     2020/6/17 14:49
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.Netty;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/6/17 14:49
 * @since 1.0.0
 */
import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.command.*;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.ChildReadConfig;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.config.XmlTool;
import com.cn.uk.dto.CameraRelatedDto.PushDataDto;
import com.cn.uk.dto.RtnData;
import com.cn.uk.service.QueryTaskService;
import com.google.common.io.BaseEncoding;
import io.netty.handler.codec.http.*;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpHeaders.Values;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sun.misc.BASE64Decoder;
import sun.misc.OSEnvironment;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {

    private static Log log = LogFactory.getLog(HttpServerInboundHandler.class);

    private HttpRequest request;

    private QueryTaskService taskService = AppContext.getBean(QueryTaskService.class);
    private ChildReadConfig config = AppContext.getBean(ChildReadConfig.class);

    private static  final int prjID = 8905;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {


        String ProjectSN ="",FileName="",FileType="",Timestamp="",BASE64="";
        System.out.println("Netty Server receive msg : " + msg);

        String ip = config.getIp();
        int port = config.getPort();

        /**
         * ProjectSN: DT8018004400094&
         * FileName: RTV20200617165231.XML&
         * FileType: 301&
         * Timestamp: 1592383951&
         * （xml文件）BASE64: PD94bWwgdmVyc2lvbj0nMS4wJyBlbmNvZGluZz0nVVRGLTgnID8 CjxyZXEgZGF0YT0nMzAxJyA Cgk8cHJqIHNuPSdEVDgwMTgwMDQ0MDAwOTQnIGNyZWF0ZWRUbT0nMTU5MjM4Mzk0NScgZGVhZGxpbmU9JzIwMjAtMDYtMTcnIG
         */
        String uri = "";
        if (msg instanceof HttpRequest) {
            request = (HttpRequest) msg;
            uri = request.getUri();
            System.out.println("Uri:" + uri);
        }


        /***************门禁接口数据处理*********************/

        if (msg instanceof HttpContent && uri.equals("/doorrecord")) { //上传门禁记录

            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

            String contentStr = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            log.info("接收到的doorrecord报文为："+contentStr);

            Map<String,Object> map = JSONObject.parseObject(contentStr);

            /********修改图片路径片段，开始**********/
            if(map.containsKey("pictureFile")){ //修改门禁图片地址路径为网关机IP+端口

                String imageUrl = String.valueOf(map.get("pictureFile"));

                int down = imageUrl.indexOf("down");
                imageUrl = imageUrl.substring(down);

                imageUrl = ip+":"+port+"/"+imageUrl;
                map.put("pictureFile",imageUrl);
            }
            JSONObject json = (JSONObject) JSONObject.toJSON(map.get("person"));
            Person person = json.toJavaObject(Person.class);

            String personPhoto = person.getPersonPhoto();
            personPhoto = ip+":"+port+"/"+personPhoto;
            person.setPersonPhoto(personPhoto);

            map.put("person",person);

            /********修改图片路径片段，结束**********/

            Map<String,Object> mapp = new HashMap<>();
            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();

            mapp.put("substation",subStation);
            mapp.put("type",1);
            mapp.put("passRecord",map);

            try {
                int time = (int)((new Date().getTime())/1000);
                String timeString = time+"";
                String text = BaseEncoding.base64().encode(JSON.toJSONString(mapp).getBytes(StandardCharsets.UTF_8));
                Object object = PushDataDto.buildDataDto("fs"+timeString,text);
                WebSocketServer.sendInfo(JSON.toJSONString(object), null);
                log.info("上传门禁记录:"+mapp);
            } catch (IOException e) {
                e.printStackTrace();
            }

            RtnData rtnData = new RtnData();
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMsg("成功");
            rtnData.setMessage("成功");
            String jsonStr = JSONObject.toJSONString(rtnData);
            FullHttpResponse fullHttpResponse = httpReturn(jsonStr);
            ctx.write(fullHttpResponse);
            ctx.flush();

        }else if (msg instanceof HttpContent && uri.equals("/carin")) { //接收车辆入场记录

            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

            String contentStr = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            log.info("接收到的doorrecord报文为："+contentStr);

            Map<String,Object> map = JSONObject.parseObject(contentStr);

            if(!map.containsKey("remark")){
                map.put("remark","");
            }


            if(map.containsKey("inImage")){ //修改门禁图片地址路径为网关机IP+端口

                String imageUrl = String.valueOf(map.get("inImage"));

                int down = imageUrl.indexOf("down");
                imageUrl = imageUrl.substring(down);

                imageUrl = ip+":"+port+"/"+imageUrl;
                map.put("inImage",imageUrl);
            }

            Map<String,Object> mapp = new HashMap<>();

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();

            mapp.put("substation",subStation);
            mapp.put("type",31);
            mapp.put("passRecord",map);

            try {
                int time = (int)((new Date().getTime())/1000);
                String timeString = time+"";
                String text = BaseEncoding.base64().encode(JSON.toJSONString(mapp).getBytes(StandardCharsets.UTF_8));
                Object object = PushDataDto.buildDataDto("fs"+timeString,text);
                WebSocketServer.sendInfo(JSON.toJSONString(object), null);
                log.info("接收车辆入场记录:"+mapp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RtnData rtnData = new RtnData();
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMsg("成功");
            rtnData.setMessage("成功");
            String jsonStr = JSONObject.toJSONString(rtnData);
            FullHttpResponse fullHttpResponse = httpReturn(jsonStr);
            ctx.write(fullHttpResponse);
            ctx.flush();

        }else if (msg instanceof HttpContent && uri.equals("/carout")) { //接收车辆出场记录

            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

            String contentStr = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            log.info("接收到的doorrecord报文为："+contentStr);

            Map<String,Object> map = JSONObject.parseObject(contentStr);

            if(!map.containsKey("remark")){
                map.put("remark","");
            }

            if(map.containsKey("inImage")){ //修改门禁图片地址路径为网关机IP+端口

                String imageUrl = String.valueOf(map.get("inImage"));
                int down = imageUrl.indexOf("down");
                imageUrl = imageUrl.substring(down);

                imageUrl = ip+":"+port+"/"+imageUrl;
                map.put("inImage",imageUrl);
            }

            if(map.containsKey("outImage")){ //修改门禁图片地址路径为网关机IP+端口

                String imageUrl = String.valueOf(map.get("outImage"));
                int down = imageUrl.indexOf("down");
                imageUrl = imageUrl.substring(down);

                imageUrl = ip+":"+port+"/"+imageUrl;
                map.put("outImage",imageUrl);
            }


            Map<String,Object> mapp = new HashMap<>();
            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();

            mapp.put("substation",subStation);
            mapp.put("type",32);
            mapp.put("passRecord",map);

            try {
                int time = (int)((new Date().getTime())/1000);
                String timeString = time+"";
                String text = BaseEncoding.base64().encode(JSON.toJSONString(mapp).getBytes(StandardCharsets.UTF_8));
                Object object = PushDataDto.buildDataDto("fs"+timeString,text);
                WebSocketServer.sendInfo(JSON.toJSONString(object), null);
                log.info("接收车辆出场记录:"+mapp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RtnData rtnData = new RtnData();
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMsg("成功");
            rtnData.setMessage("成功");
            String jsonStr = JSONObject.toJSONString(rtnData);
            FullHttpResponse fullHttpResponse = httpReturn(jsonStr);
            ctx.write(fullHttpResponse);
            ctx.flush();

        }else if (msg instanceof HttpContent && uri.equals("http://192.168.10.2/XMLReceiver.cgi")) {   //动环接口数据处理
    //    }else if (msg instanceof HttpContent && uri.equals("/XMLReceiver.cgi")) {   //动环接口数据处理 /XMLReceiver.cgi

            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

            String contentStr = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            log.info("接收到的content报文为："+contentStr);

            try {

                WebSocketServer.sendInfo(JSON.toJSONString(contentStr), null);
                log.info("接收到的XMLReceiver.cgi的content报文为:"+contentStr);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if(contentStr.indexOf("&")>-1){
                String[] strings = contentStr.split("&");

                ProjectSN = strings[0];
                FileName  = strings[1];
                FileType  = strings[2];
                Timestamp = strings[3];
                BASE64    = strings[4];


                String data = decode(BASE64);
                log.info("解析出来的报文xml："+data);

                //读取字符串 data的值
                XMLSerializer xmlSerializer = new XMLSerializer();
                String resutStr = xmlSerializer.read(data).toString();
                JSONObject obj = JSONObject.parseObject(resutStr);
                String dataVal = String.valueOf(obj.get("@data"));

                String resultXML = "";
                if(dataVal.equals("101")){ //表示注册数据上送

                    RegisterData registerData = XmlTool.serializeModelData(data, RegisterData.class, PrjReg.class, Reg.class);
                    log.info("序列化成功！");
                    String sn = registerData.getPrj().getSn();

                    if(ProjectSN!=""){

                        if(ProjectSN.equals(sn)){
                            resultXML = SuccessXML("101");
                        }else{
                            resultXML = ErrorXML("101","注册ID不匹配！");
                        }
                    }

                    try {
                        WebSocketServer.sendInfo(JSON.toJSONString(resultXML), null);
                        log.info("返回给对方 101报文 结果:"+resultXML);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    FullHttpResponse fullRespcnse = getResultXML(resultXML);
                    ctx.write(fullRespcnse);
                    ctx.flush();

                }else if(dataVal.equals("201")){  //用户自定义配置参数

                    log.info("获取 201数据："+data);
                    resultXML = SuccessXML("201");

                    try {
                        WebSocketServer.sendInfo(JSON.toJSONString(resultXML), null);
                        log.info("返回给对方 201报文 结果:"+resultXML);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FullHttpResponse fullRespcnse = getResultXML(resultXML);
                    ctx.write(fullRespcnse);
                    ctx.flush();


                }else if(dataVal.equals("202")){  //表示站点配置参数

                    ConfigData configData = XmlTool.serializeConfigData(data, ConfigData.class, ConPrjReg.class, ConItem.class);
                    log.info("202序列化成功！");
                    String sn = configData.getPrj().getSn();
                    if(ProjectSN!=""){

                        if(ProjectSN.equals(sn)){
                            resultXML = SuccessXML("202");
                        }else{
                            resultXML = ErrorXML("202","注册ID不匹配！");
                        }
                    }

                    try {
                        WebSocketServer.sendInfo(JSON.toJSONString(resultXML), null);
                        log.info("返回给对方 202报文 结果:"+resultXML);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                    FullHttpResponse fullRespcnse = getResultXML(resultXML);
                    ctx.write(fullRespcnse);
                    ctx.flush();

                }else if(dataVal.equals("203")){  //设备配置参数

                    log.info("获取 203数据："+data);

                    ConfigData configData = XmlTool.serializeConfigData(data, ConfigData.class, ConPrjReg.class, ConfigItem3.class);
                    log.info("203序列化成功！");
                    String sn = configData.getPrj().getSn();
                    if(ProjectSN!=""){

                        if(ProjectSN.equals(sn)){
                            resultXML = SuccessXML("203");
                        }else{
                            resultXML = ErrorXML("203","注册ID不匹配！");
                        }
                    }

                    try {
                        WebSocketServer.sendInfo(JSON.toJSONString(resultXML), null);
                        log.info("返回给对方 203报文 结果:"+resultXML);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FullHttpResponse fullRespcnse = getResultXML(resultXML);
                    ctx.write(fullRespcnse);
                    ctx.flush();


                }else if(dataVal.equals("204")){  //信号配置参数

                    log.info("获取 204数据："+data);

                    resultXML = SuccessXML("204");

                    try {
                        WebSocketServer.sendInfo(JSON.toJSONString(resultXML), null);
                        log.info("返回给对方 204报文 结果:"+resultXML);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FullHttpResponse fullRespcnse = getResultXML(resultXML);
                    ctx.write(fullRespcnse);
                    ctx.flush();


                }else if(dataVal.equals("301")){  //表示实时数据

                    log.info("获取 301数据："+data);

                    /*************将实时数据上送到主站***********/

                    XMLSerializer serializer = new XMLSerializer();
                    log.info("开始转换！");
                    String realStr = serializer.read(data).toString();
                    System.out.println("serializer将xml转换为字符串为:"+realStr);
                    realStr = realStr.replace("@", "");

                    JSONObject objReal = JSONObject.parseObject(realStr);

                    Object prj = objReal.get("prj");
                    JSONObject objPrj = (JSONObject) JSONObject.toJSON(prj);
                    String sn = String.valueOf(objPrj.get("sn"));

                    log.info("转为字符串后 sn:"+sn);

                    RealTimeResult timeResult = objReal.toJavaObject(RealTimeResult.class);
                    String data1 = timeResult.getData();
                    Prj prj1 = timeResult.getPrj();

                    String sn1 = prj1.getSn();
                    long createdTm = prj1.getCreatedTm();
                    List<Dev> dev1 = prj1.getDev();

                    List<Devices> list = new ArrayList<>();
                    if(dev1.size()>0){
                        for(int i=0;i<dev1.size();i++){

                            Devices device = new Devices();
                            device.setId(dev1.get(i).getId());

                            List<Item> items = dev1.get(i).getItem();

                            List<Items> list1 = new ArrayList<>();
                            List<Map<String,Object>> mapList = new ArrayList<>();
                            if(items != null && items.size()>0){
                                for(int j=0;j<items.size();j++){
                                    Items items1 = new Items();
                                    items1.setSigId(items.get(j).getSigId());
                                    items1.setTm(items.get(j).getTm());
                                    items1.setVal(items.get(j).getVal());
                                    list1.add(items1);


                                    String s = timeStamp2Date(String.valueOf(createdTm), "");
                                    //date='2020-05-22' time='16:00:00' sigId='8001' val='27.1000000'
                                    //历史数据里device的items

                                    String date = s.substring(0,10);
                                    String time = s.substring(11);

                                    Map<String,Object> itemMap = new HashMap<>();
                                    itemMap.put("date",date);
                                    itemMap.put("time",time);
                                    itemMap.put("sigId",items.get(j).getSigId());
                                    itemMap.put("val",items.get(j).getVal());

                                    mapList.add(itemMap);

                                    //获取厂站名称
                                    Map<String,Object> m = taskService.getSubstation();
                                    String subStation = m.get("substation").toString();

                                    //判断val是否在阈值之内，否则则告警出去 -1.0E9
                                    float val = items.get(j).getVal();
                                    if(val != 0.0){
                                        if(val<0.0 || val >30.0){
                                            Map<String,Object> alarmMap = new HashMap<>();
                                            alarmMap.put("type","sensorAlarm");
                                            alarmMap.put("substation",subStation);
                                            alarmMap.put("id",dev1.get(i).getId());
                                            alarmMap.put("sigId",items.get(j).getSigId());
                                            alarmMap.put("val",val);
                                            alarmMap.put("tm",items.get(j).getTm());
                                            alarmMap.put("max","30");
                                            alarmMap.put("min",0);
                                            alarmMap.put("alarm","高温预警");
                                            alarmMap.put("alarmLevel","");

                                            int time1 = (int)((new Date().getTime())/1000);
                                            String timeString = time1+"";
                                            String text = BaseEncoding.base64().encode(JSON.toJSONString(alarmMap).getBytes(StandardCharsets.UTF_8));
                                            Object object = PushDataDto.buildDataDto("fs"+timeString,text);
                                            WebSocketServer.sendInfo(JSON.toJSONString(object), null);

                                        }
                                    }

                                }
                            }
                            device.setItems(list1);
                            list.add(device);
                            log.info("mapList数据为："+mapList);
                            //将动环实时数据保存到表，作为10分钟之后的历史数据进行提交
                            Map<String,Object> map = new HashMap<>();

                            map.put("sn",sn1);
                            map.put("createdTm",createdTm);
                            map.put("deviceID",dev1.get(i).getId());

                            String mapStr = JSONObject.toJSONString(mapList);
                            map.put("deviceItem",mapStr);

                            int flag = taskService.insertHisDhData(map);
                            if(flag>0) log.info("实时数据插入到表成功!");

                        }
                    }
                    sensorGateway sensorGateway = new sensorGateway();
                    Project timeProject = new Project();

                    timeProject.setDevices(list);
                    timeProject.setCreatedTm(createdTm);
                    timeProject.setSn(sn1);

                    sensorGateway.setData(data1);
                    sensorGateway.setProject(timeProject);

                    //获取厂站名称
                    Map<String,Object> m = taskService.getSubstation();
                    String subStation = m.get("substation").toString();
                    sensorGateway.setSubstation(subStation);
                    sensorGateway.setType("sensorGateway");

                    Map<String,Object> map = new HashMap<>();
                    map.put("sensorGateway",sensorGateway);
                //    String jsonStr = JSONObject.toJSONString(sensorGateway);
                    try {

                        int time = (int)((new Date().getTime())/1000);
                        String timeString = time+"";
                        String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
                        Object object = PushDataDto.buildDataDto("fs"+timeString,text);
                        WebSocketServer.sendInfo(JSON.toJSONString(object), null);

                        //   WebSocketServer.sendInfo(JSON.toJSONString(jsonStr), null);
                        log.info("动环实时数据开始上送到主站>>>:"+JSON.toJSONString(map));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(ProjectSN!=""){

                        if(ProjectSN.equals(sn)){
                            resultXML = SuccessXML("301");
                        }else{
                            resultXML = ErrorXML("301","注册ID不匹配！");
                        }
                    }
                    FullHttpResponse fullResponse = getResultXML(resultXML);
                    ctx.write(fullResponse);
                    ctx.flush();



                }else if(dataVal.equals("402")){  //表示10-15分钟历史数据

                    log.info("获取 402数据："+data);
                    XMLSerializer serializer = new XMLSerializer();
                    log.info("开始转换！");
                    String realStr = serializer.read(data).toString();
                    System.out.println("serializer将xml转换为字符串为:"+realStr);
                    realStr = realStr.replace("@", "");

                    JSONObject objReal = JSONObject.parseObject(realStr);

                    Object prj = objReal.get("prj");
                    JSONObject objPrj = (JSONObject) JSONObject.toJSON(prj);
                    String sn = String.valueOf(objPrj.get("sn"));

                    log.info("转为字符串后 sn:"+sn);

                    HisResult hisResult = objReal.toJavaObject(HisResult.class);

                    String data1 = hisResult.getData();
                    HisPrj prj1 = hisResult.getPrj();
                    String sn1 = prj1.getSn();
                    long createdTm = prj1.getCreatedTm();
                    List<HisDevices> dev = prj1.getDev();

                    Map<String, Object> sensorGateway = new HashMap<>();
                    Map<String,Object> map = new HashMap<>();
                    Map<String,Object> map1 = new HashMap<>();

                    map1.put("sn",sn1);
                    map1.put("createdTm",createdTm);
                    map1.put("devices",dev);

                    sensorGateway.put("data",data1);
                    sensorGateway.put("project",map1);

                    map.put("sensorGateway",sensorGateway);
                    map.put("substation","FS_01");
                    map.put("type","sensorGateway");

                    String jsonStr = JSONObject.toJSONString(map);

                    try {

                        int time = (int)((new Date().getTime())/1000);
                        String timeString = time+"";
                        String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
                        Object object = PushDataDto.buildDataDto("fs"+timeString,text);
                        WebSocketServer.sendInfo(JSON.toJSONString(object), null);


                    //    WebSocketServer.sendInfo(JSON.toJSONString(jsonStr), null);
                        log.info("动环历史数据开始上送到主站>>>:"+jsonStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(ProjectSN!=""){

                        resultXML = SuccessXML("402");
                    }
                    FullHttpResponse fullResponse = getResultXML(resultXML);
                    ctx.write(fullResponse);
                    ctx.flush();

                }

            }
            buf.release();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage());
        ctx.close();
    }

    public FullHttpResponse getResultXML(String resultXML ){

        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(resultXML.getBytes("UTF-8")));
            //设置通用请求属性为默认浏览器编码类型
            response.headers().set(CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");

            response.headers().set(CONTENT_LENGTH,
                    response.content().readableBytes());

            response.headers().set(ACCEPT,"text/plain,*/*");
            response.headers().set(ACCEPT_ENCODING,"gzip,deflate,sdch");
            response.headers().set(ACCEPT_LANGUAGE,"zh-CN,zh;q=0.8");

            //允许跨域访问
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"Origin, X-Requested-With, Content-Type, Accept");
            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"GET, POST, PUT,DELETE");

            if (HttpHeaders.isKeepAlive(request)) {
                response.headers().set(CONNECTION, Values.KEEP_ALIVE);
            }
        }catch (Exception e){
            log.error("返回的xml有异常");
        }

        return response;

    }


    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-4 上午09:24:26
     */
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"UTF-8");
        } catch (Exception e) {
            return null;
        }
    }


    public String SuccessXML(String data){

        String resultXML = "";
        // 创建解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            // 不显示standalone="no"
            document.setXmlStandalone(true);
            Element res = document.createElement("res");
            // 为res节点添加属性
            res.setAttribute("data", data);

            Element code = document.createElement("code");
            code.setTextContent("0");

            Element prjId = document.createElement("prjId");
            prjId.setTextContent(prjID+"");

            res.appendChild(code);
            res.appendChild(prjId);

            document.appendChild(res);


            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            resultXML = documentToString(document);
            System.out.println("生成xml 的document："+resultXML);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e){
            log.error("xml解析异常");
        }

        return resultXML;
    }

    public String ErrorXML(String data,String message){

        log.error(message);
        String resultXML = "";
        // 创建解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            // 不显示standalone="no"
            document.setXmlStandalone(true);
            Element res = document.createElement("res");
            // 为res节点添加属性
            res.setAttribute("data", data);

            Element code = document.createElement("code");
            code.setTextContent("-1");

            Element msg = document.createElement("msg");
            msg.setTextContent(message);

            res.appendChild(code);
            res.appendChild(msg);
            document.appendChild(res);

            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            resultXML = documentToString(document);
            System.out.println("生成xml 的document："+resultXML);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e){
            log.error("xml解析异常");
        }
        return resultXML;
    }



    public static String documentToString(Document document) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            StringWriter sw = new StringWriter();
            // 输出内容是否使用换行
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.transform(new DOMSource(document), new StreamResult(sw));
            return sw.toString();
        } catch (Exception tEx) {
            tEx.printStackTrace();
        }
        return null;
    }


    public FullHttpResponse httpReturn(String data){

        FullHttpResponse response = null;
        try{

            response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(data.getBytes("UTF-8")));
            //设置通用请求属性为默认浏览器编码类型
            response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");

            response.headers().set(CONTENT_LENGTH,
                    response.content().readableBytes());

            response.headers().set(ACCEPT,"text/plain,*/*");
            response.headers().set(ACCEPT_LANGUAGE,"zh-CN,zh;q=0.8");

            //允许跨域访问
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"Origin, X-Requested-With, Content-Type, Accept");
            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"GET, POST, PUT,DELETE");

            if (HttpHeaders.isKeepAlive(request)) {
                response.headers().set(CONNECTION, Values.KEEP_ALIVE);
            }
        }catch (Exception e){

            log.error("http返回异常",e.getCause());
        }

        return response;
    }

    /**
     * 格式化xml
     * @param str
     * @return
     * @throws Exception
     */
    public static String formatXml(String str) throws Exception {
        org.dom4j.Document document = null;
        document = DocumentHelper.parseText(str);
        // 格式化输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        StringWriter writer = new StringWriter();
        // 格式化输出流
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        // 将document写入到输出流
        xmlWriter.write(document);
        xmlWriter.close();

        return writer.toString();
    }


    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }



    public static void main(String[] args) throws Exception {


        String url = "http://1.10.165.12:9012/down/pic/20190729/door/148080896/00_47_a00007aa_5d3f19bf.jpg";

        int down = url.indexOf("down");

        String substring = url.substring(down);

        System.out.println(substring+"down"+down);

        String data = "<?xml version='1.0' encoding='UTF-8' ?>\n" +
                "<req data='301' >\n" +
                "        <prj sn='DT8018004400094' createdTm='1592583359' deadline='2020-06-20' dailySeq='190' totalSeq='5091' >\n" +
                "                <dev id='1' category='0' >\n" +
                "                </dev>\n" +
                "                <dev id='1111' category='1' >\n" +
                "                        <item sigId='1212' val='2200.0000000' tm='1592583357' />\n" +
                "                        <item sigId='1404' val='2200.0000000' tm='1592583357' />\n" +
                "                </dev>\n" +
                "                <dev id='60001' category='0' >\n" +
                "                </dev>\n" +
                "                <dev id='60002' category='0' >\n" +
                "                </dev>\n" +
                "                <dev id='60011' category='0' >\n" +
                "                </dev>\n" +
                "                <dev id='60013' category='0' >\n" +
                "                </dev>\n" +
                "        </prj>\n" +
                "</req>";

        data = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<req data='402' >\n" +
                "\t<prj sn='DT8018004400093' createdTm='1590135504' deadline='2020-05-22' dailySeq='72' totalSeq='72' >\n" +
                "\t\t<dev id='3014' category='8'>\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8001' val='27.1000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8001' val='27.2000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8002' val='27.1000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8002' val='27.2000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8003' val='27.2000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8003' val='27.2000000' />\n" +
                "\t\t</dev>\n" +
                "\t\t<dev id='3019' category='8'>\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8001' val='28.3000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8001' val='28.7000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8002' val='28.3000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8002' val='28.7000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8003' val='28.3000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8003' val='28.7000000' />\n" +
                "\t\t</dev>\n" +
                "\t\t<dev id='3020' category='8'>\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8001' val='28.3000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8001' val='28.4000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8002' val='28.3000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8002' val='28.7000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:00:00' sigId='8003' val='28.3000000' />\n" +
                "\t\t\t<item date='2020-05-22' time='16:10:00' sigId='8003' val='28.4000000' />\n" +
                "\t\t</dev>\n" +
                "\n" +
                "\t</prj>\n" +
                "</req>";

        log.info("获取 402数据："+data);
        XMLSerializer serializer = new XMLSerializer();
        log.info("开始转换！");
        String realStr = serializer.read(data).toString();
        System.out.println("serializer将xml转换为字符串为:"+realStr);
        realStr = realStr.replace("@", "");

        JSONObject objReal = JSONObject.parseObject(realStr);

        Object prj = objReal.get("prj");
        JSONObject objPrj = (JSONObject) JSONObject.toJSON(prj);
        String sn = String.valueOf(objPrj.get("sn"));

        log.info("转为字符串后 sn:"+sn);

        HisResult hisResult = objReal.toJavaObject(HisResult.class);

        String data1 = hisResult.getData();
        HisPrj prj1 = hisResult.getPrj();
        String sn1 = prj1.getSn();
        long createdTm = prj1.getCreatedTm();
        List<HisDevices> dev = prj1.getDev();

        Map<String, Object> sensorGateway = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();

        map1.put("sn",sn1);
        map1.put("createdTm",createdTm);
        map1.put("devices",dev);

        sensorGateway.put("data",data1);
        sensorGateway.put("project",map1);

        map.put("sensorGateway",sensorGateway);
        map.put("substation","FS_01");
        map.put("type","sensorGateway");

        String jsonStr = JSONObject.toJSONString(map);
        System.out.println(jsonStr);



        /*

        XMLSerializer serializer = new XMLSerializer();

        String realStr = serializer.read(data).toString();
        realStr = realStr.replace("@", "");


        JSONObject objReal = JSONObject.parseObject(realStr);
        RealTimeResult timeResult = objReal.toJavaObject(RealTimeResult.class);

        String data1 = timeResult.getData();
        Prj prj1 = timeResult.getPrj();

        String sn1 = prj1.getSn();
        long createdTm = prj1.getCreatedTm();
        List<Dev> dev1 = prj1.getDev();

        List<Devices> list = new ArrayList<>();
        if(dev1.size()>0){
            for(int i=0;i<dev1.size();i++){

                Devices device = new Devices();
                device.setId(dev1.get(i).getId());

                List<Item> items = dev1.get(i).getItem();

                List<Items> list1 = new ArrayList<>();
                if(items != null && items.size()>0){
                    for(int j=0;j<items.size();j++){
                        Items items1 = new Items();
                        items1.setSigId(items.get(j).getSigId());
                        items1.setTm(items.get(j).getTm());
                        items1.setVal(items.get(j).getVal());
                        list1.add(items1);
                    }
                }
                device.setItems(list1);

                list.add(device);
            }
        }
        System.out.println(timeResult.toString());

        sensorGateway sensorGateway = new sensorGateway();
        Project timeProject = new Project();

        timeProject.setDevices(list);
        timeProject.setCreatedTm(createdTm);
        timeProject.setSn(sn1);


        sensorGateway.setData(data1);
        sensorGateway.setProject(timeProject);
        sensorGateway.setSubstation("FS_01");
        sensorGateway.setType("sensorGateway");

        String jsonStr = JSONObject.toJSONString(sensorGateway);

        System.out.println(jsonStr);*/



    }



}