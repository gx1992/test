package com.cn.uk.common.pagination;

import java.util.ArrayList;
import java.util.List;


public class PageHelper<T> {
    //实体类集合
    private List<T> rows = new ArrayList<>();
    //数据总条数
    private int total;
 
    public PageHelper() {
        super();
    }
 
    public List<T> getRows() {
        return rows;
    }
 
    public void setRows(List<T> list) {
        this.rows = list;
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }
 
}
