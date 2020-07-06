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
import com.cn.uk.config.AppContext;
import com.cn.uk.config.ChildReadConfig;
import com.cn.uk.dto.CameraRelatedDto.CommandRtnDataDto;
import com.cn.uk.dto.MessageTours;
import com.cn.uk.dto.RtnData;
import com.cn.uk.dto.Tourresults;
import com.cn.uk.dto.socketCommDto.CameraRtspURLDto;
import com.cn.uk.dto.socketCommDto.Runbycameraid;
import com.cn.uk.dto.socketCommDto.RunbycameraidData;
import com.cn.uk.service.QueryTaskService;
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
 * 〈报文通信HttpCient结果返回〉
 *
 * @author gaoxu
 * @create 2020/4/9 csg.app.yalian.ptzcontrol.post.re11:44
 * @since 1.0.0
 */
@Controller
public class SocketResultController {


    private static Logger logger = LoggerFactory.getLogger(SocketResultController.class);
    private ChildReadConfig readConfig = AppContext.getBean(ChildReadConfig.class);

    @Autowired
    private QueryTaskService taskService;
    private boolean stopFlag;

    public static CommandRtnDataDto commandRtnDataDto1 = null;
    public static Runbycameraid runbycameraid1 = null;
    public static CameraRtspURLDto cameraRtspURLDto1 =null;
    public static RtnData rtnData1 = null;
    public static RtnData rtnData2 = null;
    public static Tourresults tourresults1 = null;

    //1.4.6	云镜控制
    //接口名称：csg.app.uk.camera.control.post.re
    @RequestMapping("/csg.app.yalian.ptzcontrol.post.re")
    @ResponseBody
    public CommandRtnDataDto cameraOpen(@RequestBody Map<String, Object> map1){

        CommandRtnDataDto commandRtnDataDto = new CommandRtnDataDto();

        commandRtnDataDto.setResultCode(Integer.parseInt(map1.get("code").toString()));
        commandRtnDataDto.setLockStatus(map1.get("lockStatus").toString());

        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = m.get("substation").toString();
        commandRtnDataDto.setSubstation(subStation);

        commandRtnDataDto1 = commandRtnDataDto;

        return commandRtnDataDto1;
    }


    //1.3.12	手动启动巡视任务(new)
    //接口名称：csg.app.uk.task.manualtask.post.re

    @RequestMapping("/csg.app.yalian.runbycameraid.post.re")
    @ResponseBody
    public Runbycameraid manualstart(@RequestBody Map<String,Object> mapRoot){

        Map<String, Object> map;

        String commData = "";
        commData = String.valueOf(mapRoot.get("data"));
        map = JSON.parseObject(commData);

        //可能存在转换问题 gaoxu


        //巡视任务下发
        Runbycameraid runbycameraid = new Runbycameraid();
        List<RunbycameraidData> data = new ArrayList<>();

        int cameraNo = 0;

        if(map.containsKey("cameraId")){
            cameraNo = Integer.parseInt(map.get("cameraId").toString());
        }
        String ip = readConfig.getIp();
        int port = readConfig.getPort();
        String url = "http://"+ip+":"+port+"";

        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = m.get("substation").toString();
        runbycameraid.setSubstation(subStation); //厂站
        runbycameraid.setCode(Integer.parseInt(mapRoot.get("result").toString()));

        RunbycameraidData tem = new RunbycameraidData();

        tem.setCameraId(String.valueOf(cameraNo));//相机NO

        //时间改为毫秒字符串
        String time = map.get("time").toString();
        Long timestamp = getTimestamp(time);

        tem.setImageTime(timestamp);//报警时间

        //判断是否有图片
        if(!map.get("picUrl").toString().equals("")){
            url += map.get("picUrl").toString();
            tem.setImageUrl(url);//画面截图
        }else{
            tem.setImageUrl(map.get("picUrl").toString());//画面截图
        }

        tem.setMsgType(map.get("msgType").toString()); //消息类型

        Map<String,Object> sm = new HashMap<>();
        sm.put("msgType",map.get("msgType"));
        Map<String,Object> rMap = taskService.queryName(sm);

        //查出对应中文
        String alarm_name = "";
        if (rMap != null){
            alarm_name = String.valueOf(rMap.get("alarm_name"));
        }
        tem.setMsgTypeCn(alarm_name);//消息类型中文含义

        Map<String,Object> gm = new HashMap<>();
        gm.put("meterValue_cn",map.get("value"));
        gm.put("discernType_cn",alarm_name);
        gm.put("meterValue",map.get("value"));
        gm.put("discernType",0);

        tem.setContent(gm);

        data.add(tem);
        runbycameraid.setData(data);
        runbycameraid1 = runbycameraid;

        return runbycameraid1;
    }



