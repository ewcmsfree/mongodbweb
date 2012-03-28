/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 转换成{@link Float}
 *  
 * @author WangWei
 */
class FloatConvert extends NumberConvert<Float> {
    
    @Override
    public Float parse(String value) throws ConvertException{
        try{
            return Float.valueOf(value);
        }catch(NumberFormatException e){
            throw new ConvertException(e);
        }
    }

	@Override
	protected Float toValue(Number number) {
		return number.floatValue();
	}
}
