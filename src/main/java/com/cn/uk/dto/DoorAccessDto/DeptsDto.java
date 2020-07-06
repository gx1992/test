/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: DeptsDto
 * 创建者:   高旭
 * 生成日期:     2020/5/18 8:52
 * 描述: 部门信息表
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.dto.DoorAccessDto;

/**
 * 〈功能简述〉<br> 
 * 〈部门信息表〉
 *
 * @author gaoxu
 * @create 2020/5/18 8:52
 * @since 1.0.0
 */
public class DeptsDto {

      private String deptId;
      private String deptNo;
      private String deptName;
      private String parentId;
      private String remark;


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
