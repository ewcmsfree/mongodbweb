/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query;

/**
 * 实现分页信息{@link Pagination}
 * 
 * @author wangwei
 */
public class PaginationImpl implements Pagination{

	private final int size;
	private final int number;
	
	/**
	 * 构造{@link PaginationImpl}
	 * 
	 * @param size 页面大小
	 * @param number 页数
	 */
	public PaginationImpl(int size,int number){
		this.size = size;
		this.number = number;
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
