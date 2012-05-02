package com.ewcms.mongo.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "/edit.action",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "personId", required = false)String personId, Model model) throws Exception{
		Person person = new Person();
		if (personId != null && personId.length() > 0){
			person = personRepositoryImpl.findOne(personId);
		}
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
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("selections") List<String> selections) throws Exception{
		for (String personId : selections){
			personRepositoryImpl.delete(personId);
    	}
    	return "person/index";
	}
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public Map<String, Object> query(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "selections", required = false) String selections,
			@ModelAttribute(value = "person")Person person) {
		page = page - 1;
		Pagination pageination = new PaginationImpl(10,page,Direction.ASC,"id");
		
		EasyQuery<Person> query = new EasyQueryImpl.Where<Person>(Person.class).build(mongoOperations);
		
		ResultPage<Person> result = query.findPage(pageination);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", result.getTotalElements());
		resultMap.put("rows", result.getContent());
		return resultMap;
	}
}
