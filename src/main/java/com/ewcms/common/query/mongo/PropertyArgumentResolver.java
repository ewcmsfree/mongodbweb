/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query.mongo;

import java.lang.RuntimeException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyArgumentResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyArgumentResolver.class);
	
	private final Class<?> type;
	
	/**
	 * 创建{@link PropertyArgumentResolver}
	 * 
	 * @param type
	 */
	public PropertyArgumentResolver(Class<?> type){
		this.type = type;
	}
	
	public Class<?> getPropertyType(String name){
		try {
			Class<?> type =PropertyUtils.getPropertyType(this.type, name);
			return type;
		} catch (Exception  e) {
			logger.error("Get property type error:{}",e.toString());
			throw new RuntimeException(e);
		}
	}
	
	public Object getPropertyValue(String property,Object v){
		//TODO must instance
		return null;
	}
}
