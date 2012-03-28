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
 * 单元测试{@link FloatConvert}
 * 
 * @author wangwei
 */
public class FloatConvertTest {

   private Convert<Float> handler;
    
    @Before
    public void setUp(){
        this.handler = new FloatConvert();
    }
    
    @Test
    public void testParse() throws Exception{

        String test = "20";
        Float testFloat = handler.parse(test);
        assertEquals(20, testFloat, 0.0001);

        test = "0.99";
        testFloat = handler.parse(test);
        assertEquals(0.99, testFloat, 0.0001);
    }

    @Test(expected=ConvertException.class)
    public void testParseFail() throws Exception{
        String test = "20.0.0";
        handler.parse(test);
    }
}
