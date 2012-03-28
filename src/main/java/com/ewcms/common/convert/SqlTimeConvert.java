/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * 转换成{@link java.sql.Time}，时间格式{@code hh:mm:ss}。
 * 
 * @author WangWei
 */
class SqlTimeConvert extends DateConvert<Time> {

    private final static DateFormat DEFAULT_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /**
     * 格式为{@code hh:mm:ss}
     */
    @Override
    public Time parse(String value)throws ConvertException {
        try {
            return new Time(DEFAULT_FORMAT.parse(value).getTime());
        } catch (ParseException e) {
            throw new ConvertException(e);
        }
    }

    @Override
    public String toString(Time value) {
        return DEFAULT_FORMAT.format(value);
    }

	@Override
	protected Time toValue(long time) {
		return new Time(time);
	}

}
