package com.cn.uk.dao;

import com.cn.uk.model.TigCameraSetup;

import java.util.List;
import java.util.Map;

public interface TigCameraSetupDao {

    List<TigCameraSetup> queryAll(Map<String,Object> params);
}
