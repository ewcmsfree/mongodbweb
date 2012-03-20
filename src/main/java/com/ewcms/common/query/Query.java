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
public interface Query<T>{
    
	/**
	 * 查询数据
	 * 
	 * @return
	 */
    Result<T> find();
    
    /**
     * 查询分页数据
     * 
     * @param page
     * @return
     */
    ResultPage<T> find(Pagination page);
}
