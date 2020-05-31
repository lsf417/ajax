package org.java.service.impl;

import java.util.List;

import org.java.dao.InfMapper;
import org.java.entity.Inf;
import org.java.service.InfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfServiceImpl implements InfService {
	@Autowired
	private InfMapper infMapper;
	
	@Override
	public void add(Inf f) {
		infMapper.add(f);

	}
	@Override
	public List<Inf> getList() {
		return infMapper.getList();
	}

}
