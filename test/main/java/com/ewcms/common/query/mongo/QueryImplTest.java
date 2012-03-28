/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewcms.mongo.demo.model.Person;

/**
 * 单元测试{@link QueryImpl}
 * 
 * @author wangwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class QueryImplTest {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Test
	public void testNewQueryNotWhere(){
		QueryImpl<Person> query = 
				(QueryImpl<Person>)new QueryImpl
				.NoWhere<Person>(mongoOperations)
				.build();
		Assert.assertNotNull(query);
	}
	
	@Test
	public void testNewQueryHasWhere(){
		QueryImpl<Person> query = 
				(QueryImpl<Person>)new QueryImpl
				.Where<Person>(mongoOperations,"wangwei")
				.build();
		Assert.assertNotNull(query);
	}
}
