/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: SocketController
 * 创建者:   高旭
 * 生成日期:     2020/4/9 11:44
 * 描述: 报文通信接口
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.common.utils.HttpClientUtils;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.ChildReadConfig;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.dto.CameraRelatedDto.*;
import com.cn.uk.dto.RtnData;
import com.cn.uk.dto.Tourresults;
import com.cn.uk.dto.socketCommDto.CameraRtspURLDto;
import com.cn.uk.dto.socketCommDto.Runbycameraid;
import com.cn.uk.service.QueryTaskService;
import com.cn.uk.util.ConnectLocalStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 〈功能简述〉<br> 
 * 〈报文通信接口〉
 *
 * @author gaoxu
 * @create 2020/4/9 csg.app.yalian.ptzcontrol.post11:44
 * @since 1.0.0
 */
@Controller
public class SocketController {


    private static Logger logger = LoggerFactory.getLogger(SocketController.class);
    private ChildReadConfig readConfig = AppContext.getBean(ChildReadConfig.class);

    @Autowired
    private QueryTaskService taskService;
    private boolean stopFlag;

    //1.4.6	云镜控制
    //接口名称：csg.app.uk.camera.control.post
    @RequestMapping("/csg.app.yalian.ptzcontrol.post")
    @ResponseBody
    public CommandRtnDataDto cameraOpen(@RequestBody Map<String, Object> map1) throws Exception{


        CommandRtnDataDto commandRtnDataDto = new CommandRtnDataDto();
        if(map1.containsKey("cameraCode")){

            int cameraCode = Integer.parseInt(map1.get("cameraCode").toString());
            int controlCode =  Integer.parseInt(map1.get("controlCode").toString());
            String controlPara1 ="",controlPara2="";
            if(map1.containsKey("controlPara1")){

                controlPara1 = map1.get("controlPara1").toString();
            }
            if(map1.containsKey("controlPara2")){

                controlPara2 = map1.get("controlPara2").toString();
            }

            //向服务器端发送数据
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> map2 = new HashMap<String, Object>();
            map.put("cmd","csg.app.uk.camera.control");

            //这里的控制码和内部规定码有出入，要改动处理(+1处理)
            if(controlCode <= 11){
                controlCode = controlCode + 1;
            }else{
                switch (controlCode){
                    case 12: controlCode = 14;break;
                    case 13: controlCode = 15;break;
                    case 14: controlCode = 16;break;
                    case 15: controlCode = 17;break;
                    case 16: controlCode = 20;break;
                    case 17: controlCode = 21;break;
                    case 18: controlCode = 22;break;
                    case 19: controlCode = 25;break;
                    case 20: controlCode = 1;break;
                    case 21: controlCode = 26;break;
                    case 22: controlCode = 27;break;
                    case 23: controlCode = 28;break;
                    case 24: controlCode = 29;break;
                    case 25: controlCode = 30;break;
                    case 26: controlCode = 31;break;
                    case 27: controlCode = 18;break;
                    case 28: controlCode = 19;break;
                    case 29: controlCode = 32;break;
                    case 30: controlCode = 33;break;
                    case 31: controlCode = 34;break;
                    case 32: controlCode = 0;break;
                    case 33: controlCode = 0;break;
                    case 34: controlCode = 0;break;
                }
            }
            map2.put("cameraNo",cameraCode+"");
            map2.put("controlCode",controlCode);
            map2.put("param1",controlPara1);
            map2.put("param2",controlPara2);
            map.put("data",map2);

            JSONObject jsonObject = new JSONObject(map);
            ConnectLocalStream.commonStream(jsonObject);

            Thread.currentThread().sleep(1000);
            commandRtnDataDto = SocketResultController.commandRtnDataDto1;

            while (commandRtnDataDto == null){
                Thread.currentThread().sleep(1000);
                commandRtnDataDto = SocketResultController.commandRtnDataDto1;
            }

        }
        SocketResultController.commandRtnDataDto1 = null;
        return commandRtnDataDto;
    }


    //1.3.12	手动启动巡视任务(new)
    //接口名称：csg.app.uk.task.manualtask.post

