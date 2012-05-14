/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.mongo.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ewcms.mongo.demo.model.Person;

@NoRepositoryBean
public interface PersonRepository extends MongoRepository<Person,String> {

	List<Person> findWorkByAgeRang(int start,int end);
}
