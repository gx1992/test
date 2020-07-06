/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Const
 * 创建者:   高旭
 * 生成日期:     2020/6/28 19:23
 * 描述: 全局常量类
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.config.WebSocketCofig;

/**
 * 〈功能简述〉<br> 
 * 〈全局常量类〉
 *
 * @author gaoxu
 * @create 2020/6/28 19:23
 * @since 1.0.0
 */
public class Const {

    /**
     * webSocket订阅频道枚举
     */
    public enum Channel{

        CSG_ROBOT_FAULT_DATA_CHANNEL(1,"/events/115131"),
        CSG_ROBOT_PATROL_DATA_CHANNEL(2,"/events/115128");


        private final int code;
        private final String name;

        Channel(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
