/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: RealVideoController
 * 创建者:   高旭
 * 生成日期:     2020/4/8 17:23
 * 描述: 实时视频接口
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.controller;


import com.cn.uk.dto.RtnData;
import com.cn.uk.service.RealVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈实时视频接口〉
 *
 * @author gaoxu
 * @create 2020/4/8 17:23
 * @since 1.0.0
 */
@RestController
public class RealVideoController {

    @Autowired
    public RealVideoService videoService;


    //1.4.1	查询相机设备信息
    //接口名称：csg.app.uk.querycamera.post
    @RequestMapping("/csg.app.uk.querycamera.post")
    public RtnData queryCameraInfo(@RequestParam(required = false) int pageIndex,
                               @RequestParam(required = false) int pageSize,
                               @RequestParam(required = false) String cameraNo){

        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("cameraNo",cameraNo);

        int totalCount = videoService.queryCameraTotal(map);

        List<Map<String,Object>> list =  videoService.queryCamera(map);

        if(list.size()>0 ){
            loadOtherParams(pageIndex,pageSize,totalCount,list);
        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("cameraNo","");
            m.put("cameraName","");
            m.put("manufacturer","");
            m.put("model","");
            m.put("ip","");
            m.put("port","");
            m.put("type","");
            m.put("status","");

            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);

            list.add(m);
        }
        return RtnData.ok(list);
    }

    //1.4.2	查询NVR设备信息
    //接口名称：csg.app.uk.querynvr.post
    @RequestMapping("/csg.app.uk.querynvr.post")
    public RtnData querynvr(@RequestParam(required = false) int pageIndex,
                                   @RequestParam(required = false) int pageSize,
                                   @RequestParam(required = false) String nvrNo){

        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("nvrNo",nvrNo);

        int totalCount = videoService.querynvrTotal(map);

        List<Map<String,Object>> list =  videoService.querynvr(map);

        if(list.size()>0 ){
            loadOtherParams(pageIndex,pageSize,totalCount,list);
        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("nvrNo","");
            m.put("nvrName","");
            m.put("manufacturer","");
            m.put("model","");
            m.put("ip","");
            m.put("port","");

            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);

            list.add(m);
        }
        return RtnData.ok(list);
    }

    //1.4.3	查询NVR通道信息
    //接口名称：csg.app.uk.querynvrchannel.post
    @RequestMapping("/csg.app.uk.querynvrchannel.post")
    public RtnData querynvrchannel(@RequestParam(required = false) int pageIndex,
                            @RequestParam(required = false) int pageSize,
                            @RequestParam(required = false) String nvrNo){

        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("nvrNo",nvrNo);

        int totalCount = videoService.querynvrchannelTotal(map);

        List<Map<String,Object>> list =  videoService.querynvrchannel(map);

        if(list.size()>0 ){
            loadOtherParams(pageIndex,pageSize,totalCount,list);
        }else{
            Map<String,Object> m = new HashMap<>();
            list = new ArrayList<>();

            m.put("nvrNo","");
            m.put("channelNo","");
            m.put("channelName","");
            m.put("cameraNo","");
            // -------------
            m.put("totalCount",0);
            m.put("pageIndex",pageIndex);
            m.put("pageSize",pageSize);
            m.put("pageCount",0);

            list.add(m);
        }
        return RtnData.ok(list);
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
