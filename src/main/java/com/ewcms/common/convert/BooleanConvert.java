/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 *  转换成{@link Boolean}
 *       
 *  @author WangWei
 */
class BooleanConvert implements Convert<Boolean> {

    @Override
    public Boolean parse(String value)throws ConvertException {
        return Boolean.valueOf(value);
    }
    
    @Override
	public Boolean parseFor(String patter, String value)throws ConvertException {
		 return Boolean.valueOf(value);
	}

    @Override
    public String toString(Boolean value) {
        return value.toString();
    }

	@Override
	public String format(String patter, Boolean value) {
		return value.toString();
	}

}
