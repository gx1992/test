/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: QueryTaskController
 * 创建者:   高旭
 * 生成日期:     2020/4/7 19:06
 * 描述: 巡视任务接口
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.controller;

import com.alibaba.fastjson.JSON;
import com.cn.uk.dto.Cambyareanum;
import com.cn.uk.dto.CameraRelatedDto.*;
import com.cn.uk.dto.MainConfig;
import com.cn.uk.dto.RtnData;
import com.cn.uk.dto.socketCommDto.DeviceList;
import com.cn.uk.service.QueryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 〈功能简述〉<br> 
 * 〈巡视任务接口〉
 *
 * @author gaoxu
 * @create 2020/4/7 19:06
 * @since 1.0.0
 */
@RestController
public class QueryTaskController {

    @Autowired
    private QueryTaskService taskService;

    @RequestMapping("/csg.app.uk.task.querytask.post")
    public RtnData querytask(@RequestParam(required = false) Integer  pageIndex,
                              @RequestParam(required = false) Integer  pageSize,
                              @RequestParam(required = false) String startTime,
                              @RequestParam(required = false) String endTime){


        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        int totalCount = taskService.getHisPlanTotal(map);

        List<Map<String,Object>> list =  taskService.queryCamera(map);

        if(list.size()>0 ){
            loadOtherParams(pageIndex,pageSize,totalCount,list);
        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("id","");
            m.put("name","");
            m.put("runTime","");
            m.put("finishTime","");
            m.put("alarmCount","");
            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);
            list.add(m);
        }

