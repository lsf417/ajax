package org.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Inf;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface InfMapper {
	
	public void add(Inf f);
	
	public List<Inf> getList();
}
