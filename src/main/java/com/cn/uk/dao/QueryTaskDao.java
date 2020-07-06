package com.cn.uk.dao;

import com.cn.uk.dto.CameraRelatedDto.Camera;
import com.cn.uk.dto.MainConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface QueryTaskDao {

    List<Map<String, Object>> queryCamera(Map<String, Object> map);

    int getHisPlanTotal(Map<String, Object> map);

    List<Map<String, Object>> queryTaskDetail(Map<String, Object> map);

    int getHisPlanDetailTotal(Map<String, Object> map);

    int queryprojectTotal(Map<String, Object> map);

    List<Map<String, Object>> queryproject(Map<String, Object> map);

    int queryprojectDetailTotal(Map<String, Object> map);

    List<Map<String, Object>> queryprojectDetail(Map<String, Object> map);

    int queryplanTotal(Map<String, Object> map);

    List<Map<String, Object>> queryplan(Map<String, Object> map);

    int updateProject(Map<String, Object> map);

    int addProject(Map<String, Object> map);

    int searchProject(Map<String, Object> map);

    int updateSetprojectdetail(Map<String, Object> map);

    int searchSetprojectdetail(Map<String, Object> map);

    int addSetprojectdetail(Map<String, Object> map);

    int updateSetplan(Map<String, Object> map);

    int searchSetplan(Map<String, Object> map);

    int addSetplan(Map<String, Object> map);

    int deleteProject(Map<String, Object> map);

    int deleteProjectItem(Map<String, Object> map);

    int deletePlan(Map<String, Object> map);

    int deleteHisPlan(Map<String, Object> map);

    int deleteHisPlanItem(Map<String, Object> map);

    List<Camera> getDeviceListByType(Map<String, Object> map);

    Map<String,Object> getSubstation();

    int getTotal(Map<String,Object> map);
    Map<String,Object> getNvrCode(Map<String,Object> gMap );

    List<Camera> getCamera();

    List<Map<String, Object>> getDevicePoints(Map<String,Object> map);

    List<Map<String, Object>> getPresetttings(Map<String,Object> map);

    List<Map<String, Object>> queryPlanItemByNo(Map<String, Object> map1);

    List<Map<String, Object>> queryPlanDefeatByNo(Map<String, Object> map1);

    Map<String, Object> getCameraAndProjectItem(Map<String, Object> map);

    Map<String, Object> getMaxTime(Map<String, Object> tMap);

    Map<String, Object> getMinTime(Map<String, Object> tMap);

    List<MainConfig> findAll(Map<String, Object> map);

    Map<String, Object> queryName(Map<String, Object> sm);

    int insertHisDhData(Map<String, Object> map);

    List<Map<String, Object>> queryDhHisData(Map<String,Object> map);

    List<Map<String, Object>> findAllDeviceIds();

    int deleteHisDHData();
}
