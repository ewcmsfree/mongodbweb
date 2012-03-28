/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换成日期类型
 * 
 * @author wangwei
 *
 * @param <T>
 */
abstract class DateConvert<T extends Date> implements Convert<T> {

	@Override
	public T parseFor(String patter, String value) throws ConvertException {
		try {
			DateFormat dateFormat = new SimpleDateFormat(patter);
			return toValue(dateFormat.parse(value).getTime());
		} catch (ParseException e) {
			throw new ConvertException(e);
		}
	}
	
	/**
	 * 得到日期
	 * 
	 * @param number
	 * @return
	 */
	protected abstract T toValue(long time);

	@Override
	public String format(String patter, Date value) {
		DateFormat dateFormat = new SimpleDateFormat(patter);
		return dateFormat.format(value);
	}
}
