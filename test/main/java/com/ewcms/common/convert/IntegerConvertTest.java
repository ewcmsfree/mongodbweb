/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试{@link IntegerConvert}
 * 
 * @author wangwei
 */
public class IntegerConvertTest {

    private Convert<Integer> handler;
    
    @Before
    public void setUp(){
        handler = new IntegerConvert();
    }
    @Test
    public void testParse() throws Exception{

        String test = "10";
        Integer testInteger = handler.parse(test);
        assertEquals(10, testInteger.intValue());
    }

    @Test(expected=ConvertException.class)
    public void testParseFail() throws Exception{

        String test = "10.9";
        handler.parse(test);
    }
}
