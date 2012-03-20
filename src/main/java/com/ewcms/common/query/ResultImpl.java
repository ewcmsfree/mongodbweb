/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 实现查询结果{@code Result}
 * 
 * @author wangwei
 *
 * @param <T>
 */
public class ResultImpl<T> implements Result<T> {

	private final List<T> content;
	private final Map<String,?> addition;

	/**
	 * 创建{@code ResultImpl}实现
	 * 
	 * @param content 查询内容
	 */
	public ResultImpl(List<T> content) {
		this(content,new HashMap<String,Object>());
	}
	
	/**
	 * 创建{@code ResultImpl}实现
	 * 
	 * @param content 查询内容
	 * @param addition 附加查询内容
	 */
	public ResultImpl(List<T> content,Map<String,?> addition){
		this.content = content;
		this.addition = addition;
	}

	@Override
	public int getNumberOfElements() {
		return content.size();
	}

	@Override
	public Iterator<T> iterator() {
		return content.iterator();
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
	public Map<String,?> getAddition() {
		return addition;
	}

	@Override
	public boolean hasAddition() {
		return !addition.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addition == null) ? 0 : addition.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultImpl<T> other = (ResultImpl<T>) obj;
		if (addition == null) {
			if (other.addition != null)
				return false;
		} else if (!addition.equals(other.addition))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResultImpl [ content=" + content + ", addition=" + addition + "]";
	}
}
