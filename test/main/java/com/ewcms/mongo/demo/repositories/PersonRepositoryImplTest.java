package com.ewcms.mongo.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewcms.mongo.demo.model.Person;
import com.ewcms.mongo.demo.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PersonRepositoryImplTest {

	@Autowired
	private PersonRepository repository;

	@Before
	public void before() {
		repository.deleteAll();
	}

	private Person createEntity(String name,int age) {
		Person person = new Person();
		person.setName(name);
		person.setAge(age);
		person.setEmail("hhywangwei@gmail.com");
		person.setAddress("jiangxinanchang");
		return person;
	}

	@Test
	public void testSave() {
		Person person = createEntity("wangwei",30);
		repository.save(person);
		Assert.assertEquals(1, repository.count());
		Assert.assertNotNull(person.getId());
	}

	@Test
	public void testExists(){
		Person person = createEntity("wangwei",30);
		repository.save(person);
		Assert.assertTrue(repository.exists(person.getId()));
	}
	
	@Test
	public void testFindOn() {
		Person person = createEntity("wangwei",30);
		repository.save(person);
		Assert.assertEquals(1, repository.count());

		Person entity = repository.findOne(person.getId());
		Assert.assertNotNull(entity);
	}

	@Test
	public void testSaveCollection() {
		List<Person> persons = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			Person person = createEntity("wangwei" + i,i+1);
			persons.add(person);
		}

		repository.save(persons);
		Assert.assertEquals(100, repository.count());
	}

	@Test
	public void testFindAll() {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Person person = createEntity("wangwei" + i,i+1);
			persons.add(person);
		}
		repository.save(persons);

		List<Person> entities = repository.findAll();
		Assert.assertEquals(100, entities.size());
	}

	@Test
	public void testFindAllPage() {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 103; i++) {
			Person person = createEntity("wangwei" + i,i+1);
			persons.add(person);
		}
		repository.save(persons);
		
		Page<Person> page = repository.findAll(new PageRequest(1,20));
		Assert.assertEquals(103, page.getTotalElements());
		Assert.assertEquals(6, page.getTotalPages());
		Assert.assertEquals(20, page.getNumberOfElements());
		Assert.assertEquals(1, page.getNumber());
		Assert.assertEquals(20, page.getSize());
		
		page = repository.findAll(new PageRequest(5,20));
		Assert.assertEquals(3, page.getNumberOfElements());
		Assert.assertEquals(5, page.getNumber());
		Assert.assertEquals(20, page.getSize());
	}
	
	@Test
	public void testFindAllSort(){
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 103; i++) {
			Person person = createEntity("wangwei" + i,i+1);
			persons.add(person);
		}
		repository.save(persons);
		
		Sort sort = new Sort(new Order(Direction.ASC,"name"));
		List<Person> entities = repository.findAll(sort);
		Assert.assertEquals(103, entities.size());
		Assert.assertEquals("wangwei0",entities.get(0).getName());
		Assert.assertEquals("wangwei99",entities.get(102).getName());
	}
	
	@Test
	public void testFindWorkByAge(){
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 103; i++) {
			Person person = createEntity("wangwei" + i,i+1);
			persons.add(person);
		}
		repository.save(persons);
		
		List<Person> entities = repository.findWorkByAgeRang(20, 60);
		Assert.assertEquals(41, entities.size());
		Assert.assertNotNull(entities.get(0).getId());
	}
}
