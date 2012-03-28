/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ewcms.common.convert.BooleanConvert;

/**
 * 单元测试{@link BooleanConvert}
 * 
 * @author wangwei
 */
public class BooleanConvertTest {

    private Convert<Boolean> handler;
    
    @Before
    public void setUp(){
        handler = new BooleanConvert();
    }
    
    @Test
    public void testParse() throws Exception{

        assertTrue(handler.parse("TRUE"));
        assertTrue(handler.parse("True"));
        assertTrue(handler.parse("tRuE"));

        assertFalse(handler.parse("FALSE"));
        assertFalse(handler.parse("False"));
        assertFalse(handler.parse("F"));
        assertFalse(handler.parse("sdfw"));
    }
    
    @Test
    public void testParseForFormat()throws Exception{
    	assertTrue(handler.parseFor("","TRUE"));
    }
    
    @Test
    public void testToString(){
        
        assertEquals(handler.toString(Boolean.valueOf(true)),"true");
        assertEquals(handler.toString(Boolean.valueOf(false)),"false");
    }
    
    @Test
    public void testFormat(){
    	assertEquals(handler.format("",Boolean.valueOf(true)),"true");
    }
}
