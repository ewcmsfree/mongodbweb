/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.ewcms.common.convert.ConvertException;
import com.ewcms.common.query.EasyQuery;
import com.ewcms.common.query.Pagination;
import com.ewcms.common.query.Result;
import com.ewcms.common.query.ResultImpl;
import com.ewcms.common.query.ResultPage;
import com.ewcms.common.query.ResultPageImpl;
import com.ewcms.common.query.Sort;

/**
 * Mongo数据库查询实现
 * 
 * @author wangwei
 * 
 */
public class EasyQueryImpl<T> implements EasyQuery<T> {

	private final MongoOperations operations;
	private final Criteria criteria;
	private final Class<T> entityClass;

	protected EasyQueryImpl(MongoOperations operations, Criteria criteria,Class<T> entityClass) {
		this.operations = operations;
		this.criteria = criteria;
		this.entityClass = entityClass;
	}

	@Override
	public Result<T> find() {
		List<T> list = operations.find(Query.query(criteria), entityClass);
		return new ResultImpl<T>(list);
	}

	@Override
	public Result<T> findSort(Sort sort) {
		Query query = Query.query(criteria);
		setSort(query,sort);
		List<T> list = operations.find(query, entityClass);
		return new ResultImpl<T>(list);
	}
	
	private void setSort(Query query,Sort sort){
		for(Sort.Order order : sort){
			if(order.isAscending()){
				query.sort().on(order.getProperty(),Order.ASCENDING);	
			}else{
				query.sort().on(order.getProperty(),Order.DESCENDING);	
			}
		}
	}

	@Override
	public ResultPage<T> findPage(Pagination page) {
		Query query = Query.query(criteria);
		long count = operations.count(query, entityClass);
		
		setSort(query,page.getSort());
		
		query.skip(page.getOffset());
		query.limit(page.getSize());
		
		List<T> list = operations.find(query, entityClass);
		
		return new ResultPageImpl<T>(page,count,list);
	}


	/**
	 * 构建有条件查询
	 * 
	 * @author wangwei
	 *
	 * @param <T>
	 */
	public static class Where<T> {
		
		private static final String DEFAULT_DELIMITER = ",";
		
		private Criteria criteria;
		private Class<T> entityClass;
		private PropertyConvert convert;

		/**
		 * 创建{@link Where}实现
		 * 
		 * @param entityClass 不能为{@literal null}
		 */
		public Where(Class<T> entityClass){
			if(entityClass == null){
				throw new IllegalArgumentException("entityType must not null!");
			}
			criteria = new Criteria();
			this.entityClass = entityClass;
			this.convert = new PropertyConvert(entityClass);
		}
		
		/**
		 * 创建{@link Where}实现
		 * 
		 * @param entityClass 不能为{@literal null}
		 * @param key 不能为{@literal null}
		 */
		public Where(Class<T> entityClass,String key) {
			if(entityClass == null){
				throw new IllegalArgumentException("entityType must not null!");
			}
			if(key == null){
				throw new IllegalArgumentException("key must not null!");
			}
			this.criteria = CriteriaWapper.where(key);
			this.entityClass = entityClass;
			this.convert = new PropertyConvert(entityClass);
		}

		/**
		 * 用户key创建criteria
		 * 
		 * @param key 不能为{@literal null}
		 * @return
		 */
		public Where<T> and(String key) {
			criteria = criteria.and(key);
			return this;
		}

		/**
		 * 在{@link setCriteriaOperation}使用，具体实现由各具体条件实现
		 * 
		 * @author wangwei
		 *
		 */
		interface CriteriaOperation<T> {
			
			/**
			 * 条件操作
			 * 
			 * @param o
			 */
			void Operator(T o);
		}

		/**
		 * 创建相等({@code ==})规则
		 * 
		 * @param o
		 * @return
		 */
		public Where<T> is(Object o) {
			setCriteriaOperation(o, new CriteriaOperation<Object>() {
				@Override
				public void Operator(Object o) {
					criteria.is(o);
				}
			});
			return this;
		}

