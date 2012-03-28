/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试{@link DateConvert}
 * 
 * @author wangwei
 */
public class DateConvertTest {

	private Convert<Date> handler;

	@Before
	public void setUp() {
		handler = new DateConvertImpl();
	}

	@Test
	public void testParseForFormat() throws Exception {
		String test = "2006/01/12";
		Date date = handler.parseFor("yyyy/MM/dd", test);
		assertEqualsDate(date, 2006, 0, 12);
	}

	private void assertEqualsDate(Date date, int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(year, calendar.get(Calendar.YEAR));
		assertEquals(month, calendar.get(Calendar.MONTH));
		assertEquals(day, calendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testFormat() throws Exception {
		String test = "2006-01-12";
		Date testDate = handler.parseFor("yyyy-MM-dd",test);
		assertEquals(handler.format("yyyy/MM/dd", testDate),
				"2006/01/12");
	}

	private class DateConvertImpl extends DateConvert<Date> {

		@Override
		public Date parse(String value) throws ConvertException {
			// not test
			return null;
		}

		@Override
		public String toString(Date value) {
			// not test
			return null;
		}

		@Override
		protected Date toValue(long time) {
			return new Date(time);
		}
	}
}
