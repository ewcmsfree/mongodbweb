/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import java.util.Date;

import org.junit.Test;

/**
 * 单元测试{@link PropertyArgumentResolver}
 * 
 * @author wangwei
 *
 */
public class PropertyArgumentResolverTest {

	@Test(expected=RuntimeException.class)
	public void testGetPropertyTypeIsNotExists(){
		PropertyArgumentResolver resolver = new PropertyArgumentResolver(Person.class);
		resolver.getPropertyType("name1");
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
	}
}
