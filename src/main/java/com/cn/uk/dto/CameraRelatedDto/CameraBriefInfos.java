/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: CameraBriefInfos
 * 创建者:   高旭
 * 生成日期:     2020/4/16 15:09
 * 描述:
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.CameraRelatedDto;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author gaoxu
 * @create 2020/4/16 15:09
 * @since 1.0.0
 */
public class CameraBriefInfos {

    public  int total;
    public IndexRange indexRange;
    public CameraBriefInfoList cameraBriefInfoList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public IndexRange getIndexRange() {
        return indexRange;
    }

    public void setIndexRange(IndexRange indexRange) {
        this.indexRange = indexRange;
    }

    public CameraBriefInfoList getCameraBriefInfoList() {
        return cameraBriefInfoList;
    }

    public void setCameraBriefInfoList(CameraBriefInfoList cameraBriefInfoList) {
        this.cameraBriefInfoList = cameraBriefInfoList;
    }

    @Override
    public String toString() {
        return "CameraBriefInfos{" +
                "total=" + total +
                ", indexRange=" + indexRange +
                ", cameraBriefInfoList=" + cameraBriefInfoList.toString() +
                '}';
    }
}
