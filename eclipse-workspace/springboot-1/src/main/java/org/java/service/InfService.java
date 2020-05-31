package org.java.service;

import org.java.dao.InfDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("infService")
public class InfService {
	@Autowired
	private InfDao infDao;
	
	public void addData() {
		infDao.add();
	}
}
