/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query.mongo;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;

import com.ewcms.common.convert.ConvertException;
import com.ewcms.common.query.Pagination;
import com.ewcms.common.query.Query;
import com.ewcms.common.query.Result;
import com.ewcms.common.query.ResultImpl;
import com.ewcms.common.query.ResultPage;

/**
 * Mongo数据库查询实现
 * 
 * @author wangwei
 *
 */
public class QueryImpl<T> implements Query<T>{

	private final MongoOperations operations;
	private final Criteria criteria;
	private final Class<T> entityType;
	
	protected QueryImpl(MongoOperations operations,Criteria criteria,Class<T> entityType){
		this.operations = operations;
		this.criteria = criteria;
		this.entityType = entityType;
	}
	
	@Override
	public Result<T> find() {
		List<T> list = operations.find(
				new org.springframework.data.mongodb.core.query.Query(criteria)
				,entityType);
		return new ResultImpl<T>(list);
	}
	
	@Override
	public ResultPage<T> find(Pagination page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static class NoWhere<T>{
		private final MongoOperations operations;
		private final Class<T> entityType;
		
		@SuppressWarnings("unchecked")
		public NoWhere(MongoOperations operations){
			this.operations = operations;
			ParameterizedType genericSuperclass =(ParameterizedType) getClass().getGenericSuperclass();
			entityType =(Class<T>) genericSuperclass.getActualTypeArguments()[0];
		}
		
		public Query<T> build(){
			return new QueryImpl<T>(operations,new Criteria(),entityType);
		}
	}
	
	public static class Where<T> {
		private MongoOperations operations;
		private Criteria criteria;
		private Class<T> entityType;
		private PropertyConvert convert;
		
		private void init(MongoOperations operations,Class<T> entityType,String key){
			this.operations = operations;
			this.criteria = Criteria.where(key);
			this.entityType = entityType;
			this.convert = new PropertyConvert(entityType);
		}
		
		@SuppressWarnings("unchecked")
		public Where(MongoOperations operations,String key){
			 ParameterizedType genericSuperclass =(ParameterizedType) getClass().getGenericSuperclass();
			 Class<T> entityType =(Class<T>) genericSuperclass.getActualTypeArguments()[0];
			 init(operations,entityType,key);
		}
		
		public Where(MongoOperations operations,Class<T> entityType,String key){
			init(operations,entityType,key);
		}
		
		public Where<T> and(String key) {
			criteria = criteria.and(key);
			return this;
		}
		
		public Where<T> is(Object o) {
			if(o != null){
				criteria.is(o);	
			}
			return this;
		}
		
		public Where<T> is(String o) throws ConvertException{
			Object c = convert.convert(criteria.getKey(),o);
			criteria.is(c);
			return this;
		}
		
		public Where<T> is(String o,String patter)throws ConvertException{
			Object c = convert.convertFormat(criteria.getKey(),patter,o);
			criteria.is(c);
			return this;
		}
		
		public Where<T> ne(Object o) {
			criteria.is(o);
			return this;
		}
		
		public Where<T> lt(Object o) {
			criteria.lt(o);
			return this;
		}
		
		public Where<T> lte(Object o) {
			criteria.lte(o);
			return this;
		}
		
		public Where<T> gt(Object o) {
			criteria.gt(o);
			return this;
		}
		
		public Where<T> gte(Object o) {
			criteria.gte(o);
			return this;
		}
		
		public Where<T> in(Object... o){
			criteria.in(o);
			return this;
		}
		
		public Where<T> in(Collection<?> c) {
			criteria.in(c);
			return this;
		}
		
		public Where<T> nin(Object... o) {
			criteria.nin(o);
			return this;
		}
		
		public Where<T> nin(Collection<?> c) {
			criteria.nin(c);
			return this;
		}
		
		public Where<T> mod(Number value, Number remainder) {
			criteria.mod(value,remainder);
			return this;
		}
		
		public Where<T> all(Object... o) {
			criteria.all(o);
			return this;
		}
		
		public Where<T> all(Collection<?> c){
			criteria.all(c);
			return this;
		}
		
		public Where<T> size(int s) {
			criteria.size(s);
			return this;
		}
		
		public Where<T> exists(boolean b) {
			criteria.exists(b);
			return this;
		}
		
		public Where<T> type(int t) {
			criteria.type(t);
			return this;
		}
		
		public Where<T> not() {
			criteria.not();
			return this;
		}
		
		public Where<T> regex(String re) {
			criteria.regex(re);
			return this;
		}
		
		public Where<T> regex(String re, String options) {
			criteria.regex(re,options);
			return this;
		}
		
		public Where<T> elemMatch(Criteria c) {
			criteria.elemMatch(c);
			return this;
		}
		
		public Where<T> orOperator(Criteria... c) {
			criteria.orOperator(c);
			return this;
		}
		
		public Where<T> norOperator(Criteria... c) {
			criteria.norOperator(c);
			return this;
		}
		
		public Where<T> andOperator(Criteria... c) {
			criteria.andOperator(c);
			return this;
		}
		
		public Query<T> build(){
			return new QueryImpl<T>(operations,criteria,entityType);
		}
	}
}
