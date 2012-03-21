/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * 查询排序选项。你提供一个排序属性列表，属性不能为{@link null}或空字符串。缺省按照升序排列{@link  Sort#DEFAULT_DIRECTION}
 * 
 * @author wangwei
 *
 */
public class Sort implements Iterable<com.ewcms.common.query.Sort.Order>{

	public static final Direction DEFAULT_DIRECTION = Direction.ASC;
	
	private List<Order> orders;
	
	/**
	 * 创建{@link Sort}实现
	 * 
	 * @param orders order不能为{@link null}或内容{@literal null}
	 */
	public Sort(List<Order> orders){
		if (null == orders || orders.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
		}
		
		this.orders = orders;
	}
	
	/**
	 * 创建{@link Sort}实现
	 * 
	 * @param orders
	 */
	public Sort(Order...orders){
		this(Arrays.asList(orders));
	}
	
	/**
	 * 创建{@link Sort}实现
	 * 
	 * @param direction 缺省是{@value Sort#DEFAULT_DIRECTION}
	 * @param properties 属性集合不能为{@literal null}或内容{@literal null}或空字符串
	 */
	public Sort(Direction direction,List<String> properties){
		if (properties == null || properties.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
		}
		
		this.orders = new ArrayList<Order>(properties.size());
		for(String property : properties){
			orders.add(new Order(direction,property));
		}
	}
	
	/**
	 * 创建{@link Sort}实现
	 * 
	 * @param direction
	 * @param properties
	 */
	public Sort(Direction direction,String... properties){
		this(direction,Arrays.asList(properties));
	}
	
	/**
	 * 创建{@link Sort}实现。排序缺省为{@value Sort#DEFAULT_DIRECTION} 
	 * 
	 * @param properties 属性集合不能为{@literal null}或内容{@literal null}或空字符串
	 */
	public Sort(String... properties){
		this(DEFAULT_DIRECTION,properties);
	}
	
	/**
	 * 把当前的{@link Sort}和传入的{@link Sort}组成一个新的{@link Sort}返回。
	 * 
	 * @param sort 能是{@literal null}
	 * @return
	 */
	public Sort and(Sort sort){
		if(sort == null){
			return this;
		}
		
		List<Order> these = new ArrayList<Order>(orders);
		for(Order order : sort){
			these.add(order);
		}
		return new Sort(these);
	}
	
	/**
	 * 返回属性注册的排序
	 * 
	 * @param property 属性名
	 * @return 不存在返回{@literal null}
	 */
	public Order getOrderFor(String property){
		
		for(Order order : orders){
			if(order.getProperty().equals(property)){
				return order;
			}
		}
		
		return null;
	}
	
	@Override
	public Iterator<Order> iterator() {
		return orders.iterator();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		Sort other = (Sort) obj;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sort [orders=" + orders + "]";
	}

	/**
	 * 排序方向
	 * 
	 * @author wangwei
	 */
	public enum Direction {
		ASC,DEC;
		
		public static Direction fromString(String value){
			try{
				return Direction.valueOf(value.toUpperCase(Locale.US));
			}catch(Exception e){
				throw new IllegalArgumentException(String.format(
						"Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
			}
		}
	}
	
	/**
	 * 实现属性排序
	 * 
	 * @author wangwei
	 *
	 */
	public static class Order{
		private final Direction direction;
		private final String property;
		
		/**
		 * 创建{@link Order}实现。如果direction为{@literal null}缺省为{@value Sort#DEFAULT_DIRECTION}
		 * 
		 * @param direction 能为{@literal null}
		 * @param property 不能为{@literal null}或空字符串
		 */
		public Order(Direction direction,String property){
			
			if (property == null || "".equals(property.trim())) {
				throw new IllegalArgumentException("PropertyPath must not null or empty!");
			}
			
			this.direction =  direction == null ? DEFAULT_DIRECTION : direction;
			this.property = property;
		}
		
		/**
		 * 创建{@link Order}实现。排序缺省为{@value Sort#DEFAULT_DIRECTION} 
		 * 
		 * @param property 不能为{@literal null}或空字符串
		 */
		public Order(String property){
			this(DEFAULT_DIRECTION,property);
		}
		
		/**
		 * 返回排序方向
		 * 
		 * @return
		 */
		public Direction getDirection(){
			return direction;
		}
		
		/**
		 * 返回排序属性
		 * 
		 * @return
		 */
		public String getProperty(){
			return property;
		}
		
		/**
		 * 按照升序排序
		 * 
		 * @return
		 */
		public boolean isAscending(){
			return Direction.ASC.equals(direction);
		}
		
		/**
		 * 根据{@link Direction}返回一个新的{@link Order}
		 * 
		 * @param direction
		 * @return
		 */
		public Order with(Direction direction){
			return new Order(direction,property);
		}

		/**
		 * 根据属性返回{@link Sort}实现
		 * 
		 * @param properties
		 * @return
		 */
		public Sort withProperties(String... properties) {
			return new Sort(this.direction, properties);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((direction == null) ? 0 : direction.hashCode());
			result = prime * result
					+ ((property == null) ? 0 : property.hashCode());
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
			Order other = (Order) obj;
			if (direction != other.direction)
				return false;
			if (property == null) {
				if (other.property != null)
					return false;
			} else if (!property.equals(other.property))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Order [direction=" + direction + ", property=" + property
					+ "]";
		}
	}

	
}