    @RequestMapping("/csg.app.yalian.runbycameraid.post")
    @ResponseBody
    public Runbycameraid manualstart(@RequestBody Map<String,Object> map1) throws Exception{

        Runbycameraid runbycameraid = new Runbycameraid();
        if(map1.containsKey("cameraId")){
            int cameraNo = 0,presetNo = 1;

            if(map1.containsKey("cameraId")){
                cameraNo = Integer.parseInt(map1.get("cameraId").toString());
            }
            if(map1.containsKey("presettingCode")){
                presetNo = Integer.parseInt(map1.get("presettingCode").toString());
            }

            //向服务器端发送数据
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> map2 = new HashMap<String, Object>();
            map.put("cmd","csg.app.uk.task.manualtask");
            //---------可变map
            map2.put("cameraNo",cameraNo);
            map2.put("presetNo",presetNo);
            //--------end
            map.put("data",map2);

            JSONObject json = new JSONObject(map);
            ConnectLocalStream.commonStream(json);

            Thread.currentThread().sleep(1000);
            runbycameraid = SocketResultController.runbycameraid1;

            while (runbycameraid == null){
                Thread.currentThread().sleep(1000);
                runbycameraid = SocketResultController.runbycameraid1;
            }
        }
        SocketResultController.runbycameraid1 = null;
        return runbycameraid;
    }



     //1.4.4.1	获取视频URL(实时视频)
    // 接口名称：csg.app.uk.camera.open.post
    //请求方式：post
    @RequestMapping("/csg.app.yalian.rtspurl.post")
    @ResponseBody
    public CameraRtspURLDto activepush(@RequestBody Map<String,Object> map1) throws Exception{


        String cameraCode="",startTime="",endTime="";
        int serviceType = 0,packProtocolType=0,streamType=0,protocolType=0,transMode=0,broadCastType=0,clientType=0;
        Map<String,Object>  mm = new HashMap<>();
        Map<String,Object>   tt  = new HashMap<>();

        if(map1.containsKey("cameraCode")) cameraCode = map1.get("cameraCode").toString();

        if(map1.containsKey("mediaURLParam")){

            Map<String,Object>  mediaURLParam = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map1.get("mediaURLParam")));


            if(mediaURLParam.containsKey("serviceType")) serviceType = Integer.parseInt(mediaURLParam.get("serviceType").toString());
            if(mediaURLParam.containsKey("packProtocolType")) packProtocolType = Integer.parseInt(mediaURLParam.get("packProtocolType").toString());
            if(mediaURLParam.containsKey("streamType")) streamType = Integer.parseInt(mediaURLParam.get("streamType").toString());
            if(mediaURLParam.containsKey("protocolType")) protocolType = Integer.parseInt(mediaURLParam.get("protocolType").toString());
            if(mediaURLParam.containsKey("transMode")) transMode = Integer.parseInt(mediaURLParam.get("transMode").toString());
            if(mediaURLParam.containsKey("broadCastType")) broadCastType = Integer.parseInt(mediaURLParam.get("broadCastType").toString());
            if(mediaURLParam.containsKey("clientType")) clientType = Integer.parseInt(mediaURLParam.get("clientType").toString());

