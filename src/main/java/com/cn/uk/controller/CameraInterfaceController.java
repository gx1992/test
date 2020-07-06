package com.cn.uk.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.cn.uk.common.utils.BuildPtzCommandUtil;
import com.cn.uk.common.utils.ConcurrentMap;
import com.cn.uk.config.AppContext;
import com.cn.uk.config.DoorAccessConfig;
import com.cn.uk.config.Layout;
import com.cn.uk.dto.CameraRelatedDto.*;
import com.cn.uk.dto.RtnData;
import com.cn.uk.model.TigCamera;
import com.cn.uk.service.TigCameraService;
import com.cn.uk.socket.localRelate.SmartGatewayClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@ResponseBody
@Layout(value = "layouts/default")
public class CameraInterfaceController {
    public static final Logger logger = LoggerFactory.getLogger(CameraInterfaceController.class);
    //存储method对应的方法名
    private static final ConcurrentHashMap<String,String> methodParams = new ConcurrentHashMap<>();
    @Autowired
    private TigCameraService tigCameraService;
    static{
        methodParams.put("smartgateway.getCameraInfos", "getCameraDeviceInfos");
        methodParams.put("smartgateway.getVideoUrl", "getVideoUrl");
        methodParams.put("smartgateway.ptzControl", "ptzControl");
        methodParams.put("smartgateway.queryCameraInfosByAreaNum", "queryCameraInfosByAreaNum");
        methodParams.put("smartgateway.startCameraTask", "startCameraTask");
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

    }