     //1.4.4.1	获取视频URL(实时视频)
    // 接口名称：csg.app.uk.camera.open.post.re
    //请求方式：post
    @RequestMapping("/csg.app.yalian.rtspurl.post.re")
    @ResponseBody
    public CameraRtspURLDto activepush(@RequestBody Map<String,Object> map1) throws Exception{

        CameraRtspURLDto cameraRtspURLDto = new CameraRtspURLDto();

        cameraRtspURLDto.setRtspURL(map1.get("rtspURL").toString());
        cameraRtspURLDto.setResultCode(Integer.parseInt(map1.get("result").toString()));

        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = m.get("substation").toString();
        cameraRtspURLDto.setSubstation(subStation);

        cameraRtspURLDto1 = cameraRtspURLDto;
        return cameraRtspURLDto1;
    }


    /**************** 巡检计划任务  ***********************/
    @RequestMapping("/csg.app.yalian.runpatrolplaninfo.post.re")
    @ResponseBody
    public RtnData runPatrolPlanInfo(@RequestBody Map<String,Object> map) throws Exception{

        RtnData rtnD = new RtnData();

        rtnD.setMessage(String.valueOf(map.get("message")));

        boolean suc = false;
        String success = map.get("success").toString();

        if (success.equals("true")) suc = true;
        rtnD.setSuccess(suc);

        String code = String.valueOf(map.get("code"));
        int Rcode = Integer.parseInt(code);
        rtnD.setCode(Rcode);

        if (Rcode == 0){

            rtnD.setData("执行成功");
        }else{
            rtnD.setData("执行失败");
        }
        rtnData1 = rtnD;

        return rtnData1;
    }



    //7  12 获取巡检计划任务结果
    //url: {ip}:{port}/smartsecurity/patroPlan/getMessagePatrolResults
    @RequestMapping("/csg.app.yalian.getmessagepatrolresults.post.re")
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
                if(ddMap.containsKey("pic_url") && !ddMap.get("pic_url").equals("")) linkUrl = url+ ddMap.get("pic_url");

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
     * 接口名称method：csg.app.yalian.reqexemetertask.post.re
     * @return
     * @throws Exception
     */
    @RequestMapping("/csg.app.yalian.reqexemetertask.post.re")
    @ResponseBody
    public RtnData reqexemetertask(@RequestBody Map<String,Object> map){

        RtnData req = new RtnData();

        Map<String,Object> mm = new HashMap<>();
        JSONObject paramsObj = new JSONObject(map);
        JSONObject obj = paramsObj.getJSONObject("data");

        mm.put("tourKey",obj.get("tourKey").toString());
        req.setData(obj);
        req.setSuccess(Boolean.valueOf(map.get("success").toString()));
        req.setCode(Integer.parseInt(map.get("code").toString()));
        req.setMessage(String.valueOf(map.get("message")));

        //获取厂站名称
        Map<String,Object> m1 = taskService.getSubstation();
        String subStation = m1.get("substation").toString();
        req.setSubstation(subStation);


        rtnData2 = req;

        logger.info("返回给主post的数据为："+JSONObject.toJSONString(rtnData2));

        return rtnData2;
    }

    /**
     * 获取内部返回
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/csg.app.yalian.getmessagetourresults.post.re")
    @ResponseBody
    public Tourresults getmessagetourresults(@RequestBody Map<String,Object> map){

        Tourresults tourresults = new Tourresults();

        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("data");

        List<MessageTours> setList = new ArrayList<>();
         System.out.println(list.toString());

         if(list != null && list.size()>0){
             for(Map<String,Object> messageTours:list){

                 MessageTours tours = new MessageTours();


                 tours.setAreaId(String.valueOf(messageTours.get("areaId")));
                 tours.setCameraId(String.valueOf(messageTours.get("cameraId")));
                 tours.setContent(String.valueOf(messageTours.get("content")));
                 tours.setCreateDate(String.valueOf(messageTours.get("createDate")));
                 tours.setImageTime(String.valueOf(messageTours.get("imageTime")));
                 tours.setImageUrl(String.valueOf(messageTours.get("imageUrl")));
                 tours.setMsgId(String.valueOf(messageTours.get("msgId")));
                 tours.setMsgType(String.valueOf(messageTours.get("msgType")));
                 tours.setMsgTypeCn(String.valueOf(messageTours.get("msgTypeCn")));
                 tours.setTaskId(String.valueOf(messageTours.get("taskId")));

                 setList.add(tours);

             }
         }
        tourresults.setData(setList);
        tourresults.setCode(0);
        tourresults.setSuccess(true);
        tourresults.setMessage("SUCCESS");

        //获取厂站名称
        Map<String,Object> m1 = taskService.getSubstation();
        String subStation = m1.get("substation").toString();
        tourresults.setSubstation(subStation);

        tourresults1 = tourresults;
        return tourresults1;
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
