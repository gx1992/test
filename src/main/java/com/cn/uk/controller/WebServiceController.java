/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: CxfClient
 * 创建者:   高旭
 * 生成日期:     2020/5/19 15:17
 * 描述: 基于CXF的客户端
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.config.LnWebServiceConfig;
import com.cn.uk.config.WebServiceConfig;
import com.cn.uk.dto.RtnData;
import com.cn.uk.service.QueryTaskService;
import com.cn.uk.webService.Pojo.*;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈基于CXF的客户端〉
 *
 * @author gaoxu
 * @create 2020/5/19 15:17
 * @since 1.0.0
 */
@Controller
public class WebServiceController {

    private final Logger logger = LoggerFactory.getLogger(WebServiceController.class);
    @Autowired
    private QueryTaskService taskService;

    @Autowired
    private WebServiceConfig webServiceConfig;

    @Autowired
    private LnWebServiceConfig lnWebServiceConfig;

    /**
     *    5.5.2	召唤巡检值
     *    功能说明：集控平台向指定本地监控系统主动召唤巡检数据。
     *    函数名：get_patrol_data
     * @param map
     * @return
     */
    @RequestMapping("/robot/getPatrolData")
    @ResponseBody
    public RtnData getPatrolData(@RequestBody Map<String,String> map){

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();

        String substation = map.get("substation");
        String start_time = map.get("start_time");
        String end_time = map.get("end_time");

        try {
            Client client = webserviceReuslt();

            Object [] result =  client.invoke("get_patrol_data", start_time,end_time,substation);
            Map<String,Object> mapp = JSON.parseObject(String.valueOf(result[0]));

            System.out.println(mapp.toString());

            String  jpatrol_para_his_list = String.valueOf(mapp.get("jpatrol_para_his_list"));
            JSONArray jsonArray = JSONArray.parseArray(jpatrol_para_his_list);
            List<Jpatrol_para_his> jpatrolParaHis = jsonArray.toJavaList(Jpatrol_para_his.class);
            mapRes.put("jpatrol_para_his_list",jpatrolParaHis);

            String   jrt_device_rl_list = String.valueOf(mapp.get("jrt_device_rl_list"));
            JSONArray jsonArray2 = JSONArray.parseArray(jrt_device_rl_list);
            List<Jrt_Device_rl> jrtDeviceRls = jsonArray2.toJavaList(Jrt_Device_rl.class);
            mapRes.put("jrt_device_rl_list",jrtDeviceRls);

            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();
            rtnData.setSubstation(subStation);

        }catch (Exception e){

            mapRes.put("jpatrol_para_his_list",null);
            mapRes.put("jrt_device_rl_list",null);

            rtnData.setData(mapRes);
            rtnData.setCode(1);
            rtnData.setMsg("失败");
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }

        return rtnData;

    }

    /**
     *  5.5.4	召唤设备告警
     *  功能说明：集控平台主动查询指定时间段内的本地监控系统设备告警信息（包括已确认设备告警）。
     *  函数名：get_fault_value
     * @param map
     * @return
     */
    @RequestMapping("/robot/getFaultValue")
    @ResponseBody
    public RtnData getFaultValue(@RequestBody  Map<String,String> map){


        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();

        String substation = map.get("substation");
        String start_time = map.get("start_time");
        String end_time = map.get("end_time");

        try {

            Client client = webserviceReuslt();
            Object [] result =  client.invoke("get_fault_value", start_time,end_time,substation); //首个参数为方法名，后面参数为传入的参数

            String result2 = String.valueOf(result[0]).replace("\\","");
            JSONArray jsonArray = JSONArray.parseArray(result2);

            List<Jalarm_data_rl> jalarmDataRls = jsonArray.toJavaList(Jalarm_data_rl.class);
            mapRes.put("jalarm_data_rl_list",jalarmDataRls);

            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();
            rtnData.setSubstation(subStation);
        } catch (Exception e) {
            mapRes.put("jalarm_data_rl_list",null);

            rtnData.setData(mapRes);
            rtnData.setCode(1);
            rtnData.setMsg("失败");
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }
        return rtnData;
    }


    /**
     * 5.5.5	下发设备告警确认信息
     * 功能说明：集控平台主动下发设备告警确认信息至本地监控系统；本地监控系统设备告警确认信息主动发送至集控平台。
     * 函数名：send_alarm_confirm
     * @param map
     * @return
     */
    @RequestMapping("/robot/sendAlarmConfirm")
    @ResponseBody
    public RtnData sendAlarmConfirm(@RequestBody @RequestParam(required = false) Map<String,Object> map){

        System.out.println(map.toString());

        Jdevice_w jdeviceW = new Jdevice_w();
        jdeviceW.setPatrol_point_id("1");
        jdeviceW.setAlarm_level("1");
        jdeviceW.setAlarm_type("1");
        jdeviceW.setRecognition_time("");
        jdeviceW.setRecognition_type("1");
        jdeviceW.setCoordinate("1");
        jdeviceW.setTask_run_his_id("1");
        jdeviceW.setRecogn_state("1");
        jdeviceW.setReview_value("1");
        jdeviceW.setReview_time("");

        List<Jdevice_w> list = new ArrayList<>();
        list.add(jdeviceW);

        String jdevice_w_lis = JSONObject.toJSONString(list);
        RtnData rtnData = new RtnData();
        try {
            Client client = webserviceReuslt();
            Object [] resultObj =  client.invoke("send_alarm_confirm", "ZS_01",jdevice_w_lis); //首个参数为方法名，后面参数为传入的参数

            String  res_conf = String.valueOf(resultObj[0]);

            String result ="";
            if(res_conf.equals("1")){
                result ="成功";
                rtnData.setSuccess(true);
                rtnData.setCode(0);
            }else{
                result ="失败";
                rtnData.setSuccess(false);
                rtnData.setCode(1);
            }
            rtnData.setMsg(result);
            rtnData.setMessage(result);

        }catch (Exception e){
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }

        return rtnData;
    }


