package com.cn.uk.webService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.config.WebSocketCofig.Const;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.dto.CameraRelatedDto.PushDataDto;
import com.cn.uk.webService.Pojo.*;
import com.google.common.io.BaseEncoding;
import com.lnzn.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@WebService(serviceName="CentralPlatformServiceImplService", targetNamespace="http://ws.lnzn.com/", endpointInterface= "com.cn.uk.webService.CentralPlatformServiceImplService")
@Component
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public class CentralPlatformServiceImplServiceImpl
        implements CentralPlatformServiceImplService
{

    //这个就是我们可以指定频道推送对应数据的对象
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //在服务端任意位置推送消息，需要使用到 SimpMessageSendingOperations 类

    public String SendPatrolData(String substation_id, String jrt_device_rl_list)
    {
        System.out.println("本地变电站名称:" + substation_id + "-----list的json数据值：" + jrt_device_rl_list);


        String result = "函数名：send_patrol_data本地变电站名称:" + substation_id + "-----list的json数据值：" + jrt_device_rl_list;
        try
        {

            Map<String,Object> map = new HashMap<>();
            map.put("substation",substation_id);

            List<Map<String,Object>> resultList = new ArrayList<>();

            if(jrt_device_rl_list!=""){
                JSONArray jsonArray = JSONArray.parseArray(jrt_device_rl_list);
                List<Jrt_Device_rl> jrtDeviceRls = jsonArray.toJavaList(Jrt_Device_rl.class);

                if(jrtDeviceRls!=null && jrtDeviceRls.size()>0){
                    for(int i=0;i<jrtDeviceRls.size();i++){
                        Map<String,Object> m = new HashMap<>();

                        m.put("patrolDeviceId",jrtDeviceRls.get(i).getRobot_mac());
                        m.put("prodPlanId",jrtDeviceRls.get(i).getTask_id());
                        m.put("objectType","1");
                        m.put("deviceId",jrtDeviceRls.get(i).getDevice_id());
                        m.put("patrolPointId",jrtDeviceRls.get(i).getPatrol_point_id());
                        m.put("patrolPointName",jrtDeviceRls.get(i).getPatrol_point_name());
                        m.put("deviceName",jrtDeviceRls.get(i).getDevice_name());
                        m.put("monitorDate",jrtDeviceRls.get(i).getRecognition_time());
                        m.put("monitorItem",jrtDeviceRls.get(i).getRecognition_type());
                        m.put("monitorResult",jrtDeviceRls.get(i).getPatrol_value_show());
                        m.put("patrolValue",jrtDeviceRls.get(i).getPatrol_value());

                        m.put("patroled_num",jrtDeviceRls.get(i).getPatroled_num());
                        m.put("coordinate",jrtDeviceRls.get(i).getCoordinate());
                        m.put("alarmLevel",jrtDeviceRls.get(i).getAlarm_level());
                        m.put("linkUrl",jrtDeviceRls.get(i).getFile_path());
                        m.put("fileType",jrtDeviceRls.get(i).getFile_type());
                        m.put("isStatus","1");//告警等级 1正常，其他异常
                        m.put("siteId",substation_id);
                        m.put("siteName","变电站1");
                        m.put("taskId",jrtDeviceRls.get(i).getTask_id());

                        resultList.add(m);
                    }
                }
            }

            map.put("patrolPointResult",resultList);

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            Object object = PushDataDto.buildDataDto("fs"+timeString,text);
        //    WebSocketServer.sendInfo(JSON.toJSONString(object), "");

            simpMessagingTemplate.convertAndSend("/events/115128",JSON.toJSONString(object));

            simpMessagingTemplate.convertAndSendToUser("management/cspg","/events/115128",JSON.toJSONString(object));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";

        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendPatrolDataResponse sendPatrolDataResponse = new SendPatrolDataResponse();

        sendPatrolDataResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendFaultValue(String substation_id, String jalarm_data_rl_list)
    {
        String result = "函数名：send_fault_value本地变电站名称:" + substation_id + "-----list的json数据值：" + jalarm_data_rl_list;
        try
        {
            Map<String,Object> map = new HashMap<>();
            map.put("substation",substation_id);

            List<Map<String,Object>> alarmList = new ArrayList<>();

            if(jalarm_data_rl_list!=""){
                JSONArray jsonArray = JSONArray.parseArray(jalarm_data_rl_list);
                List<Jalarm_data_rl> jalarmDataRls = jsonArray.toJavaList(Jalarm_data_rl.class);

                if(jalarmDataRls!=null && jalarmDataRls.size()>0){
                    for(int i=0;i<jalarmDataRls.size();i++){
                        Map<String,Object> m = new HashMap<>();

                        m.put("patrolDeviceId",jalarmDataRls.get(i).getDevice_id());
                        m.put("prodPlanId",jalarmDataRls.get(i).getPatrol_point_id());
                        m.put("objectType","1");
                        m.put("deviceId",jalarmDataRls.get(i).getDevice_id());
                        m.put("patrolPointId",jalarmDataRls.get(i).getPatrol_point_id());
                        m.put("patrolPointName",jalarmDataRls.get(i).getPatrol_point_name());
                        m.put("deviceName",jalarmDataRls.get(i).getDevice_name());
                        m.put("monitorDate",jalarmDataRls.get(i).getRecognition_time());
                        m.put("monitorItem",jalarmDataRls.get(i).getRecognition_type());
                        m.put("monitorResult",jalarmDataRls.get(i).getPatrol_value_show());
                        m.put("patrolValue",jalarmDataRls.get(i).getPatrol_value());
                        m.put("alarmContent",jalarmDataRls.get(i).getContent());
                        m.put("alarmLevel",jalarmDataRls.get(i).getAlarm_level());
                        m.put("alarmType",jalarmDataRls.get(i).getAlarm_type());
                        m.put("alarmNmu",jalarmDataRls.get(i).getAlarm_num());
                        m.put("siteId",substation_id);
                        m.put("siteName","变电站1");
                        m.put("taskId",jalarmDataRls.get(i).getTask_id());

                        alarmList.add(m);
                    }
                }
            }

            map.put("patrolPointResult",alarmList);

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            Object object = PushDataDto.buildDataDto("fs"+timeString,text);
        //    WebSocketServer.sendInfo(JSON.toJSONString(object), "");

            simpMessagingTemplate.convertAndSend("/events/115131",JSON.toJSONString(object));

        //    WebSocketServer.sendInfo(result, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendFaultValueResponse sendFaultValueResponse = new SendFaultValueResponse();
        sendFaultValueResponse.setResConf(jsonString);

        return jsonString;
    }

    public String SendMicroClimate(String substation_id, String jrt_station_rl_list)
    {
        String result = "函数名：send_micro_climate本地变电站名称:" + substation_id + "-----list的json数据值：" + jrt_station_rl_list;
        try
        {
            Map<String,Object> map = new HashMap<>();
            map.put("substation",substation_id);

            List<Map<String,Object>> list = new ArrayList<>();
            if(jrt_station_rl_list!= null ){
                JSONArray jsonArray = JSONArray.parseArray(jrt_station_rl_list);
                List<Jrt_station_rl> jrtStationRls = jsonArray.toJavaList(Jrt_station_rl.class);
                if(jrtStationRls!=null && jrtStationRls.size()>0){
                    for (int i=0;i<jrtStationRls.size();i++){

                        Map<String,Object> m = new HashMap<>();
                        m.put("measureTime",jrtStationRls.get(i).getMeasure_time());
                        m.put("type",jrtStationRls.get(i).getType());
                        m.put("value",jrtStationRls.get(i).getValue());
                        m.put("siteId",substation_id);
                        m.put("siteName","变电站1");

                        list.add(m);
                    }
                }
            }
            map.put("siteScene",list);

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            Object object = PushDataDto.buildDataDto("fs"+timeString,text);
        //    WebSocketServer.sendInfo(JSON.toJSONString(object), "");

            simpMessagingTemplate.convertAndSend("/events/115133",JSON.toJSONString(object));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendMicroClimateResponse sendMicroClimateResponse = new SendMicroClimateResponse();
        sendMicroClimateResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendPatrolPath(String substation_id, String robot_name, String robot_mac, String task_name, String task_id, String jmap_list)
    {
        String result = "函数名：send_patrol_path本地变电站名称:" + substation_id + "-----robot_name：" + robot_name + "robot_mac：" + robot_mac + "task_name：" + task_name + "task_id：" + task_id + "jmap_list：" + jmap_list;
        try
        {

           /* int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            Object object = PushDataDto.buildDataDto("fs"+timeString,text);
            WebSocketServer.sendInfo(JSON.toJSONString(object), "");*/

            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendPatrolPathResponse sendPatrolPathResponse = new SendPatrolPathResponse();
        sendPatrolPathResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendRobotState(String substation_id, String jrobot_state_list)
    {
        String result = "函数名：send_robot_state本地变电站名称:" + substation_id + "-----jrobot_state_list的json字符串：" + jrobot_state_list;
        try
        {
            Map<String,Object> map = new HashMap<>();
            map.put("substation","ZS_01");
            List<Map<String,Object>> list = new ArrayList<>();
            if(jrobot_state_list!= null && jrobot_state_list!=""){

                JSONArray jsonArray = JSONArray.parseArray(jrobot_state_list);
                List<Jrobot_state> jrobot_states = jsonArray.toJavaList(Jrobot_state.class);
                if(jrobot_states!=null && jrobot_states.size()>0){

                    for(int i=0;i<jrobot_states.size();i++){
                        Map<String,Object> m =  new HashMap<>();

                        m.put("robotName",jrobot_states.get(i).getRobot_name());
                        m.put("robotMac",jrobot_states.get(i).getRobot_mac());
                        m.put("robotState",jrobot_states.get(i).getRobot_state());
                        m.put("siteId",substation_id);
                        m.put("siteName","变电站1");

                        list.add(m);

                    }
                }
                map.put("robotStates",list);
            }else{
                map.put("robotStates",null);
            }

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            Object object = PushDataDto.buildDataDto("fs"+timeString,text);
        //    WebSocketServer.sendInfo(JSON.toJSONString(object), "");

            simpMessagingTemplate.convertAndSend("/events/115135",JSON.toJSONString(object));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendRobotStateResponse sendRobotStateResponse = new SendRobotStateResponse();
        sendRobotStateResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendBodyAlarm(String substation_id, String jevent_rl_list)
    {
        String result = "函数名：send_body_alarm本地变电站名称:" + substation_id + "-----jevent_rl_list的json字符串：" + jevent_rl_list;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";

        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendBodyAlarmResponse sendBodyAlarmResponse = new SendBodyAlarmResponse();
        sendBodyAlarmResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendControlInfo(String substation_id, String jrt_data_rl_list)
    {
        String result = "函数名：send_control_info本地变电站名称:" + substation_id + "-----jrt_data_rl_list的json字符串：" + jrt_data_rl_list;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendControlInfoResponse sendControlInfoResponse = new SendControlInfoResponse();
        sendControlInfoResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendTaskExecuteInfo(String substation_id, String jtask_rl)
    {
        String result = "函数名：send_task_execute_info本地变电站名称:" + substation_id + "-----jtask_rl的json字符串：" + jtask_rl;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendTaskExecuteInfoResponse sendTaskExecuteInfoResponse = new SendTaskExecuteInfoResponse();
        sendTaskExecuteInfoResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendConfigInfo(String substation_id, String jstation_info)
    {
        String result = "函数名：send_config_info本地变电站名称:" + substation_id + "-----jstation_info的json字符串：" + jstation_info;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Jconn_re_info jconnReInfo = new Jconn_re_info();
        FileAddr FileAddr = new FileAddr();

        FileAddr.setIP("192.168.3.12");
        FileAddr.setPassWord("123");
        FileAddr.setUserName("Admin");
        FileAddr.setPort("21");

        ServerAddr ServerAddr = new ServerAddr();
        ServerAddr.setIP("192.168.3.12");
        ServerAddr.setPort("1000");

        jconnReInfo.setFileAddr(FileAddr);
        jconnReInfo.setIsRlUploadFile(Integer.valueOf(0));
        jconnReInfo.setServerAddr(ServerAddr);




        Map<String, Object> map = new HashMap();
        map.put("jconn_re_info", jconnReInfo);
        String jsonString = JSONObject.toJSONString(map);

        SendConfigInfoResponse sendConfigInfoResponse = new SendConfigInfoResponse();
        sendConfigInfoResponse.setJconnReInfo(jsonString);
        return jsonString;
    }

    public String ChangePermissionState(String substation_id, String jpermission_list)
    {
        String result = "函数名：change_permission_state本地变电站名称:" + substation_id + "-----jpermission_list的json字符串：" + jpermission_list;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        ChangePermissionStateResponse changePermissionStateResponse = new ChangePermissionStateResponse();

        changePermissionStateResponse.setResConf(jsonString);

        return jsonString;
    }

    public String CheckTime(String substation_id)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = format.format(date);

        Map<String, Object> map = new HashMap();
        map.put("time", time);
        String jsonString = JSONObject.toJSONString(map);

        CheckTimeResponse checkTimeResponse = new CheckTimeResponse();
        checkTimeResponse.setTime(jsonString);

        String result = "函数名：check_time 本地变电站名称:" + substation_id + "-----time的json字符串：" + jsonString;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return jsonString;
    }

    public String GetEquipmentInfo(String substation_id)
    {
        TwoEightInfo twoEightInfo = new TwoEightInfo();
        twoEightInfo.setTransformer("1");
        twoEightInfo.setBreaker("1");
        twoEightInfo.setCt("1");
        twoEightInfo.setDisconnector("1");
        twoEightInfo.setGis("1");
        twoEightInfo.setSwitchgear("1");

        Map<String, Object> map = new HashMap();
        map.put("equipment_infos", twoEightInfo);
        String jsonString = JSONObject.toJSONString(map);


        GetEquipmentInfoResponse getEquipmentInfoResponse = new GetEquipmentInfoResponse();
        getEquipmentInfoResponse.setEquipmentInfos(jsonString);
        return jsonString;
    }

    public String GetPatrolPlan(String substation)
    {
        Patrol_plan patrol_plan = new Patrol_plan();

        patrol_plan.setPlan_num("001");
        patrol_plan.setPlan_type("周");
        patrol_plan.setPlan_state("新增");
        patrol_plan.setWork_type("巡视");
        patrol_plan.setWorkplace("办公室");
        patrol_plan.setWork_content("监视");
        patrol_plan.setRefined_type("日常巡视");
        patrol_plan.setDepartment("001号部门");
        patrol_plan.setWorkgroup("1班");
        patrol_plan.setFounder("admin");
        patrol_plan.setWork_mode("打电话");
        patrol_plan.setVoltage_level("110kv");
        patrol_plan.setPrincipal("superAdmin");
        patrol_plan.setPlan_source("办公会");


        String start_time = "2020-06-06 12:12:12";
        String end_time = "2020-06-09 12:12:12";
        patrol_plan.setPlanned_start_time(start_time);
        patrol_plan.setPlanned_end_time(end_time);
        patrol_plan.setActual_start_time(start_time);
        patrol_plan.setActual_end_time(end_time);


        Map<String, Object> map = new HashMap();
        map.put("patrol_plans", patrol_plan);
        String jsonString = JSONObject.toJSONString(map);


        GetPatrolPlanResponse getPatrolPlanResponse = new GetPatrolPlanResponse();
        getPatrolPlanResponse.setPatrolPlans(jsonString);

        return jsonString;
    }

    public String SendPatrolPoint(String substation_id, String robot_name, String robot_mac, String task_name, String task_id, String jmap)
    {
        String result = "函数名：send_patrol_point本地变电站名称:" + substation_id + "-----robot_name：" + robot_name + "robotMac：" + robot_mac + "task_name：" + task_name + "task_id：" + task_id + "jmap：" + jmap;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";

        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendPatrolPointResponse sendPatrolPointResponse = new SendPatrolPointResponse();
        sendPatrolPointResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendRobotMotionState(String substation, String jrt_data_rl_list)
    {
        String result = "函数名：send_robot_motion_state本地变电站名称:" + substation + "-----jrt_data_rl_list：" + jrt_data_rl_list;
        try
        {
            Map<String,Object> map = new HashMap<>();
            List<Map<String,Object>> list = new ArrayList<>();
            map.put("substation",substation);
            if(jrt_data_rl_list!=null && jrt_data_rl_list!=""){
                JSONArray jsonArray = JSONArray.parseArray(jrt_data_rl_list);
                List<Jrt_data_rl> jrtDataRls = jsonArray.toJavaList(Jrt_data_rl.class);
                if(jrtDataRls!=null && jrtDataRls.size()>0){
                    //{"robot_mac":"1","measure_time":"2020-06-22 10:31:51","type":53,"value":"0.0","value_show":"0"}

                    for(int i=0;i<jrtDataRls.size();i++){
                        Map<String,Object> m = new HashMap<>();
                        m.put("robotName",jrtDataRls.get(i).getRobot_name());
                        m.put("robotMac",jrtDataRls.get(i).getRobot_mac());
                        m.put("measureTime",jrtDataRls.get(i).getMeasure_time());
                        m.put("type",jrtDataRls.get(i).getType());
                        m.put("value",jrtDataRls.get(i).getValue());
                        m.put("valueShow",jrtDataRls.get(i).getValue_show());

                        m.put("siteId",substation);
                        m.put("siteName","变电站1");

                        list.add(m);
                    }
                }
                map.put("robotStates",list);
            }else{
                map.put("robotStates",null);
            }

            int time = (int)((new Date().getTime())/1000);
            String timeString = time+"";
            String text = BaseEncoding.base64().encode(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
            Object object = PushDataDto.buildDataDto("fs"+timeString,text);
        //    WebSocketServer.sendInfo(JSON.toJSONString(object), "");

            simpMessagingTemplate.convertAndSend("/events/115138",JSON.toJSONString(object));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendRobotMotionStateResponse stateResponse = new SendRobotMotionStateResponse();
        stateResponse.setResConf(jsonString);
        return jsonString;
    }

    public String SendTaskFinishInfo(String substation_id, String jtask_data_rl_list)
    {
        String result = "函数名：send_task_finish_info本地变电站名称:" + substation_id + "-----jtask_data_rl_list：" + jtask_data_rl_list;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SendTaskFinishInfoResponse sendTaskFinishInfoResponse = new SendTaskFinishInfoResponse();
        sendTaskFinishInfoResponse.setResConf(jsonString);
        return jsonString;
    }

    @WebMethod(operationName = "sync_task_templet")
    public String SyncTaskTemplet(String substation_id, String jtask_link)
    {
        String result = "函数名：sync_task_templet本地变电站名称:" + substation_id + "-----jtask_link：" + jtask_link;
        try
        {
            WebSocketServer.sendInfo(result, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String res_conf = "1";
        Map<String, Object> map = new HashMap();
        map.put("res_conf", res_conf);
        String jsonString = JSONObject.toJSONString(map);

        SyncTaskTempletResponse syncTaskTempletResponse = new SyncTaskTempletResponse();
        syncTaskTempletResponse.setResConf(jsonString);
        return jsonString;
    }
}
