/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewcms.common.query.EasyQuery;
import com.ewcms.common.query.Result;
import com.ewcms.common.query.mongo.EasyQueryImpl.Where.CriteriaOperation;
import com.ewcms.common.query.mongo.model.Certificate;

/**
 * 单元测试{@link EasyQueryImpl}
 * 
 * @author wangwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class EasyQueryImplTest {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Before
	public void before()throws Exception{
		EasyQueryInit init = new EasyQueryInit(mongoOperations);
		init.init();
	}
	
	@Test
	public void testNewQueryNoWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.NoWhere<Certificate>(mongoOperations,Certificate.class)
				.build();
		Assert.assertNotNull(query);
	}
	
	@Test
	public void testNewQueryWhere(){
		EasyQuery<Certificate> query =new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.build();
		Assert.assertNotNull(query);
	}
	
	@Test
	public void testSetCriteriaOperationIsNull(){
		EasyQueryImpl.Where<Certificate> where = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name");
		where.setCriteriaOperation(null, new CriteriaOperation<Object>(){
			@Override
			public void Operator(Object o) {
				Assert.fail();
			}
		});
	}
	
	@Test
	public void testIsOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.is("王伟")
				.build();
		Result<Certificate>  result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testSetCriteriaOperationFormatIsNull(){
		EasyQueryImpl.Where<Certificate> where = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit");
		where.setCriteriaOperation(null, new CriteriaOperation<Object>(){
			@Override
			public void Operator(Object o) {
				Assert.fail();
			}
		},"#.00");
	}
	
	@Test
	public void testIsFormatOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.is("298.00","#.00")
				.build();
		Result<Certificate>  result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testNeOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"cerId")
				.ne("72300125")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(159, result.getNumberOfElements());
	}
	
	@Test
	public void testNeFormatOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.ne("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(159, result.getNumberOfElements());
	}
	
	@Test
	public void testLtOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lt(298)
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(4, result.getNumberOfElements());
	}
	
	@Test
	public void testLtFormatOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lt("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(4, result.getNumberOfElements());
	}
	
	@Test
	public void testLteOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lte(298)
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(5, result.getNumberOfElements());
	}
	
	@Test
	public void testLteFormatOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.lte("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(5, result.getNumberOfElements());
	}
	
	@Test
	public void testGtOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gt("298")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(155, result.getNumberOfElements());
	}
	
	@Test
	public void testGtFormatOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gt("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(155, result.getNumberOfElements());
	}
	
	@Test
	public void testGteOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gte("298")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(156, result.getNumberOfElements());
	}
	
	@Test
	public void testGteFormatOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.gte("298.00","#.00")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(156, result.getNumberOfElements());
	}
	
	@Test
	public void testBetweenOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"brithdate")
				.between("1976-12-23","1976-12-25")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testBetweenFormatOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"brithdate")
				.between("1976/12/23","1976/12/25","yyyy/MM/dd")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals("王伟", result.getContent().get(0).getName());
	}
	
	@Test
	public void testRegexOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.regex("^王")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testRegexOptionsOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.regex("^王","i")
				.build();	
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeStartOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeStart("王")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeStartPrefixRegexOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeStart("^王")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(6, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeAnyOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeAny("小")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(2, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeEndOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeEnd("华")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(7, result.getNumberOfElements());
	}
	
	@Test
	public void testLikeEndSuffixRegexOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.likeEnd("华$")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(7, result.getNumberOfElements());
	}
	
	@Test
	public void testInForArrayOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.in("王伟","周冬初")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(2, result.getNumberOfElements());
	}
	
	@Test
	public void testInForCollectionOfWhere(){
		List<String> names = new ArrayList<String>();
		names.add("王伟");
		names.add("周冬初");
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.in(names)
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(2, result.getNumberOfElements());
	}
	
	@Test
	public void testInSplitOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations, Certificate.class, "name")
				.inSplit("王伟,周冬初")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(2, result.getNumberOfElements());
	}
	
	@Test
	public void testInSplitForDelimiterOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations, Certificate.class, "name")
				.inSplit("王伟,周冬初",",")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(2, result.getNumberOfElements());
	}
	
	@Test
	public void testNinForArrayOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.nin("王伟","周冬初")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(158, result.getNumberOfElements());
	}
	
	@Test
	public void testNinForCollectionOfWhere(){
		List<String> names = new ArrayList<String>();
		names.add("王伟");
		names.add("周冬初");
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.nin(names)
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(158, result.getNumberOfElements());
	}
	
	@Test
	public void testNinSplitOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.ninSplit("王伟,周冬初")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(158, result.getNumberOfElements());
	}
	
	@Test
	public void testNinSplitForDelimiterOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.ninSplit("王伟|周冬初","|")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(158, result.getNumberOfElements());
	}
	
	@Test
	public void testModOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"limit")
				.mod(10, 6)
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(4, result.getNumberOfElements());
	}
	
	@Test
	public void testAllForArrayOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"phones")
				.all("12345678","87654321")
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
	}
	
	@Test
	public void testAllForCollectionsOfWhere(){
		List<String> phones = new ArrayList<String>();
		phones.add("12345678");
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"phones")
				.all(phones)
				.build();
		Result<Certificate> result = query.find();
		Assert.assertEquals(2, result.getNumberOfElements());
	}
	
	@Test
	public void testAllSplitForWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations, Certificate.class, "phones")
				.allSplit("12345678,87654321")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
	}
	
	@Test
	public void testAllSplitForDelimiterOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations, Certificate.class, "phones")
				.allSplit("12345678|87654321","|")
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
	}
	
	@Test
	public void testSizeOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations, Certificate.class, "phones")
				.size(3)
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
		Assert.assertEquals(result.getContent().get(0).getName(), "周冬初");
	}
	
	
	@Test
	public void testNotOfWhere(){
		EasyQuery<Certificate> query = new EasyQueryImpl
				.Where<Certificate>(mongoOperations, Certificate.class, "limit")
				.not()
				.gte(298)
				.build();
		
		Result<Certificate> result = query.find();
		Assert.assertEquals(4, result.getNumberOfElements());
	}
	
	@Test
	public void testAndOfWhere(){
		EasyQueryImpl<Certificate> query = 
				(EasyQueryImpl<Certificate>)new EasyQueryImpl
				.Where<Certificate>(mongoOperations,Certificate.class,"name")
				.is("王伟")
				.and("cerId")
				.is("72300125")
				.build();
		
		Result<Certificate>  result = query.find();
		Assert.assertEquals(1, result.getNumberOfElements());
	}
}
