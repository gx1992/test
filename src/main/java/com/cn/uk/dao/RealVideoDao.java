package com.cn.uk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RealVideoDao {


    int queryCameraTotal(Map<String, Object> map);

    List<Map<String, Object>> queryCamera(Map<String, Object> map);

    int querynvrTotal(Map<String, Object> map);

    List<Map<String, Object>> querynvr(Map<String, Object> map);

    int querynvrchannelTotal(Map<String, Object> map);

    List<Map<String, Object>> querynvrchannel(Map<String, Object> map);
}
