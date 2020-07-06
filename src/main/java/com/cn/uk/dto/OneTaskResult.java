/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: OneTaskResult
 * 创建者:   高旭
 * 生成日期:     2020/5/24 10:05
 * 描述: 6.3 一键巡视任务推送结果
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈6.3 一键巡视任务推送结果〉
 *
 * @author gaoxu
 * @create 2020/5/24 10:05
 * @since 1.0.0
 */
public class OneTaskResult {

    private String message;
    private String       type;
    private String subStation;
    private List<TaskResultAlarmMsg> alarmMsg;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TaskResultAlarmMsg> getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(List<TaskResultAlarmMsg> alarmMsg) {
        this.alarmMsg = alarmMsg;
    }

    public String getSubStation() {
        return subStation;
    }

    public void setSubStation(String subStation) {
        this.subStation = subStation;
    }
}
