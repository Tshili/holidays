package com.tracker.price.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutboundLeg {

	
	private String departureDate;
	private int destinationId;
	private Integer OriginId;
	private List<Integer> CarrierIds;
	
	
	
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
	public Integer getOriginId() {
		return OriginId;
	}
	public void setOriginId(Integer originId) {
		OriginId = originId;
	}
	
	@JsonProperty("CarrierIds")
	public List<Integer> getCarrierIds() {
		return CarrierIds;
	}
	public void setCarrierIds(List<Integer> carrierIds) {
		CarrierIds = carrierIds;
	}
	@Override
	public String toString() {
		return "OutboundLeg [departureDate=" + departureDate + ", destinationId=" + destinationId + ", OriginId="
				+ OriginId + ", CarrierIds=" + CarrierIds + "]";
	}
	
	
	
	
	
}
