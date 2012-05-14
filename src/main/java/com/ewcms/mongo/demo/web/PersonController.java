/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.mongo.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ewcms.common.query.EasyQuery;
import com.ewcms.common.query.Pagination;
import com.ewcms.common.query.PaginationImpl;
import com.ewcms.common.query.ResultPage;
import com.ewcms.common.query.Sort.Direction;
import com.ewcms.common.query.mongo.EasyQueryImpl;
import com.ewcms.mongo.demo.model.Person;
import com.ewcms.mongo.demo.repositories.PersonRepository;

@Controller
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonRepository personRepositoryImpl;
	@Autowired
	private MongoOperations mongoOperations;
	
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "personId", required = false)String personId, Model model) throws Exception{
		Person person = StringUtils.hasText(personId) ?
				personRepositoryImpl.findOne(personId) : new Person();
		//TODO 添加persion == null 判断是否查询到制定人
		model.addAttribute("person", person);
		return "person/edit";
	}
	
	@RequestMapping(value = "/save.action",method = RequestMethod.POST)
	public String save(@ModelAttribute("person")Person person, Model model) throws Exception{
		if (person.getId() == null || person.getId().length() == 0){
			personRepositoryImpl.save(person);
		}else{
			personRepositoryImpl.save(person);
		}
		return "person/index";
	}
	
	@RequestMapping(value = "/delete.action", method = RequestMethod.POST)
	public String delete(@RequestParam("selections") List<String> selections) throws Exception{
		for (String personId : selections){
			personRepositoryImpl.delete(personId);
    	}
    	return "person/index";
	}
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public Map<String, Object> query(@ModelAttribute QueryParameter params) {
		
		int page =  params.getPage();
		int pageSize = params.getRows();
		
		EasyQuery<Person> query;
		if(params.getSelections().size()>0){
			 query = new EasyQueryImpl
						.Where<Person>(Person.class,"id")
						.in(params.getSelections())
						.build(mongoOperations);
		}else{
			 query = new EasyQueryImpl
						.Where<Person>(Person.class,"name")
						.likeAny(params.getParameters().get("name"))
						.and("email")
						.likeAny(params.getParameters().get("email"))
						.build(mongoOperations);
		}
		
		Pagination pageination = StringUtils.hasText(params.getSort()) ?
				new PaginationImpl(pageSize,(page - 1),Direction.fromString(params.getOrder()),params.getSort())
		        :new PaginationImpl(pageSize,(page - 1));
		
		ResultPage<Person> result = query.findPage(pageination);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", result.getTotalElements());
		resultMap.put("rows", result.getContent());
		return resultMap;
	}
}