            mm.put("serviceType",serviceType);
            mm.put("packProtocolType",packProtocolType);
            mm.put("streamType",streamType);
            mm.put("protocolType",protocolType);
            mm.put("transMode",transMode);
            mm.put("broadCastType",broadCastType);
            mm.put("clientType",clientType);

        }

        if (map1.containsKey("timeSpan")){

            Map<String,Object>  mediaURLParam = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map1.get("timeSpan")));

            if(mediaURLParam.containsKey("startTime")){
                startTime = mediaURLParam.get("startTime").toString();
                tt.put("startTime",startTime);
            }
            if(mediaURLParam.containsKey("endTime")){
                endTime = mediaURLParam.get("endTime").toString();
                tt.put("endTime",endTime);
            }
        }


        String ip = readConfig.getIp();
        int port = readConfig.getPort();
        String url = "http://"+ip+":"+port+"";

        //向服务器端发送数据
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("cmd","csg.app.uk.rtspurl");
        //---------可变map
        map2.put("cameraCode",cameraCode);
        map2.put("mediaURLParam",mm);
        map2.put("timeSpan",tt);

        //--------end
        map.put("data",map2);

        JSONObject json = new JSONObject(map);
        ConnectLocalStream.commonStream(json);

        CameraRtspURLDto cameraRtspURLDto = new CameraRtspURLDto();

        Thread.currentThread().sleep(1000);
        cameraRtspURLDto = SocketResultController.cameraRtspURLDto1;

        while (cameraRtspURLDto == null){
            Thread.currentThread().sleep(1000);
            cameraRtspURLDto = SocketResultController.cameraRtspURLDto1;
        }
        SocketResultController.cameraRtspURLDto1 = null;
        return cameraRtspURLDto;
    }


    /**************** 巡检计划任务  ***********************/
    @RequestMapping("/csg.app.yalian.runpatrolplaninfo.post")
    @ResponseBody
    public RtnData runPatrolPlanInfo(@RequestBody Map<String,Object> map1) throws Exception{


        //向服务器端发送数据
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("cmd","runPatrolPlanInfo");
        //---------可变map

        if(!map1.containsKey("planEndDate")) map1.put("planEndDate","");
        if(!map1.containsKey("planBeginDate")) map1.put("planBeginDate","");
        if(!map1.containsKey("workContent")) map1.put("workContent","");
        if(!map1.containsKey("planCode")) map1.put("planCode","");
        if(!map1.containsKey("deviceList")) map1.put("deviceList","");
        if(!map1.containsKey("siteId")) map1.put("siteId","");
        if(!map1.containsKey("siteName")) map1.put("siteName","");
        if(!map1.containsKey("prodPlanId")) map1.put("prodPlanId","");

        map2.put("planEndDate",map1.get("planEndDate"));
        map2.put("planBeginDate",map1.get("planBeginDate"));
        map2.put("workContent",map1.get("workContent"));
        map2.put("planCode",map1.get("planCode"));
        map2.put("deviceList",map1.get("deviceList"));
        map2.put("siteId",map1.get("siteId"));
        map2.put("siteName",map1.get("siteName"));
        map2.put("prodPlanId",map1.get("prodPlanId"));
        //--------end
        map.put("data",map2);

        JSONObject json = new JSONObject(map);
        ConnectLocalStream.commonStream(json);

        RtnData rtnD = new RtnData();

        Thread.currentThread().sleep(1000);
        rtnD = SocketResultController.rtnData1;

        while (rtnD == null){
            Thread.currentThread().sleep(1000);
            rtnD = SocketResultController.rtnData1;
        }
        SocketResultController.rtnData1 = null;

        return rtnD;
    }



    //7  12 获取巡检计划任务结果
    //url: {ip}:{port}/smartsecurity/patroPlan/getMessagePatrolResults
    @RequestMapping("/csg.app.yalian.getmessagepatrolresults.post")
    @ResponseBody
    public RtnData getMessagePatrolResults(@RequestBody Map<String,Object> map1){


        String ip = readConfig.getIp();
        int port = readConfig.getPort();
        String url = "http://"+ip+":"+port+"";

        RtnData rtnData = new RtnData();
        List<Object> tlist = new ArrayList<>();

        Map<String, Object> substation = taskService.getSubstation();
        String sub_no = String.valueOf(substation.get("sub_no"));
        String sub_name = String.valueOf(substation.get("substation"));

        String prodPlanId ="";
        if(map1.containsKey("prodPlanId")) prodPlanId= String.valueOf(map1.get("prodPlanId"));

        List<Map<String,Object>> plist  = taskService.queryPlanItemByNo(map1);  //查询plan_item

        List<Map<String,Object>> slist = new ArrayList<>();

        if(plist != null && plist.size()>0){

            for(int j =0 ; j< plist.size() ;j++){

                Map<String,Object> zMap = new HashMap<>();//装载data

                Map<String, Object> ppMap = plist.get(j);

                Map<String,Object> cMap = taskService.getCameraAndProjectItem(ppMap);  //获取camera设备的一些信息

                /***********设备一些信息***************/
                String patrolDeviceId="",deviceId="",deviceName="";
                if(cMap != null && cMap.size()>0){

                     patrolDeviceId = String.valueOf(cMap.get("camera_no"));
                     deviceId =  String.valueOf(cMap.get("deviceID"));
                     deviceName =  String.valueOf(cMap.get("name"));
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String monitorDate = format.format(date);

                /********巡检计划数据**********/
                String item_no = String.valueOf(ppMap.get("item_no"));
                String monitorItem = String.valueOf(ppMap.get("item_name"));
                String monitorResult = String.valueOf(ppMap.get("result_desc"));

                String alarmLevel ="";
                String isStatus ="";
                String alarmType = String.valueOf(ppMap.get("alarm_no"));
                String siteId = sub_no +"";
                String siteName =sub_name;

                String realEndDate= "",realBeginDate="";
                Map<String,Object> tMap = new HashMap<>();
                tMap.put("item_no",item_no);
                tMap.put("his_plan_id",String.valueOf(ppMap.get("his_plan_id")));
                Map<String,Object> ttMax = taskService.getMaxTime(tMap);
                Map<String,Object> ttMin = taskService.getMinTime(tMap);

                if(ttMax != null ) realEndDate =String.valueOf(ttMax.get("maxTime"));
                if(ttMin != null ) realBeginDate =String.valueOf(ttMin.get("minTime"));

                String isMissDevice = 1+"";
                String isOver = "";

                //判断是否结束，根据当前时间与最大值时间比较

                String run_time = String.valueOf(ppMap.get("run_time"));
                int compareTo = realEndDate.compareTo(run_time);
                if(compareTo > 0) {
                    isOver = "0"; //未结束
                }else {
                    isOver = "1"; //未结束
                }


                zMap.put("prodPlanId",prodPlanId);
                zMap.put("patrolDeviceId",patrolDeviceId);
                zMap.put("objectType",2); //默认为2
                zMap.put("deviceId",deviceId);
                zMap.put("deviceName",deviceName);
                zMap.put("monitorDate",monitorDate);
                zMap.put("monitorItem",monitorItem);
                zMap.put("monitorResult",monitorResult);
                zMap.put("alarmContent","");
                zMap.put("alarmLevel",alarmLevel);

                String linkUrl = "";
                if(ppMap.containsKey("pic_url") && !ppMap.get("pic_url").equals("")) linkUrl = url+ ppMap.get("pic_url");

                zMap.put("linkUrl",linkUrl);
                zMap.put("isStatus",isStatus);
                zMap.put("alarmType",alarmType);
                zMap.put("siteId",siteId);
                zMap.put("siteName",siteName);
                zMap.put("realEndDate",realEndDate);
                zMap.put("realBeginDate",realBeginDate);
                zMap.put("isMissDevice",isMissDevice);
                zMap.put("isOver",isOver);


                slist.add(zMap);
            }
            tlist.add(slist);
        }

        List<Map<String,Object>> dlist = taskService.queryPlanDefeatByNo(map1); //查询defeat表
        List<Map<String,Object>> zlist = new ArrayList<>();
        if(dlist != null && dlist.size()>0){


            for(int j =0 ; j< dlist.size() ;j++){

                Map<String,Object> zMap = new HashMap<>();//装载data

                Map<String, Object> ddMap = dlist.get(j);

                Map<String,Object> cMap = taskService.getCameraAndProjectItem(ddMap);  //获取camera设备的一些信息

                /***********设备一些信息***************/
                String patrolDeviceId ="",deviceId="",deviceName="";
                if(cMap != null && cMap.size()>0){

                     patrolDeviceId = String.valueOf(cMap.get("camera_no"));
                     deviceId =  String.valueOf(cMap.get("deviceID"));
                     deviceName =  String.valueOf(cMap.get("name"));
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String monitorDate = format.format(date);

                /************缺陷数据*******************/

                String item_no = String.valueOf(ddMap.get("item_no"));
                String monitorItem = String.valueOf(ddMap.get("plan_name"));
                String monitorResult = String.valueOf(ddMap.get("result_desc"));

                String alarmContent = "";
                if(ddMap.get("alarm_no").equals("0")) alarmContent = "无告警";
                else alarmContent = "已确认告警";
                String alarmLevel ="";

                String linkUrl = "";
                if(ddMap.containsKey("pic_url") && !ddMap.get("pic_url").equals(""))

                linkUrl = url+ ddMap.get("pic_url");

                linkUrl = StringUtils.replace(linkUrl, "#", "%23");
                linkUrl = StringUtils.replace(linkUrl, "#", "%23");

                String isStatus ="";
                String alarmType = String.valueOf(ddMap.get("alarm_no"));
                String siteId = sub_no +"";
                String siteName =sub_name;

                String realEndDate= "",realBeginDate="";
                Map<String,Object> tMap = new HashMap<>();
                tMap.put("item_no",item_no);
                tMap.put("his_plan_id",prodPlanId);
                Map<String,Object> ttMax = taskService.getMaxTime(tMap);
                Map<String,Object> ttMin = taskService.getMinTime(tMap);

                if(ttMax != null ) realEndDate =String.valueOf(ttMax.get("maxTime"));
                if(ttMin != null ) realBeginDate =String.valueOf(ttMin.get("minTime"));

                String isMissDevice = 1+"";
                String isOver = "";

                //判断是否结束，根据当前时间与最大值时间比较

                String run_time = String.valueOf(ddMap.get("run_time"));
                int compareTo = realEndDate.compareTo(run_time);
                if(compareTo > 0) {
                    isOver = "0"; //未结束
                }else {
                    isOver = "1"; //未结束
                }
                zMap.put("prodPlanId",prodPlanId);
                zMap.put("patrolDeviceId",patrolDeviceId);
                zMap.put("objectType",2); //默认为2
                zMap.put("deviceId",deviceId);
                zMap.put("deviceName",deviceName);
                zMap.put("monitorDate",monitorDate);
                zMap.put("monitorItem",monitorItem);
                zMap.put("monitorResult",monitorResult);
                zMap.put("alarmContent",alarmContent);
                zMap.put("alarmLevel",alarmLevel);
                zMap.put("linkUrl",linkUrl);
                zMap.put("isStatus",isStatus);
                zMap.put("alarmType",alarmType);
                zMap.put("siteId",siteId);
                zMap.put("siteName",siteName);
                zMap.put("realEndDate",realEndDate);
                zMap.put("realBeginDate",realBeginDate);
                zMap.put("isMissDevice",isMissDevice);
                zMap.put("isOver",isOver);

                zlist.add(zMap);
            }
            tlist.add(zlist);
        }

        if(plist.size()<=0 && dlist.size()<=0){
            rtnData.setCode(1);
            rtnData.setSuccess(false);
            rtnData.setMessage("false");
        }else{
            rtnData.setMessage("true");
            rtnData.setSuccess(true);
            rtnData.setData(tlist);
        }

        return rtnData;
    }



    /**
     * 6.1 一键巡视任务下发
     * 接口名称method：csg.app.yalian.reqexemetertask.post
     * @return
     * @throws Exception
     */
    @RequestMapping("/csg.app.yalian.reqexemetertask.post")
    @ResponseBody
    public RtnData reqexemetertask(@RequestBody Map<String,Object> map) throws Exception{


        String tourType ="";
        RtnData rtnData1 ;
        if(map.containsKey("tourType")) tourType= String.valueOf(map.get("tourType"));

        logger.info("tourType:"+tourType);
        //向服务器端发送数据
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("cmd","csg.app.yalian.reqexemetertask");
        //---------可变map
        map2.put("tourType",tourType);
        //--------end
        map1.put("data",map2);

        JSONObject json = new JSONObject(map1);

        boolean b = ConnectLocalStream.commonStream(json);

        logger.info("发送给C++数据是否成功:"+b);

        Thread.currentThread().sleep(5000);

        rtnData1 = SocketResultController.rtnData2;

        while (rtnData1 == null){
            Thread.currentThread().sleep(3000);
            rtnData1 = SocketResultController.rtnData2;
        }
        SocketResultController.rtnData2 = null;


        logger.info("返回给主站数据为："+JSONObject.toJSONString(rtnData1));
        return rtnData1;
    }


    /**
     * 6.2 获取巡视任务结果
     * csg.app.yalian.getmessagetourresults.post
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/csg.app.yalian.getmessagetourresults.post")
    @ResponseBody
    public Tourresults getmessagetourresults(@RequestBody Map<String,Object> map) throws Exception{

        String tourType ="";
        if(map.containsKey("tourType")) tourType= String.valueOf(map.get("tourType"));

        //向服务器端发送数据
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("cmd","csg.app.yalian.getmessagetourresults");
        //---------可变map
        map2.put("tourType",tourType);
        //--------end
        map1.put("data",map2);

        JSONObject json = new JSONObject(map1);

        ConnectLocalStream.commonStream(json);

        Tourresults tourresults;

        Thread.currentThread().sleep(5000);
        tourresults = SocketResultController.tourresults1;

        while (tourresults == null){
            Thread.currentThread().sleep(3000);
            tourresults = SocketResultController.tourresults1;
        }
        SocketResultController.tourresults1 = null;
        return tourresults;
    }


    /**
     * 门禁厂家上送数据
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/doorrecord")
    @ResponseBody
    public RtnData doorrecord(@RequestBody Map<String,Object> map){


        RtnData rtnData = new RtnData();
        Map<String,Object> postMap = new HashMap<>();

        //获取厂站名称
        Map<String,Object> m1 = taskService.getSubstation();
        String subStation = m1.get("substation").toString();

        postMap.put("substation",subStation);
        postMap.put("type",1);
        postMap.put("passRecord",map);

        try {
            logger.info("开始推送门禁厂家上送数据——————");

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            Object object = PushDataDto.buildDataDto("fs"+timeString,JSON.toJSONString(postMap));
            WebSocketServer.sendInfo(JSON.toJSONString(object), null);

        //    WebSocketServer.sendInfo(JSON.toJSONString(postMap), null);
            rtnData.setCode(0);
            rtnData.setMessage("发送成功");
        }catch (Exception e){
            logger.error("websocket发送数据失败!",e.getMessage());
            rtnData.setCode(1);
            rtnData.setMessage(e.getMessage());
        }

        return rtnData;
    }


    /**
     * 接收车辆出场记录
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/carout")
    @ResponseBody
    public RtnData carout(@RequestBody Map<String,Object> map){


        RtnData rtnData = new RtnData();
        Map<String,Object> postMap = new HashMap<>();

        //获取厂站名称
        Map<String,Object> m1 = taskService.getSubstation();
        String subStation = m1.get("substation").toString();

        postMap.put("substation",subStation);
        postMap.put("type",32);
        postMap.put("passRecord",map);

        try {
            logger.info("开始推送门禁厂家上送数据——————");

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            Object object = PushDataDto.buildDataDto("fs"+timeString,JSON.toJSONString(postMap));
            WebSocketServer.sendInfo(JSON.toJSONString(object), null);


        //    WebSocketServer.sendInfo(JSON.toJSONString(postMap), null);
            rtnData.setCode(0);
            rtnData.setMessage("发送成功");
        }catch (Exception e){
            logger.error("websocket发送数据失败!",e.getMessage());
            rtnData.setCode(1);
            rtnData.setMessage(e.getMessage());
        }

        return rtnData;
    }


    /**
     * 接收车辆出场记录
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/carin")
    @ResponseBody
    public RtnData carin(@RequestBody Map<String,Object> map){


        RtnData rtnData = new RtnData();
        Map<String,Object> postMap = new HashMap<>();

        //获取厂站名称
        Map<String,Object> m1 = taskService.getSubstation();
        String subStation = m1.get("substation").toString();

        postMap.put("substation",subStation);
        postMap.put("type",3);
        postMap.put("passRecord",map);

        try {


            logger.info("开始推送门禁厂家上送数据——————");
        //    String text = BaseEncoding.base64().encode(JSON.toJSONString(pushPayLoadDto).getBytes(StandardCharsets.UTF_8));

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            Object object = PushDataDto.buildDataDto("fs"+timeString,JSON.toJSONString(postMap));
            WebSocketServer.sendInfo(JSON.toJSONString(object), null);

            rtnData.setCode(0);
            rtnData.setMessage("发送成功");
        }catch (Exception e){
            logger.error("websocket发送数据失败!",e.getMessage());
            rtnData.setCode(1);
            rtnData.setMessage(e.getMessage());
        }

        return rtnData;
    }




    /**
     * @desc 字符串转时间戳
     * @example time="2019-04-19 00:00:00"
     **/
    public Long getTimestamp(String time) {
        Long timestamp = null;
        try {
            timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

}
