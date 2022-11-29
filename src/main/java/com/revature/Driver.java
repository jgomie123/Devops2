package com.revature;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConfig;
import model.Polkaman;
import repository.PolkamanRepository;
import repository.PolkamanRepositoryImpl;
import service.PolkamanService;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		/*There are a few ways we can implement the application context (IOC COntainer): 
		 * ClassPathXmlApplicationContext --> this is the one you'd use if you were using an XML file
		 * AnnotationConfigApplicationContext --> we use this one when we want to 
		 * 	leverage annotations
		 * */
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		PolkamanService ps = applicationContext.getBean("polkamanService", PolkamanService.class);
		
		System.out.println(ps.sortPolkamanByName());
		
		//ORM provides even further abstraction frmo  Spring JDBC
		
		//ps.save();
		System.out.println(ps.sortPolkamanByName());
		
		
	}

}
