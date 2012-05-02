/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.common.query.mongo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.ewcms.common.query.mongo.model.Certificate;
import com.ewcms.common.query.mongo.model.LimitLog;

/**
 * 初始模拟查询数到数据库中
 * 
 * @author wangwei
 *
 */
public class EasyQueryInit {
    
    private final SimpleDateFormat format;
    private final MongoOperations operations;
    
    public EasyQueryInit(MongoOperations operations){
    	this.operations = operations;
    	format = new SimpleDateFormat("yyyy-MM-dd");
    	format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0,"GMT")));
    	
    }
    
    public void init()throws IOException{
        if(isInit()){
            return;
        }
        insert();
    }
    
    /**
     * 判断是否初始化
     * 
     * @return
     */
    private boolean isInit(){
    	return operations.collectionExists(Certificate.class) &&
    	       operations.count(Query.query(new Criteria()), Certificate.class) > 0;
    }
    
    public void resetInit()throws IOException{
        clean();
        init();
    }
    
    private void clean(){
        operations.dropCollection(Certificate.class);
    }
    
    private void insert()throws IOException{
        insertCertificate();
    }
    
    private void insertCertificate()throws IOException{
        DataOperator<Certificate> operator = new DataOperator<Certificate>("certificate.csv");
        operator.insert(new MapperCallback<Certificate>(){
            @Override
            public Certificate mapping(String line) {
                String[] array = line.split(",");
                Certificate c = new Certificate();
                c.setCerId(array[0]);
                c.setName(array[1]);
                try {
					c.setLimitLogs(findLimitLog(array[0]));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                try {
                    c.setBrithdate(format.parse(array[3]));
                } catch (ParseException e) {
                    new RuntimeException(e);
                }
                c.setLimit(Float.valueOf(array[4]).intValue());
                if(array.length > 5 && StringUtils.hasText(array[5])){
                	c.setPhones(StringUtils.tokenizeToStringArray(array[5], "|"));
                }
                
                return c;
            }
        },operations);
    }
    
    private List<LimitLog> findLimitLog(final String id)throws IOException{
        DataOperator<LimitLog> operator = new DataOperator<LimitLog>("limitlog.csv");
        List<LimitLog> logs = operator.loadData(new MapperCallback<LimitLog>(){
            @Override
            public LimitLog mapping(String line) {
                String[] array = line.split(",");
                if(!array[1].equals(id)){
                	return null;
                }
                LimitLog log = new LimitLog();
                log.setMoney(Float.valueOf(array[2]).intValue());
                try {
                    log.setDate(format.parse(array[3]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return log;
            }
        });
        return logs;
    }
    
    class DataOperator<T>{
   
        private String name;
        
        DataOperator(String name){
            this.name = name;
        }
        
        List<T> loadData(MapperCallback<T> callback)throws IOException{
        	  BufferedReader reader =new BufferedReader(
                      new InputStreamReader(this.getClass().getResourceAsStream(name)));
              List<T> list = new ArrayList<T>();
              for(String line = reader.readLine();line != null ; line = reader.readLine()){
                  if(line == null || line.trim().equals("")){
                      continue;
                  }
                  T t = callback.mapping(line);
                  if(t != null){
                	  list.add(t);  
                  }
              }
              return list;
        }
        
        void insert(MapperCallback<T> callback,MongoOperations operations)throws IOException{
        	List<T> list = loadData(callback);
            operations.insertAll(list);
        }  
        
    }
    
    interface MapperCallback<T>{
        T mapping(String line);
    }
}
