/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 转换成{@link java.sql.Date}，缺省日期格式{@code yyyy-MM-dd}或{@code yyyy-MM-dd HH:mm:ss}。
 * 
 * @author WangWei
 */
class SqlDateConvert extends DateConvert<Date> {

    private final static String DEFAULT_PATTER = "yyyy-MM-dd HH:mm:ss";
    private final static String SHORT_PATTER = "yyyy-MM-dd";
    private final static int SHORT_LENGTH = 10;

    public SqlDateConvert(){
    	super();
    }
    
    public SqlDateConvert(Calendar calendar){
    	super(calendar);
    }
    
    private boolean isShortFormat(String value) {
        return value.trim().length() <= SHORT_LENGTH;
    }

    /**
     * 格式为{@code yyyy-MM-dd}或{@code yyyy-MM-dd HH:mm:ss}
     */
    @Override
    public Date parse(String value) throws ConvertException {
    	String patter = isShortFormat(value) ? SHORT_PATTER : DEFAULT_PATTER;
    	DateFormat format = new SimpleDateFormat(patter);
    	return parse(format,value);
    }

    @Override
    public String toString(Date value) {
    	DateFormat format = new SimpleDateFormat(DEFAULT_PATTER);
    	return format(format,value);
    }

	@Override
	protected Date toValue(long time) {
		return new Date(time);
	}
}
