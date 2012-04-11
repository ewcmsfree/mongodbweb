/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query.mongo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.ewcms.common.convert.Convert;
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
	private final boolean gmtTime;

	/**
	 * 创建{@link PropertyConvert}
	 * 
	 * @param beanClass
	 */
	public PropertyConvert(Class<?> beanClass) {
		this(beanClass,true);
	}

	public PropertyConvert(Class<?> beanClass,boolean gmtTime){
		this.beanClass = beanClass;
		this.gmtTime = gmtTime;
	}
	
	/**
	 * 是否以格林威治(GMT)时间为标准
	 * 
	 * @return
	 */
	public boolean isGMTTime(){
		return gmtTime;
	}
	
	/**
	 * 得到指定属性的类型，属性不存在或无法访问抛出{@link RuntimeException}异常。
	 * 
	 * @param name  属性名，不能为{@literal null}
	 * @return 属性类型{@value Class<?>}
	 */
	public Class<?> getPropertyType(String propertyName) {
		if(!StringUtils.hasText(propertyName)) {
			throw new IllegalArgumentException("Property's name must not null or empty!");
		}
		
		String[] names =StringUtils.tokenizeToStringArray(propertyName, NESTED);
		Class<?> type = beanClass;
		PropertyDescriptor pd = null;
		for(String name : names){
			pd = BeanUtils.getPropertyDescriptor(type, name);
			if (pd == null) {
				logger.error("\"{}\" property isn't exist.", propertyName);
				throw new RuntimeException(propertyName + " property isn't exist.");
			}
			type = pd.getPropertyType();
		}
		
		if(type.isArray()){
			return type.getComponentType();
		} 
		
		if(Collection.class.isAssignableFrom(type)){
			Method method =  pd.getReadMethod();
			if(method == null){
				logger.error("\"{}\" property is not read method.",propertyName);
				throw new RuntimeException( propertyName + " property is not read method.");
			}
			ParameterizedType returnType =(ParameterizedType) method.getGenericReturnType();
			if(returnType.getActualTypeArguments().length > 0){
				return (Class<?>)returnType.getActualTypeArguments()[0];
			}
			logger.error("\"{}\" property is collection,but it's not generic.",propertyName);
			throw new RuntimeException( propertyName + " property is collection,but it's not generic.");
		}
		
		return type;
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
		return convertFormat(propertyName,null,value);
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
	
	private ConvertFactory getConvertFactory(){
		return isGMTTime() ? ConvertFactory.instanceGMT : ConvertFactory.instance;
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
		if(sameType(propertyType,value)){
			return value;
		}
		
		Convert<?> convert = getConvertFactory().convert(propertyType);
		return StringUtils.hasText(patter)? 
				convert.parseFor(patter,value.toString())
				:convert.parse(value.toString());
	}
	
	/**
	 * 使用{@code separator}把{@code value}分割成数组，再根据{@code propertyName}把数组中的值转换成对应类型值。
	 * 
	 * @param propertyName  属性名，不能为{@literal null}
	 * @param in 匹配字符串按分割符分割
	 * @param delimiter 分割符
	 * @return
	 * @throws ConvertException
	 */
	public Collection<?> convertCollection(String propertyName,String value,String delimiter)throws ConvertException{
		return convertCollectionFormat(propertyName,null,value,delimiter);
	}
	
	/**
	 * 使用{@code separator}把{@code value}分割成数组，再根据{@code propertyName}按照指定格式{@code patter}把数组中的值转换成对应类型值。
	 * 
	 * @param propertyName  属性名，不能为{@literal null}
	 * @param in 匹配字符串按分割符分割
	 * @param delimiter 分割符
	 * @return
	 * @throws ConvertException
	 */
	public Collection<?> convertCollectionFormat(String propertyName,String patter,String value,String delimiter)throws ConvertException{
		if(isNull(value)){
			return null;
		}
		
		String[] s = StringUtils.tokenizeToStringArray(value, delimiter);
		Class<?> propertyType = getPropertyType(propertyName);
		if(sameType(propertyType,String.class)){
			return Arrays.asList(s);
		}
		
		Object[] values = new Object[s.length];
		Convert<?> convert = getConvertFactory().convert(propertyType);
		for(int i = 0 ; i < s.length; i++){
			values[i] =  StringUtils.hasText(patter) ? 
					convert.parseFor(patter,s[i])
					:convert.parse(s[i]);
		}
		return Arrays.asList(values);
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