    /**
     * 5.5.7召唤巡检文件
     */
    @RequestMapping("/robot/getPatrolFiles")
    @ResponseBody
    public RtnData getPatrolFiles(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();

        String substation= String.valueOf(map.get("substation"));
        String start_time= String.valueOf(map.get("start_time"));
        String end_time= String.valueOf(map.get("end_time"));
        int jfolder_type= Integer.parseInt(String.valueOf(map.get("jfolder_type")));
        String file_type= String.valueOf(map.get("file_type"));

        try {
            Client client = webserviceReuslt();
            Object [] resultObj =  client.invoke("get_patrol_files", substation,start_time,end_time,jfolder_type,file_type); //首个参数为方法名，后面参数为传入的参数

            rtnData.setSuccess(Boolean.parseBoolean(String.valueOf(resultObj[0])));

        }catch (Exception e){

            logger.error("获取webservice接口获取失败!",e.getMessage());

        }

        return rtnData;

    }



    /**
     * 5.5.10	召唤站内微气象数据(服务端暂时不支持)
     * 功能说明：集控平台主动查询指定时间段内的站内微气象数据。
     * 函数名：get_micro_climate
     */
    @RequestMapping("/robot/getMicroClimate")
    @ResponseBody
    public Map<String,Object> getMicroClimate(@RequestBody Map<String,String> map){

        RtnData rtnData = new RtnData();
        Map<String,Object> mapResult = new HashMap<>();
        Map<String,Object> mapRes = new HashMap<>();

        String substation = map.get("siteId");
        String start_time = map.get("start_time");
        String end_time = map.get("end_time");

        try{
            Client client = webserviceReuslt();
            Object [] resultObj =  client.invoke("get_micro_climate", substation,start_time,end_time); //首个参数为方法名，后面参数为传入的参数

            Map<String,Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));
            String jrt_station_rl_list = String.valueOf(mapp.get("jrt_station_rl_list"));
            JSONArray jsonArray = JSONArray.parseArray(jrt_station_rl_list);

            List<Jrt_station_rl> jrtStationRls = jsonArray.toJavaList(Jrt_station_rl.class);
            mapRes.put("jrt_station_rl_list",jrtStationRls);

            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();
            rtnData.setSubstation(subStation);

            mapResult.put("substation",substation);
            mapResult.put("code",0);
            mapResult.put("msg","查询微气象数据成功");

