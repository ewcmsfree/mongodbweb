/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * 单元测试{@link LongConvert}
 * 
 * @author wangwei
 */
public class LongConvertTest {

    private Convert<Long> handler;
   
    @Before
    public void setUp(){
        this.handler = new LongConvert();
    }
    
    @Test
    public void testParse()throws Exception{

        String test = "1000000";
        Long testLong = handler.parse(test);
        assertTrue(1000000 == testLong.longValue());
    }
    
    @Test(expected=ConvertException.class)
    public void testParseFail() throws Exception{

        String test = "1000.0";
        handler.parse(test);
    }
}
