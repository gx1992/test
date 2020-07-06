package com.cn.uk.model;

/**
 * 分页接口定义
 */
public interface Pagination {

    /**
     * 默认的每页记录数
     */
    long PAGE_SIZE = 10;

    /**
     * 获得起始索引
     *
     * @return 起始索引
     */
    long getStartIndex();

    /**
     * 获得结束索引
     *
     * @return 结束索引
     */
    long getEndIndex();


    /**
     * 取得每页记录数。
     * @return 每页显示的记录数。
     */
    long getPageSize();


    /**
     * 获取总记录数
     * @return 总记录数
     */
    long getTotalSize();


    /**
     * 取得当前页号
     * @return 当前页号
     */
    long getCurrentPage();


    /**
     * 返回总页面数目。
     * @return 总分页数目。
     */
    long getTotalPage() ;

}
