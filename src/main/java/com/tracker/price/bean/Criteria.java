package com.tracker.price.bean;

import java.time.LocalDate;


public class Criteria {
	

	
	 private String currency; 
	 private String country;
	 private String originplace; 
	 private String locale;
	 private String destinationplace;
	 private String inboundpartialdate;
	 private String outboundpartialdate;
	 private boolean direct;
	 private int maxPrice;
	 private String filterBydepartureDate;
	 private String filterByreturnDate;
	 
	 
	 
	 
	public Criteria() {	}
	
		
	public Criteria(String currency, String country, String originplace, String locale, String destinationplace,
			String inboundpartialdate, String outboundpartialdate) {
		this.currency = currency;
		this.country = country;
		this.originplace = originplace;
		this.locale = locale;
		this.destinationplace = destinationplace;
		this.inboundpartialdate = inboundpartialdate;
		this.outboundpartialdate = outboundpartialdate;
	}






	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getOriginplace() {
		return originplace;
	}
	public void setOriginplace(String originplace) {
		this.originplace = originplace;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getDestinationplace() {
		return destinationplace;
	}
	public void setDestinationplace(String destinationplace) {
		this.destinationplace = destinationplace;
	}
	public String getInboundpartialdate() {
		return inboundpartialdate;
	}
	public void setInboundpartialdate(String inboundpartialdate) {
		this.inboundpartialdate = inboundpartialdate;
	}
	public String getOutboundpartialdate() {
		return outboundpartialdate;
	}
	public void setOutboundpartialdate(String outboundpartialdate) {
		this.outboundpartialdate = outboundpartialdate;
	}


	public boolean isDirect() {
		return direct;
	}


	public void setDirect(boolean direct) {
		this.direct = direct;
	}


	public int getMaxPrice() {
		return maxPrice;
	}


	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}


	public String getFilterBydepartureDate() {
		return filterBydepartureDate;
	}


	public void setFilterBydepartureDate(String filterBydepartureDate) {
		this.filterBydepartureDate = filterBydepartureDate;
	}


	public String getFilterByreturnDate() {
		return filterByreturnDate;
	}


	public void setFilterByreturnDate(String filterByreturnDate) {
		this.filterByreturnDate = filterByreturnDate;
	}


	

	


	

}
