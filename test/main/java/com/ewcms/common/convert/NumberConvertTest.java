/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.convert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试{@link NumberConvert}
 * 
 * @author wangwei
 */
public class NumberConvertTest {

	private Convert<Long> handler;

	@Before
	public void setUp() {
		this.handler = new NumberConvertImpl();
	}

	@Test
	public void testParseForFormat() throws Exception {
		String test = "1,000,000.00";
		Long testLong = handler.parseFor("#,###.00", test);
		assertTrue(1000000L == testLong.longValue());
	}

	@Test
	public void testToString() {
		assertEquals(handler.toString(Long.valueOf(100L)), "100");
	}

	@Test
	public void testFormat() {
		assertEquals(handler.format("#,###", Long.valueOf(10000L)),"10,000");
	}

	private class NumberConvertImpl extends NumberConvert<Long> {

		@Override
		public Long parse(String value) throws ConvertException {
			// not test
			return null;
		}

		@Override
		protected Long toValue(Number number) {
			return number.longValue();
		}

	}
}
