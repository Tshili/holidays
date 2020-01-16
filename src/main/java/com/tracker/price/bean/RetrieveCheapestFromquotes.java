package com.tracker.price.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RetrieveCheapestFromquotes {
	
	private List<Quotes> Quotes;
	private List<Places> Places;
	private List<Carriers> Carriers;
	private List<Currencies> Currencies;
	
	
public RetrieveCheapestFromquotes() {
		
	}

	
	@JsonProperty("Quotes")
	public List<Quotes> getQuotes() {
		return Quotes;
	}
	

	public void setQuotes(List<Quotes> quotes) {
		Quotes = quotes;
	}


	@JsonProperty("Places")
	public List<Places> getPlaces() {
		return Places;
	}
	public void setPlaces(List<Places> places) {
		Places = places;
	}
	
	@JsonProperty("Carriers")
	public List<Carriers> getCarriers() {
		return Carriers;
	}
	public void setCarriers(List<Carriers> carriers) {
		Carriers = carriers;
	}
	
	
	@JsonProperty("Currencies")
	public List<Currencies> getCurrencies() {
		return Currencies;
	}
	public void setCurrencies(List<Currencies> currencies) {
		Currencies = currencies;
	}
	
	
	
	
	
	 
	   
	        
	    
	
	
	
	
	

}
