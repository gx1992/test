package com.cn.uk.model;


public class PaginationUtils {
	 public static Page getPageParam(long totalSize, long pageSize, long currentPage) {
	        Pagination pagination = new GenericPagination(totalSize,pageSize,currentPage);
	        Page page = new Page();
	        page.setStartIndex(pagination.getStartIndex());
	        page.setEndIndex(pagination.getEndIndex());
	        page.setTotalPage(pagination.getTotalPage());
	        page.setCurrentPage(pagination.getCurrentPage());
	        page.setTotalSize(pagination.getTotalSize());
	        return page;
	    }
}