    @PostMapping(value = "/router/rest")
    public String judgeMethodInvoke(HttpServletRequest request, HttpServletResponse response){
        //获取到请求头中的参数
        String method = request.getParameter("method");
        String app_id = request.getParameter("app_id");
        String access_token = request.getParameter("access_token");
        String timestamp = request.getParameter("timestamp");
        String format = request.getParameter("format");
        String sign = request.getParameter("sign");
        String sign_method = request.getParameter("sign_method");
        String nonce = request.getParameter("nonce");
        String path = request.getParameter("path");
        //对请求头参数处理，鉴别请求
        String methodName = methodParams.get(method);
        if(timestamp == null || "".equals(timestamp)){

            return this.toJsonString(RtnData.fail(1, "请求时间戳不可为空！", ""));
        }else{
            long nowTimestamp = new Date().getTime();
            long timeStampPatameter = 0;
            try{
                 timeStampPatameter = Long.parseLong(timestamp);
            }catch (Exception e){
                return this.toJsonString(RtnData.fail(1, "timestamp格式不正确，转化出错", ""));
            }

            if(!(timeStampPatameter<(nowTimestamp+10*60*1000)&& timeStampPatameter > (nowTimestamp - 10*60*1000))){
                     return this.toJsonString(RtnData.fail(1, "请求时间不在当前十分钟以内！", ""));
            }

        }
        if(method == null || "".equals(method)){
            return this.toJsonString(RtnData.fail(1, "请求头中的method参数为空！", ""));
        }
        if(methodName == null || "".equals(methodName)){
            return this.toJsonString(RtnData.fail(1, "请求头中的method参数不正确，找不到方法", ""));
        }

        try {
            Method theMethod = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            String rtnData = (String) theMethod.invoke(this,request,response);
            return rtnData;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return this.toJsonString(RtnData.fail(1, "请求中参数出错", ""));
    }


    /**
     * 获取子设备列表
     * @param request
     * @param response
     * @return
     */
    public String getCameraDeviceInfos(HttpServletRequest request,HttpServletResponse response){

        String deviceType = request.getParameter("devicetype");
        int fromIndex = Integer.valueOf(request.getParameter("fromIndex"));
        int toIndex = Integer.valueOf(request.getParameter("toIndex"));
        int endIndex = toIndex -fromIndex;
        Map<String,Object> params = new HashMap<>();
        if("2".equals(deviceType)){
           params.put("startIndex", fromIndex);
           params.put("endIndex", endIndex);
           List<TigCamera> cameras = tigCameraService.queryPage(params);
           int count = 0;
           if(cameras != null){
               count = cameras.size();
           }
           //分页信息
            IndexRange indexRange = new IndexRange();
            indexRange.setFromIndex(fromIndex);
            indexRange.setToIndex(toIndex);
            //相机信息
            List<CameraBriefInfoDto> cameraBriefInfoDtoList = new ArrayList<>();
            for(TigCamera camera : cameras){
                CameraBriefInfoDto cameraBriefInfoDto = new CameraBriefInfoDto.CameraBriefInfoDtoBuilder()
                        .setCode(camera.getCamera_no())
                        .setCameraStatus(1)
                        .setStatus(camera.getStatus())
                        .setCameraLocation("")
                        .setDeviceCreateTime("")
                        .setDeviceGroupCode("")
                        .setDeviceIP(camera.getCamera_ip())
                        .setDeviceModelType(camera.getCamera_model())
                        .setDomainCode("")
                        .setEnableVoice(camera.getEnable_voice())
                        .setIsExDomain(0)
                        .setIsSupportIntelligent(0)
                        .setName(camera.getCamera_name())
                        .setNetType(camera.getNet_type())
                        .setNvrCode(camera.getNvr_no())
                        .setParentCode("")
                        .setReserve("")
                        .setType(camera.getType())
                        .setVendorType(camera.getCamera_manufacturer())
                        .setDeviceFormType(3)
                        .build();
                cameraBriefInfoDtoList.add(cameraBriefInfoDto);
            }
            CameraBriefInfoListDto cameraBriefInfoListDto = new CameraBriefInfoListDto.CameraBriefInfoListDtoBuilder()
                    .setCameraBriefInfo(cameraBriefInfoDtoList)
                    .build();
            CameraRtnDataDto cameraRtnDataDto = new CameraRtnDataDto();
            CameraBriefsDto cameraBriefsDto = new CameraBriefsDto.CameraBriefsDtoBuilder()
                    .setTotal(count)
                    .setCameraBriefInfoList(cameraBriefInfoListDto)
                    .setIndexRange(indexRange)
                    .build();
            //视频设备列表
            cameraRtnDataDto.setCameraBriefInfos(cameraBriefsDto);
            //结果码
            cameraRtnDataDto.setResultCode(0);
            //站名
            cameraRtnDataDto.setSubstation("fs");

            return this.toJsonString(cameraRtnDataDto);
          /*  return RtnData.ok(cameraRtnDataDto);*/

       }

        return null;

    }


    /**
     * 获取视频链接
     * @param request
     */
    public String getVideoUrl(HttpServletRequest request,HttpServletResponse response){
        String cameraCode = request.getParameter("cameraCode");
        String mediaURLParam = request.getParameter("mediaURLParam");
        MediaURLParamDto mediaURLParamDto = JSON.parseObject(mediaURLParam,MediaURLParamDto.class);

        CameraRtnDataDto cameraRtnDataDto = new CameraRtnDataDto();
        cameraRtnDataDto.setSubstation("fs");
        cameraRtnDataDto.setRtspURL("ceshi---rtsp://192.168.0.1:554/01042570000000000101?DstCode=01&ServiceType=1" +
                "&ClientType=0&StreamID=1&SrcTP=2&DstTP=2&SrcPP=1&DstPP=1&MediaTransMode=0&BroadcastType=0&SV=0&Token=TjBL+9HAe0aqavdJqs8GbpmSvNZd9NVf&DomainCode=c5fa198c20c7416bb19999173b445e5a&UserId=1&");
        cameraRtnDataDto.setResultCode(0);
        return this.toJsonString(cameraRtnDataDto);
    }


    /**
     * 控制云台
     * @param request
     * @param response
     * @return
     */
    public String ptzControl(HttpServletRequest request,HttpServletResponse response){
                String cameraCode = request.getParameter("cameraCode");
                String controlCode = request.getParameter("controlCode");
                String controlPara1 = request.getParameter("controlPara1");
                String controlPara2 = request.getParameter("controlPara2");
                String command = BuildPtzCommandUtil.buildPtzCommand(cameraCode,Integer.valueOf(controlCode),
                        controlPara1,controlPara2);
                byte[] commandBytes = command.getBytes(StandardCharsets.UTF_8);
                SmartGatewayClient smartClient = ConcurrentMap.getItem("smartGatewayClient",SmartGatewayClient.class);
                OutputStream outputStream = smartClient.getOutputStream();
               if(outputStream != null ){

              }

               CommandRtnDataDto commandRtnData = new CommandRtnDataDto();
               commandRtnData.setLockStatus("0");
               commandRtnData.setResultCode(0);
               commandRtnData.setSubstation("fs");

     return this.toJsonString(commandRtnData);
    }


    /**
     * 根据区域查询摄像头档案
     * @param request,response
     * @return
     */
    public String queryCameraInfosByAreaNum(HttpServletRequest request,HttpServletResponse response){
          String areaNum = request.getParameter("areaNum");
          List<CameraPointDto> pointDtos = new ArrayList<>();
          CameraPointDto cameraPointDto = new CameraPointDto.CameraPointDtoBuilder()
                    .setId(12)
                    .setMeterType(2)
                    .setPointName("点位2")
                    .setDiscernType(2)
                    .setDeviceId("2")
                    .setCameraId(1)
                    .build();

          CameraPresettingCodeDto cameraPresettingCodeDto = new CameraPresettingCodeDto.CameraPresettingCodeDtoBuilder()
                  .setCameraId(12)
                  .setDiscernType(2)
                  .setId(1)
                  .setPresettingCode("23")
                  .setPresettingName("预置位1")
                  .build();
          List<CameraPresettingCodeDto> dtos = new ArrayList<>();
          dtos.add(cameraPresettingCodeDto);

          //
          pointDtos.add(cameraPointDto);
          CameraAndPointsInfoDto cameraAndPointsInfoDto = new CameraAndPointsInfoDto.CameraAndPointsInfoDtoBuilder()
                .setCameraPresettingList(dtos)
                .setCamBrands("hk")
                .setCameraCode("")
                .setCameraPointList(pointDtos)
                .setCamModel("")
                .setCamType("blot")
                .setChannelAlias("安全帽检测摄像头")
                .setChannelId(70)
                .setDevAddress("192.168.0.150")
                .setDevPwd("mm852456")
                .setDevUser("admin")
                .setOpenTasks("qucr,aqmjc")
                .setOrgName("/光明变电站/高压室前门")
                .setPowerName("")
                .setPowerNum("")
                .setRemark("监控对象信息")
                .setStatus("normal")
                .build();
          List<CameraAndPointsInfoDto> cameraAndPointsInfoDtos = new ArrayList<>();
          cameraAndPointsInfoDtos.add(cameraAndPointsInfoDto);
          return this.toJsonString(CommonRtnDataDto.ok(cameraAndPointsInfoDtos));

    }



    /**
     * 根据视频路径返回视频二进制流
     * @param response
     * @param time
     * @param path
     */
    @RequestMapping("/smartGateWay/getVideoStream")
    public void getVideoStreams(HttpServletResponse response,@RequestParam(name = "time")long time,
                                @RequestParam(name = "path") String path){

        try {
            FileInputStream fis = null;
            OutputStream os = null ;
            fis = new FileInputStream(path);
            int size = fis.available(); // 得到文件大小
            byte data[] = new byte[size];
            fis.read(data); // 读数据
            fis.close();
            fis = null;
         /*   response.setContentType("video/mp4"); // 设置返回的文件类型*/
            os = response.getOutputStream();
            os.write(data);
            os.flush();
            os.close();
            os = null;

        } catch (FileNotFoundException e) {
            logger.info("未找到视频文件");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据视频路径返回视频二进制流
     * @param response
     * @param path
     */
    @RequestMapping("/smartGateWay/getImageStream")
    public void getImageStreams(HttpServletResponse response,@RequestParam(name = "path") String path){

        try {
            FileInputStream fis = null;
            OutputStream os = null ;
            fis = new FileInputStream(path);
            int size = fis.available(); // 得到文件大小
            byte data[] = new byte[size];
            fis.read(data); // 读数据
            fis.close();
            fis = null;
        /*    response.setContentType("video/mp4"); */// 设置返回的文件类型
            os = response.getOutputStream();
            os.write(data);
            os.flush();
            os.close();
            os = null;

        } catch (FileNotFoundException e) {
            logger.info("未找到视频文件");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 实体类转换为json字符串
     * @param object
     * @return
     */
    public String toJsonString(Object object){


        return JSON.toJSONString(object,filter);
    }


    /**
     * null值默认为“”
     */
    private ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if(v==null)
                return "";
            return v;
        }
    };


    public static void main(String[] args){

        String theString = "E:/电影/超脱.mkv";
        try {
            System.out.println(URLEncoder.encode(theString,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String path = "http://localhost:8080/smartGateWay/getImageStream?path=E%3A%2F%E7%94%B5%E5%BD%B1%2F%E8%B6%85%E8%84%B1.mkv";
    }




}
