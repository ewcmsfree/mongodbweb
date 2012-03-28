/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 转换成{@link Short}
 *  
 * @author WangWei
 */
class ShortConvert extends NumberConvert<Short> {
    
    @Override
    public Short parse(String value)throws ConvertException {
        try{
            return Short.valueOf(value);
        }catch(NumberFormatException e){
            throw new ConvertException(e);
        }
    }

	@Override
	protected Short toValue(Number number) {
		return number.shortValue();
	}
    
    
    
   
}