		/**
		 * 设置查询规则
		 * 
		 * @param o
		 * @param operation 规则操作{@link CriteraOperation}
		 */
		protected void setCriteriaOperation(Object o, CriteriaOperation<Object> operation) {
			try {
				Object c = convert.convert(criteria.getKey(), o);
				if(c != null){
					operation.Operator(c);	
				}
			} catch (ConvertException e) {
				// TODO 设置错误信息
				throw new RuntimeException(e);
			}
		}

		/**
		 * 创建相等({@code ==})规则，值可根据设定的模版转换
		 * 
		 * @param o
		 * @param patter 可以为{@literal null}
		 * @return
		 */
		public Where<T> is(Object o, String patter) {
			setCriteriaOperation(o, new CriteriaOperation<Object>() {
				@Override
				public void Operator(Object o) {
					criteria.is(o);
				}
			},patter);
			return this;
		}
		
		protected void setCriteriaOperation(Object o, CriteriaOperation<Object> operation,String patter) {
			try {
				Object c = convert.convertFormat(criteria.getKey(), patter, o);
				if(c != null){
					operation.Operator(c);	
				}
			} catch (ConvertException e) {
				// TODO 设置错误信息
				throw new RuntimeException(e);
			}
		}
		
		/**
		 * 创建不相等({@code !=})规则
		 * 
		 * @param o
		 * @return
		 */
		public Where<T> ne(Object o) {
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.ne(o);	
				}
			});
			return this;
		}
		
		/**
		 * 创建不相等({@code !=})规则，值可根据设定的模版转换
		 * 
		 * @param o
		 * @param patter 可以为{@literal null}
		 * @return
		 */
		public Where<T> ne(Object o,String patter){
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.ne(o);		
				}
			},patter);
			return this;
		}

		/**
		 * 创建小于({@code <})规则
		 * 
		 * @param o
		 * @return
		 */
		public Where<T> lt(Object o) {
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.lt(o);
				}
			});
			return this;
		}
		
		/**
		 * 创建小于({@code <})规则，值可根据设定的模版转换
		 * 
		 * @param o
		 * @param patter 可以为{@literal null}
		 * @return
		 */
		public Where<T> lt(Object o,String patter){
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.lt(o);
				}
			},patter);
			return this;
		}

		/**
		 * 创建小于等于({@code <=})规则
		 * 
		 * @param o
		 * @return
		 */
		public Where<T> lte(Object o) {
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.lte(o);
				}
			});
			return this;
		}

		/**
		 * 创建小于等于({@code <=})规则，值可根据设定的模版转换
		 * 
		 * @param o
		 * @param patter 可以为{@literal null}
		 * @return
		 */
		public Where<T> lte(Object o,String patter){
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.lte(o);
				}
			},patter);
			return this;
		}
		
		/**
		 * 创建大于({@code >})规则 
		 * 
		 * @param o
		 * @return
		 */
		public Where<T> gt(Object o) {
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.gt(o);		
				}
			});
			return this;
		}

		/**
		 * 创建大于({@code >})规则，值可根据设定的模版转换
		 * 
		 * @param o
		 * @param patter 可以为{@literal null}
		 * @return
		 */
		public Where<T> gt(Object o,String patter){
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.gt(o);
				}
			},patter);
			return this;
		}
		
		/**
		 * 创建大于等于({@code =>})规则 
		 * 
		 * @param o
		 * @return
		 */
		public Where<T> gte(Object o) {
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.gte(o);		
				}
			});
			return this;
		}

		/**
		 * 创建大于等于({@code =>})规则，值可根据设定的模版转换
		 * 
		 * @param o
		 * @param patter 可以为{@literal null}
		 * @return
		 */
		public Where<T> gte(Object o,String patter){
			setCriteriaOperation(o,new CriteriaOperation<Object>(){
				@Override
				public void Operator(Object o) {
					criteria.gte(o);
				}
			},patter);
			return this;
		}
		
		/**
		 * 创建两个值之间（{@code lo < x < hi}）规则
		 * 
		 * @param lo 下界
		 * @param hi 上届
		 * @return
		 */
		public Where<T> between(Object lo,Object hi){
			gt(lo);
			lt(hi);
			return this;
		}
		
		/**
		 * 创建两个值之间（{@code lo < x < hi}）规则，值可根据设定的模版转换
		 * 
		 * @param lo 下界
		 * @param hi 上届
		 * @return
		 */
		public Where<T> between(Object lo,Object hi,String patter){
			gt(lo,patter);
			lt(hi,patter);
			return this;
		}
		
		/**
		 * 创建正则表达式规则
		 * 
		 * @param re 表达式
		 * @return
		 */
		public Where<T> regex(String re) {
			if(StringUtils.hasText(re)){
				criteria.regex(re);
			}
			return this;
		}
		
		/**
		 * 创建正则表达式规则
		 * 
		 * @param re 表达式
		 * @param options
		 * @return
		 */
		public Where<T> regex(String re, String options) {
			if(StringUtils.hasText(re)){
				criteria.regex(re, options);
			}
			return this;
		}
		
		/**
		 * 创建匹配开始字符串规则,简化{@link regex}用法
		 * 
		 * @param re 匹配字符串
		 * @return
		 */
		public Where<T> likeStart(String re){
			if(StringUtils.hasText(re)){
				String startRe = (re.startsWith("^") ? re : "^" + re);
				criteria.regex(startRe);
			}
			return this;
		}
		
		/**
		 * 创建匹配任何位置字符串规则,简化{@link regex}用法
		 * 
		 * @param re 匹配字符串
		 * @return
		 */
		public Where<T> likeAny(String re){
			if(StringUtils.hasText(re)){
				criteria.regex(re);
			}
			return this;
		}
		
		/**
		 * 创建匹配结束字符串规则,简化{@link regex}用法
		 * 
		 * @param re 匹配字符串
		 * @return
		 */
		public Where<T> likeEnd(String re){
			if(StringUtils.hasText(re)){
				String endRe = (re.endsWith("$") ? re :  re + "$");
				criteria.regex(endRe);
			}
			return this;
		}
		
		/**
		 * 创建$in操作规则
		 * 
		 * @param o 匹配值数组
		 * @return
		 */
		public Where<T> in(Object... o) {
			criteria.in(o);
			return this;
		}

		/**
		 * 创建$in操作规则
		 * 
		 * @param o 匹配值集合
		 * @return
		 */
		public Where<T> in(Collection<?> c) {
			criteria.in(c);
			return this;
		}
		
		/**
		 * 创建$in操作规则，通过“,”字符，分割字符串{@code c}生成的集合进行in匹配。
		 * 
		 * @param c 匹配内容
		 * @return
		 */
		public Where<T> inSplit(String c){
			return inSplit(c,DEFAULT_DELIMITER);
		}
		
		/**
		 * 创建$in操作规则，通过指定的分割符，分割字符串{@code c}生成的集合进行in匹配。 
		 * 
		 * @param c 匹配内容
		 * @param delimiter 分割符
		 * @return
		 */
		public Where<T> inSplit(String c,String delimiter){
			setCriteriaOperation(c,delimiter,new CriteriaOperation<Collection<?>>(){
				@Override
				public void Operator(Collection<?> o) {
					criteria.in(o);
				}
			});
			return this;
		}
		
		protected void setCriteriaOperation(String c,String delimiter,CriteriaOperation<Collection<?>> operation) {
			try {
				if(StringUtils.hasText(c)){
					Collection<?> collection= convert.convertCollection(criteria.getKey(), c, delimiter);
					operation.Operator(collection);
				}
			} catch (ConvertException e) {
				// TODO 设置错误信息
				throw new RuntimeException(e);
			}
		}

		/**
		 * 创建$nin操作规则
		 * 
		 * @param o 匹配值数组
		 * @return
		 */
		public Where<T> nin(Object... o) {
			criteria.nin(o);
			return this;
		}

		/**
		 * 创建$nin操作规则
		 * 
		 * @param c 匹配值数组
		 * @return
		 */
		public Where<T> nin(Collection<?> c) {
			criteria.nin(c);
			return this;
		}
		
		/**
		 * 创建$nin操作规则，通过“,”字符，分割字符串{@code c}生成的集合进行nin匹配。
		 * 
		 * @param c 匹配内容
		 * @return
		 */
		public Where<T> ninSplit(String c){
			return ninSplit(c,DEFAULT_DELIMITER);
		}
		
		/**
		 * 创建$nin操作规则，通过指定的分割符，分割字符串{@code c}生成的集合进行nin匹配。 
		 * 
		 * @param c 匹配内容
		 * @param delimiter 分割符
		 * @return
		 */
		public Where<T> ninSplit(String c,String delimiter){
			setCriteriaOperation(c,delimiter,new CriteriaOperation<Collection<?>>(){
				@Override
				public void Operator(Collection<?> o) {
					criteria.nin(o);
				}
			});
			return this;
		}

		/**
		 * 创建$mod操作规则
		 * 
		 * @param value
		 * @param remainder
		 * @return
		 */
		public Where<T> mod(Number value, Number remainder) {
			criteria.mod(value, remainder);
			return this;
		}

		/**
		 * 创建$all操作规则
		 * 
		 * @param o
		 * @return
		 */
		public Where<T> all(Object... o) {
			criteria.all(o);
			return this;
		}
		
		/**
		 * 创建$all操作规则
		 * 
		 * @param c
		 * @return
		 */
		public Where<T> all(Collection<?> c) {
			criteria.all(c);
			return this;
		}
		
		/**
		 * 创建$all操作规则，通过“,”字符，分割字符串{@code c}生成的集合进行all匹配。
		 * 
		 * @param c 匹配内容
		 * @return
		 */
		public Where<T> allSplit(String c){
			return allSplit(c,DEFAULT_DELIMITER);
		}
		
		/**
		 * 创建$all操作规则，通过指定的分割符，分割字符串{@code c}生成的集合进行all匹配。 
		 * 
		 * @param c 匹配内容
		 * @param delimiter 分割符
		 * @return
		 */
		public Where<T> allSplit(String c,String delimiter){
			setCriteriaOperation(c,delimiter,new CriteriaOperation<Collection<?>>(){
				@Override
				public void Operator(Collection<?> o) {
					criteria.all(o);
				}
			});
			return this;
		}

		/**
		 * 创建$size操作规则
		 * 
		 * @param s
		 * @return
		 */
		public Where<T> size(int s) {
			criteria.size(s);
			return this;
		}

		/**
		 * 创建$not操作规则
		 * 
		 * @return
		 */
		public Where<T> not() {
			criteria.not();
			return this;
		}

		/**
		 * 创建$elemMatch操作规则
		 * 
		 * @param w
		 * @return
		 */
		public Where<T> elemMatch(Where<?> w) {
			criteria.elemMatch(w.criteria);
			return this;
		}

		/**
		 * 为所有提供的规则，创建$or操作规则
		 * 
		 * @param ws
		 * @return
		 */
		public Where<T> orOperator(Where<?>... ws) {
			criteria.orOperator(getCriterias(ws));
			return this;
		}

		private Criteria[] getCriterias(Where<?>... ws){
			Criteria[] c = new Criteria[ws.length];
			for(int i = 0 ; i < ws.length ; i++){
				c[i] = ws[i].criteria;
			}
			return c;
		}
		
		/**
		 * 为所有提供的规则，创建$nor操作规则
		 * 
		 * @param ws
		 * @return
		 */
		public Where<T> norOperator(Where<?>... ws) {
			criteria.norOperator(getCriterias(ws));
			return this;
		}

		/**
		 * 为所有提供的规则，创建$and操作规则
		 * 
		 * @param ws
		 * @return
		 */
		public Where<T> andOperator(Where<?>... ws) {
			criteria.andOperator(getCriterias(ws));
			return this;
		}
		
		/**
		 * 构建{@linkp EasyQuery}实现
		 * 
		 * @param operations 不能为{@literal null}
		 * @return
		 */
		public EasyQuery<T> build(MongoOperations operations) {
			if(operations == null){
				throw new IllegalArgumentException("operations must not null!");
			}
			return new EasyQueryImpl<T>(operations, criteria, entityClass);
		}
	}
}
