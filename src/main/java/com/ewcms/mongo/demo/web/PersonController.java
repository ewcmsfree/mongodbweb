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

	private final static Integer DEFAULT_PAGE = 1;
	private final static Integer DEFAULT_PAGESIZE = 20;
	
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
	public Map<String, Object> query(
			@ModelAttribute QueryParameter queryParam,
			@RequestParam(value="selections[]",required=false)String[] selections) {
		
		int page =  queryParam.getPage();
		int pageSize = queryParam.getRows();
		
		EasyQuery<Person> query = new EasyQueryImpl.Where<Person>(Person.class).build(mongoOperations);
		Pagination pageination = StringUtils.hasText(queryParam.getSort()) ?
				new PaginationImpl(pageSize,(page - 1),Direction.fromString(queryParam.getOrder()),queryParam.getSort())
		        :new PaginationImpl(pageSize,(page - 1));
				
		ResultPage<Person> result = query.findPage(pageination);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", result.getTotalElements());
		resultMap.put("rows", result.getContent());
		return resultMap;
	}
}
