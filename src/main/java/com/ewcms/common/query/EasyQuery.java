/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;


/**
 * 数据查询
 *
 * @author wangwei
 */
public interface EasyQuery<T>{
    
	/**
	 * 查询
	 * 
	 * @return
	 */
    Result<T> find();
    
    /**
     * 排序查询
     * 
     * @param sort
     * @return
     */
    Result<T> findSort(Sort sort);
    
    /**
     * 分页查询
     * 
     * @param page
     * @return
     */
    ResultPage<T> findPage(Pagination page);
}
