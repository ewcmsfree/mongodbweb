/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.ewcms.common.convert.ConvertException;

/**
 * 单元测试{@link PropertyConvert}
 * 
 * @author wangwei
 *
 */
public class PropertyConvertTest {

	@Test(expected=RuntimeException.class)
	public void testGetPropertyTypeIsNotExists(){
		PropertyConvert resolver = new PropertyConvert(Person.class);
		resolver.getPropertyType("name1");
	}
	
	@Test
	public void testGetPropertyType(){
		PropertyConvert resolver = new PropertyConvert(Person.class);
		Class<?> type = resolver.getPropertyType("name");
		Assert.assertNotNull(type);
		Assert.assertEquals(String.class, type);
		
		type = resolver.getPropertyType("birthday");
		Assert.assertNotNull(type);
		Assert.assertEquals(Date.class,type);
	}
	
	@Test
	public void testPropertyTypeForSubClass(){
		PropertyConvert convert = new PropertyConvert(Person.class);
		Class<?> type = convert.getPropertyType("work.name");
		Assert.assertNotNull(type);
		Assert.assertEquals(String.class, type);
	}
	
	@Test
	public void testConvertValueIsNull()throws ConvertException{
		PropertyConvert convert = new PropertyConvert(Person.class);
		Object value = convert.convert("name", null);
		Assert.assertNull(value);
	}
	
	@Test
	public void testConvertValueNotDateSameType()throws ConvertException{
		PropertyConvert convert = new PropertyConvert(Person.class);
		Boolean man = Boolean.TRUE;
		Object value = convert.convert("man", Boolean.TRUE);
		Assert.assertTrue(man.equals(value));
	}
	
	@Test
	public void testConvert(){
		try{
			PropertyConvert convert = new PropertyConvert(Person.class);
			Object value = convert.convert("name", "wangwei");
			Assert.assertEquals(String.class, value.getClass());
			Assert.assertEquals("wangwei", value);	
			
			value = convert.convert("birthday", "1980-01-01");
			Assert.assertEquals(Date.class, value.getClass());
		}catch(ConvertException e){
			Assert.fail(e.toString());
		}
	}
	
	@Test
	public void testConvertFormatValueIsNull()throws ConvertException{
		PropertyConvert convert = new PropertyConvert(Person.class);
		Object value = convert.convertFormat("birthday","yyyy/MM/dd", null);
		Assert.assertNull(value);
	}
	
	@Test
	public void testConvetFormat(){
		try{
			PropertyConvert convert = new PropertyConvert(Person.class);
			Object value = convert.convertFormat("birthday","yyyy/MM/dd", "1980/01/01");
			Assert.assertEquals(Date.class, value.getClass());
			DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
			Assert.assertEquals("1980-01-01", dateFormat.format((Date)value));
		}catch(ConvertException e){
			Assert.fail(e.toString());
		}
	}
	
	@Test
	public void testConvertFormatPatterIsNull(){
		try{
			PropertyConvert convert = new PropertyConvert(Person.class);
			Object value = convert.convertFormat("birthday",null, "1980-01-01");
			Assert.assertEquals(Date.class, value.getClass());
			DateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd");
			Assert.assertEquals("1980/01/01", dateFormat.format((Date)value));
		}catch(ConvertException e){
			Assert.fail(e.toString());
		}
	}
	
	/**
	 * 测试使用的对象
	 * 
	 * @author wangwei
	 */
	class Person{
		private String name;
		private int age;
		private Date birthday;
		private boolean man;
		private Work work;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public boolean isMan() {
			return man;
		}
		public void setMan(boolean man) {
			this.man = man;
		}
		public Work getWork(){
			return work;
		}
		public void setWork(Work work){
			this.work = work;
		}
	}
	
	public class Work{
		private String name;
		private String address;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	}
}
