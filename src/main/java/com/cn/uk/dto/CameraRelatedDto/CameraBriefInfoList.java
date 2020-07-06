/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: CameraBriefInfoList
 * 创建者:   高旭
 * 生成日期:     2020/4/16 15:11
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.CameraRelatedDto;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/4/16 15:11
 * @since 1.0.0
 */
public class CameraBriefInfoList implements Serializable {
    public List<Map<String,Object>> cameraBriefInfo;

    public List<Map<String, Object>> getCameraBriefInfo() {
        return cameraBriefInfo;
    }

    public void setCameraBriefInfo(List<Map<String, Object>> cameraBriefInfo) {
        this.cameraBriefInfo = cameraBriefInfo;
    }
}
