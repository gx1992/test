package com.cn.uk.dto.CameraRelatedDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushDataDto {

    private String channel;
    private String id;
    private String clientId;
    private Object data;

    public static Object buildHandShakeDataDto(String id,String client,String sn,String text){

        PushDataDto pushDataDto = new PushDataDto();
        pushDataDto.setChannel("/meta/handshake");
        pushDataDto.setId(id);
        pushDataDto.setClientId(client);
        List<PushDataDto> pushDataDtos = new ArrayList<>();
        pushDataDtos.add(pushDataDto);
        return pushDataDtos;
    }

    public static Object buildDataDto(String sn,String text){
        PushDataDto pushDataDto = new PushDataDto();
        pushDataDto.setId("nxyk");
        pushDataDto.setChannel("/meta/handshake");
        pushDataDto.setClientId("smartGateWay");
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> innerData = new HashMap<>();
        Map<String,Object> source = new HashMap<>();
        source.put("name", sn);
        innerData.put("source", source);
        innerData.put("text", text);
        data.put("data", innerData);
        pushDataDto.setData(data);
        List<PushDataDto> pushDataDtos = new ArrayList<>();
        pushDataDtos.add(pushDataDto);
       return  pushDataDtos;
    }


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
