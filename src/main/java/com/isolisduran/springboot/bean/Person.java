package com.isolisduran.springboot.bean;

import io.swagger.annotations.ApiModelProperty;

public class Person {

	@ApiModelProperty(value="Person identifier", required=true, example="1", position=1)
	private int id;
	
	@ApiModelProperty(value="Person name", required=true, example="MyName", position=2)
	private String name;
	
	@ApiModelProperty(value="Person address", required=false, example="MyAddress", position=3)
	private String address;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
