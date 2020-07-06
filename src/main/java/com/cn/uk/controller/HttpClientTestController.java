package com.cn.uk.controller;


import com.cn.uk.config.AppContext;
import com.cn.uk.config.DoorAccessConfig;
import com.cn.uk.config.Layout;
import com.cn.uk.dto.DoorAccessDto.*;
import com.cn.uk.dto.RtnData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HttpClient测试类
 */
@RestController
@Layout(value = "layouts/default")
public class HttpClientTestController {
    public static final Logger logger = LoggerFactory.getLogger(HttpClientTestController.class);


    @PostMapping("/api/base/depts")
    public RtnData depts(@RequestBody Map<String,Object> map){

        //pageIndex:1,pageSize:10,deptName="测试1",parentId="00000000-0000-0000-0000-000000000000",parentId为空，默认查所有

        RtnData rtnData = new RtnData();
        System.out.println(map);

        Map<String,Object> map2 = new HashMap<>();

        List<DeptsDto> list = new ArrayList<>();
        DeptsDto deptsDto = new DeptsDto();
        deptsDto.setDeptId("123");
        deptsDto.setDeptName("测试1");
        deptsDto.setDeptNo("123-123-123");
        deptsDto.setParentId("111");
        deptsDto.setRemark("");
        list.add(deptsDto);
        DeptsDto deptsDto2 = new DeptsDto();
        deptsDto2.setDeptId("123333");
        deptsDto2.setDeptName("测试2");
        deptsDto2.setDeptNo("123-123-12333");
        deptsDto2.setParentId("222");
        deptsDto2.setRemark("");
        list.add(deptsDto2);

        map2.put("depts",list);
        map2.put("pageIndex",1);
        map2.put("pageSize",9999);
        map2.put("pageCount",1);
        map2.put("totalCount",2);

        rtnData.setData(map2);
        rtnData.setMsg("ok");
        rtnData.setCode(0);
        rtnData.setSubstation("ZS");
        return rtnData;
    }


    @PostMapping("/api/base/personlist")
    public RtnData personlist(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();
        Map<String,Object> map2 = new HashMap<>();

        System.out.println(map);

        List<PersonsDto> listt = new ArrayList<>();
        PersonsDto personsDto = new PersonsDto();
        personsDto.setAddress(null);
        personsDto.setCredentialType("IDENTITY");
        personsDto.setDeptId("5a9dc528-e7ad-4952-9f21-a885b6564a1c");
        personsDto.setDeptName("捷顺科技");
        personsDto.setEmail(null);
        personsDto.setIdentityNo(null);
        personsDto.setMobile("13912345678");
        personsDto.setPersonId("4b635adb-2a5f-4265-96b8-7fbc52a1ab01");
        personsDto.setPersonGender(1);
        personsDto.setPersonNo("000021");
        personsDto.setPersonName("张三");
        personsDto.setPersonPhoto("");
        personsDto.setRemark(null);
        personsDto.setRoomNo(null);
        personsDto.setTel1(null);
        personsDto.setTel2(null);
        personsDto.setTenementType(1);


        List<Vehicle> list = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateColor("1");
        vehicle.setPlateNumber("2");
        vehicle.setRemark("");
        vehicle.setVehicleStatus(1);
        vehicle.setVehicleBrand("1");
        vehicle.setVehicleColor(1);
        list.add(vehicle);

        personsDto.setVehicleList(list);

        listt.add(personsDto);

        map2.put("personList",listt);

        map2.put("pageIndex",1);
        map2.put("pageSize",1);
        map2.put("pageCount",1);
        map2.put("totalCount",1);

        rtnData.setData(map2);
        rtnData.setMsg("ok");
        rtnData.setCode(0);
        rtnData.setSubstation("ZS");
        return rtnData;
    }



