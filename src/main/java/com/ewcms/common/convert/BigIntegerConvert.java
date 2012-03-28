/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *  转换成{@link BigInteger}
 *
 *  @author WangWei
 */
class BigIntegerConvert extends NumberConvert<BigInteger> {

    @Override
    public BigInteger parse(String value) throws ConvertException {
        try {
            return new BigInteger(value);
        } catch (NumberFormatException e) {
            throw new ConvertException(e);
        }
    }

    @Override
	public BigInteger parseFor(String patter, String value) throws ConvertException {
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
	protected BigInteger toValue(Number number) {
		return ((BigDecimal)number).toBigInteger();
	}
}
