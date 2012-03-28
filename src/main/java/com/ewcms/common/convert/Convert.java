/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 字符串和指定类型的数据
 *    
 * @author WangWei
 */
public interface Convert<T> {

    /**
     * 转换参数数据类型
     * 
     * @param value 转换的值
     * @return 转换后的值      
     */
     public T parse(String value)throws ConvertException;
     
     /**
      * 根据指定格式来转换数据
      * 
      * @param patter 数据格式
      * @param value 被转换的值
      * @return
      * @throws ConvertException
      */
     public T parseFor(String patter,String value)throws ConvertException;
    
    /**
     * 将指定的数据转换成字符串
     * 
     * @param value 值
     * @return 
     */
    public String toString(T value);
    
    /**
     * 格式化为指定格式的字符串
     * 
     * @param patter 数据格式
     * @param value 被转换的值
     * @return
     */
    public String format(String patter,T value);
    
}
