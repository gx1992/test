/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: QueryTaskService
 * 创建者:   高旭
 * 生成日期:     2020/4/7 19:13
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.service;

import com.cn.uk.dao.QueryTaskDao;
import com.cn.uk.dto.CameraRelatedDto.Camera;
import com.cn.uk.dto.MainConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/4/7 19:13
 * @since 1.0.0
 */
@Service
public class QueryTaskService {

    @Autowired
    public QueryTaskDao queryTaskDao;

    public List<Map<String, Object>> queryCamera(Map<String, Object> map) {
        return queryTaskDao.queryCamera(map);
    }

    public int getHisPlanTotal(Map<String, Object> map) {
        return queryTaskDao.getHisPlanTotal(map);
    }

    public List<Map<String, Object>> queryTaskDetail(Map<String, Object> map) {
        return queryTaskDao.queryTaskDetail(map);
    }

    public int getHisPlanDetailTotal(Map<String, Object> map) {
        return queryTaskDao.getHisPlanDetailTotal(map);
    }

    public int queryprojectTotal(Map<String, Object> map) {

        return queryTaskDao.queryprojectTotal(map);
    }

    public List<Map<String, Object>> queryproject(Map<String, Object> map) {
        return queryTaskDao.queryproject(map);
    }

    public int queryprojectDetailTotal(Map<String, Object> map) {
        return queryTaskDao.queryprojectDetailTotal(map);
    }

    public List<Map<String, Object>> queryprojectDetail(Map<String, Object> map) {
        return queryTaskDao.queryprojectDetail(map);
    }

    public int queryplanTotal(Map<String, Object> map) {
        return queryTaskDao.queryplanTotal(map);
    }

    public List<Map<String, Object>> queryplan(Map<String, Object> map) {
        return queryTaskDao.queryplan(map);
    }

    public int updateProject(Map<String, Object> map) {
        return queryTaskDao.updateProject(map);
    }

    public int addProject(Map<String, Object> map) {
        return queryTaskDao.addProject(map);
    }

    public int searchProject(Map<String, Object> map) {
        return queryTaskDao.searchProject(map);
    }

    public int updateSetprojectdetail(Map<String, Object> map) {
        return queryTaskDao.updateSetprojectdetail(map);
    }

    public int searchSetprojectdetail(Map<String, Object> map) {
        return queryTaskDao.searchSetprojectdetail(map);
    }

    public int addSetprojectdetail(Map<String, Object> map) {
        return queryTaskDao.addSetprojectdetail(map);
    }

    public int updateSetplan(Map<String, Object> map) {
        return queryTaskDao.updateSetplan(map);
    }

    public int searchSetplan(Map<String, Object> map) {
        return queryTaskDao.searchSetplan(map);
    }

    public int addSetplan(Map<String, Object> map) {
        return queryTaskDao.addSetplan(map);
    }

    public int deleteProject(Map<String, Object> map) {
        return queryTaskDao.deleteProject(map);
    }

    public int deleteProjectItem(Map<String, Object> map) {
        return queryTaskDao.deleteProjectItem(map);
    }

    public int deletePlan(Map<String, Object> map) {
        return queryTaskDao.deletePlan(map);
    }

    public int deleteHisPlan(Map<String, Object> map) {
        return queryTaskDao.deleteHisPlan(map);
    }

    public int deleteHisPlanItem(Map<String, Object> map) {
        return queryTaskDao.deleteHisPlanItem(map);
    }

    public List<Camera> getDeviceListByType(Map<String, Object> map) {

        return queryTaskDao.getDeviceListByType(map);
    }
    public Map<String,Object> getSubstation() {

        return queryTaskDao.getSubstation();
    }


    public int getTotal(Map<String,Object> map) {

        return queryTaskDao.getTotal(map);
    }

    public Map<String,Object> getNvrCode(Map<String,Object> gMap ) {

        return queryTaskDao.getNvrCode(gMap);
    }


    public List<Camera> getCamera() {

        return queryTaskDao.getCamera();
    }

    public List<Map<String, Object>> getDevicePoints(Map<String,Object> map) {

        return queryTaskDao.getDevicePoints(map);
    }
    public List<Map<String, Object>> getPresetttings(Map<String,Object> map) {

        return queryTaskDao.getPresetttings(map);
    }

    public List<Map<String, Object>> queryPlanItemByNo(Map<String, Object> map1) {

        return queryTaskDao.queryPlanItemByNo(map1);
    }

    public List<Map<String, Object>> queryPlanDefeatByNo(Map<String, Object> map1) {

        return queryTaskDao.queryPlanDefeatByNo(map1);
    }

    public Map<String, Object> getCameraAndProjectItem(Map<String, Object> map) {

        return queryTaskDao.getCameraAndProjectItem(map);
    }

    public Map<String, Object> getMaxTime(Map<String, Object> tMap) {

        return queryTaskDao.getMaxTime(tMap);
    }

    public Map<String, Object> getMinTime(Map<String, Object> tMap) {
        return queryTaskDao.getMinTime(tMap);
    }

    public List<MainConfig> findAll(Map<String, Object> map) {

        return queryTaskDao.findAll(map);
    }

    public Map<String, Object> queryName(Map<String, Object> sm) {

        return queryTaskDao.queryName(sm);
    }

    public int insertHisDhData(Map<String, Object> map) {

        return queryTaskDao.insertHisDhData(map);
    }

    public List<Map<String, Object>> queryDhHisData(Map<String,Object> map) {

        return queryTaskDao.queryDhHisData(map);
    }

    public List<Map<String, Object>> findAllDeviceIds() {

        return queryTaskDao.findAllDeviceIds();
    }

    public int deleteHisDHData() {

        return queryTaskDao.deleteHisDHData();
    }
}
