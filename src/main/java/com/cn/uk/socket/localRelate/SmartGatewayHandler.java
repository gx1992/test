package com.cn.uk.socket.localRelate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.common.utils.HttpClientUtils;
import com.cn.uk.common.utils.StringUtil;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.ChildReadConfig;
import com.cn.uk.config.DoorAccessConfig;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.dto.CameraRelatedDto.CommandRtnDataDto;
import com.cn.uk.dto.CameraRelatedDto.PushDataDto;
import com.cn.uk.dto.MessageTours;
import com.cn.uk.dto.OneTaskResult;
import com.cn.uk.dto.RtnData;
import com.cn.uk.dto.TaskResultAlarmMsg;
import com.cn.uk.dto.socketCommDto.Runbycameraid;
import com.cn.uk.dto.socketCommDto.RunbycameraidData;
import com.google.common.io.BaseEncoding;
import javafx.beans.binding.ObjectBinding;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Cache;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 与C++那边通信，长连接，进行持续接收报文
 */

public class SmartGatewayHandler implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(SmartGatewayHandler.class);
    private ChildReadConfig readConfig = AppContext.getBean(ChildReadConfig.class);
    private boolean stopFlag;
    public static Socket socket;
    private String resp = "";


    public SmartGatewayHandler(Socket socket){
           this.socket = socket;
    }

    //存储捷顺门禁的key,name以及url地址
    private static final ConcurrentHashMap<String,String> accessKeyAndUrlMap = new ConcurrentHashMap();

    private HashMap<String,String> headParams = new HashMap<>();
    private  String apiurl="";
    {
        String ip = "127.0.0.1";//readConfig.getIp();
        int port = readConfig.getPort();
        apiurl = "http://"+ip+":"+port+"";
        //初始化请求头
        headParams.put("Content-type", "application/json; charset=utf-8");
        headParams.put("Accept-Language", "zh-CN,zh;q=0.9");
        headParams.put("Accept", "application/json");
        headParams.put("v", "1.0");

        accessKeyAndUrlMap.put("camera", apiurl+"/csg.app.yalian.ptzcontrol.post.re");
        accessKeyAndUrlMap.put("manualtask", apiurl+"/csg.app.yalian.runbycameraid.post.re");
        accessKeyAndUrlMap.put("rtspurl", apiurl+"/csg.app.yalian.rtspurl.post.re");
        accessKeyAndUrlMap.put("runPatrolPlanInfo", apiurl+"/csg.app.yalian.runpatrolplaninfo.post.re");
        accessKeyAndUrlMap.put("reqexemetertask", apiurl+"/csg.app.yalian.reqexemetertask.post.re");
        accessKeyAndUrlMap.put("getmessagetourresults", apiurl+"/csg.app.yalian.getmessagetourresults.post.re");


    }


    @Override
    public void run() {
        System.out.println("SmartGatewayHandler开始读取传来的流数据");
        byte[] bytes = new byte[8192];
        String timeString = (int)((new Date().getTime())/1000)+"";
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            logger.error("获取输入流失败", e);
            return;
        }

        final BlockingQueue<String> blockingQueue =  new LinkedBlockingDeque<>();
        Thread messageHandler =  new Thread(() -> {
            Map<String,Object> gMap = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> mapRoot;
            int nCount = 0;
            int code = 0;
            String temp = "", commName = "", commData = "";
            JSONArray jsonArray = null;


            while (!Thread.currentThread().isInterrupted()) {
                try {

                    String resp = blockingQueue.take();
                    //处理数据
                    //去掉换行符
                    resp = resp.replace("\r", "");
                    resp = resp.replace("\n", "");
                    resp = resp.replace("\t", "");
                    resp = resp.replace(" ", "");

                    for (int i = 0; i < resp.length(); i++) {
                        temp += resp.charAt(i) + "";
                        if (resp.charAt(i) == '{') {
                            nCount++;
                        } else if (resp.charAt(i) == '}') {
                            nCount--;
                            if (nCount < 0) {
                                System.out.println("错误：左右花括号个数不对应");
                                temp = "";
                                nCount = 0;
                            }
                        } else {
                        }

                        if (nCount == 0 && !temp.isEmpty()) {

                            System.out.println("获取了从C++传来的流数据:"+temp);

                            mapRoot = JSON.parseObject(temp);


                            //***************** 处理cmd的json数据 ********************//*
                            commName = String.valueOf(mapRoot.get("cmd"));
                            commData = String.valueOf(mapRoot.get("data"));

                            if(commName == "null" ){
                                commName = String.valueOf(mapRoot.get("msg"));
                            }

                            Object jsobj = JSONObject.parse(commData);
                            if(jsobj instanceof JSONObject){
                                map = JSON.parseObject(commData);
                            }else if(jsobj instanceof JSONArray){

                                jsonArray = JSON.parseArray(commData);

                            }


                            String gx_url = "",jsonString = "";
                            if(commName.equals("csg.app.uk.camera.control")){ //云台控制
                               jsonString  = JSON.toJSONString(map);
                                gx_url = accessKeyAndUrlMap.get("camera");

                            }else if(commName.equals("csg.app.uk.task.manualtask")){

                                jsonString  = JSON.toJSONString(mapRoot);
                                gx_url = accessKeyAndUrlMap.get("manualtask");

                            }else if(commName.equals("csg.app.uk.rtspurl")){ //获取实时视频URL

                                jsonString  = JSON.toJSONString(map);
                                gx_url = accessKeyAndUrlMap.get("rtspurl");

                            }else if(commName.equals("runPatrolPlanInfo")){
                                jsonString  = JSON.toJSONString(map);
                                gx_url = accessKeyAndUrlMap.get("runPatrolPlanInfo");
                            }else if(commName.equals("csg.app.yalian.reqexemetertask")){ //一键顺控下发

                                logger.info("收到6.1 一键顺控下发");

                                jsonString  = JSON.toJSONString(map);
                                gx_url = accessKeyAndUrlMap.get("reqexemetertask");

                            }else if(commName.equals("csg.app.yalian.getmessagetourresults")){ //获取巡视任务结果

                                if(jsonArray != null){

                                    Map<String,Object> tm = new HashMap<>();
                                    tm.put("data",jsonArray);
                                    jsonString  = JSON.toJSONString(tm);
                                    gx_url = accessKeyAndUrlMap.get("getmessagetourresults");
                                }

                                logger.info("收到 6.2 获取巡视任务结果");

                            }
                          //结果内部推送
                            if(gx_url != ""){
                                try {
                                    HttpClientUtils.doPost(gx_url,headParams,jsonString);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (commName.equals("taskEvent")) {   //11 巡检计划任务推送结果 11.1巡检计划任务推送结果，调用网关接口推送数据

                                logger.info("巡检计划任务推送结果——————");
                                Map<String,Object> gMap1 = new HashMap<>();

                                gMap.put("message","patrolResult");

                                gMap1.put("patrolDeviceId",map.get("cameraNo"));
                                gMap1.put("prodPlanId",map.get("prodPlanId"));
                                gMap1.put("objectType",2);
                                gMap1.put("deviceId",map.get("devicePointId"));
                                gMap1.put("deviceName",map.get("devicePointName"));

                                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                gMap1.put("monitorDate",s.format(new Date()));

                                gMap1.put("monitorItem",map.get("msgType"));

                                String ip = readConfig.getIp();
                                int port = readConfig.getPort();
                                String url = "http://"+ip+":"+port+"/";

                                String picUrl = "";

                                if(map.containsKey("picUrl")) picUrl = url+map.get("picUrl");

                                picUrl = StringUtils.replace(picUrl, "#", "%23");
                                picUrl = StringUtils.replace(picUrl, "#", "%23");
                                gMap1.put("linkUrl",picUrl);

                                gMap1.put("monitorResult",map.get("resultDesc"));
                                gMap1.put("alarmLevel","");
                                gMap1.put("isStatus","");
                                gMap1.put("alarmType","");

                                gMap1.put("siteId",1);
                                gMap1.put("siteName","220kv变电站");
                                gMap1.put("realEndDate",s.format(new Date()));
                                gMap1.put("realBeginDate",s.format(new Date()));


                                gMap1.put("isMissDevice",map.get("isMissDevice"));

                                String isOver =  String.valueOf(map.get("isOver"));
                                if(isOver.equals("false")) isOver ="0";
                                else isOver ="1";
                                gMap1.put("isOver",isOver);
                                gMap1.put("id",map.get("id"));
                            //    gMap1.put("createDate",map.get("time"));
                                gMap1.put("createDate",s.format(new Date()));

                                gMap1.put("tourType",map.get("tourType"));
                                gMap1.put("pointID",map.get("pointID"));


                                gMap.put("data",gMap1);
                                gMap.put("substation","FS_01");
                                gMap.put("type","DeviceTourResult");

                                logger.info("6.6 巡检计划任务推送结果"+gMap);

                            }else if(commName.equals("taskEventEx")){  // 6.3 一键巡视任务推送结果

                                gMap = map;
                                logger.info("6.3 一键巡视任务推送结果"+gMap);

                          /*      String flag = "0";
                                String test_url = readConfig.getTest_url();
                                try{
                                    URL url1 = new URL(test_url);
                                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                                    DataInputStream dataInputStream = new DataInputStream(url1.openStream());
                                    FileOutputStream fileOutputStream = new FileOutputStream("/home/uk/uk9101");

                                    byte[] buffer = new  byte[1024];
                                    int length;
                                    while((length = dataInputStream.read(buffer))>0){
                                        output.write(buffer,0,length);
                                    }
                                    fileOutputStream.write(output.toByteArray());
                                    dataInputStream.close();
                                    fileOutputStream.close();
                                    flag = "1";
                                    logger.error("图片下载成功"+flag);
                                }catch (Exception e){
                                    logger.error("图片下载失败"+flag);
                                }

                                logger.info("6.3 一键巡视任务推送结果"+gMap);*/
                            }
                            try {
                                logger.info("开始推送——————");
                                if(!gMap.isEmpty()){

                                    String text = BaseEncoding.base64().encode(JSON.toJSONString(gMap).getBytes(StandardCharsets.UTF_8));
                                    Object object = PushDataDto.buildDataDto("fs"+timeString,text);

                                    WebSocketServer.sendInfo(JSON.toJSONString(object), null);
                                }

                            }catch (Exception e){
                                logger.error("websocket发送数据失败!",e.getMessage());
                            }


                            temp = "";
                            gMap = new HashMap<>();
                        }
                    }

                } catch (Exception e) {
                    logger.error(  "智能网关报文接收失败", e);
                    break;
                }
            }

        });

        messageHandler.start();
        while (!stopFlag) {
            try {
                int availbleByte = inputStream.available();

                if(availbleByte > bytes.length){
                    bytes = Arrays.copyOf(bytes,availbleByte);
                }
                int len = inputStream.read(bytes);
                if (len <= 0) {
                    continue;
                }

                resp = new String(bytes, 0, len, StandardCharsets.UTF_8);

                blockingQueue.put(resp);

            } catch (IOException e) {
                logger.error(" 报文接收失败", e);
                continue;
            } catch (Exception e){
                logger.error("接收程序有错，非io错误", e);
                continue;
            }
        }
        messageHandler.interrupt();

    }


    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }
}
