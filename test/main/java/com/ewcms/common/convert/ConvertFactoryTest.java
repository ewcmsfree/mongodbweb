/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 单元测试{@link ConvertFactory}
 * 
 * @author wangwei
 */
public class ConvertFactoryTest {

	private static final List<Class<?>> HAVE_TYPES;
	
	@Test
	public void testInstanceNotGMT(){
		ConvertFactory factory = ConvertFactory.instance;
		Assert.assertFalse(factory.isGMT());
	}
	
	@Test
	public void testInstanceGMT(){
		ConvertFactory factory = ConvertFactory.instanceGMT;
		Assert.assertTrue(factory.isGMT());
	}
	
    @Test
    public void testConvert() throws Exception {
    	for(Class<?> type :HAVE_TYPES){
    		assertNotNull(ConvertFactory.instance.convert(type));	
    	}
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testNullConvert() throws Exception {
        ConvertFactory.instance.convert(Object.class);
    }
    
    static{
		HAVE_TYPES = new ArrayList<Class<?>>();
		HAVE_TYPES.add(Byte.class);
		HAVE_TYPES.add(byte.class);
		HAVE_TYPES.add(Integer.class);
		HAVE_TYPES.add(int.class);
		HAVE_TYPES.add(Short.class);
		HAVE_TYPES.add(short.class);
		HAVE_TYPES.add(Long.class);
		HAVE_TYPES.add(long.class);
		HAVE_TYPES.add(Float.class);
		HAVE_TYPES.add(float.class);
		HAVE_TYPES.add(Double.class);
		HAVE_TYPES.add(double.class);
		HAVE_TYPES.add(Boolean.class);
		HAVE_TYPES.add(boolean.class);
		HAVE_TYPES.add(java.math.BigDecimal.class);
		HAVE_TYPES.add(java.math.BigInteger.class);
		HAVE_TYPES.add(java.util.Date.class);
		HAVE_TYPES.add(java.sql.Date.class);
		HAVE_TYPES.add(java.sql.Time.class);
		HAVE_TYPES.add(java.sql.Timestamp.class);
		HAVE_TYPES.add(String.class);
	}
}
