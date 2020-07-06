package com.cn.uk.dao;

import com.cn.uk.model.TigCameraPreset;

import java.util.List;
import java.util.Map;

public interface TigCameraPresetDao {

    List<TigCameraPreset> queryAll(Map<String, Object> params);

}
