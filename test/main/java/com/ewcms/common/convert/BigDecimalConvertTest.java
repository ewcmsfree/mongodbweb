/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ewcms.common.convert.BigDecimalConvert;
import com.ewcms.common.convert.ConvertException;

/**
 *单元测试{@link BigDecimalConvert}
 *
 * @author wangwei
 */
public class BigDecimalConvertTest {

    private Convert<BigDecimal> handler;

    @Before
    public void setUp(){
        handler = new BigDecimalConvert();
    }

    @Test
    public void testParse() throws Exception{
        BigDecimal bigDecimal = handler.parse("20.9");
        assertEquals(20.9, bigDecimal.floatValue(), 0.0001);
    }
    
    @Test
    public void testParseForFormat()throws Exception{
    	BigDecimal bigDecimal = handler.parseFor("#.00","20.90");
    	assertEquals(20.9, bigDecimal.floatValue(), 0.0001);
    }

    @Test(expected=ConvertException.class)
    public void testParseFail()throws Exception{
        String test = "wangwei";
        handler.parse(test);
    }
}
