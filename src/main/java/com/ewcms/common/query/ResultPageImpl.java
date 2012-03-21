/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现分页查询集合{@code ResultPage}
 * 
 * @author wangwei
 *
 * @param <T>
 */
public class ResultPageImpl<T> extends ResultImpl<T> implements ResultPage<T> {
	
	private final int number;
	private final int size;
	private final long total;
	private final Map<String,?> pageAddition;

	/**
	 * 创建{@code ResultPageImpl}实现
	 * 
	 * @param page 页信息
	 * @param total 总记录数
	 * @param content 内容
	 */
	public ResultPageImpl(Pagination page,long total,List<T> content){
		this(page,total,content,new HashMap<String,Object>());
	}
	
	/**
	 * 创建{@code ResultPageImpl}实现
	 * 
	 * @param page 页信息
	 * @param total 总记录数
	 * @param content 内容
	 * @param pageAddition 当前页附加内容
	 */
	public ResultPageImpl(Pagination page, long total, List<T> content, Map<String,?> pageAddition) {
		this(page,total,content,pageAddition,new HashMap<String,Object>());
	}
	
	/**
	 * 创建{@code ResultPageImpl}实现
	 * 
	 * @param page 页信息
	 * @param total 总记录数
	 * @param content 内容
	 * @param pageAddition 当前页附加内容
	 * @param addition 总附加内容
	 */
	public ResultPageImpl(Pagination page,long total,List<T> content,Map<String,?> pageAddition,Map<String,?> addition){
		super(content,addition);
		
		this.number = page.getNumber();
		this.size = page.getSize();
		this.total = total;
		this.pageAddition = pageAddition == null ? new HashMap<String,Object>():pageAddition;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getTotalPages() {
		return (int)(total + size - 1) / size;
	}

	@Override
	public long getTotalElements() {
		return total;
	}

	@Override
	public boolean hasPreviousPage() {
		return number > 0;
	}

	@Override
	public boolean isFirstPage() {
		return number == 0;
	}

	@Override
	public boolean hasNextPage() {
		return number < (getTotalPages() - 1);
	}

	@Override
	public boolean isLastPage() {
		return number == (getTotalPages() - 1);
	}

	@Override
	public Map<String, ?> getPageAddition() {
		return this.pageAddition;
	}

	@Override
	public boolean hasPageAddition() {
		return !pageAddition.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + number;
		result = prime * result
				+ ((pageAddition == null) ? 0 : pageAddition.hashCode());
		result = prime * result + size;
		result = prime * result + (int) (total ^ (total >>> 32));
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultPageImpl<T> other = (ResultPageImpl<T>) obj;
		if (number != other.number)
			return false;
		if (pageAddition == null) {
			if (other.pageAddition != null)
				return false;
		} else if (!pageAddition.equals(other.pageAddition))
			return false;
		if (size != other.size)
			return false;
		if (total != other.total)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResultPageImpl [number=" + number + ", size=" + size
				+ ", total=" + total + ", pageAddition=" + pageAddition + "]";
	}
	
}
