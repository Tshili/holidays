package com.tracker.price.bean;

public class DestinationDate {
	
	private String departureDate;
	private String returnDate;
	public String getDepartureDate() {
		return departureDate;
	}
	
	
	
	public DestinationDate(String departureDate, String returnDate) {
		
		this.departureDate = departureDate;
		this.returnDate = returnDate;
	}



	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	

}
