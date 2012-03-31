/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 转换成{@link java.sql.Timestamp}，缺省格式{@code yyyy-MM-dd HH:mm:ss}。
 * 
 * @author WangWei
 */
class SqlTimestampConvert extends DateConvert<Timestamp> {

    private static final String DEFAULT_PATTER = "yyyy-MM-dd HH:mm:ss";

    public SqlTimestampConvert(){
    	super();
    }
    
    public SqlTimestampConvert(Calendar calendar){
    	super(calendar);
    }
    
    /**
     * 格式为{@code yyyy-MM-dd HH:mm:ss}
     */
    @Override
    public Timestamp parse(String value) throws ConvertException {
    	DateFormat format = new SimpleDateFormat(DEFAULT_PATTER);
    	return parse(format,value);
    }

    @Override
    public String toString(Timestamp value) {
    	DateFormat format = new SimpleDateFormat(DEFAULT_PATTER);
        return format(format,value);
    }
 
	@Override
	protected Timestamp toValue(long time) {
		return new Timestamp(time);
	}
}
