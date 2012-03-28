/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.convert;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 转换成数字
 * 
 * @author wangwei
 * 
 * @param <T>
 */
abstract class NumberConvert<T extends Number> implements Convert<T> {
	
	@Override
	public T parseFor(String patter, String value) throws ConvertException {
		try {
			NumberFormat numberFormat = new DecimalFormat(patter);
			Number number = numberFormat.parse(value);
			return toValue(number);
		} catch (ParseException e) {
			throw new ConvertException(e);
		}
	}
	
	/**
	 * 得到值
	 * 
	 * @param number
	 * @return
	 */
	protected abstract T toValue(Number number);

	@Override
	public String toString(T value) {
		return value.toString();
	}

	@Override
	public String format(String patter, T value) {
		NumberFormat numberFormat = new DecimalFormat(patter);
		return numberFormat.format(value);
	}
}
