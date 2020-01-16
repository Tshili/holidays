package com.tracker.price.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InboundLeg {
	
	private String departureDate;
	private int destinationId;
	private int OriginId;
	private List<Integer> CarrierIds;
	
	
	public InboundLeg() {
		
	}
	
	
	@JsonProperty("DepartureDate")
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	
	@JsonProperty("DestinationId")
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	
	@JsonProperty("OriginId")
	public int getOriginId() {
		return OriginId;
	}
	public void setOriginId(int originId) {
		OriginId = originId;
	}
	
	@JsonProperty("CarrierIds")
	public List<Integer> getCarrierIds() {
		return CarrierIds;
	}
	public void setCarrierIds(List<Integer> carrierIds) {
		CarrierIds = carrierIds;
	}

}
