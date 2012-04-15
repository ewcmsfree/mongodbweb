/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.common.query.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.BasicBSONList;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 包装{@link Criteria}，过滤空的条件规则。
 * 
 * @author wangwei
 *
 */
public class CriteriaWapper extends Criteria {

	private List<Criteria> criteriaChain;
	
	public CriteriaWapper() {
		super();
		this.criteriaChain = new ArrayList<Criteria>();
	}

	public CriteriaWapper(String key) {
		super(key);
		this.criteriaChain = new ArrayList<Criteria>();
		this.criteriaChain.add(this);
	}

	protected CriteriaWapper(List<Criteria> criteriaChain, String key) {
		super(criteriaChain, key);
	}

	public static CriteriaWapper where(String key) {
		return new CriteriaWapper(key);
	}
	
	/**
	 * 设置本地{@code criteriaChain}保持与{@code super.criteriaChina}一致。
	 * 
	 * @param criteria
	 */
	public Criteria orOperator(Criteria... criteria) {
		super.orOperator(criteria);
		BasicBSONList bsonList = createCriteriaList(criteria);
		criteriaChain.add(new Criteria("$or").is(bsonList));
		return this;
	}

	/**
	 * 设置本地{@code criteriaChain}保持与{@code super.criteriaChina}一致。
	 * 
	 * @param criteria
	 */
	public Criteria norOperator(Criteria... criteria) {
		super.norOperator(criteria);
		BasicBSONList bsonList = createCriteriaList(criteria);
		criteriaChain.add(new Criteria("$nor").is(bsonList));
		return this;
	}

	/**
	 * 设置本地{@code criteriaChain}保持与{@code super.criteriaChina}一致。
	 * 
	 * @param criteria
	 */
	public Criteria andOperator(Criteria... criteria) {
		super.andOperator(criteria);
		BasicBSONList bsonList = createCriteriaList(criteria);
		criteriaChain.add(new Criteria("$and").is(bsonList));
		return this;
	}
	
	private BasicBSONList createCriteriaList(Criteria[] criteria) {
		BasicBSONList bsonList = new BasicBSONList();
		for (Criteria c : criteria) {
			bsonList.add(c.getCriteriaObject());
		}
		return bsonList;
	}

	public CriteriaWapper and(String key) {
		return new CriteriaWapper(this.criteriaChain, key);
	}
	
	protected DBObject getSingleCriteriaObject() {
		DBObject dbo = super.getSingleCriteriaObject();
		return hasCriteria(dbo) ? dbo : new BasicDBObject();
	}

	/**
	 * 判断是否含有条件规则
	 * 
	 * @param dbo
	 * @return
	 */
	private boolean hasCriteria(DBObject dbo) {
		boolean has = false;
		for (String key : dbo.keySet()) {
			Object v = dbo.get(key);
			if (v instanceof DBObject) {
				has = has || !((DBObject) v).keySet().isEmpty();
			} else {
				has = has || true;
			}
		}
		return has;
	}
}
