/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试{@link Sort}
 * 
 * @author wangwei
 */
public class SortTest {

	@Test
	public void testDirectionFromString(){
		Sort.Direction asc = Sort.Direction.fromString("asc");
		Assert.assertEquals(Sort.Direction.ASC, asc);
		
		Sort.Direction dec = Sort.Direction.fromString("Dec");
		Assert.assertEquals(Sort.Direction.DEC, dec);
		
		dec = Sort.Direction.fromString("Desc");
		Assert.assertEquals(Sort.Direction.DEC, dec);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDirectionFromStringIsNotExist(){
		Sort.Direction.fromString("asc1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewOrderPropertyIsNull(){
		new Sort.Order(Sort.Direction.DEC, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewOrderPropertyIsEmpty(){
		new Sort.Order(Sort.Direction.DEC," ");
	}
	
	@Test
	public void testNewOrderDirectionIsNull(){
		Sort.Order order = new Sort.Order(null,"name");
		Assert.assertEquals(Sort.Direction.ASC, order.getDirection());
	}
	
	@Test
	public void testOrderGetProperty(){
		Sort.Order order = new Sort.Order(null,"name");
		Assert.assertEquals("name",order.getProperty());
	}
	
	@Test
	public void testOrderGetDirection(){
		Sort.Order order = new Sort.Order(Sort.Direction.DEC,"name");
		Assert.assertEquals(Sort.Direction.DEC,order.getDirection());
	}
	
	@Test
	public void testOrderIsAscending(){
		Sort.Order order = new Sort.Order(Sort.Direction.DEC,"name");
		Assert.assertFalse(order.isAscending());
		
		order = new Sort.Order(Sort.Direction.ASC,"name");
		Assert.assertTrue(order.isAscending());
	}
	
	@Test
	public void testOrderWith(){
		Sort.Order order = new Sort.Order(Sort.Direction.DEC,"name");
		order = order.with(Sort.Direction.ASC);
		
		Assert.assertEquals(Sort.Direction.ASC, order.getDirection());
	}
	
	@SuppressWarnings("unused")
	private int ordersSize(Sort sort){
		int size = 0;
		for(Sort.Order o : sort){
			size++;
		}
		return size;
	}
	
	@Test
	public void testOrderWithProperties(){
		Sort.Order order = new Sort.Order(Sort.Direction.DEC,"name");
		
		Sort sort = order.withProperties("name","sex");
		int size = ordersSize(sort);
		Assert.assertTrue(size == 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewSortByOrdersIsNull(){
		List<Sort.Order> orders = null;
		new Sort(orders);
	}
	
	@Test
	public void testNewSortByOrders(){
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Sort.Order("name"));
		Sort sort = new Sort(orders);
		int size = ordersSize(sort);
		Assert.assertTrue(size == 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewSortByDirectionAndPropertiesIsNull(){
		List<String> properties = null;
		new Sort(Sort.Direction.DEC,properties);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewSortByDirectionAndPropertiesIsEmpty(){
		new Sort(Sort.Direction.DEC,new ArrayList<String>());
	}
	
	@Test
	public void testNewSortByDirectionAndProperties(){
		List<String> properties = new ArrayList<String>();
		properties.add("name");
		properties.add("sex");
		
		Sort sort = new Sort(Sort.Direction.DEC,properties);
		int size = ordersSize(sort);
		Assert.assertTrue(size == 2);
	}
	
	@Test
	public void testAndBySortIsNull(){
		Sort sort = new Sort("name");
		Sort these = sort.and(null);
		Assert.assertTrue(sort == these);
	}
	
	@Test
	public void testAnd(){
		Sort sort = new Sort("name");
		Sort combSort = new Sort("sex");
		
		Sort newSort = sort.and(combSort);
		int size = ordersSize(newSort);
		Assert.assertTrue(size == 2);
	}
	
	@Test
	public void testGetOrderFor(){
		Sort sort = new Sort("name");
		Sort.Order order = sort.getOrderFor("name");
		Assert.assertNotNull(order);
		Assert.assertEquals("name", order.getProperty());
	}
	
	@Test
	public void testGetOrderForIsNotRegister(){
		Sort sort = new Sort("name");
		Sort.Order order = sort.getOrderFor("sex");
		Assert.assertNull(order);
	}
}
