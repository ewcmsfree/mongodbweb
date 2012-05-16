/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.mongo.demo.web;

import java.util.ArrayList;
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
	private PersonRepository repository;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@RequestMapping(value = "/edit.action",method = RequestMethod.GET)
	public String edit(@RequestParam(required = false)List<String> selections, Model model){
		if(selections == null || selections.isEmpty()){
			model.addAttribute("person", new Person());
			model.addAttribute("selections", new ArrayList<String>(0));
		}else{
			Person person = repository.findOne(selections.get(0));
			person = (person == null  ? new Person() : person);
			model.addAttribute("person", person);
			model.addAttribute("selections", selections);
		}
		
		return "person/edit";
	}
	
	@RequestMapping(value = "/save.action",method = RequestMethod.POST)
	public String save(@ModelAttribute("person")Person person,
			@RequestParam(required=false) List<String> selections,
			Model model){
		Boolean close = Boolean.FALSE;
		if (StringUtils.hasText(person.getId())){
			repository.save(person);
			selections.remove(0);
			if(selections == null || selections.isEmpty()){
				close = Boolean.TRUE;
			}else{
				person = repository.findOne(selections.get(0));
				model.addAttribute("person", person);
				model.addAttribute("selections", selections);
			}
		}else{
			repository.save(person);
			selections = selections == null ? new ArrayList<String>() : selections;
			selections.add(0,person.getId());
			model.addAttribute("person", new Person());
			model.addAttribute("selections", selections);
		}
		model.addAttribute("close",close);
		
		return "person/edit";
	}
	
	@RequestMapping(value = "/remove.action", method = {RequestMethod.POST,RequestMethod.GET})
	public String remove(@RequestParam("selections") List<String> selections){
		for (String id : selections){
			repository.delete(id);
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
