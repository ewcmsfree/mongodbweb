/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewcms.common.query.Query;
import com.ewcms.common.query.Result;
import com.ewcms.common.query.model.Certificate;
import com.ewcms.common.query.mongo.QueryImpl.Where.CriteriaOperation;

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
	
	@Before
	public void before()throws Exception{
		QueryInit init = new QueryInit(mongoOperations);
		init.init();
	}
	
	@Test
	public void testNewQueryNoWhere(){
		Query<Certificate> query = new QueryImpl
				.NoWhere<Certificate>(mongoOperations,Certificate.class)
				.build();
		Assert.assertNotNull(query);
	}
	
	@Test
	public void testNewQueryWhere(){
		Query<Certificate> query =new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.build();
		Assert.assertNotNull(query);
	}
	
	@Test
	public void testSetCriteriaOperationIsNull(){
		QueryImpl.Where<Certificate> where = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name");
		where.setCriteriaOperation(null, new CriteriaOperation(){
			@Override
			public void Operator(Object o) {
				Assert.fail();
			}
		});
	}
	
	@Test
	public void testIsOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.is("王伟")
				.build();
		Result<Certificate>  result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testSetCriteriaOperationFormatIsNull(){
		QueryImpl.Where<Certificate> where = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit");
		where.setCriteriaOperation(null, new CriteriaOperation(){
			@Override
			public void Operator(Object o) {
				Assert.fail();
			}
		},"#.00");
	}
	
	@Test
	public void testIsFormatOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.is("298.00","#.00")
				.build();
		Result<Certificate>  result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testNeOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"cerId")
				.ne("72300125")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(159, result.getNumberOfElements());
	}
	
	@Test
	public void testNeFormatOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.ne("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(159, result.getNumberOfElements());
	}
	
	@Test
	public void testLtOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lt(298)
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(4, result.getNumberOfElements());
	}
	
	@Test
	public void testLtFormatOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lt("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(4, result.getNumberOfElements());
	}
	
	@Test
	public void testLteOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lte(298)
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(5, result.getNumberOfElements());
	}
	
	@Test
	public void testLteFormatOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lte("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(5, result.getNumberOfElements());
	}
	
	@Test
	public void testGtOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gt("298")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(155, result.getNumberOfElements());
	}
	
	@Test
	public void testGtFormatOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gt("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(155, result.getNumberOfElements());
	}
	
	@Test
	public void testGteOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gte("298")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(156, result.getNumberOfElements());
	}
	
	@Test
	public void testGteFormatOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gte("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(156, result.getNumberOfElements());
	}
	
	@Test
	public void testBetweenOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"brithdate")
				.between("1976-12-23","1976-12-25")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testBetweenFormatOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"brithdate")
				.between("1976/12/23","1976/12/25","yyyy/MM/dd")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testRegexOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.regex("^王")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testRegexOptionsOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.regex("^王","i")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeStartOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeStart("王")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeStartPrefixRegexOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeStart("^王")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeAnyOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeAny("小")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(2, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeEndOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeEnd("华")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(7, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeEndSuffixRegexOfWhere(){
		Query<Certificate> query = new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeEnd("华$")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(7, result.getNumberOfElements());
	}
	
	@Test
	public void testAndOfWhere(){
		QueryImpl<Certificate> query = 
				(QueryImpl<Certificate>)new QueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.is("王伟")
				.and("cerId")
				.is("72300125")
				.build();
		
		Result<Certificate>  result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
	}
}
