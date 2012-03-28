/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * 测试java.util.Date转换
 * 
 * @author 王伟
 */
public class UtilDateConvertTest {

    private Convert<Date> handler;
    
    @Before
    public void setUp(){
        handler = new UtilDateConvert();
    }
    
    @Test
    public void testParse()throws Exception{

        String dateString = "2005-06-01 20:12:24";
        Date date = handler.parse(dateString);
        assertEqualsDate(date,2005,5,1,20,12,24);

        dateString = " 2005-06-01 ";
        date = handler.parse(dateString);
        assertEqualsDate(date,2005,5,1,0,0,0);
    }

    private void assertEqualsDate(Date date,
            int year,int month,int day,int hour,int minute,int second){
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
    public void testParseFail()throws Exception{
        String dateString = "2005/06/01";
        handler.parse(dateString);
    }
    
    @Test
    public void testToString()throws Exception{
        String dateString = "2005-06-01 20:12:24";
        Date date = (Date) handler.parse(dateString);
        assertEquals(handler.toString(date),dateString);
    }
}
