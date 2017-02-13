package com.isolisduran.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isolisduran.springboot.bean.Person;
import com.isolisduran.springboot.service.PersonService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/api")
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@ApiOperation(value="Get a person by id", notes="Provides an operation to get a Person object by its identifier")
	@ApiResponses(value={
		@ApiResponse(code=200, message="OK", response=Person.class),
		@ApiResponse(code=404, message="Not Found", response=String.class),
		@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	@RequestMapping(method = RequestMethod.GET, value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> getById(@ApiParam(value="Person identifier", required=true) @PathVariable int id) {
		Person person = personService.getById(id);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@ApiOperation(value="Create a new Person", notes="Provides an operation to create a new Person object and return its identifier")
	@ApiResponses(value={
		@ApiResponse(code=201, message="Created", response=String.class),
		@ApiResponse(code=400, message="Bad Request", response=String.class),
		@ApiResponse(code=500, message="Internal Server Error", response=String.class)
	})
	@RequestMapping(method = RequestMethod.POST, value = "/person", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> create(@ApiParam(value="JSON with Person to create", required=true) @Valid @RequestBody Person person) {
		String newPersonId = String.valueOf(personService.create(person).getId());
		return new ResponseEntity<String>(newPersonId, HttpStatus.CREATED);
	}
}
