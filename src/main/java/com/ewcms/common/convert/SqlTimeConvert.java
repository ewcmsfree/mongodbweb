/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 转换成{@link java.sql.Time}，时间格式{@code hh:mm:ss}。
 * 
 * @author WangWei
 */
class SqlTimeConvert extends DateConvert<Time> {

    private final static String DEFAULT_PATTER = "HH:mm:ss";

    public SqlTimeConvert(){
    	super();
    }
    
    public SqlTimeConvert(Calendar calendar){
    	super(calendar);
    }
    
    /**
     * 格式为{@code hh:mm:ss}
     */
    @Override
    public Time parse(String value)throws ConvertException {
    	DateFormat format = new SimpleDateFormat(DEFAULT_PATTER);
    	return parse(format,value);
    }

    @Override
    public String toString(Time value) {
    	DateFormat format = new SimpleDateFormat(DEFAULT_PATTER);
        return format(format,value);
    }

	@Override
	protected Time toValue(long time) {
		return new Time(time);
	}

}
