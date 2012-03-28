/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ewcms.common.convert.StringConvert;

/**
 * 单元测试{@link StringConvert}
 * 
 * @author wangwei
 */
public class StringConvertTest {

    private Convert<String> handler;
    
    @Before
    public void setUp(){
        handler = new StringConvert();
    }
    
    @Test
    public void testParse() throws Exception{

        String test = "wangwei";

        assertEquals("wangwei", handler.parse(test));
    }
    
    @Test
    public void testParseForFormat() throws Exception{

        String test = "wangwei";

        assertEquals("wangwei", handler.parseFor("",test));
    }
    
    @Test
    public void testToString(){
        assertEquals(handler.toString("wangwei"),"wangwei");
    }
    
    @Test
    public void testFormat(){
        assertEquals(handler.format("","wangwei"),"wangwei");
    }
}
