/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query.mongo;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.ewcms.common.convert.ConvertException;
import com.ewcms.common.convert.ConvertFactory;

/**
 * 根据类型对象（Model）属性，把字符传转换为对应类型的值，用与查询条件值转换。
 * 
 * @author wangwei
 *
 */
public class PropertyConvert {

	private static final Logger logger = LoggerFactory.getLogger(PropertyConvert.class);
	private static final Map<Class<?>,Class<?>> primitiveWrapper = initPrimitiveWrapper();
	private static final String NESTED = ".";

	private final Class<?> beanClass;

	/**
	 * 创建{@link PropertyConvert}
	 * 
	 * @param beanClass
	 */
	public PropertyConvert(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * 得到指定属性的类型，属性不存在或无法访问抛出{@link RuntimeException}异常。
	 * 
	 * @param name  属性名，不能为{@literal null}
	 * @return 属性类型{@value Class<?>}
	 */
	public Class<?> getPropertyType(String name) {
		if(name == null || name.trim().equals("")) {
			throw new IllegalArgumentException("name must not null or empty!");
		}
		
		String[] propertyNames =StringUtils.tokenizeToStringArray(name, NESTED);
		Class<?> propertyType = beanClass;
		for(String propertyName : propertyNames){
			PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(propertyType, propertyName);
			if (pd == null) {
				logger.error("\"{}\" property isn't exist.", propertyName);
				throw new RuntimeException(propertyName + " property isn't exist.");
			}
			propertyType = pd.getPropertyType();
		}
		return propertyType;
	}
	
	/**
	 * 根据{@code propertyName}把{@code value}转换成对应类型值。
	 * 
	 * @param propertyName 属性名，不能为{@literal null}
	 * @param value 值
	 * @return
	 * @throws ConvertException
	 */
	public Object convert(String propertyName, Object value)throws ConvertException {
		if(isNull(value)){
			return value;
		}
		
		Class<?> propertyType = getPropertyType(propertyName);
		return sameType(propertyType,value) 
				? value : ConvertFactory
				          .instanceGMT
				          .convert(propertyType)
				          .parse(value.toString());
	}
	
	
	/**
	 * 判断值是否为{@value null}或与指定类型相同。
	 * 
	 * @param type 指定类型
	 * @param value 值
	 * @return
	 */
	private boolean isNull(Object value){
		return value == null ;
	}
	
	/**
	 * 判断值是否为{@value null}或与指定类型相同。
	 * 
	 * @param type 指定类型
	 * @param value 值
	 * @return
	 */
	private boolean sameType(Class<?> type,Object value){
		if(type.isPrimitive()){
			Class<?> wapper = primitiveWrapper.get(type);
			if(wapper == null){
				throw new IllegalArgumentException(type.getName()+" wapper is not exist!");
			}
			return wapper == value.getClass();
		}
		return type == value.getClass();
	}
	
	/**
	 * 根据{@code propertyName}按照指定格式{@code patter}把{@code value}转换成对应类型值。
	 * 
	 * @param propertyName 属性名，不能为{@literal null}
	 * @param patter 格式模版,可以为{@literal null}
	 * @param value 值
	 * @return
	 * @throws ConvertException
	 */
	public Object convertFormat(String propertyName,String patter,Object value)throws ConvertException{
		
		if(isNull(value)){
			return value;
		}
		
		Class<?> propertyType = getPropertyType(propertyName);
		return sameType(propertyType,value) 
				? value : ConvertFactory
				          .instanceGMT
				          .convert(propertyType)
				          .parseFor(patter,value.toString());
	}
	
	private static Map<Class<?>,Class<?>> initPrimitiveWrapper(){
		Map<Class<?>,Class<?>> map = new HashMap<Class<?>,Class<?>>();
		
		map.put(int.class, Integer.class);
		map.put(short.class, Short.class);
		map.put(long.class, Long.class);
		map.put(float.class, Float.class);
		map.put(double.class, Double.class);
		map.put(boolean.class, Boolean.class);
		map.put(byte.class, Byte.class);
		
		return map;
	}
}
