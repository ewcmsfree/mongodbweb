/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 单元测试{@link ResultImpl}
 * 
 * @author wangwei
 */
public class ResultImplTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNewResultContentIsNull(){
		new ResultImpl<Object>(null);
	}
	
	@Test
	public void testGetNumberOfElements(){
		List<Object> content = new ArrayList<Object>();
		content.add(new Object());
		content.add(new Object());
		
		ResultImpl<Object> result =new ResultImpl<Object>(content);
		Assert.assertTrue(result.getNumberOfElements()==2);
	}
	
	@Test
	public void testHasContent(){
		List<Object> content = new ArrayList<Object>();
		
		ResultImpl<Object> result = new ResultImpl<Object>(content);
		Assert.assertFalse(result.hasContent());
		
		content.add(new Object());
		Assert.assertTrue(result.hasContent());
	}
	
	@Test
	public void testGetContent(){
		List<Object> content = new ArrayList<Object>();
		content.add(new Object());
		
		ResultImpl<Object> result =new ResultImpl<Object>(content);
		Assert.assertTrue(result.getContent() == content);
	}
	
	@Test
	public void testHasAddition(){
		ResultImpl<Object> result = new ResultImpl<Object>(new ArrayList<Object>(),null);
		Assert.assertFalse(result.hasAddition());
		
		result = new ResultImpl<Object>(new ArrayList<Object>(),new HashMap<String,Object>());
		Assert.assertFalse(result.hasAddition());
		
		Map<String,Object> addition = new HashMap<String,Object>();
		addition.put("money", 10000);
		result = new ResultImpl<Object>(new ArrayList<Object>(),addition);
		Assert.assertTrue(result.hasAddition());
	}
	
	@Test
	public void testGetAddition(){
		Map<String,Object> addition = new HashMap<String,Object>();
		addition.put("money", 10000);
		ResultImpl<Object> result = new ResultImpl<Object>(new ArrayList<Object>(),addition);
		Assert.assertTrue(result.getAddition() == addition);
	}
}
