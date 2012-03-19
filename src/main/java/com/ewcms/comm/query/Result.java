/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.comm.query;

import java.util.List;

public interface Result<T> {

	int getNumber();
	
	int getSize();
	
	int getTotalPages();
	
	int getNumberOfElements();
	
	int getTotalNumbers();
	
	boolean hasPreviousPage();
	
	boolean isFirstPage();
	
	boolean hasNextPage();
	
	boolean isLastPage();
		
	List<T> getContent();	
	
	boolean hasContent();
	
	List<T> getFooter();
	
	boolean hasFooter();
}
