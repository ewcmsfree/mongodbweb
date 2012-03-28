/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

/**
 *单元测试{@link BigIntegerConvert}
 *
 * @author wangwei
 */
public class BigIntegerConvertTest {

    private Convert<BigInteger> handler;

    @Before
    public void setUp() {
        handler = new BigIntegerConvert();
    }

    @Test
    public void testParse() throws Exception {
        BigInteger bigInteger = handler.parse("200");
        assertEquals(200, bigInteger.intValue());
    }
    
    @Test
    public void testParseForFormat()throws Exception{
    	BigInteger bigInteger = handler.parseFor("#.00","20.00");
    	assertEquals(20, bigInteger.intValue());
    }

   @Test(expected=ConvertException.class)
    public void testParseFail() throws Exception {
        String test = "wangwei";
        handler.parse(test);
    }
}
