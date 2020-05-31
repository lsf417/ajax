package org.java.demo;

import org.java.config.SpringConfig;
import org.java.service.InfService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext cxf = new AnnotationConfigApplicationContext(
				SpringConfig.class);
		
		
		InfService service = (InfService) cxf.getBean("infService");
		
		service.addData();

	}
}
