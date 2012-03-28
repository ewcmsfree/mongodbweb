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
 * 单元测试{@link SqlDateConvert}
 * 
 * @author wangwei
 */
public class SqlDateConvertTest {

    private Convert<Date> handler;

    @Before
    public void setUp() {
        handler = new SqlDateConvert();
    }

    @Test
    public void testParse() throws Exception {

        String test = "2006-01-12";
        Date date = handler.parse(test);
        assertEqualsDate(date, 2006, 0, 12,0,0,0);

        test = "2006-5-9 01:20:01";
        date = handler.parse(test);
        assertEqualsDate(date, 2006, 4, 9,1,20,1);
    }
    
    private void assertEqualsDate(Date date,
            int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        assertEquals(year, calendar.get(Calendar.YEAR));
        assertEquals(month, calendar.get(Calendar.MONTH));
        assertEquals(day, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(minute, calendar.get(Calendar.MINUTE));
        assertEquals(second, calendar.get(Calendar.SECOND));
    }

    @Test(expected=ConvertException.class)
    public void testParseFail() throws Exception {

        String test = "2006-01d-12";
        handler.parse(test);
    }

    @Test
    public void testToString() throws Exception {
        String test = "2006-01-12 00:00:00";
        Date testDate = (Date) handler.parse(test);
        assertEquals(handler.toString(testDate), test);
    }
}