            List<Map<String,Object>> list = new ArrayList<>();
            if(jrtStationRls != null && jrtStationRls.size()>0){

                for(int i=0;i<jrtStationRls.size();i++){

                    Map<String,Object> siteScene = new HashMap<>();

                    siteScene.put("measureTime",jrtStationRls.get(i).getMeasure_time());
                    siteScene.put("type",jrtStationRls.get(i).getType());
                    siteScene.put("value",jrtStationRls.get(i).getValue());
                    siteScene.put("valueShow",jrtStationRls.get(i).getValue_show());
                    siteScene.put("siteId","ZS_01");
                    siteScene.put("siteName",substation);

                    list.add(siteScene);
                }
            }
            mapResult.put("siteScene",list);

        }catch (Exception e){
            mapResult.put("substation",substation);
            mapResult.put("code",1);
            mapResult.put("msg","查询微气象数据失败");
            mapResult.put("siteScene",null);
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }

        return mapResult;
    }


    /**
     * 5.7.1	获取巡检点位信息
     * 功能说明：集控平台获取本地监控系统设备巡检点位信息。
     * 函数名：get_device_patrolled
     */
    @RequestMapping(value = "/robot/getDevicePatrolled")
    @ResponseBody
    public RtnData getDevicePatrolled(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();

        String substation = String.valueOf(map.get("substation"));
        int jdevice_level = Integer.parseInt(String.valueOf(map.get("jdevice_level")));
        List<Jdevice_extra> jdeviceExtras = new ArrayList<>();
        List<Jdevice> jdevices = new ArrayList<>();
        try{
            Client client = webserviceReuslt();
            Object [] resultObj =  client.invoke("get_device_patrolled", substation,jdevice_level); //首个参数为方法名，后面参数为传入的参数

            Map<String,Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));

        // 朗驰    String jpatrol_para_his_list = String.valueOf(mapp.get("jdeviceExtrasList")).replace("\\","").replace(" ","");
        // 鲁能    String jpatrol_para_his_list = String.valueOf(mapp.get("jdeviceExtrasList")).replace("\\","").replace(" ","");

            String jpatrol_para_his_list = String.valueOf(mapp.get("jdevice_extras_list")).replace("\\","").replace(" ","");


            JSONArray jsonArray = JSONArray.parseArray(jpatrol_para_his_list);
            jdeviceExtras = jsonArray.toJavaList(Jdevice_extra.class);
            mapRes.put("jdevice_extras_list",jdeviceExtras);

        // 朗驰    String jdeviceList = String.valueOf(mapp.get("jdeviceList")).replace("\\","").replace(" ","");
        // 鲁能    String jdeviceList = String.valueOf(mapp.get("jdevice_list")).replace("\\","").replace(" ","");
            String jdeviceList = String.valueOf(mapp.get("jdevice_list")).replace("\\","").replace(" ","");

            JSONArray jsonArray2 = JSONArray.parseArray(jdeviceList);
            jdevices = jsonArray2.toJavaList(Jdevice.class);
            mapRes.put("jdevice_list",jdevices);


            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();
            rtnData.setSubstation(subStation);

        }catch (Exception e){
            mapRes.put("jdevice_extras_list",jdeviceExtras);
            mapRes.put("jdevice_list",jdevices);
        //    mapRes.put("jrt_station_rl_list",null);
            rtnData.setData(mapRes);
            rtnData.setCode(1);
            rtnData.setMsg("失败");
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }

        return rtnData;
    }

    /**
     * 5.7.2	获取任务模板
     * 功能说明：集控平台获取本地监控系统已编制好的任务模板。
     * 函数名：get_task_templet
     */
    @RequestMapping(value = "/robot/getTaskTemplet")
    @ResponseBody
    public RtnData getTaskTemplet(@RequestBody Map<String,String> map){

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        String substation = map.get("substation");


        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_task_templet", substation); //首个参数为方法名，后面参数为传入的参数

            System.out.println(resultObj[0].toString());
            Map<String, Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));

            logger.info("get_task_templet接收到的数据为："+mapp.toString());

            try {
                String jtask_list = String.valueOf(mapp.get("jtask_list")).replace("\\","").replace(" ","");
                JSONArray jsonArray = JSONArray.parseArray(jtask_list);
                List<Jtask> jtaskList = jsonArray.toJavaList(Jtask.class);
                mapRes.put("jtask_list",jtaskList);
            }catch (Exception e){
                logger.error("jtask_list对象序列化失败!");

            }
            try{

                String jlink_list = String.valueOf(mapp.get("jlink_list")).replace("\\","").replace(" ","");
                JSONArray jsonArray2 = JSONArray.parseArray(jlink_list);
                List<Jlink> jlinkList = jsonArray2.toJavaList(Jlink.class);
                mapRes.put("jlink_list",jlinkList);
            }catch (Exception e){
                logger.error("jlink_list对象序列化失败!");

            }

            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();
            rtnData.setSubstation(subStation);

        }catch (Exception e){

            rtnData.setCode(1);
            rtnData.setMsg("失败");
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }

        return rtnData;
    }


    /**
     * 5.7.3	下发任务模板(主站那边没有)
     * 功能说明：集控平台向本地监控系统发送已编制好的任务模板。
     * 函数名：send_task_templet
     * @return
     */
    @RequestMapping(value = "/robot/send_task_templet")
    @ResponseBody
    public RtnData sendTaskTemplet(/*@RequestBody Map<String,String> map*/){

        RtnData rtnData = new RtnData();
        String substation = "1";
        int jdb_action_type = 1;

        Quartz_enable quartz_enable = new Quartz_enable();

        List<lFixDate> lFixDateList = new ArrayList<>();
        List<lIntervalDate> intervalDateList = new ArrayList<>();
        List<lWeekDate> lWeekDatesList = new ArrayList<>();


        lFixDate lFixDate  = new lFixDate();
        lFixDate.setID("1");
        lFixDate.setStartTime("2019-12-19 00:21");
        lFixDate.setType(0);
        lFixDateList.add(lFixDate);

        lIntervalDate intervalDate = new lIntervalDate();
        intervalDate.setID("1");
        intervalDate.setInterval(3);
        intervalDate.setIntervalType(1);
        intervalDate.setStartTime("2019-12-9 00:25");
        intervalDate.setType(2);
        intervalDateList.add(intervalDate);


        lWeekDate lWeekDate = new lWeekDate();
        lWeekDate.setID("1");

        Integer [] a = new Integer[2];
        a[0] = 1;
        a[1] = 2;
        lWeekDate.setlMonth(a);

        Integer [] b = new Integer[2];
        b[0] = 1;
        b[1] = 2;
        lWeekDate.setlWeek(b);
        lWeekDate.setStartTime("02:00");
        lWeekDatesList.add(lWeekDate);

        quartz_enable.setlFixDates(lFixDateList);
        quartz_enable.setlIntervalDates(intervalDateList);
        quartz_enable.setlWeekDates(lWeekDatesList);


        Quartz_disable quartz_disable = new Quartz_disable();


        lSpecialDate lSpecialDate = new lSpecialDate();
        lSpecialDate.setID("1");
        lSpecialDate.setEndTime("2019-12-20 19:25");
        lSpecialDate.setStartTime("2019-12-19 02:00");
        lSpecialDate.setTimeType(1);
        lSpecialDate.setType(3);

        List<lSpecialDate> lSpecialDateList = new ArrayList<>();
        lSpecialDateList.add(lSpecialDate);
        quartz_disable.setlSpecialDates(lSpecialDateList);

        Jtask jtask = new Jtask();
        //这里周期可用不可用做为字符串处理
        jtask.setQuartz_disable("");
        jtask.setQuartz_enable("");
        jtask.setRobot_mac("1");
        jtask.setTask_id("");
        jtask.setType("1601");
        jtask.setTask_name("test1");

        List<Jtask> jtaskList = new ArrayList<>();
        jtaskList.add(jtask);
        String jt = JSONObject.toJSONString(jtask);

        Jlink jlink = new Jlink();
        jlink.setId("1");
        jlink.setLink_id("104");




        List<Jlink> jlinks = new ArrayList<>();
        jlinks.add(jlink);
        String jlinkt = JSONObject.toJSONString(jlinks);

        //朗驰 最后两个为ArrayOfString
       /* ArrayOfstring ret = new ArrayOfstring();
        ArrayOfstring ret2 = new ArrayOfstring();

        List<String> list1 = new ArrayList<>();
        list1.add("1501");
        ret.setString(list1);*/

        List<String> list11 = new ArrayList<>();
        list11.add("1501");

        List<String> list2 = new ArrayList<>();
        list2.add("");

        String res_conf = "";
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("send_task_templet", substation,jdb_action_type,jt,"104",list11,list2); //首个参数为方法名，后面参数为传入的参数

            res_conf = String.valueOf(resultObj[0]);

            String result;
            if(res_conf.equals("1")){
                result ="成功";
                rtnData.setSuccess(true);
                rtnData.setCode(0);
            }else{
                result ="失败";
                rtnData.setSuccess(false);
                rtnData.setCode(1);
            }
            rtnData.setMsg(result);
            rtnData.setMessage(result);

        }catch (Exception e){
            rtnData.setCode(1);
            rtnData.setMsg(res_conf);
            rtnData.setSuccess(false);
            rtnData.setMessage(res_conf);
            logger.error("获取webservice接口获取失败",e.getMessage());
        }

        return rtnData;
    }

    /**
     * 5.7.7	当前任务浏览
     * 功能说明：集控平台主动获取机器人当前任务信息。
     * 函数名：get_search_task_current_info
     */
    @RequestMapping(value = "/robot/getSearchTaskCurrentInfo")
    @ResponseBody
    public RtnData getSearchTaskCurrentInfo(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();

        String substation = String.valueOf(map.get("substation"));
        String robot_mac = String.valueOf(map.get("robot_mac"));

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_search_task_current_info", substation,robot_mac); //首个参数为方法名，后面参数为传入的参数

            Map<String,Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));
            /*String jrt_station_rl_list = String.valueOf(mapp.get("jtask_rl_list"));
            JSONArray jsonArray = JSONArray.parseArray(jrt_station_rl_list);

            List<Jtask_rl> jtask_rl_list = jsonArray.toJavaList(Jtask_rl.class);
            map.put("jtask_rl_list",jtask_rl_list);*/


            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(resultObj[0]));
            Jtask_rl jtask_rl = jsonObject.toJavaObject(Jtask_rl.class);

            rtnData.setData(jtask_rl);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();
            rtnData.setSubstation(subStation);

        }catch (Exception e){

            rtnData.setCode(1);
            rtnData.setMsg("失败");
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }


        return rtnData;
    }

    /**
     *5.7.8	计划任务浏览
     * 功能说明：获取某站或某台机器人指定时间段内的计划任务列表
     * 函数名：get_search_task_plan_info
     */
    @RequestMapping(value = "/robot/getSearchTaskPlanInfo")
    @ResponseBody
    public RtnData getSearchTaskPlanInfo(@RequestBody Map<String,Object> map ){

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        String substation = String.valueOf(map.get("substation"));
        String robot_mac  = String.valueOf(map.get("robot_mac"));
        String start_time = String.valueOf(map.get("start_time"));
        String end_time  =  String.valueOf(map.get("end_time"));
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_search_task_plan_info",start_time,end_time, substation,robot_mac); //首个参数为方法名，后面参数为传入的参数


            JSONArray jsonArray = JSONArray.parseArray(String.valueOf(resultObj[0]));


            List<Jtask_plan_info> jtaskPlanInfos = jsonArray.toJavaList(Jtask_plan_info.class);
            mapRes.put("jtask_plan_info_list",jtaskPlanInfos);

            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

            //获取厂站名称
            Map<String,Object> m = taskService.getSubstation();
            String subStation = m.get("substation").toString();
            rtnData.setSubstation(subStation);

        }catch (Exception e){

            rtnData.setCode(1);
            rtnData.setMsg("失败");
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("获取webservice接口获取失败!",e.getMessage());
        }

        return rtnData;
    }

    /**
     *5.7.9	历史任务浏览
     * 功能说明：获取某站或某台机器人的历史任务列表
     * 函数名：get_search_task_finish_info
     */
    @RequestMapping(value = "/robot/getSearchTaskFinishInfo")
    @ResponseBody
    public RtnData getSearchTaskFinishInfo(@RequestBody Map<String,Object> map ){

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        String substation = String.valueOf(map.get("substation"));
        String robot_mac  = String.valueOf(map.get("robot_mac"));
        String start_time = String.valueOf(map.get("start_time"));
        String end_time  =  String.valueOf(map.get("end_time"));

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_search_task_finish_info", start_time,end_time,substation,robot_mac); //首个参数为方法名，后面参数为传入的参数

            JSONArray jsonArray = JSONArray.parseArray(String.valueOf(resultObj[0]));

            List<Jtask_data_rl> jtaskDataRls = jsonArray.toJavaList(Jtask_data_rl.class);
            mapRes.put("jtask_data_rl",jtaskDataRls);

            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

        }catch (Exception e){

            logger.error("获取webservice接口获取失败",e.getMessage());
        }

        return rtnData;
    }


    /**
     * 5.5.1	电网设备台账数据接口(主站文档暂无)
     * 功能说明：1、集控平台从安全生产管理信息系统获取指定变电站的设备台帐；2、本地监控系统从集控平台获取指定变电站的设备台帐。
     * 函数名：get_equipment_info
     */
    @RequestMapping(value = "/robot/get_equipment_info")
    @ResponseBody
    public Equipment get_equipment_info(@RequestBody Map<String,Object> map ){

        Equipment equipment = new Equipment();

        String substation = String.valueOf(map.get("substation_id"));

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_equipment_info", substation); //首个参数为方法名，后面参数为传入的参数


            Map<String, Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));

            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(mapp));
            equipment = jsonObject.toJavaObject(Equipment.class);

        }catch (Exception e){

            logger.error("获取WebService接口数据失败",e.getMessage());
        }

        return equipment;
    }

    /**
     *  5.2.2 设备巡检计划数据接口
     * 功能说明：1、集控平台从安全生产管理信息系统获取指定变电站的巡检计划数据；2、本地监控系统从集控平台获取指定变电站的巡检计划数据。
     * 函数名：get_patrol_plan
     */
    @RequestMapping(value = "/robot/get_patrol_plan")
    @ResponseBody
    public Patrol get_patrol_plan(@RequestBody Map<String,Object> map ){

        Patrol patrol = new Patrol();

        String substation = String.valueOf(map.get("substation")); //收取到的是：name值

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_patrol_plan", substation); //首个参数为方法名，后面参数为传入的参数


            Map<String, Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));

            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(mapp));
            patrol = jsonObject.toJavaObject(Patrol.class);

        }catch (Exception e){

            logger.error("获取WebService接口数据失败",e.getMessage());
        }

        return patrol;
    }

    /**
     *  5.3.1	发送调度倒闸操作指令
     * 功能说明：调度自动化系统主动向集控平台发送调度操作操作指令。
     * 函数名：send_break_operation_command
     */
    @RequestMapping(value = "/robot/send_break_operation_command")
    @ResponseBody
    public RtnData send_break_operation_command(@RequestBody Map<String,Object> map ){

        String power_supply_bureau = String.valueOf(map.get("power_supply_bureau"));
        String voltage_level = String.valueOf(map.get("voltage_level"));
        String substation = String.valueOf(map.get("substation"));
        String device_type = String.valueOf(map.get("device_type"));
        String device_name = String.valueOf(map.get("device_name"));
        String function_position = String.valueOf(map.get("function_position"));
        String operation_time = String.valueOf(map.get("operation_time"));

        RtnData rtnData =new RtnData();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("send_break_operation_command", power_supply_bureau,voltage_level,substation,device_name,device_type,function_position,operation_time); //首个参数为方法名，后面参数为传入的参数

            Map<String,Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));
            String  res_conf = String.valueOf(mapp.get("res_conf"));

            if(res_conf.equals("1")){
                rtnData.setCode(0);
                rtnData.setSuccess(true);
            }else{
                rtnData.setCode(1);
                rtnData.setSuccess(false);
            }
            rtnData.setMessage(res_conf);
            rtnData.setMsg(res_conf);

        }catch (Exception e){
            logger.error("获取WebService接口数据失败",e.getMessage());
        }
        return rtnData;
    }

    /**
     * 5.3.2	获取调度倒闸操作历史指令
     * 功能说明：集控平台主动向调度自动化系统获取指定时间段内的调度操作操作指令。
     * 函数名：get_break_operation_command
     */
    @RequestMapping(value = "/robot/get_break_operation_command")
    @ResponseBody
    public RtnData get_break_operation_command(@RequestBody Map<String,Object> map ){

        String start_time = String.valueOf(map.get("start_time"));
        String end_time = String.valueOf(map.get("end_time"));
        String power_supply_bureau = String.valueOf(map.get("power_supply_bureau"));
        String substation_id = String.valueOf(map.get("substation_id"));

        Operation_command operation_command = new Operation_command();

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("send_break_operation_command", start_time,end_time,power_supply_bureau,substation_id); //首个参数为方法名，后面参数为传入的参数

            Map<String, Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));

            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(mapp));
            operation_command = jsonObject.toJavaObject(Operation_command.class);

            mapRes.put("operation_command",operation_command);
            rtnData.setData(mapRes);
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMsg("成功");
            rtnData.setMessage("成功");

        }catch (Exception e){

            logger.error("获取WebService接口数据失败",e.getMessage());
        }
        return rtnData;
    }


    /**
     * 5.6.1	获取机器人台账信息
     * 功能说明：集控平台主动查询、获取站内机器人台账信息。
     * 函数名：get_robot_device_info
     */
    @RequestMapping(value = "/robot/getRobotDeviceInfo")
    @ResponseBody
    public Map<String,Object> get_robot_device_info(@RequestBody Map<String,Object> map ){

        RtnData rtnData = new RtnData();
        String substation_id = String.valueOf(map.get("siteId"));
        Map<String,Object> mapRes = new HashMap<>();

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_robot_device_info", substation_id); //首个参数为方法名，后面参数为传入的参数

            JSONArray jsonArray = JSONArray.parseArray(String.valueOf(resultObj[0]));
            List<Jrobot> jrobots = jsonArray.toJavaList(Jrobot.class);

            List<Map<String,Object>> robotList = new ArrayList<>();

            if(jrobots!= null && jrobots.size()>0){
                for (int i=0;i<jrobots.size();i++){
                    Map<String,Object> m = new HashMap<>();

                    m.put("siteId",substation_id);
                    m.put("robotMac",jrobots.get(i).getRobot_mac());
                    m.put("robotName",jrobots.get(i).getRobot_name());
                    m.put("manufacturer",jrobots.get(i).getManufacturer());
                    m.put("batteryQuantity",jrobots.get(i).getBattery_capacity());
                    m.put("robotMode",jrobots.get(i).getDevice_type());
                    m.put("serialNumber",jrobots.get(i).getManufacture_num());
                    m.put("manufacturerId","3202");//山东鲁能
                    m.put("navigationType",jrobots.get(i).getNavigation_type());
                    m.put("type",jrobots.get(i).getType());
                    m.put("driveType",jrobots.get(i).getDrive_type());

                    robotList.add(m);
                }
            }
            mapRes.put("RobotInfo",robotList);
            mapRes.put("code",0);
            mapRes.put("msg","查询机器人台账信息成功");
            mapRes.put("substation",substation_id);

        }catch (Exception e){

            mapRes.put("RobotInfo",null);
            mapRes.put("code",1);
            mapRes.put("msg","查询机器人台账信息失败");
            mapRes.put("substation",substation_id);
            logger.error("获取WebService接口数据失败",e.getMessage());
        }
        return mapRes;
    }


    /**
     * 5.5.8	实时视频数据接口
     * get_realTime_media_url
     * @return 返回一个rtsp地址，传参为空
     */

    /*@RequestMapping("/robot/get_realTime_media_url")
    @ResponseBody
    public RtnData  get_realTime_media_url(){

        String resUrl = "";
        RtnData rtnData = new RtnData();
        Map<String,String> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_robot_device_info"); //首个参数为方法名，后面参数为传入的参数

            resUrl = String.valueOf(resultObj[0]);
            if(resUrl != "" || resUrl != null){

                mapRes.put("url",resUrl);
                rtnData.setData(mapRes);
                rtnData.setSuccess(true);
                rtnData.setMsg("成功");
                rtnData.setMessage("成功");
                rtnData.setCode(0);
            }else{
                rtnData.setSuccess(false);
                rtnData.setMsg("失败");
                rtnData.setMessage("失败");
                rtnData.setCode(1);
            }

        }catch (Exception e){
            logger.error("获取实时数据接口url失败",e.getMessage());
        }
        return rtnData;
    }*/



    /**
     * 下发配置信息
     * 功能说明：集控平台下发配置信息至本地监控系统。
     * 函数名：send_config_info
     */

    @RequestMapping(value = "/robot/send_config_info")
    @ResponseBody
    public RtnData  send_config_info(@RequestBody Map<String,Object> map ){

        String result = "";
        String substation_id = String.valueOf(map.get("substation_id"));
        String jconn_re_info = String.valueOf(map.get("jconn_re_info"));

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map.get("jconn_re_info"));
        Jconn_re_info jconn_re_info1 = jsonObject.toJavaObject(Jconn_re_info.class);

        RtnData rtnData = new RtnData();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("send_config_info",substation_id,jconn_re_info); //首个参数为方法名，后面参数为传入的参数

            result = String.valueOf(resultObj[0]);

            if(result.equals("1")){

                rtnData.setSuccess(true);
                rtnData.setMsg("成功");
                rtnData.setMessage("成功");
                rtnData.setCode(0);
            }else{
                rtnData.setSuccess(false);
                rtnData.setMsg("失败");
                rtnData.setMessage("失败");
                rtnData.setCode(1);
            }

        }catch (Exception e){
            logger.error("获取实时数据接口url失败",e.getMessage());
        }

        return rtnData;
    }

    /**
     * 5.6.6	获取巡检路径文件
     * 功能说明：集控平台主动获取本地监控系统当前任务巡检路径。其中，巡检路径采用jpg图片格式，不含地图底文件。
     * 函数名：get_patrol_path
     */
    @RequestMapping(value = "/robot/get_patrol_path")
    @ResponseBody
    public RtnData  get_patrol_path(@RequestBody Map<String,Object> map ){

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        String substation_id = String.valueOf(map.get("substation_id"));
        String robot_name =String.valueOf(map.get("robot_name"));
        String robot_mac=String.valueOf(map.get("robot_mac"));

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_patrol_path",substation_id,robot_name,robot_mac); //首个参数为方法名，后面参数为传入的参数

            //假使获取为该对应的数组
            JSONArray jsonArray =null;
            if(!resultObj[0].equals("")){
                jsonArray = JSONArray.parseArray(String.valueOf(resultObj[0]));
            }else{
                rtnData.setSuccess(false);
                rtnData.setMessage("失败");
                rtnData.setCode(1);
                rtnData.setMsg("失败");
                return rtnData;
            }

            List<Jmap> jmaps = jsonArray.toJavaList(Jmap.class);

            mapRes.put("jmp_list",jmaps);

            rtnData.setData(mapRes);
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");
            rtnData.setCode(0);
            rtnData.setMsg("成功");

        }catch (Exception e){

            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            rtnData.setCode(1);
            rtnData.setMsg("失败");
            
            logger.error("获取巡检路径文件失败",e.getMessage());
        }
        return rtnData;
    }




    /**
     * 5.6.7	查询当前站内有无巡检机器人
     * 功能说明：查询指定变电站当前有无巡检机器人（针对一机多站问题）。
     * 函数名：get_robot_location
     */
    @RequestMapping(value = "/robot/get_robot_location")
    @ResponseBody
    public RtnData  get_robot_location(@RequestBody Map<String,Object> map ){

        String substation_id = String.valueOf(map.get("substation_id"));

        String robot_location = "";
        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_robot_location", substation_id);

            robot_location = String.valueOf(resultObj[0]);

            if (robot_location.equals("1")){
                mapRes.put("robot_location",robot_location);
                rtnData.setCode(0);
                rtnData.setSuccess(true);
                rtnData.setMessage("成功");
                rtnData.setMsg("成功");
                rtnData.setSuccess(true);
            } else{
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("失败");
                rtnData.setMsg("失败");
            }
        }catch (Exception e){

            logger.error("查询当前站内有无巡检机器人失败",e.getMessage());
        }
        return rtnData;
    }

    /**
     * 5.6.11	召唤机器人本体告警信息
     * 功能说明：集控平台主动查询本地监控系统机器人本体告警信息。
     * 函数名：get_body_alarm
     */

    @RequestMapping("/robot/get_body_alarm")
    @ResponseBody
    public RtnData  get_body_alarm(@RequestBody Map<String,Object> map ){

        String start_time = String.valueOf(map.get("start_time"));
        String end_time = String.valueOf(map.get("end_time"));
        String robot_mac = String.valueOf(map.get("robot_mac"));
        String review_state = String.valueOf(map.get("review_state"));

        ArrayOfstring ret = new ArrayOfstring();
        ArrayOfstring ret2 = new ArrayOfstring();

        List<String> list1 = new ArrayList<>();
        list1.add(robot_mac);
        ret.setString(list1);

        List<String> list2 = new ArrayList<>();
        list2.add(review_state);
        ret2.setString(list2);


        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
        // 朗驰    Object[] resultObj = client.invoke("get_body_alarm", start_time,end_time,ret,ret2);

            Object[] resultObj = client.invoke("get_body_alarm", start_time,end_time,list1,list2);

        //    Map<String,Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));
            String jevent_rl_list = String.valueOf(resultObj[0]);
            JSONArray jsonArray = JSONArray.parseArray(jevent_rl_list);

            List<Jevent_rl> jeventRls = jsonArray.toJavaList(Jevent_rl.class);

            mapRes.put("jevent_rl_list",jeventRls);
            rtnData.setData(mapRes);
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");
            rtnData.setCode(0);
            rtnData.setMsg("成功");

        }catch (Exception e){
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            rtnData.setCode(1);
            rtnData.setMsg("失败");
            logger.error("召唤机器人本体告警信息失败",e.getMessage());
        }

        return rtnData;
    }

    /**
     * 下发机器人状态设置信息
     * 功能说明：集控平台下发机器人设置信息至本地监控系统，包括红外功能、可见光功能、雨刷功能、避障功能、车灯状态、充电房门、机器人状态。
     * 函数名：send_setting_info
     */
    @RequestMapping(value = "/robot/send_setting_info")
    @ResponseBody
    public RtnData  send_setting_info(@RequestBody Map<String,Object> map ){

        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jsetting_info = Integer.parseInt(String.valueOf(map.get("jsetting_info")));
        String value = String.valueOf(map.get("value"));

        RtnData rtnData = new RtnData();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("send_setting_info", robot_mac,jsetting_info,value);

            String  res_conf = String.valueOf(resultObj[0]);
            if (res_conf.equals("1")){
                res_conf ="成功";
                rtnData.setCode(0);
                rtnData.setSuccess(true);

            } else{
                res_conf="失败";
                rtnData.setCode(1);
                rtnData.setSuccess(false);
            }
            rtnData.setMessage(res_conf);
            rtnData.setMsg(res_conf);

        }catch (Exception e){
            logger.error("发机器人状态设置信息",e.getMessage());
        }

        return rtnData;
    }


    /**
     * 5.6.15	机器人遥控（第一视角模式）
     * 功能说明：以第一视角远程遥控机器人运动
     * 函数名：robot_remote_control
     */
    @RequestMapping(value = "/robot/robot_remote_control")
    @ResponseBody
    public RtnData  robot_remote_control(@RequestBody Map<String,Object> map ){

        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jmotor = Integer.parseInt(String.valueOf(map.get("jmotor")));
        String linear_velocity = String.valueOf(map.get("linear_velocity"));
        String angular_velocity = String.valueOf(map.get("angular_velocity"));


        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("robot_remote_control", robot_mac,jmotor,linear_velocity,angular_velocity);

            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(resultObj[0]));
            Return_remote_control returnRemoteControl = jsonObject.toJavaObject(Return_remote_control.class);

            mapRes.put("return_remote_control",returnRemoteControl);

            rtnData.setData(mapRes);

            rtnData.setMsg("成功");
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

        }catch (Exception e){
            rtnData.setMsg("失败");
            rtnData.setCode(1);
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("机器人遥控（第一视角模式）数据接收失败",e.getMessage());
        }

        return rtnData;
    }



    /**5.6.16	机器人遥控（地图选点模式）
     功能说明：集控平台将巡检地图中选点信息发送至本地监控系统，本地监控系统解析、生成任务执行。
     函数名：send_robot_control_map
     *
     */
    @RequestMapping("/robot/send_robot_control_map")
    @ResponseBody
    public RtnData  send_robot_control_map(@RequestBody Map<String,Object> map ){

        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jtask_type = Integer.parseInt(String.valueOf(map.get("jtask_type")));
        int jpoint_type = Integer.parseInt(String.valueOf(map.get("jpoint_type")));
        String id_list = String.valueOf(map.get("id_list"));
        boolean is_need_return = Boolean.valueOf(String.valueOf(map.get("is_need_return")));


        RtnData rtnData = new RtnData();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("send_robot_control_map", robot_mac,jtask_type,jpoint_type,id_list,is_need_return);

            String result = String.valueOf(resultObj[0]);

            if(result.equals("1")){
                rtnData.setMsg("成功");
                rtnData.setCode(0);
                rtnData.setSuccess(true);
                rtnData.setMessage("成功");
            }else if (result.equals("0")){
                rtnData.setMsg("失败");
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("失败");
            }
        }catch (Exception e){
            logger.error("机器人遥控（第一视角模式）数据接收失败",e.getMessage());
        }

        return rtnData;
    }


    /**
     * 5.6.17	云台控制
     * 功能说明：控制相机云台转动。
     * 函数名：platform_remote_control
     */
    @RequestMapping("/robot/platform_remote_control")
    @ResponseBody
    public RtnData  platform_remote_control(@RequestBody Map<String,Object> map ){

        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jcamera_type = Integer.parseInt(String.valueOf(map.get("jcamera_type")));
        int jyuntai = Integer.parseInt(String.valueOf(map.get("jyuntai")));


        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("platform_remote_control", robot_mac,jcamera_type,jyuntai);

            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(resultObj[0]));
            Return_remote_control returnRemoteControl = jsonObject.toJavaObject(Return_remote_control.class);

            mapRes.put("return_remote_control",returnRemoteControl);

            rtnData.setData(mapRes);

            rtnData.setMsg("成功");
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMessage("成功");

        }catch (Exception e){
            rtnData.setMsg("失败");
            rtnData.setCode(1);
            rtnData.setSuccess(false);
            rtnData.setMessage("失败");
            logger.error("机器人遥控（第一视角模式）数据接收失败",e.getMessage());
        }

        return rtnData;
    }

    /**
     * 5.6.18	高清相机控制
     * 功能说明：控制机器人高清相机完成相应业务功能
     * 具体接口要求参照《南方电网变电站视频及环境监控系统技术规范》执行。
     * 集控平台扩展支持如下接口：
     * （新增）镜头控制：拉焦、变倍
     * 函数名：camera_control
     */
    @RequestMapping("/robot/camera_control")
    @ResponseBody
    public RtnData  camera_control(@RequestBody Map<String,Object> map ) {

        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jcamera_type= Integer.parseInt(String.valueOf(map.get("jcamera_type")));
        int jcamera_run_mode= Integer.parseInt(String.valueOf(map.get("jcamera_run_mode")));
        int jfocus= Integer.parseInt(String.valueOf(map.get("jfocus")));
        String param= String.valueOf(map.get("param"));

        RtnData rtnData = new RtnData();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("camera_control", robot_mac, jcamera_type,jcamera_run_mode,jfocus,param);

            String result = String.valueOf(resultObj[0]);

            if(result.equals("1")){
                rtnData.setMsg("成功");
                rtnData.setCode(0);
                rtnData.setSuccess(true);
                rtnData.setMessage("成功");
            }else if (result.equals("0")){
                rtnData.setMsg("失败");
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("失败");
            }
        }catch (Exception e){
            logger.error("高清相机控制失败",e.getMessage());
        }

        return rtnData;
    }


        /**
         * 5.6.20	（新增）控制权
         * 功能说明：集控平台主动发起申请、释放机器人控制权请求。
         * 函数名：control
         */
    @RequestMapping("/robot/control")
    @ResponseBody
    public RtnData  control(@RequestBody Map<String,Object> map ){

        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jcontrol = Integer.parseInt(String.valueOf(map.get("jcontrol")));

        RtnData rtnData = new RtnData();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("control", robot_mac, jcontrol);

            String  res_conf = String.valueOf(resultObj[0]);
            if (res_conf.equals("1")){
                res_conf ="成功";
                rtnData.setCode(0);
                rtnData.setSuccess(true);

            } else{
                res_conf="失败";
                rtnData.setCode(1);
                rtnData.setSuccess(false);
            }
            rtnData.setMessage(res_conf);
            rtnData.setMsg(res_conf);

        }catch (Exception e){
            logger.error("控制权,获取失败",e.getMessage());
        }
        return rtnData;
    }



    /**
     * 5.6.21	（新增）校时
     * 功能说明：本地监控系统主动获取集控平台的系统时间。
     * 函数名：check_time
     */
    @RequestMapping("/robot/check_time")
    @ResponseBody
    public RtnData  check_time(@RequestBody Map<String,Object> map ){

        String substation_id = String.valueOf(map.get("substation_id"));
        Map<String,Object> mapRes = new HashMap<>();
        RtnData rtnData = new RtnData();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("check_time", substation_id);

            String time = String.valueOf(resultObj[0]);
            if(time != "" || time != null){

                mapRes.put("time",time);
                rtnData.setData(mapRes);
                rtnData.setCode(0);
                rtnData.setMsg("成功");
                rtnData.setMessage("成功");
                rtnData.setSuccess(true);
            }else{
                rtnData.setData(mapRes);
                rtnData.setCode(1);
                rtnData.setMsg("失败");
                rtnData.setMessage("失败");
                rtnData.setSuccess(false);
            }

        }catch (Exception e){
            logger.error("控制权,获取失败",e.getMessage());
        }
        return rtnData;
    }

    /**
     * 5.6.19	控制模式切换
     * 功能说明：切换当前机器人控制模式
     * 函数名：robot_mode_type
     */
    @RequestMapping("/robot/robot_mode_type")
    @ResponseBody
    public RtnData  robot_mode_type(@RequestBody Map<String,Object> map ){

        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jmodeType = Integer.parseInt(String.valueOf(map.get("jmode_type")));

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("robot_mode_type", robot_mac,jmodeType);

            String jmode_type = String.valueOf(resultObj[0]);

            mapRes.put("jmode_type",jmode_type);  //当前控制模式类型
            rtnData.setData(mapRes);
            rtnData.setMessage("成功");
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);

        }catch (Exception e){
            logger.error("切换当前机器人控制模式失败",e.getMessage());
        }


        return rtnData;
    }


    /**
     * 5.7.6	下发任务编辑指令
     * 函数名：send_task_edit
     * 输入参量：任务编辑指令信息
     * 字段名称	数据类型	字段内容
     * substation_id	string	变电站编码
     * task_name	string	任务名称
     * task_id	string	任务id
     * jtask_state	string	任务控制
     */
    @RequestMapping("/robot/send_task_edit")
    @ResponseBody
    public RtnData  send_task_edit(@RequestBody Map<String,Object> map ){

        String substation_id = String.valueOf(map.get("substation_id"));
        String task_name = String.valueOf(map.get("task_name"));
        String task_id = String.valueOf(map.get("task_id"));
        String jtask_state = String.valueOf(map.get("jtask_state"));

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("send_task_edit", substation_id,task_name,task_id,jtask_state);

            String  res_conf = String.valueOf(resultObj[0]);

            mapRes.put("res_conf",res_conf);
            rtnData.setData(mapRes);
            rtnData.setMessage("成功");
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);

        }catch (Exception e){
            logger.error("切换当前机器人控制模式失败",e.getMessage());
        }

        return rtnData;
    }


    /*
    函数名：camera_file_control
     */
    @RequestMapping("/robot/camera_file_control")
    @ResponseBody
    public RtnData  camera_file_control(@RequestBody Map<String,Object> map ) {

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        String robot_mac = String.valueOf(map.get("robot_mac"));
        int jcamera_type = Integer.parseInt(String.valueOf(map.get("jcamera_type")));
        int jcamera_control_mode = Integer.parseInt(String.valueOf(map.get("jcamera_control_mode")));
        String user_name = String.valueOf(map.get("user_name"));

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("camera_file_control", robot_mac, jcamera_type, jcamera_control_mode, user_name);

           /* Map<String, Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));
            String file_name = String.valueOf(mapp.get("file_name"));*/

            String file_name = String.valueOf(resultObj[0]);
            System.out.println(file_name);


            mapRes.put("res_conf",file_name);
            rtnData.setData(mapRes);
            rtnData.setMessage("成功");
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
        }catch (Exception e){
            logger.error("camera_file_control获取失败",e.getMessage());
        }

        return rtnData;
    }


    /**
     * 函数名：get_robot_body_info
     */
    @RequestMapping("/robot/get_robot_body_info")
    @ResponseBody
    public RtnData  get_robot_body_info(@RequestBody Map<String,Object> map ) {

        RtnData rtnData = new RtnData();
        Map<String,Object> mapRes = new HashMap<>();
        String robot_mac = String.valueOf(map.get("robot_mac"));

        try {
            Client client = webserviceReuslt();
            Object[] resultObj = client.invoke("get_robot_body_info", robot_mac);

            Map<String, Object> mapp = JSON.parseObject(String.valueOf(resultObj[0]));

            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(mapp));
            Jrobot_body_info jrobot_body_info = jsonObject.toJavaObject(Jrobot_body_info.class);

            mapRes.put("res_conf",jrobot_body_info);
            rtnData.setData(mapRes);
            rtnData.setMessage("成功");
            rtnData.setCode(0);
            rtnData.setMsg("成功");
            rtnData.setSuccess(true);
        }catch (Exception e){
            logger.error("get_robot_body_info获取失败",e.getMessage());
        }
        return rtnData;
    }




        /**
         * webservice调用服务端接口数据
         * @return
         */
    public  Client webserviceReuslt() {


        String ip="",port="",childUrl="";

        if(webServiceConfig.isEnabled()){  //朗驰开启服务
            ip = webServiceConfig.getIp();
            port = webServiceConfig.getPort();
            childUrl = webServiceConfig.getChildUrl();
        }
        if(lnWebServiceConfig.isEnabled()){ //鲁能开启服务
            ip = lnWebServiceConfig.getIp();
            port = lnWebServiceConfig.getPort();
            childUrl = lnWebServiceConfig.getChildUrl();
        }

        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        Client client = null;
        try{

            client = dcf.createClient(ip+":"+port+childUrl);
            // 需要密码的情况需要加上用户名和密码
            // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));

            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setConnectionTimeout(30000);  //连接超时
            httpClientPolicy.setAllowChunking(false);    //取消块编码
            httpClientPolicy.setReceiveTimeout(180000);     //响应超时
            conduit.setClient(httpClientPolicy);
            logger.info("连接机器人服务端  wsdl --- 成功！");
        }catch (Exception e){
            logger.error("连接机器人服务端  wsdl --- 失败！");
        }

        return client;
    }

}
