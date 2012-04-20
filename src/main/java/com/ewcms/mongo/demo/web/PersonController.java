package com.ewcms.mongo.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ewcms.mongo.demo.model.Person;
import com.ewcms.mongo.demo.repositories.PersonRepository;

@Controller
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonRepository personRepositoryImpl;
	
	@RequestMapping(value = "/edit.do",method = RequestMethod.GET)
	public void edit(Model model) throws Exception{
		
	}
	
	@RequestMapping(value = "/save.do",method = RequestMethod.POST)
	public void save(@ModelAttribute("person")Person category, Model model) throws Exception{
		
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String delete(@RequestParam("selections") List<String> selections) throws Exception{
		for (String personId : selections){
			personRepositoryImpl.delete(personId);
    	}
    	return "person/index";
	}
	
	@RequestMapping(value = "/query.do")
	@ResponseBody
	public Map<String, Object> query(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "selections", required = false) String selections,
			@RequestParam(value = "cacheKey", required = false) String cacheKey,
			@ModelAttribute(value = "person")Person person) {
		page = page - 1;
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
	}
}
