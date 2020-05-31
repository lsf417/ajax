package org.java.dao;

import org.springframework.stereotype.Repository;

@Repository
public class InfDao {
	
	public void add(){
		System.out.println("向数据库添加了一条数据.....");
	}
}
