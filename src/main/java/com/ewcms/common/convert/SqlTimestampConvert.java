/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 转换成{@link java.sql.Timestamp}，缺省格式{@code yyyy-MM-dd HH:mm:ss}。
 * 
 * @author WangWei
 */
class SqlTimestampConvert extends DateConvert<Timestamp> {

    private  static final  DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式为{@code yyyy-MM-dd HH:mm:ss}
     */
    @Override
    public Timestamp parse(String value) throws ConvertException {
        try {
            return new Timestamp(DEFAULT_FORMAT.parse(value).getTime());
        } catch (ParseException e) {
            throw new ConvertException(e);
        }
    }

    @Override
    public String toString(Timestamp value) {
        return DEFAULT_FORMAT.format(value);
    }
 
	@Override
	protected Timestamp toValue(long time) {
		return new Timestamp(time);
	}
}
