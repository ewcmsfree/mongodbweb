/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换数据对象的工厂封装 
 * 
 * @author WangWei
 */
public enum ConvertFactory {

	/**
	 * 实现{@link ConvertFactory},日期转换以本地时间为准
	 */
    instance(new GregorianCalendar(),false),
    /**
	 * 实现{@link ConvertFactory},日期转换以GMT（格林威治）时间为准
	 */
    instanceGMT(new GregorianCalendar(new SimpleTimeZone(0,"GMT")),true);
    
    private final Map<Class<?>, Convert<?>> converts;
    private final boolean gmtTime;

    ConvertFactory(Calendar calendar,boolean gmtTime) {
        converts = initConverts(calendar);
        this.gmtTime = gmtTime;
    }

    /**
     * 根据Class类型 返回转换类型操作
     * 
     * @param clazz 数据类型
     * @return 
     */
    public Convert<?> convert(Class<?> clazz) {
        Convert<?> convert = converts.get(clazz);
        if(convert == null){
            throw new IllegalArgumentException(clazz.getName() + " type cant not convert");
        }
        return convert;
    }
    
	/**
	 * 是否以格林威治(GMT)时间为标准
	 * 
	 * @return
	 */
    public boolean isGMTTime(){
    	return gmtTime;
    }
    
    private Map<Class<?>, Convert<?>> initConverts(Calendar calendar) {
        Map<Class<?>, Convert<?>> map = new HashMap<Class<?>, Convert<?>>();

        map.put(BigDecimal.class, new BigDecimalConvert());
        map.put(BigInteger.class, new BigIntegerConvert());
        map.put(Boolean.class, new BooleanConvert());
        map.put(boolean.class, new BooleanConvert());
        map.put(Byte.class, new ByteConvert());
        map.put(byte.class, new ByteConvert());
        map.put(Double.class, new DoubleConvert());
        map.put(double.class, new DoubleConvert());
        map.put(Float.class, new FloatConvert());
        map.put(float.class, new FloatConvert());
        map.put(Integer.class, new IntegerConvert());
        map.put(int.class, new IntegerConvert());
        map.put(Long.class, new LongConvert());
        map.put(long.class, new LongConvert());
        map.put(Short.class, new ShortConvert());
        map.put(short.class, new ShortConvert());
        map.put(java.util.Date.class, new UtilDateConvert(calendar));
        map.put(java.sql.Date.class, new SqlDateConvert(calendar));
        map.put(java.sql.Timestamp.class, new SqlTimestampConvert(calendar));
        map.put(java.sql.Time.class, new SqlTimeConvert(calendar));
        map.put(String.class, new StringConvert());

        return map;
    }
}
