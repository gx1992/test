package com.cn.uk.dao;

import com.cn.uk.model.TigCamera;

import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

public interface TigCameraDao {

    List<TigCamera> queryAll(Map<String,Object> params);
    List<TigCamera> queryPage(Map<String,Object> params);

}
