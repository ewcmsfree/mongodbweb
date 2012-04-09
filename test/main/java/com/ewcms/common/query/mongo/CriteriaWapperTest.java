/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import org.junit.Assert;
import org.junit.Test;

import com.mongodb.DBObject;

/**
 * 单元测试{@link CriteriaWapper}
 * 
 * @author wangwei
 */
public class CriteriaWapperTest {

	@Test
	public void testNewCriteraWapperByKey(){
		CriteriaWapper criteria = new CriteriaWapper("name");
		criteria.is("test");
		DBObject db = criteria.getCriteriaObject();
		Assert.assertEquals(db.keySet().size(), 1);
		Assert.assertEquals(db.keySet().iterator().next(), "name");
	}
	
	@Test
	public void testAnd(){
		CriteriaWapper criteria = new CriteriaWapper("name");
		criteria.is("test");
		criteria = criteria.and("sex");
		criteria.is("man");
		DBObject db = criteria.getCriteriaObject();
		Assert.assertEquals(db.keySet().size(), 2);
	}
	
	@Test
	public void testGetSingleCriteriaObjectNoneCriteria(){
		CriteriaWapper criteria = new CriteriaWapper("name");
		DBObject db = criteria.getCriteriaObject();
		Assert.assertTrue(db.keySet().isEmpty());
	}
	
	@Test
	public void testGetSingleCriteriaObjectValueIsNull(){
		CriteriaWapper criteria = new CriteriaWapper("name");
		criteria.is(null);
		DBObject db = criteria.getCriteriaObject();
		Assert.assertFalse(db.keySet().isEmpty());
	}
}
