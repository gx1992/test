package com.cn.uk.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.uk.common.utils.HttpClientUtils;
import com.cn.uk.common.utils.SecurityUtil;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.DoorAccessConfig;
import com.cn.uk.config.Layout;
import com.cn.uk.config.WebSocketCofig.WebSocketServer;
import com.cn.uk.dto.CameraRelatedDto.PushDataDto;
import com.cn.uk.dto.DoorAccessDto.AccessSignDto;
import com.cn.uk.dto.RtnData;
import com.cn.uk.service.QueryTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Layout(value = "layouts/default")
public class DoorEntranceController {

    @Autowired
    private QueryTaskService taskService;

    public static final Logger logger = LoggerFactory.getLogger(DoorEntranceController.class);

    //存储捷顺门禁的key,name以及url地址
    private static final ConcurrentHashMap<String,String> accessKeyAndUrlMap = new ConcurrentHashMap();

    private DoorAccessConfig accessConfig = AppContext.getBean(DoorAccessConfig.class);
    private HashMap<String,String> headParams = new HashMap<>();
    private  String apiurl="";
    {
        apiurl = accessConfig.getApiBaseUrl();
        //初始化请求头
        headParams.put("Content-type", "application/json; charset=utf-8");
        headParams.put("Accept-Language", "zh-CN,zh;q=0.9");
        headParams.put("Accept", "application/json");
        headParams.put("v", "1.0");

        accessKeyAndUrlMap.put("getSignUrl", apiurl+"/internal/sign");
        accessKeyAndUrlMap.put("getDeptsUrl", apiurl+"/base/depts"); //查询所有部门
        accessKeyAndUrlMap.put("getPersonsUrl", apiurl+"/base/personlist"); //所有人事资料
        accessKeyAndUrlMap.put("getPersonUrl", apiurl+"/base/person"); //查询单个人事资料
        accessKeyAndUrlMap.put("getDevicesUrl", apiurl+"/base/devices"); //查询所有设备信息
        accessKeyAndUrlMap.put("getInparkingrecordUrl", apiurl+"/park/inparkingrecord"); //查询车辆信息
        accessKeyAndUrlMap.put("getBlackwhitelistUrl", apiurl+"/park/blackwhitelist"); //2.1.6 查询车辆基本信息接口
        accessKeyAndUrlMap.put("getDept", apiurl+"/base/dept"); //2.1.7 查询单个组织结构
        accessKeyAndUrlMap.put("getAdddept", apiurl+"/base/adddept"); //2.1.8  新增组织结构
        accessKeyAndUrlMap.put("getUpdatedept", apiurl+"/base/updatedept"); //2.1.9  更新组织结构
        accessKeyAndUrlMap.put("getDeletedept", apiurl+"/base/deletedept"); //2.1.10  删除组织结构
        accessKeyAndUrlMap.put("getAddperson", apiurl+"/base/addperson"); //2.1.11 新增人事资料
        accessKeyAndUrlMap.put("getUpdateperson", apiurl+"/base/updateperson"); //2.1.12 更新人事资料
        accessKeyAndUrlMap.put("getDeleteperson", apiurl+"/base/deleteperson"); //2.1.13 删除人事资料
        accessKeyAndUrlMap.put("getAddblackwhitelist", apiurl+"/park/addblackwhitelist"); //2.1.14 新增黑白名单
        accessKeyAndUrlMap.put("getUpdateblackwhitelist", apiurl+"/park/updateblackwhitelist"); //2.1.15 更新黑白名单
        accessKeyAndUrlMap.put("getDeleteblackwhitelist", apiurl+"/park/deleteblackwhitelist"); //2.1.16 删除黑白名单
    }

