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
	 * @param size 页面大小,必须大于零。
	 * @param number 页数从零开始。
	 */
	public PaginationImpl(int size,int number){
		if(size < 1) {
			throw new IllegalArgumentException("page size must gt zero!");
		}
		
		if(number < 0) {
			throw new IllegalArgumentException("page number must gte zero!");
		}
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaginationImpl other = (PaginationImpl) obj;
		if (number != other.number)
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaginationImpl [size=" + size + ", number=" + number + "]";
	}
}
