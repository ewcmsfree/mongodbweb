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
 * 单元测试{@link DoubleConvert}
 * 
 * @author 王伟
 */
public class DoubleConvertTest {

    private Convert<Double> handler;
    
    @Before
    public void setUp(){
        this.handler = new DoubleConvert();
    }
    
    @Test
    public void testParse() throws Exception{

        String test = "200";
        Double testDouble = (Double) handler.parse(test);
        assertEquals(200, testDouble.doubleValue(), 0);

        test = "0.23";
        testDouble = (Double) handler.parse(test);
        assertEquals(0.23, testDouble, 0);
    }

    @Test(expected=ConvertException.class)
    public void testParseFail() throws Exception{
        String test = "200.2.2";
        handler.parse(test);
    }
}
