package com.ewcms.repositories;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.DefaultEntityInformationCreator;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import com.mongodb.DBObject;

public abstract class MongoBaseRepository<T,ID extends Serializable> implements MongoRepository<T,ID> {

	private Class<T> type;
	private MongoOperations mongoOperations;
	private MongoConverter mongoConverter;
	private MongoEntityInformation<T, ID> entityInformation;
	private SimpleMongoRepository<T,ID> repository;
	
	private void init(Class<T> entityClass,MongoOperations operations){
		type = entityClass;
		mongoOperations = operations;
		mongoConverter = mongoOperations.getConverter();
		entityInformation=new DefaultEntityInformationCreator(
				mongoConverter.getMappingContext()).
				<T,ID>getEntityInformation(type);
        repository = new SimpleMongoRepository<T,ID>(entityInformation,mongoOperations);
	}
	
	@SuppressWarnings("unchecked")
	public MongoBaseRepository(MongoOperations operations){
	    ParameterizedType genericSuperclass =(ParameterizedType) getClass().getGenericSuperclass();
	    Class<T> entityClass =(Class<T>) genericSuperclass.getActualTypeArguments()[0];
	    init(entityClass,operations);
	}
	
	public MongoBaseRepository(Class<T> entityClass,MongoOperations operations){
		init(entityClass,operations);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public T findOne(ID id) {
		return repository.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return repository.exists(id);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(ID id) {
		repository.delete(id);
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		repository.delete(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public List<T> save(Iterable<? extends T> entities) {
		return repository.save(entities);
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}
	
	protected T convertEntity(DBObject source){
		return mongoConverter.read(type, source);
	}
	
	protected MongoOperations getMongoOperations() {
		return this.mongoOperations;
	}
	
	protected MongoConverter getmongoConverter(){
		return this.mongoConverter;
	}

	protected MongoEntityInformation<T, ID> getEntityInformation() {
		return this.entityInformation;
	}
}
