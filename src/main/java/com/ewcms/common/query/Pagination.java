/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

/**
 * 分页信息
 * 
 * @author wangwei
 */
public interface Pagination {

	/**
	 * 返回页面大小
	 * 
	 * @return
	 */
   int getSize();

   /**
    * 返回页数
    * 
    * @return
    */
   int getNumber();
   
   /**
    * 返回偏移量根据页数和页面大小计算
    * 
    * @return
    */
   int getOffset();
   
   /**
    * 返回排序
    * 
    * @return
    */
   Sort getSort();
}
