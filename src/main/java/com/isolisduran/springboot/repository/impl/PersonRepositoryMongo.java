package com.isolisduran.springboot.repository.impl;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.isolisduran.springboot.bean.Person;
import com.isolisduran.springboot.repository.PersonRepository;

@Repository
public class PersonRepositoryMongo implements PersonRepository{

	private static final int MIN = 2;
	private static final int MAX = 10;
	
	@Override
	public Person findOne(int id) {
		return getMockedPerson(id);
	}

	@Override
	public Person save(Person person) {
		person.setId(new Random().nextInt(MAX - MIN + 1) + MIN);
		return person;
	}
	
	private Person getMockedPerson(int id) {
		Person person = new Person();
		person.setId(id);
		person.setName("MockName");
		person.setAddress("Mock Address");
		return person;
	}

}
