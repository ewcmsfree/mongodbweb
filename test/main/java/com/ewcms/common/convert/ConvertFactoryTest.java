/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 *  测试ParseTypeHandlerFactory对象
 * 
 * @author 王伟
 */
public class ConvertFactoryTest {

    @Test
    public void testConvertHandler() throws Exception {
        assertNotNull(ConvertFactory.instance.convert(Byte.class));
        assertNotNull(ConvertFactory.instance.convert(Integer.class));
        assertNotNull(ConvertFactory.instance.convert(Short.class));
        assertNotNull(ConvertFactory.instance.convert(Long.class));
        assertNotNull(ConvertFactory.instance.convert(Boolean.class));
        assertNotNull(ConvertFactory.instance.convert(Float.class));
        assertNotNull(ConvertFactory.instance.convert(Double.class));
        assertNotNull(ConvertFactory.instance.convert(java.math.BigDecimal.class));
        assertNotNull(ConvertFactory.instance.convert(java.math.BigInteger.class));
        assertNotNull(ConvertFactory.instance.convert(java.util.Date.class));
        assertNotNull(ConvertFactory.instance.convert(java.sql.Date.class));
        assertNotNull(ConvertFactory.instance.convert(java.sql.Time.class));
        assertNotNull(ConvertFactory.instance.convert(java.sql.Timestamp.class));
        assertNotNull(ConvertFactory.instance.convert(String.class));
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testNullConvertHandler() throws Exception {
        ConvertFactory.instance.convert(Object.class);
    }
}
