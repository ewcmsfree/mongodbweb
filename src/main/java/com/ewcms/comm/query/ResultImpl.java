/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.comm.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class ResultImpl<T> implements Result<T> {

	private final int number;
	private final int size;
	private final int total;
	private final List<T> content;
	private final List<T> footer;

	public ResultImpl(List<T> content) {
		this(0,content.size(),content.size(),content);
	}

	public ResultImpl(int number,int size,int total,List<T> content){
		this(number,size,total,content,new ArrayList<T>());
	}
	
	public ResultImpl(int number,int size, int total, List<T> content, List<T> footer) {
		Assert.isTrue(number >= 0, "number < 0");
		Assert.isTrue(total >= 0, "total < 0");
		Assert.isTrue(size > 0, "size < 0");

		this.number = number;
		this.total = total;
		this.size = size;
		this.content = content;
		this.footer = footer;
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
		return (total + size - 1) / size;
	}

	@Override
	public int getNumberOfElements() {
		return content.size();
	}

	@Override
	public int getTotalNumbers() {
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
		return number < getTotalPages();
	}

	@Override
	public boolean isLastPage() {
		return number == (getTotalPages()-1);
	}

	@Override
	public List<T> getContent() {
		return content;
	}

	@Override
	public boolean hasContent() {
		return !content.isEmpty();
	}

	@Override
	public List<T> getFooter() {
		return footer;
	}

	@Override
	public boolean hasFooter() {
		return !footer.isEmpty();
	}
}
