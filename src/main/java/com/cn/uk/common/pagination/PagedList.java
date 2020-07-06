package com.cn.uk.common.pagination;

import java.util.List;


public class PagedList<T> {
    private long pageIndex;
    private long pageSize;
    private long totalCount;
    private List<T> items;

    public PagedList() {
    }

    public PagedList(long pageIndex, long pageSize, long totalCount, List<T> items) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.items = items;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
