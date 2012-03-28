/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试{@link ShortConvert}
 * 
 * @author wangwei
 */
public class ShortConvertTest {

    private Convert<Short> handler;
    
    @Before
    public void setUp(){
        handler = new ShortConvert();
    }
    
    @Test
    public void testParse() throws Exception{
        String test = "200";
        Short testShort = handler.parse(test);
        assertTrue(200 == testShort.shortValue());
    }

    @Test(expected=ConvertException.class)
    public void testParseFail() throws Exception{

        String test = "20.0";
        assertNull(handler.parse(test));
    }
}
