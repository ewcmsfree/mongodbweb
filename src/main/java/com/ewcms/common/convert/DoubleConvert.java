/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 转换成{@link Double}
 *  
 * @author WangWei
 */
class DoubleConvert extends NumberConvert<Double> {
    
    @Override
    public Double parse(String value)throws ConvertException {
        try{
            return Double.valueOf(value);
        }catch(NumberFormatException e){
            throw new ConvertException(e);
        }
    }

	@Override
	protected Double toValue(Number number) {
		return number.doubleValue();
	}
}
