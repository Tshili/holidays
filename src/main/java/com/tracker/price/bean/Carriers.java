package com.tracker.price.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Carriers {
	
	private String CarrierId;
	private String Name;
	
	
	public Carriers() {
		
	}

	@JsonProperty("CarrierId")
	public String getCarrierId() {
		return CarrierId;
	}


	public void setCarrierId(String carrierId) {
		CarrierId = carrierId;
	}


	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}
	
	
	
	
	

}
