/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 转换成{@link Long}
 *  
 * @author WangWei
 */
class LongConvert extends NumberConvert<Long> {

    @Override
    public Long parse(String value)throws ConvertException {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ConvertException(e);
        }
    }
    
	@Override
	protected Long toValue(Number number) {
		return number.longValue();
	}
}
