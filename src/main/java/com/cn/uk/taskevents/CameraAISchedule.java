package com.cn.uk.taskevents;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.command.*;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.dto.CameraRelatedDto.PushDataDto;
import com.cn.uk.service.QueryTaskService;
import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class CameraAISchedule {
    private static final Logger logger = LoggerFactory.getLogger(CameraAISchedule.class);

    private QueryTaskService taskService = AppContext.getBean(QueryTaskService.class);


    //每三十秒执行一次这个方法

    /**
     * 因动环历史数据无上送，则通过上送的实时数据，先存表，然后经过 30*2*10 10分钟一次进行上传，
     */
    @Scheduled(cron = "0/600 * * * * ?")
    public void getCametaAIResult(){

        String sn = null;
        String createdTm = null;

        List<Map<String,Object>> list = taskService.findAllDeviceIds();

        List<Map<String,Object>> deviceList = new ArrayList<>();

        if(list!=null & list.size()>0){
            for(int i = 0;i<list.size();i++){
                Map<String,Object> deviceMap = new HashMap<>();

                sn = String.valueOf(list.get(i).get("sn"));
                createdTm = String.valueOf(list.get(i).get("createdTm"));
                int deviceID = Integer.parseInt(String.valueOf(list.get(i).get("deviceID")));

                Map<String,Object> map = new HashMap<>();
                List<String> itemMap = new ArrayList<>();
                map.put("deviceID",deviceID);
                List<Map<String,Object>> mapList = taskService.queryDhHisData(map);

                if(mapList!=null && mapList.size()>0){
                    for(int j=0;j<mapList.size();j++){
                        String item = String.valueOf(mapList.get(j).get("deviceItem"));
                        
                        if(!item.equals("[]")){
                            JSONArray jsonArray = JSONArray.parseArray(item);
                            List<HisItem> items = jsonArray.toJavaList(HisItem.class);

                            for(HisItem tm:items){
                                String json = JSONObject.toJSONString(tm);
                                itemMap.add(json);
                            }
                        }
                    }
                }

                //取出10分钟内某deviceID的所有items

                deviceMap.put("id",deviceID);
                deviceMap.put("items",itemMap);

                deviceList.add(deviceMap);
            }
        }

        Map<String, Object> sensorGateway = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();

        map1.put("sn",sn);
        map1.put("createdTm",createdTm);
        map1.put("devices",deviceList);

        sensorGateway.put("data","402");
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
            logger.info("动环历史数据开始上送到主站>>>:"+jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //然后删除表所有数据
        int flag = taskService.deleteHisDHData();
        if(flag>0){
            logger.info("历史数据上传成功，清理数据表成功!"+flag);
        }
    }




}
