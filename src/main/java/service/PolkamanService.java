package service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import model.Polkaman;
import repository.PolkamanRepository;
import repository.PolkamanRepositoryImpl;

public class PolkamanService {
	@Autowired
	private PolkamanRepository polkamanRepository;
	
	public PolkamanService() {
		
	}
	
	@Autowired
	public PolkamanService(PolkamanRepository polkamanRepository){
		this.polkamanRepository = polkamanRepository;
	}
	
	/*By default, Spring uses setter injectino - meaning when it tries to perform depenency injection,
	 * it attempts to call a setter method in which it passes the bean to us
	 * 
	 * Spring is very much about convention over configuration. It expections things to be done in a ver
	 * conventional way. For example, this setter method needs to follow the naming convention for 
	 * setters (set + propertyName)
	 * */
	@Autowired
	public void setPolkamanRepository(PolkamanRepository polkamanRepository) {
		this.polkamanRepository = polkamanRepository;
	}
	
	public List<Polkaman> sortPolkamanByName(){
				
		List<Polkaman> polkamans = this.polkamanRepository.findAll();
		
		return polkamans;
	}
	
//	public void save() {
//		
//		//this.polkamanRepository.save(polkaman);
//	}
	
}
