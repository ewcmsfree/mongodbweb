/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试{@link ResultPageImpl}
 * 
 * @author wangwei
 */
public class ResultPageImplTest {

	@Test
	public void testGetNumber(){
		Pagination page = new PaginationImpl(20,1);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,0L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.getNumber() == 1);
	}
	
	@Test
	public void testGetSize(){
		Pagination page = new PaginationImpl(20,1);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,0L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.getSize() == 20);
	}
	
	@Test
	public void testGetTotalElements(){
		Pagination page = new PaginationImpl(20,1);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.getTotalElements() == 200L);
	}
	
	@Test
	public void testGetTotalPages(){
		Pagination page = new PaginationImpl(20,1);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.getTotalPages() == 10);
		resultPage = new ResultPageImpl<Object>(page,201L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.getTotalPages() == 11);
		resultPage = new ResultPageImpl<Object>(page,179L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.getTotalPages() == 9);
	}
	
	@Test
	public void testHasPreviousPage(){
		Pagination page = new PaginationImpl(20,0);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertFalse(resultPage.hasPreviousPage());
		
		page = new PaginationImpl(20,1);
		resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.hasPreviousPage());
	}
	
	@Test
	public void testIsFristPage(){
		Pagination page = new PaginationImpl(20,0);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.isFirstPage());
		
		page = new PaginationImpl(20,1);
		resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertFalse(resultPage.isFirstPage());
	}
	
	@Test
	public void testHasNextPage(){
		Pagination page = new PaginationImpl(20,0);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.hasNextPage());
		
		page = new PaginationImpl(20,9);
		resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertFalse(resultPage.hasNextPage());
	}
	
	@Test
	public void testIsLastPage(){
		Pagination page = new PaginationImpl(20,0);
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertFalse(resultPage.isLastPage());
		
		page = new PaginationImpl(20,9);
		resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>());
		Assert.assertTrue(resultPage.isLastPage());
	}
	
	@Test
	public void testGetPageAddition(){
		Pagination page = new PaginationImpl(20,0);
		Map<String,Object> pageAddition = new HashMap<String,Object>();
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>(),pageAddition);
		Assert.assertTrue(resultPage.getPageAddition() == pageAddition);
	}
	
	@Test
	public void testHasPageAddition(){
		Pagination page = new PaginationImpl(20,0);
		Map<String,Object> pageAddition = new HashMap<String,Object>();
		ResultPageImpl<Object> resultPage = new ResultPageImpl<Object>(page,200L,new ArrayList<Object>(),pageAddition);
		Assert.assertFalse(resultPage.hasPageAddition());
		
		pageAddition.put("name", new Object());
		Assert.assertTrue(resultPage.hasPageAddition());
	}
}
