/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 转换成日期类型
 * 
 * @author wangwei
 *
 * @param <T>
 */
abstract class DateConvert<T extends Date> implements Convert<T> {
	private final Calendar calendar ;
	
	public DateConvert(){
		calendar = new GregorianCalendar();
	}
	
	public DateConvert(Calendar calendar){
		this.calendar = calendar;
	}
	
	@Override
	public T parseFor(String patter, String value) throws ConvertException {
		DateFormat format = new SimpleDateFormat(patter);
		return parse(format,value);
	}
	
	protected T parse(DateFormat format ,String value)throws ConvertException{
		try {
			format.setCalendar(calendar);
			return toValue(format.parse(value).getTime());
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
	public String format(String patter, T value) {
		DateFormat format = new SimpleDateFormat(patter);
		return format(format,value);
	}
	
	protected String format(DateFormat format ,T value){
		format.setCalendar(calendar);
		return format.format(value);
	}
}
