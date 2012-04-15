/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query;

import java.util.ArrayList;

import com.ewcms.common.query.Sort.Direction;

/**
 * 实现分页信息{@link Pagination}
 * 
 * @author wangwei
 */
public class PaginationImpl implements Pagination{

	private final int size;
	private final int number;
	private final Sort sort;
	
	/**
	 * 创建{@link PaginationImpl}实现
	 * 
	 * @param size 页面大小,必须大于零。
	 * @param number 页数从零开始。
	 */
	public PaginationImpl(int size,int number){
		this(size,number,new Sort(new ArrayList<Sort.Order>()));
	}
	
	/**
	 * 创建{@link PaginationImpl}实现
	 * 
	 * @param size 页面大小,必须大于零。
	 * @param number 页数从零开始。
	 * @param direction 排序方向
	 * @param properties 属性名
	 */
	public PaginationImpl(int size,int number,Direction direction,String... properties){
		this(size,number,new Sort(direction,properties));
	}
	
	/**
	 *  创建{@link PaginationImpl}实现
	 *  
	 * @param size 页面大小,必须大于零。
	 * @param number 页数从零开始。
	 * @param sort 排序对象
	 */
	public PaginationImpl(int size,int number,Sort sort){
		if(size < 1) {
			throw new IllegalArgumentException("page size must gt zero!");
		}
		if(number < 0) {
			throw new IllegalArgumentException("page number must gte zero!");
		}

		this.size = size;
		this.number = number;
		this.sort = sort;
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
	public int getOffset() {
		return number * size;
	}

	@Override
	public Sort getSort() {
		return sort;
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