    @PostMapping("/api/base/person")
    public RtnData person(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();
        Map<String,Object> map2 = new HashMap<>();

        System.out.println(map);

        PersonsDto personsDto = new PersonsDto();
        personsDto.setAddress(null);
        personsDto.setCredentialType("IDENTITY");
        personsDto.setDeptId("5a9dc528-e7ad-4952-9f21-a885b6564a1c");
        personsDto.setDeptName("捷顺科技");
        personsDto.setEmail(null);
        personsDto.setIdentityNo(null);
        personsDto.setMobile("13912345678");
        personsDto.setPersonId("4b635adb-2a5f-4265-96b8-7fbc52a1ab01");
        personsDto.setPersonGender(1);
        personsDto.setPersonNo("000021");
        personsDto.setPersonName("张三");
        personsDto.setPersonPhoto("");
        personsDto.setRemark(null);
        personsDto.setRoomNo(null);
        personsDto.setTel1(null);
        personsDto.setTel2(null);
        personsDto.setTenementType(1);


        List<Vehicle> list = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateColor("1");
        vehicle.setPlateNumber("2");
        vehicle.setRemark("");
        vehicle.setVehicleStatus(1);
        vehicle.setVehicleBrand("1");
        vehicle.setVehicleColor(1);
        list.add(vehicle);

        personsDto.setVehicleList(list);

        rtnData.setData(personsDto);
        rtnData.setMsg("ok");
        rtnData.setCode(0);
        rtnData.setSubstation("ZS");
        return rtnData;
    }

    @PostMapping("/api/base/devices")
    public RtnData devices(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();
        Map<String,Object> map2 = new HashMap<>();

        System.out.println(map);

        List<Devices> list = new ArrayList<>();
        Devices devices = new Devices();
        devices.setDeviceGateway("172.16.20.1");
        devices.setDeviceGuid("69c22997-3b48-44e1-867c-1190bac3b911");
        devices.setDeviceId("1684982784");
        devices.setDeviceIoType(1);
        devices.setDeviceIp("172.16.20.124");
        devices.setDeviceName("速通 II 停车场 124");
        devices.setDeviceNetmask("255.255.252.0");
        devices.setParentId("284086272");
        devices.setRemark(null);

        list.add(devices);
        Map<String,Object> mapp = new HashMap<>();
        mapp.put("devices",list);

        rtnData.setData(mapp);
        rtnData.setMsg("ok");
        rtnData.setCode(0);
        rtnData.setSubstation("ZS");
        return rtnData;
    }


    @PostMapping("/api/park/inparkingrecord")
    public RtnData inparkingrecord(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();

        System.out.println(map);

        List<Records> list = new ArrayList<>();
        Records records = new Records();

        records.setInDeviceId("98762087123e483fa96f176d7eaefe52");
        records.setInDeviceName("入口");
        records.setInImage("http://jielink.jieshun.com/api/base/image?id=1234.jpg");
        records.setInRecordId("26ecaf80470d490786a4f7e3eca832b3");
        records.setInTime("2018-03-07 10:01:00");
        records.setParkId("34ebff70583f476986a4f7e3ffa742c5");
        records.setPlateColor("blue");
        records.setPlateNumber("粤-B12345");
        records.setSealName("临时用户 A");
        records.setStationOperator("admin");

        list.add(records);
        Map<String,Object> mapp = new HashMap<>();
        mapp.put("records",list);

        mapp.put("pageIndex",1);
        mapp.put("pageSize",1);
        mapp.put("pageCount",1);
        mapp.put("totalCount",1);

        rtnData.setData(mapp);
        rtnData.setMsg("ok");
        rtnData.setCode(0);
        rtnData.setSubstation("ZS");
        return rtnData;
    }


    @PostMapping("/api/park/blackwhitelist")
    public RtnData blackwhitelist(@RequestBody Map<String,Object> map){

        RtnData rtnData = new RtnData();

        System.out.println(map);

        Map<String,Object> mapp = new HashMap<>();
        mapp.put("numberPlate","粤Y8888");
        mapp.put("registrationDate","2019.1.1");

        rtnData.setData(mapp);
        rtnData.setMsg("ok");
        rtnData.setCode(0);
        rtnData.setSubstation("ZS");
        return rtnData;
    }


    //测试：获取key
    @PostMapping("/api/internal/sign")
    public RtnData getKey(@RequestBody  @RequestParam(required = false) Map<String,Object> map){

        Map<String,Object> mapp = new HashMap<>();

        mapp.put("appId","app01");
        mapp.put("key","9b9be1a7-9967-11e9-96a6-7ec3d9afa1de");

        return  RtnData.ok(mapp);
    }


}
