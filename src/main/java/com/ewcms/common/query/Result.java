/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import java.util.List;
import java.util.Map;

/**
 * 查询结果集合,可以获得查询集合的信息。
 * 
 * @author wangwei
 *
 * @param <T>
 */
public interface Result<T> extends Iterable<T> {

	/**
	 * 查询记录数
	 * 
	 * @return
	 */
    int getNumberOfElements();
	
	/**
	 * 查询内容{@link List}
	 * 
	 * @return
	 */
	List<T> getContent();	
	
	/**
	 * 是否有内容
	 * 
	 * @return 如果有内容为“true”
	 */
	boolean hasContent();
	 
	/**
	 * 查询附加内容{@link Map}，如指定属性的求和或平均数等。
	 *  
	 * @return
	 */
    Map<String,?> getAddition();
	
    /**
     * 是否有附加内容
     * 
     * @return 如果有附加内容为"true"
     */
	boolean hasAddition();
}
