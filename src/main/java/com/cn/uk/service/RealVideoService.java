/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: RealVideoService
 * 创建者:   高旭
 * 生成日期:     2020/4/8 17:25
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.service;

import com.cn.uk.dao.RealVideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/4/8 17:25
 * @since 1.0.0
 */
@Service
public class RealVideoService {
    @Autowired
    public RealVideoDao realVideoDao;

    public int queryCameraTotal(Map<String, Object> map) {
        return realVideoDao.queryCameraTotal(map);
    }

    public List<Map<String, Object>> queryCamera(Map<String, Object> map) {

        return realVideoDao.queryCamera(map);
    }

    public int querynvrTotal(Map<String, Object> map) {
        return realVideoDao.querynvrTotal(map);
    }

    public List<Map<String, Object>> querynvr(Map<String, Object> map) {
        return realVideoDao.querynvr(map);
    }

    public int querynvrchannelTotal(Map<String, Object> map) {
        return realVideoDao.querynvrchannelTotal(map);
    }

    public List<Map<String, Object>> querynvrchannel(Map<String, Object> map) {
        return realVideoDao.querynvrchannel(map);
    }
}
