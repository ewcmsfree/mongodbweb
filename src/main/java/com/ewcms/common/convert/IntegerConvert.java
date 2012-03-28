/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 转换成{@link Integer}
 *  
 * @author wangwei
 */
class IntegerConvert extends NumberConvert<Integer> {
    
    @Override
    public Integer parse(String value)throws ConvertException {
        try{
            return Integer.valueOf(value);
        }catch(NumberFormatException e){
            throw new ConvertException(e);
        }
    }

	@Override
	protected Integer toValue(Number number) {
		return number.intValue();
	}
}
