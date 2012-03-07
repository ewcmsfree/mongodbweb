package com.ewcms.mongo.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ewcms.mongo.demo.model.Person;

@NoRepositoryBean
public interface PersonRepository extends MongoRepository<Person,String> {

	List<Person> findWorkByAgeRang(int start,int end);
}
