/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换成{@link java.util.Date}，缺省格式为{@code yyyy-MM-dd}或{@code yyyy-MM-dd HH:mm:ss}。
 *
 * @author WangWei
 */
class UtilDateConvert extends DateConvert<Date> {

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
                return SHORT_FORMAT.parse(value);
            } else {
                return DEFAULT_FORMAT.parse(value);
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
