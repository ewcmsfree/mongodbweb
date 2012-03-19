/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.comm.query;

public interface Pagination {

   static final int DEFAULT_SIZE = 20;
   
   static final int DEFAULT_NUMBER = 0;
    
   Pagination setSize(int row);
    
   Pagination setNumber(int page);
   
   Pagination setTotal(int total);
}
