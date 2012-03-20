/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import java.util.Map;

/**
 * 查询集合的分页查询集合，可以获得当前页查询集合信息。
 * 
 * @author wangwei
 *
 * @param <T>
 */
public interface ResultPage<T> extends Result<T> {
	
	/**
	 * 当前页数
	 * 
	 * @return
	 */
	int getNumber();
	
    /**
     * 页面大小（一页最大记录数）
     * 
     * @return
     */
    int getSize();
	
    /**
     * 总记录数
     * 
     * @return
     */
    long getTotalElements();
    
    /**
     * 总页面数
     * 
     * @return
     */
	int getTotalPages();
	
	/**
	 * 是否有前一页
	 * 
	 * @return 如果有前一页为“true”
	 */
	boolean hasPreviousPage();
	
	/**
	 * 当前页是否是第一页
	 * 
	 * @return 如果是第一页为“true”
	 */
	boolean isFirstPage();
	
	/**
	 * 是否有下一页
	 * 
	 * @return 如果有下一页“true”
	 */
	boolean hasNextPage();
	
	/**
	 * 当前是否是最后一页
	 * 
	 * @return 如果是最后一页“true”
	 */
	boolean isLastPage();
	
	/**
	 * 当前页查询附加内容，如：sum(xxxx),agv(xxxx){@link Map}。
	 * 
	 * @return
	 */
	Map<String,?> getPageAddition();
	
	/**
     * 是否有当前页附加内容
     * 
     * @return 如果有当前页附加内容为"true"
     */
	boolean hasPageAddition();
}
