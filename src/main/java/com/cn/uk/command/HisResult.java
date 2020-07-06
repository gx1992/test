/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: HisResult
 * 创建者:   高旭
 * 生成日期:     2020/6/20 16:52
 * 描述: 历史数据xml转字符串实体
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.command;

/**
 * 〈功能简述〉<br> 
 * 〈历史数据xml转字符串实体〉
 *
 * @author gaoxu
 * @create 2020/6/20 16:52
 * @since 1.0.0
 */
public class HisResult {

    private String data;
    private HisPrj prj;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HisPrj getPrj() {
        return prj;
    }

    public void setPrj(HisPrj prj) {
        this.prj = prj;
    }
}
