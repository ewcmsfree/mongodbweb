package com.ewcms.mongo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.ewcms.mongo.model.Person;
import com.ewcms.repositories.MongoBaseRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

@Repository
public class PersonRepositoryImpl extends MongoBaseRepository<Person,String> implements PersonRepository{

	@Autowired
	public PersonRepositoryImpl(MongoOperations mongoOperations){
		super(Person.class,mongoOperations);
	}
	
	@Override
	public List<Person> findWorkByAgeRang(final int start,final int end) {
		return getMongoOperations().execute(new DbCallback<List<Person>>(){
			@Override
			public List<Person> doInDB(DB db) throws MongoException,DataAccessException {
				DBCollection coll = db.getCollection("person");
				BasicDBObject query = new BasicDBObject();
			    query.put("age", new BasicDBObject("$gte", 20).append("$lte", 60));
			    DBCursor cur = coll.find(query);
			    List<Person> list = new ArrayList<Person>();
			    for(;cur.hasNext();){
			    	DBObject source = cur.next();
			    	Person person = convertEntity(source);
			    	list.add(person);
			    }
				return list;
			}
		});
	}
	
	
}