        return RtnData.ok(list);

    }

    //查询历史结果明细
    @RequestMapping("/csg.app.uk.task.querytaskdetail.post")
    public RtnData querytaskdetail(@RequestParam(required = false) Integer pageIndex,
                                   @RequestParam(required = false) Integer pageSize,
                                   @RequestParam(required = false) String id){

        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("id",id);


        int totalCount = taskService.getHisPlanDetailTotal(map);

        List<Map<String,Object>> list = taskService.queryTaskDetail(map);

        if(list.size()>0 ){

            loadOtherParams(pageIndex,pageSize,totalCount,list);

        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("runTime","");
            m.put("name","");
            m.put("runtime","");
            m.put("value","");
            m.put("resultDesc","");
            m.put("picUrl","");
            m.put("videoUrl","");
            m.put("alarmPicUrl","");
            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);

            list.add(m);
        }

        return RtnData.ok(list);
    }



    //1.3.3	查询巡视方案   csg.app.uk.task.queryproject.post
    @RequestMapping("/csg.app.uk.task.queryproject.post")
    public RtnData queryproject(@RequestParam(required = false) int pageIndex,
                                @RequestParam(required = false) int pageSize,
                                @RequestParam(required = false) int projectNo){


        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("projectNo",projectNo);

        int totalCount = taskService.queryprojectTotal(map);

        List<Map<String,Object>> list = taskService.queryproject(map);

        if(list.size()>0 ){
            loadOtherParams(pageIndex,pageSize,totalCount,list);
        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("projectNo","");
            m.put("projectName","");
            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);

            list.add(m);
        }


        return RtnData.ok(list);
    }

    //查询方案明细 csg.app.uk.task.queryprojectdetail.post
    @RequestMapping("/csg.app.uk.task.queryprojectdetail.post")
    public RtnData queryprojectdetail(@RequestParam(required = false) int pageIndex,
                                @RequestParam(required = false) int pageSize,
                                @RequestParam(required = false) int projectNo){


        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("projectNo",projectNo);

        int totalCount = taskService.queryprojectDetailTotal(map);

        List<Map<String,Object>> list = taskService.queryprojectDetail(map);

        if(list.size()>0 ){
            loadOtherParams(pageIndex,pageSize,totalCount,list);
        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("itemNo","");
            m.put("itemName","");
            m.put("cameraNo","");
            m.put("actType","");
            m.put("actParams","");

            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);

            list.add(m);
        }


        return RtnData.ok(list);
    }

    //查询巡视方案 csg.app.uk.task.queryplan.post
    @RequestMapping("/csg.app.uk.task.queryplan.post")
    public RtnData queryplan(@RequestParam(required = false) int pageIndex,
                                      @RequestParam(required = false) int pageSize){


        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);

        int totalCount = taskService.queryplanTotal(map);

        List<Map<String,Object>> list = taskService.queryplan(map);

        if(list.size()>0 ){
            loadOtherParams(pageIndex,pageSize,totalCount,list);
        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("planNo","");
            m.put("planName","");
            m.put("projectNo","");
            m.put("planTime","");
            m.put("enabled","");

            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);

            list.add(m);
        }


        return RtnData.ok(list);
    }


    //1.3.6	设置巡视方案
    //接口名称：csg.app.uk.task.setproject.post
    @RequestMapping("/csg.app.uk.task.setproject.post")
    public RtnData setproject(@RequestParam(required = false) int projectNo,
                             @RequestParam(required = false) String projectName,
                              @RequestParam(required = false) int oper){


        Map<String,Object> map = new HashMap<>();
        map.put("projectNo",projectNo);
        map.put("projectName",projectName);


        RtnData rtnData = new RtnData();
        if(oper == 1){  //更新
            int flag = taskService.updateProject(map);
            if(flag>0){
                rtnData.setCode(0);
                rtnData.setSuccess(true);
                rtnData.setMessage("更新成功");
            }else{
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("更新失败");
            }

        }else if(oper == 2){ //新增

            int flag = taskService.searchProject(map);
            if(flag > 0 ){
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("新增失败,主键project_no冲突");
            }else{
                flag = taskService.addProject(map);
                if(flag>0){
                    rtnData.setCode(0);
                    rtnData.setSuccess(true);
                    rtnData.setMessage("新增成功");
                }else{
                    rtnData.setCode(1);
                    rtnData.setSuccess(false);
                    rtnData.setMessage("新增失败");
                }

            }

        }

        return rtnData;

    }

    //1.3.7	设置巡视方案明细
    //接口名称：csg.app.uk.task.setprojectdetail.post
    @RequestMapping("/csg.app.uk.task.setprojectdetail.post")
    public RtnData setprojectdetail(@RequestParam(required = false) int projectNo,
                                    @RequestParam(required = false) int itemNo,
                                    @RequestParam(required = false) String itemName,
                                    @RequestParam(required = false) String cameraNo,
                                    @RequestParam(required = false) int presetNo,
                                    @RequestParam(required = false) String actType,
                                    @RequestParam(required = false) String actParams,
                                    @RequestParam(required = false) int oper){

        Map<String,Object> map = new HashMap<>();
        map.put("projectNo",projectNo);
        map.put("itemNo",itemNo);
        map.put("itemName",itemName);
        map.put("cameraNo",cameraNo);
        map.put("presetNo",presetNo);
        map.put("actType",actType);
        map.put("actParams",actParams);
        map.put("oper",oper);

        RtnData rtnData = new RtnData();
        if(oper == 1){  //更新
            int flag = taskService.updateSetprojectdetail(map);
            if(flag>0){
                rtnData.setCode(0);
                rtnData.setSuccess(true);
                rtnData.setMessage("更新成功");
            }else{
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("更新失败");
            }

        }else if(oper == 2){ //新增

            int flag = taskService.searchSetprojectdetail(map);
            if(flag > 0 ){
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("新增失败,主键project_no冲突");
            }else{
                flag = taskService.addSetprojectdetail(map);
                if(flag>0){
                    rtnData.setCode(0);
                    rtnData.setSuccess(true);
                    rtnData.setMessage("新增成功");
                }else{
                    rtnData.setCode(1);
                    rtnData.setSuccess(false);
                    rtnData.setMessage("新增失败");
                }

            }

        }

        return rtnData;

    }

    //1.3.8	设置巡视计划
        //接口名称：csg.app.uk.task.setplan.post
    @RequestMapping("/csg.app.uk.task.setplan.post")
    public RtnData setplan(@RequestParam(required = false) String planName,
                           @RequestParam(required = false) int planNo,
                           @RequestParam(required = false) int projectNo,
                           @RequestParam(required = false) String runtime,
                           @RequestParam(required = false) int oper){

        Map<String,Object> map = new HashMap<>();
        map.put("planName",planName);
        map.put("planNo",planNo);
        map.put("projectNo",projectNo);
        map.put("runtime",runtime);
        map.put("oper",oper);

        RtnData rtnData = new RtnData();
        if(oper == 1){  //更新
            int flag = taskService.updateSetplan(map);
            if(flag>0){
                rtnData.setCode(0);
                rtnData.setSuccess(true);
                rtnData.setMessage("更新成功");
            }else{
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("更新失败");
            }

        }else if(oper == 2){ //新增

            int flag = taskService.searchSetplan(map);
            if(flag > 0 ){
                rtnData.setCode(1);
                rtnData.setSuccess(false);
                rtnData.setMessage("新增失败,主键project_no冲突");
            }else{
                flag = taskService.addSetplan(map);
                if(flag>0){
                    rtnData.setCode(0);
                    rtnData.setSuccess(true);
                    rtnData.setMessage("新增成功");
                }else{
                    rtnData.setCode(1);
                    rtnData.setSuccess(false);
                    rtnData.setMessage("新增失败");
                }

            }

        }

        return rtnData;

    }


    //1.3.9	删除巡视方案
    //删除巡视方案的同时会删除巡视项。
    //接口名称：csg.app.uk.task.delproject.post
    @RequestMapping("/csg.app.uk.task.delproject.post")
    @Transactional
    public RtnData delproject(@RequestParam(required = false) int projectNo){

        Map<String,Object> map = new HashMap<>();
        RtnData rtnData = new RtnData();
        map.put("projectNo",projectNo);

        int flag = taskService.deleteProject(map);
        int flag2 = taskService.deleteProjectItem(map); //删除巡视项

        if(flag>0 && flag2>0){
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMessage("删除成功，且已删除对应巡视项");
        }else{
            rtnData.setCode(1);
            rtnData.setSuccess(false);
            rtnData.setMessage("删除失败");
        }

        return rtnData;

    }

    //1.3.10	删除巡视方案明细
    //接口名称：csg.app.uk.task.delprojectdetail.post
    @RequestMapping("/csg.app.uk.task.delprojectdetail.post")
    @Transactional
    public RtnData delprojectdetail(@RequestParam(required = false) int projectNo,
                                    @RequestParam(required = false) int itemNo){

        Map<String,Object> map = new HashMap<>();
        RtnData rtnData = new RtnData();
        map.put("projectNo",projectNo);
        map.put("itemNo",itemNo);

        int flag = taskService.deleteProjectItem(map); //删除巡视项

        if(flag>0 ){
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMessage("删除成功");
        }else{
            rtnData.setCode(1);
            rtnData.setSuccess(false);
            rtnData.setMessage("删除失败");
        }

        return rtnData;

    }

    //1.3.11	删除巡视计划
    //删除巡视方案的同时会删除巡视项。
    //接口名称：csg.app.uk.task.deplanpost
    @RequestMapping("/csg.app.uk.task.deplanpost")
    public RtnData deplanpost(@RequestParam(required = false) int planNo){

        Map<String,Object> map = new HashMap<>();
        RtnData rtnData = new RtnData();
        map.put("planNo",planNo);

        int flag = taskService.deletePlan(map);
        int flag2 = taskService.deleteHisPlan(map);
        int flag3 = taskService.deleteHisPlanItem(map);

        if(flag>0 && flag2>0 && flag3>0){
            rtnData.setCode(0);
            rtnData.setSuccess(true);
            rtnData.setMessage("删除成功,且已删除对应巡视项");
        }else{
            rtnData.setCode(1);
            rtnData.setSuccess(false);
            rtnData.setMessage("删除失败");
        }

        return rtnData;

    }

    //3.1 实时浏览接口（主站post）
    // deviceType 2：摄像机设备3：语音设备4：告警设备
    //3.1.1 获取子设备列表

    @RequestMapping("/csg.app.yalian.devicelist.post")
    public DeviceList devicelist(@RequestBody Map<String,Object> map1){

        List<Map<String,Object>> cameraBriefInfo =new ArrayList<>();
        CameraBriefInfoList cameraBriefInfoList = new CameraBriefInfoList();

        //向服务器端发送数据
        Map<String, Object> map = new HashMap<String, Object>();

        String deviceType = map1.get("deviceType").toString();
        int fromIndex = Integer.parseInt(map1.get("fromIndex").toString())-1;
        int toIndex =  Integer.parseInt(map1.get("toIndex").toString());
        map.put("deviceType",deviceType);
        map.put("fromIndex",fromIndex);
        map.put("toIndex",toIndex);
        DeviceList deviceList =new DeviceList();

        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = "";
        if(m !=null){
            subStation= m.get("substation").toString();
        }


        //获取总数
        int total = taskService.getTotal(map);

        if(deviceType.equals("2")){  //摄像机设备

            deviceList.setSubstation(subStation);

            CameraBriefInfos cameraBriefInfos = new CameraBriefInfos();
            IndexRange indexRange = new IndexRange();

            indexRange.setFromIndex(fromIndex);
            indexRange.setToIndex(toIndex);
            cameraBriefInfos.setIndexRange(indexRange);

            cameraBriefInfos.setTotal(total);

            deviceList.setAudioBriefInfos(null);
            deviceList.setAlarmBriefInfos(null);


            List<Camera> list = taskService.getDeviceListByType(map);


            if(list.size()>0){

                deviceList.setResultCode(0);

                for(int i=0;i<list.size();i++){

                    Map<String,Object> mapp = new HashMap<>();
                    Camera camera = list.get(i);

                    long camera_no = camera.getCamera_no();
                    Map<String,Object> gMap = new HashMap<>();
                    gMap.put("camera_no",camera_no);
                    //获取nvr的no
                    Map<String,Object> m1 = taskService.getNvrCode(gMap);
                    String nvr_no = "";
                    if(m1 != null){
                        nvr_no = m1.get("t_nvr_no").toString();
                    }
                    mapp.put("code",camera_no);
                    mapp.put("name",camera.getCamera_name());
                    mapp.put("deviceGroupCode","");
                    mapp.put("parentCode","");
                    mapp.put("domainCode","");
                    mapp.put("deviceModelType",camera.getCamera_model() == null?"":camera.getCamera_model());
                    mapp.put("vendorType",camera.getCamera_manufacturer());
                    mapp.put("deviceFormType",1);//主设备类型 1：IPC 2：DVS 3：DVR 4：eNVR
                    mapp.put("type",camera.getType());
                    mapp.put("cameraLocation","");
                    mapp.put("cameraStatus",1);//摄像机扩展，1:正常 2：丢失
                    mapp.put("status",camera.getStatus());
                    mapp.put("netType",camera.getNet_type());
                    mapp.put("enableVoice",camera.getEnable_voice());
                    mapp.put("deviceIP",camera.getCamera_ip());
                    mapp.put("reserve",camera.getRemark());

                    mapp.put("nvrCode",nvr_no == null?"":nvr_no);
                    mapp.put("isSupportIntelligent",1);
                    mapp.put("deviceCreateTime","20200401151316");
                    mapp.put("isExDomain",1);

                    cameraBriefInfo.add(mapp);

                }
            }
            cameraBriefInfoList.setCameraBriefInfo(cameraBriefInfo);
            cameraBriefInfos.setCameraBriefInfoList(cameraBriefInfoList);
            deviceList.setCameraBriefInfos(cameraBriefInfos);

        }

        return deviceList;
    }


    //4.1.1 根据区域编码查询摄像头列表接口(递归查询不分页)
    //服务地址
    //接口名称methcambyareanumod：csg.app.yalian.cambyareanum.post
    @RequestMapping("/csg.app.yalian.cambyareanum.post")
    public Cambyareanum cambyareanum(@RequestBody Map<String,Object> mam){

        String areaNum="";
        if(mam.containsKey("areaNum")){
            areaNum = mam.get("areaNum").toString();
        }

        Cambyareanum cambyareanum = new Cambyareanum();

        Map<String,Object> map = new HashMap<>();
        RtnData rtnData = new RtnData();
        map.put("areaNum",areaNum);

        //查询数据库返回
        //获取厂站名称
        Map<String,Object> m = taskService.getSubstation();
        String subStation = m.get("substation").toString();
        String sub_no = m.get("sub_no").toString();


        cambyareanum.setSubstation(subStation);
        cambyareanum.setCode(0);
        cambyareanum.setMessage("执行成功");
        cambyareanum.setSuccess(true);



        List<Camera> clist = taskService.getCamera();
        List<Map<String,Object>> mlist =new ArrayList<>();

        Map<String,Object> mm = new HashMap<>();
        // 表计类型
        mm.put("parent", 50);
        List<MainConfig> meterTypes = taskService.findAll(mm);

        // 识别类型
        mm.clear();
        mm.put("parent", 1);
        List<MainConfig> recognitionTypes = taskService.findAll(mm);





        if (clist.size()>0){

            for(int i = 0;i<clist.size();i++){

                Camera camera = clist.get(i);
                long camera_no = camera.getCamera_no();

                Map<String,Object> map2 = new HashMap<>();
                map2.put("camera_no",camera_no);
                map2.put("camBrands",camera.getCamera_manufacturer());
                map2.put("channelAlias",camera.getCamera_name());
                map2.put("devAddress",camera.getCamera_ip());
                map2.put("devPort",camera.getSdk_port());
                map2.put("devUser",camera.getCamera_user());
                map2.put("devPwd",camera.getCamera_password());
                map2.put("channelId",camera.getCamera_no());
                map2.put("orgName","");
                map2.put("powerName",subStation);
                map2.put("powerNum",sub_no);
                map2.put("status","normal");
                map2.put("remark","");
                map2.put("cameraCode",camera.getCamera_no());

                map2.put("openTasks","ybjc");
                map2.put("camModel","");
                //摄像机类型：
                String camType = "";
                int cameraType = camera.getType();
                switch (cameraType){
                    case 0:
                        camType = "blot";break;
                    case 1:
                        camType = "blot";break;
                    case 2:
                        camType = "ptz";break;
                    case 3:
                        camType = "half_ptz";break;
                    case 5:
                        camType = "blot";break;
                    default:
                        camType ="other";break;
                }
                map2.put("camType",camType);


                Map<String,Object> cMap = new HashMap<>();
                cMap.put("camera_code",camera_no);
                //点位
                List<Map<String,Object>> dlist = taskService.getDevicePoints(cMap);
                List<Map<String,Object>> ddlist = new ArrayList<>();
                if(dlist.size()>0){
                    for(int g = 0;g<dlist.size();g++){
                        Map<String, Object> dMap = dlist.get(g);
                        Map<String,Object> dMapp = new HashMap<>();

                        dMapp.put("deviceId",dMap.get("deviceID"));
                        dMapp.put("pointName", dMap.get("name"));
                        dMapp.put("id",dMap.get("id"));
                        dMapp.put("cameraId",camera_no);

                        int recognition_type = (int)dMap.get("recognition_type");
                        int temp_recog = 0;

                        for(int gg = 0;gg<recognitionTypes.size();gg++){
                            MainConfig mainConfig = recognitionTypes.get(gg);
                            int intValue = mainConfig.getIntValue();
                            if((intValue & recognition_type) > 0 ){
                                temp_recog =(int)mainConfig.getId();

                                switch (temp_recog){
                                    case 2:temp_recog = 1;break;
                                    case 3:temp_recog = 2;break;
                                    case 4:temp_recog = 3;break;
                                    case 5:temp_recog = 3;break;
                                    case 6:temp_recog = 4;break;
                                    case 7:temp_recog = 5;break;
                                }
                            }
                        }
                        dMapp.put("discernType",temp_recog);

                        int temp_meter = 0;
                        int meter_type = (int)dMap.get("meter_type");
                        for(int xx = 0;xx<meterTypes.size();xx++){
                            MainConfig mainConfig = recognitionTypes.get(xx);
                            int intValue = mainConfig.getIntValue();
                            if((intValue & meter_type) > 0 ){
                                temp_meter =(int)mainConfig.getId();

                                switch (temp_meter){
                                    case 51:temp_meter = 1;break;
                                    case 57:temp_meter = 2;break;
                                    case 54:temp_meter = 3;break;
                                    case 56:temp_meter = 4;break;
                                    case 55:temp_meter = 5;break;
                                    case 52:temp_meter = 6;break;
                                }

                            }
                        }

                        dMapp.put("meterType",temp_meter);

                        ddlist.add(dMapp);
                    }
                }
                map2.put("cameraPointList",ddlist);

                //预置位
                List<Map<String,Object>> plist = taskService.getPresetttings(cMap);
                List<Map<String,Object>> pplist = new ArrayList<>();
                if(plist.size()>0){
                    for(int g = 0;g<plist.size();g++){

                        Map<String, Object> pMap = plist.get(g);
                        Map<String,Object> pMapp = new HashMap<>();
                        pMapp.put("cameraId",camera_no);
                        pMapp.put("presettingCode",pMap.get("preset_no"));
                        pMapp.put("presettingName",pMap.get("preset_name"));
                        pMapp.put("id",pMap.get("preset_no"));

                        //预置位识别类型(1：刀闸（横杆分合），2：分合显示球，3：区域监控，4：圆表盘（彩色），5：圆表盘（避雷KATE），6：圆表盘（黑白），7：已储能，8：接地刀闸拐臂，9：控制柜)
                        pMapp.put("discernType",2);

                        pplist.add(pMapp);
                    }
                }
                map2.put("cameraPresettingList",pplist);

                mlist.add(map2);
            }
            cambyareanum.setData(mlist);
        }
        return cambyareanum;
    }

    //7.1.1 查询所有动环设备信息
    //接口名称method：csg.app.yalian.ringair.post
    @RequestMapping("/csg.app.yalian.ringair.post")
    public Map<String,Object> ringair(){

        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        Map<String,Object> map3 = new HashMap<>();
        Map<String,Object> map4 = new HashMap<>();
        map.put("code","1");
        map.put("msg","查询成功");

        List<Map<String,Object>> list = new ArrayList<>();

        map2.put("euid","1202");
        map2.put("location","220KV母联开关端子箱");
        map2.put("type","");
        list.add(map2);
        map3.put("euid","1100");
        map3.put("location","220KV母联开关");
        map3.put("type","");
        list.add(map3);
        map4.put("euid","1200");
        map4.put("location","220KV刀闸开关");
        map4.put("type","");
        list.add(map4);
        map.put("data",list);

        return map;

    }



    //通用方法，将查询的结果改写成接口所需的形式
    private void loadOtherParams(int pageIndex,int pageSize,int totalCount, List<Map<String, Object>> list) {

        int pageCount = (int)Math.ceil(totalCount/list.size());
        for(int i =0;i<list.size();i++){
            Map<String,Object> m = new HashMap<>();
            m = list.get(i);
            m.put("totalCount",totalCount);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",pageCount);

        }

    }



}
