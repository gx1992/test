package com.cn.uk.controller;


import com.alibaba.fastjson.JSON;
import com.cn.uk.common.utils.HttpClientUtils;
import com.cn.uk.common.utils.SecurityUtil;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.DoorAccessConfig;
import com.cn.uk.config.Layout;
import com.cn.uk.dto.DoorAccessDto.AccessHistoryDto;
import com.cn.uk.dto.DoorAccessDto.AccessSignDto;
import com.cn.uk.dto.RtnData;
import com.cn.uk.model.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@ResponseBody
@Layout(value = "layouts/default")
public class DoorAccessInterfaceController {
    public static final Logger logger = LoggerFactory.getLogger(DoorAccessInterfaceController.class);
    //存储捷顺门禁的key,name以及url地址
    private static final ConcurrentHashMap<String,String> accessKeyAndUrlMap = new ConcurrentHashMap();
    //存储method对应的方法名
    private static final ConcurrentHashMap<String,String> methodParams = new ConcurrentHashMap<>();
    static{
        methodParams.put("csg.app.yalian.hr.departments.post", "getDeptInfos");
        methodParams.put("csg.app.yalian.hr.departments.post", "getDeptInfos");
        methodParams.put("csg.app.yalian.hr.departments.post", "getDeptInfos");
    }
    private DoorAccessConfig accessConfig = AppContext.getBean(DoorAccessConfig.class);
    private HashMap<String,String> headParams = new HashMap<>();
    private HashMap<String,String> bodyParams = new HashMap<>();
    private  String apiurl="";
    {
        apiurl = accessConfig.getApiBaseUrl();
        //初始化请求头
        headParams.put("Content-type", "application/json; charset=utf-8");
        headParams.put("Accept-Language", "zh-CN,zh;q=0.9");
        headParams.put("Accept", "application/json");
        headParams.put("v", "1.0");

        accessKeyAndUrlMap.put("getDeptsUrl", apiurl+"/base/depts");
        accessKeyAndUrlMap.put("getSignUrl", apiurl+"/internal/sign");

    }

    @PostMapping(value = "/access/test")
    public RtnData judgeMethodInvoke(HttpServletRequest request, HttpServletResponse response){
          String method = request.getHeader("method");
          String app_id = request.getHeader("app_id");
          String access_token = request.getHeader("access_token");
          String timestamp = request.getHeader("timestamp");
          String format = request.getHeader("format");
          String sign = request.getHeader("sign");
          String sign_method = request.getHeader("sign_method");
          String nonce = request.getHeader("nonce");

          String methodName = methodParams.get(method);
          if(methodName == null || "".equals(methodName)){
              return RtnData.fail(1, "请求头中的method参数为空", "");
          }

        try {
            Method theMethod = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            RtnData rtnData = (RtnData) theMethod.invoke(this,request,response);
            return rtnData;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return RtnData.fail(1, "请求中method参数出错", "");
    }


    /**
     * 获取所有部门信息
     * @param request
     * @return
     */
    public RtnData getDeptInfos(HttpServletRequest request, HttpServletResponse response){

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
        try {
            RtnData rtnData = HttpClientUtils.doPost(accessKeyAndUrlMap.get("getDeptsUrl"),headParams,"");
            return rtnData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RtnData.fail(1,"查询失败", "");
    }


    /**
     * 同步门禁卡
     * @param empNo
     * @param cardFixno
     * @param cardStatusid
     * @return
     */

    @RequestMapping(value = "/access/getCardInfos")
    public RtnData getCardInfos(@RequestParam(name = "empNo") String empNo,
                                @RequestParam(name = "cardFixno") String cardFixno,
                                @RequestParam(name = "cardStatusid") String cardStatusid){
        return RtnData.ok("");
    }

    /**
     * 操作门禁权限接口
     * @param empNo
     * @param doorID
     * @param oprtType
     * @return
     */
    @RequestMapping(value = "/access/operateDoorAccount")
    public RtnData operateDoorAccount(@RequestParam(name = "empNo") String empNo,
                                      @RequestParam(name = "doorID") long doorID,
                                      @RequestParam(name = "oprtType") String oprtType){
        HashMap<String,String> params = new HashMap<>();
        params.put("", "");
        try {
            RtnData rtnData = HttpClientUtils.doPost("", headParams, params);
            return RtnData.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail(1, "请求失败", "");
        }

    }

    /**
     * 获取所有的门禁信息
     * @return
     */
    @RequestMapping(value = "/access/getAllDoorInfos")
    public RtnData getAllDoorInfos(){

        try {
            RtnData rtnData = HttpClientUtils.doPost("", headParams, headParams);
            String doorSting = JSON.toJSONString(rtnData.getData());
            List<Door> doors = JSON.parseArray(doorSting, Door.class);

            return RtnData.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail(1, "处理失败", "");
        }

    }

    /**
     * 查询门禁记录
     * @param startDate
     * @param endDate
     * @param empType
     * @return
     */
    @RequestMapping(value = "/access/getAccessHistoryRecords")
    public RtnData accessHistoryRecords(@RequestParam(name = "startDate") String startDate,
                                        @RequestParam(name = "endDate") String endDate,
                                        @RequestParam(name = "empType") String empType){

        Map<String,String> params = new HashMap<>();
        params.put("startDate",startDate);
        params.put("endDate", endDate);
        params.put("empType",empType);
        try {
            RtnData rtnData = HttpClientUtils.doPost("", headParams, params);

            if(rtnData != null){
                String accessHistoryString = JSON.toJSONString(rtnData.getData());
                List<AccessHistoryDto> historyDtos = JSON.parseArray(accessHistoryString, AccessHistoryDto.class);
                return rtnData;
            }else{
                return RtnData.fail(1, "失败", "无返回结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return RtnData.fail(1, "失败", "无返回结果");
        }


    }

    /**
     * 得到所有卡片信息
     * @param empNo
     * @return
     */
    @RequestMapping(value = "/access/getCardInfosByEmp")
    public RtnData getCardInfosByEmp(@RequestParam(name = "empNo") String empNo){

        return RtnData.ok("");
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
            String paramsJsonString = JSON.toJSONString(params);
            String url =  accessKeyAndUrlMap.get("getSignUrl");
            try {
                RtnData rtnData = HttpClientUtils.doPost(url,headParams,paramsJsonString);
                List<AccessSignDto> signs = JSON.parseArray(JSON.toJSONString(rtnData.getData()),AccessSignDto.class);
                if(signs != null && signs.size()> 0){
                    key = signs.get(0).getKey();
                    accessKeyAndUrlMap.put("key", key);
                    accessKeyAndUrlMap.put("appId",signs.get(0).getAppId());
                    return key;
                }

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
            String paramsJsonString = JSON.toJSONString(params);
            String url =  accessKeyAndUrlMap.get("getSignUrl");
            try {
                RtnData rtnData = HttpClientUtils.doPost(url,headParams,paramsJsonString);
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





}
