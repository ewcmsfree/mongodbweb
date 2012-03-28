/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 转换成{@link java.sql.Date}，缺省日期格式{@code yyyy-MM-dd}或{@code yyyy-MM-dd HH:mm:ss}。
 * 
 * @author WangWei
 */
class SqlDateConvert extends DateConvert<Date> {

    private final static DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static DateFormat SHORT_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final static int SHORT_LENGTH = 10;

    private boolean isShortFormat(String value) {
        return value.trim().length() <= SHORT_LENGTH;
    }

    /**
     * 格式为{@code yyyy-MM-dd}或{@code yyyy-MM-dd HH:mm:ss}
     */
    @Override
    public Date parse(String value) throws ConvertException {
        try {
            if (isShortFormat(value)) {
                return new Date(SHORT_FORMAT.parse(value).getTime());
            } else {
                return new Date(DEFAULT_FORMAT.parse(value).getTime());
            }
        } catch (ParseException e) {
            throw new ConvertException(e);
        }
    }

    @Override
    public String toString(Date value) {
    	return DEFAULT_FORMAT.format(value);
    }

	@Override
	protected Date toValue(long time) {
		return new Date(time);
	}
}
