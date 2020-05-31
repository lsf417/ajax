package org.java.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
* 这个类起到的作用，就相当于以前applicationContext.xml的作用
*  
*
*/
@Configuration //标识，当前类起的作用与applicationContext.xml一样
@ComponentScan(value="org.java")//配置组件扫描 
@PropertySource(value="classpath:jdbc.properties")
public class SpringConfig {
	
	@Value("${url}")
	private String url;
	
	//模拟读取资源文件的信息
	@Bean
	public String getResources(){
		
		System.out.println("url的值是:"+url);
		
		return url;
	}
}
