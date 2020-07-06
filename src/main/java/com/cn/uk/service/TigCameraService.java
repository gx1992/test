package com.cn.uk.service;

import com.cn.uk.dao.TigCameraDao;
import com.cn.uk.model.TigCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TigCameraService {

    @Autowired
    private TigCameraDao tigCameraDao;

    public  List<TigCamera> queryAll(Map<String,Object> params){
        return tigCameraDao.queryAll(params);
    }
    public  List<TigCamera> queryPage(Map<String,Object> params){
        return tigCameraDao.queryPage(params);
    }

}
