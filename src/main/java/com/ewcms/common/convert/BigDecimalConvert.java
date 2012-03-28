/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *  转换成{@link BigDecimal}
 *
 *  @author WangWei
 */
class BigDecimalConvert extends NumberConvert<BigDecimal> {

    @Override
    public BigDecimal parse(String value)throws ConvertException{
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new ConvertException(e);
        }
    }

    @Override
	public BigDecimal parseFor(String patter, String value) throws ConvertException {
		try {
			DecimalFormat numberFormat = new DecimalFormat(patter);
			numberFormat.setParseBigDecimal(true);
			Number number = numberFormat.parse(value);
			return toValue(number);
		} catch (ParseException e) {
			throw new ConvertException(e);
		}
	}
    
	@Override
	protected BigDecimal toValue(Number number) {
		return (BigDecimal)number;
	}
}
