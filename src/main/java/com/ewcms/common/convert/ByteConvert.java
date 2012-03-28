/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.convert;

/**
 * 转换成{@link Byte}
 * 
 * @author WangWei
 */
class ByteConvert extends NumberConvert<Byte> {

    @Override
    public Byte parse(String value) throws ConvertException {
        try {
            return Byte.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ConvertException(e);
        }
    }

	@Override
	protected Byte toValue(Number number) {
		return number.byteValue();
	}
}
