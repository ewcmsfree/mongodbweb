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

import com.ewcms.common.query.model.Certificate;
import com.ewcms.common.query.model.LimitLog;
import com.ewcms.common.query.model.Sex;

public class QueryInit {
    
    private final SimpleDateFormat format;
    private final MongoOperations operations;
    
    public QueryInit(MongoOperations operations){
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
    	return operations.collectionExists(Certificate.class);
    }
    
    public void resetInit()throws IOException{
        clean();
        init();
    }
    
    private void clean(){
        operations.dropCollection(Certificate.class);
    }
    
    private void insert()throws IOException{
        //insertSex();
        insertCertificate();
        //insertLimitLog();
    }
        
    private void insertSex()throws IOException{
        Insert<Sex> insert = new Insert<Sex>("sex.csv",operations);
        insert.insert(new InsertCallback<Sex>(){    
            @Override
            public Sex mapping(String line) {
                String[] array = line.split(",");
                Sex sex = new Sex();
                sex.setId(Integer.valueOf(array[0]));
                sex.setName(array[1]);
                
                return sex;
            }
        });
    }
    
    private void insertCertificate()throws IOException{
        Insert<Certificate> insert = new Insert<Certificate>("certificate.csv",operations);
        insert.insert(new InsertCallback<Certificate>(){
            @Override
            public Certificate mapping(String line) {
                String[] array = line.split(",");
                Certificate c = new Certificate();
                c.setCerId(array[0]);
                c.setName(array[1]);
                try {
                    c.setBrithdate(format.parse(array[3]));
                } catch (ParseException e) {
                    new RuntimeException(e);
                }
                c.setLimit(Float.valueOf(array[4]).intValue());
//                c.setSex(jpaTemplate.getReference(Sex.class, Integer.valueOf(array[2])));
                
                return c;
            }
        });
    }
    
    private void insertLimitLog()throws IOException{
        Insert<LimitLog> insert = new Insert<LimitLog>("limitlog.csv",operations);
        insert.insert(new InsertCallback<LimitLog>(){
            @Override
            public LimitLog mapping(String line) {
                String[] array = line.split(",");
                LimitLog log = new LimitLog();
                log.setId(Integer.valueOf(array[0]));
                //log.setCertificate(jpaTemplate.getReference(Certificate.class, array[1]));
                log.setMoney(Float.valueOf(array[2]).intValue());
                try {
                    log.setDate(format.parse(array[3]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return log;
            }
        });
    }
    
    class Insert<T>{
   
        private String name;
        private MongoOperations operations;
        
        Insert(String name,MongoOperations operations){
            this.name = name;
            this.operations = operations;
        }
        
        void insert(InsertCallback<T> callback)throws IOException{
            BufferedReader reader =new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream(name)));
            List<T> list = new ArrayList<T>();
            for(String line = reader.readLine();line != null ; line = reader.readLine()){
                if(line == null || line.trim().equals("")){
                    continue;
                }
                T t = callback.mapping(line);
                list.add(t);
            }
            operations.insertAll(list);
        }  
        
    }
    
    interface InsertCallback<T>{
        T mapping(String line);
    }
}
