/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 转换成{@link String}
 * 
 * @author WangWei
 */
class StringConvert implements Convert<String> {

    @Override
    public String parse(String value) {
        return value;
    }

    @Override
	public String parseFor(String patter, String value) throws ConvertException {
		return value;
	}
	
    @Override
    public String toString(String value) {
        return value;
    }
    
    @Override
	public String format(String patter, String value) {
		return value;
	}
}