    /**
     * 2.1.1 查询所有部门接口

     * @return
     */
    @PostMapping(value="/csg.app.yalian.departments.post")
    public RtnData departments(@RequestBody Map<String,Object> map ){

        // pageIndex:1,pageSize:10,deptName="测试1",parentId="00000000-0000-0000-0000-000000000000",parentId为空，默认查所有

        headParams = commonLoad();

        if(!map.containsKey("deptName")) map.put("deptName","");
        if(!map.containsKey("parentId")) map.put("parentId","");

        String json = JSON.toJSONString(map);//map转String

        try {
            logger.info("开始执行httpClient请求");
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getDeptsUrl"),headParams,json);

            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    //post

    /**
     * 获取组织结构下所有人事数据
     * pageIndex
     * pageSize
     * deptId
     * keyWord
     * compareRule
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.person.post")
    public RtnData person(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("deptId")) map.put("deptId","");
        if(!map.containsKey("keyWord")) map.put("keyWord","");
        if(!map.containsKey("compareRule")) map.put("compareRule","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getPersonsUrl"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }


    /**
     * 查询单个人员的数据信息
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.queryperson.post")
    public RtnData queryperson(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getPersonUrl"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     *查询所有设备信息
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.querydevice.post")
    public RtnData querydevice(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getDevicesUrl"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }


    /**
     * 2.1.5 查询停车场内车辆接口
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.querycars.post")
    public RtnData querycars(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("plateNumber")) map.put("plateNumber","");
        if(!map.containsKey("startTime")) map.put("startTime","");
        if(!map.containsKey("endTime")) map.put("endTime","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getInparkingrecordUrl"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     * 2.1.6 查询车辆基本信息接口
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.vehicle.post")
    public RtnData vehicle(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("plateNumber")) map.put("plateNumber","");
        if(!map.containsKey("blackWhiteType")) map.put("blackWhiteType","");
        if(!map.containsKey("blackGuid")) map.put("blackGuid","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getBlackwhitelistUrl"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     2.1.7 查询单个组织结构
     接口名称：csg.app.yalian.dept.post
     HTTP请求方式：post
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.dept.post")
    public RtnData dept(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getDept"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }
    /**
     2.1.8 查询新增组织结构
     接口名称：csg.app.yalian.adddept.post
     HTTP请求方式：post
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.adddept.post")
    public RtnData adddept(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("remark")) map.put("remark","");
        if(!map.containsKey("parentId")) map.put("parentId","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getAdddept"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     2.1.9 更新组织结构(主站文档有误)
     接口名称：csg.app.yalian.updatedept.post
     HTTP请求方式：post
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.updatedept.post")
    public RtnData updatedept(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("remark")) map.put("remark","");
        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getUpdatedept"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     2.1.10 删除组织结构
     接口名称：csg.app.yalian.deletedept.post
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.deletedept.post")
    public RtnData deletedept(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getDeletedept"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     2.1.11 新增人事资料
     接口名称：csg.app.yalian.addperson.post
     HTTP请求方式：post

     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.addperson.post")
    public RtnData addperson(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("deptId")) map.put("deptId","");
        if(!map.containsKey("personNo")) map.put("personNo","");
        if(!map.containsKey("personGender")) map.put("personGender","");
        if(!map.containsKey("remark")) map.put("remark","");
        if(!map.containsKey("personPhoto")) map.put("personPhoto","");
        if(!map.containsKey("certificateType")) map.put("certificateType","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getAddperson"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     2.1.12 更新人事资料
     接口名称：csg.app.yalian.updateperson.post
     HTTP请求方式：post

     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.updateperson.post")
    public RtnData updateperson(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("personGender")) map.put("personGender","");
        if(!map.containsKey("remark")) map.put("remark","");
        if(!map.containsKey("personPhoto")) map.put("personPhoto","");
        if(!map.containsKey("certificateType")) map.put("certificateType","");
        if(!map.containsKey("identityNo")) map.put("identityNo","");
        if(!map.containsKey("tel1")) map.put("tel1","");
        if(!map.containsKey("tel2")) map.put("tel2","");
        if(!map.containsKey("email")) map.put("email","");
        if(!map.containsKey("roomNo")) map.put("roomNo","");
        if(!map.containsKey("address")) map.put("address","");
        if(!map.containsKey("tenementType")) map.put("tenementType","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getUpdateperson"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }
    /**
     2.1.13 删除人事资料
     接口名称：csg.app.yalian.deleteperson.post
     HTTP请求方式：post

     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.deleteperson.post")
    public RtnData deleteperson(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getDeleteperson"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }

    /**
     2.1.14 新增黑白名单
     接口名称：csg.app.yalian.addblackwhitelist.post
     HTTP请求方式：post
     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.addblackwhitelist.post")
    public RtnData addblackwhitelist(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("startDate")) map.put("startDate","");
        if(!map.containsKey("endDate")) map.put("endDate","");
        if(!map.containsKey("reason")) map.put("reason","");
        if(!map.containsKey("remark")) map.put("remark","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getAddblackwhitelist"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }


    /**
     2.1.15 更新黑白名单
     接口名称：csg.app.yalian.updateblackwhitelist.post
     HTTP请求方式：post

     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.updateblackwhitelist.post")
    public RtnData updateblackwhitelist(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("startDate")) map.put("startDate","");
        if(!map.containsKey("endDate")) map.put("endDate","");
        if(!map.containsKey("reason")) map.put("reason","");
        if(!map.containsKey("remark")) map.put("remark","");
        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getUpdateblackwhitelist"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }


    /**
     2.1.16 删除黑白名单
     接口名称：csg.app.yalian.deleteblackwhitelist.post
     HTTP请求方式：post


     * @param map
     * @return
     */
    @PostMapping("/csg.app.yalian.deleteblackwhitelist.post")
    public RtnData deleteblackwhitelist(@RequestBody Map<String,Object> map){

        headParams = commonLoad();

        if(!map.containsKey("blackGuid")) map.put("blackGuid","");
        if(!map.containsKey("plateNumber")) map.put("plateNumber","");

        String json = JSON.toJSONString(map);//map转String

        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getDeleteblackwhitelist"),headParams,json);
            rtnData.setSuccess(true);
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");

    }


    /**************门禁主动上送接口*********************/

    /**
     *接收车辆入场记录
     * /carin
     *
     */
    @PostMapping("/carin")
    public void carin(@RequestBody Map<String,Object> map){

        Map<String,Object> mapp = new HashMap<>();

        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = m.get("substation").toString();

        mapp.put("substation",subStation);
        mapp.put("type",3);
        mapp.put("passRecord",map);

        /*int time = (int)((new Date().getTime())/1000);
        String timeString = time+"";
        Object object = PushDataDto.buildDataDto("fs"+timeString,JSON.toJSONString(postMap));*/
        try {
            WebSocketServer.sendInfo(JSON.toJSONString(mapp), null);
            logger.info("接收车辆入场记录:"+mapp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *接收车辆出场记录
     * /carout
     *
     */
    @PostMapping("/carout")
    public void carout(@RequestBody Map<String,Object> map){


        Map<String,Object> mapp = new HashMap<>();

        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = m.get("substation").toString();

        mapp.put("substation",subStation);
        mapp.put("type",32);
        mapp.put("passRecord",map);

        try {
            WebSocketServer.sendInfo(JSON.toJSONString(mapp), null);
            logger.info("接收车辆出场记录:"+mapp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     *上传门禁记录
     * /doorrecord
     *
     */
    @PostMapping("/doorrecord")
    public void doorrecord(@RequestBody Map<String,Object> map){

        Map<String,Object> mapp = new HashMap<>();

        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = m.get("substation").toString();

        mapp.put("substation",subStation);
        mapp.put("type",1);
        mapp.put("passRecord",map);

        try {
            WebSocketServer.sendInfo(JSON.toJSONString(mapp), null);
            logger.info("上传门禁记录:"+mapp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    /**
     * 获取key
     * @return
     */
    public String getKey(){
        String key =  accessKeyAndUrlMap.get("key");
        if(key == null || "".equals(key)){
            Map<String,String> params = new HashMap<>(4);
            String userName = accessConfig.getUserName();
            String passWord = accessConfig.getPassWord();
            params.put("userName", userName);
            params.put("password", passWord);
            logger.info("获取配置中账户信息:root="+userName+"密码："+passWord);
            String paramsJsonString = JSON.toJSONString(params);
            String url =  accessKeyAndUrlMap.get("getSignUrl");
            try {
                logger.info("开始进行获取appId的获取.."+url+"head头部参数"+headParams+"参数:"+paramsJsonString);
                RtnData rtnData = HttpClientUtils.doPost(url,headParams,paramsJsonString);
                logger.info("获取RtnData"+rtnData+"开始返回list结果");
                List<AccessSignDto> signs = JSON.parseArray(JSON.toJSONString(rtnData.getData()),AccessSignDto.class);
                if(signs != null && signs.size()> 0){
                    key = signs.get(0).getKey();
                    accessKeyAndUrlMap.put("key", key);
                    accessKeyAndUrlMap.put("appId",signs.get(0).getAppId());
                    return key;
                }
               /*if(rtnData!=null){   测试
                   JSONObject data = (JSONObject)rtnData.getData();
                   accessKeyAndUrlMap.put("key", data.get("key").toString());
                   accessKeyAndUrlMap.put("appId",data.get("appId").toString());
                   key = data.get("key").toString();
               }*/

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("请求过程中发生错误");
            }


        }else{
            return key;
        }
        return key;
    }












    /**
     * 获取appID
     * @return
     */
    public String getAppId(){
        String appId =  accessKeyAndUrlMap.get("appId");
        if(appId == null || "".equals(appId)){
            Map<String,String> params = new HashMap<>(4);
            String userName = accessConfig.getUserName();
            String passWord = accessConfig.getPassWord();
            params.put("userName", userName);
            params.put("password", passWord);
            logger.info("获取配置中账户信息:root="+userName+"密码："+passWord);
            String paramsJsonString = JSON.toJSONString(params);
            String url =  accessKeyAndUrlMap.get("getSignUrl");
            try {

                logger.info("开始进行获取appId的获取.."+url+"head头部参数"+headParams+"参数:"+paramsJsonString);
                RtnData rtnData = HttpClientUtils.doPost(url,headParams,paramsJsonString);

                logger.info("获取RtnData"+rtnData+"开始返回list结果");
                List<AccessSignDto> signs = JSON.parseArray(JSON.toJSONString(rtnData.getData()),AccessSignDto.class);
                if(signs != null && signs.size()> 0){
                    appId = signs.get(0).getAppId();
                    accessKeyAndUrlMap.put("key", signs.get(0).getKey());
                    accessKeyAndUrlMap.put("appId",signs.get(0).getAppId());
                    return appId;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("请求过程中发生错误");
            }


        }else{
            return appId;
        }
        return appId;
    }



    public HashMap<String,String> commonLoad(){
        String key = getKey();
        String appId = getAppId();
        String random = SecurityUtil.getRandomString(6);
        String timestamp = SecurityUtil.getTimestamp();
        String preSign = "random"+random+"timestamp"+timestamp+"key"+key;
        String sign = SecurityUtil.MD5(preSign);
        headParams.put("appId",appId);
        headParams.put("random", random);
        headParams.put("timestamp", timestamp);
        headParams.put("sign", sign);

        return headParams;
    }


}
