/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.comm.query;

/**
 *
 * @author wangwei
 */
public interface Query<T> extends Pagination {
    
    Result<T> find();
}
