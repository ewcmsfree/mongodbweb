/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试{@link PaginationImpl}
 * 
 * @author wangwei
 */
public class PaginationImplTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNewPaginationSizeIsZore(){
		new PaginationImpl(0,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewPaginationSizeLtZore(){
		new PaginationImpl(-1,1);
	}
	
	@Test
	public void testNewPaginationNumberIsZore(){
		new PaginationImpl(1,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewPaginationNumberLtZore(){
		new PaginationImpl(1,-1);
	}
	
	@Test
	public void testGetSize(){
		PaginationImpl pagination = new PaginationImpl(1,0);
		Assert.assertTrue(pagination.getSize() == 1);
	}
	
	@Test
	public void testGetNumber(){
		PaginationImpl pagination = new PaginationImpl(1,0);
		Assert.assertTrue(pagination.getNumber() == 0);
	}
}
