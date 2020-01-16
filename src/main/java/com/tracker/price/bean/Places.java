package com.tracker.price.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Places {
	
	private int PlaceId;
	private String  Name;
	private String Type;
	private String SkyscannerCode;
	private String IataCode;
	private String CityName;
	private String CityId;
	private String CountryName;
	
	
	
	@JsonProperty("IataCode")
	public String getIataCode() {
		return IataCode;
	}
	public void setIataCode(String iataCode) {
		IataCode = iataCode;
	}
	
	@JsonProperty("CityName")
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	
	@JsonProperty("CityId")
	public String getCityId() {
		return CityId;
	}
	
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	
	@JsonProperty("CountryName")
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	@JsonProperty("PlaceId")
	public int getPlaceId() {
		return PlaceId;
	}
	public void setPlaceId(int placeId) {
		PlaceId = placeId;
	}
	
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@JsonProperty("Type")
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	@JsonProperty("SkyscannerCode")
	public String getSkyscannerCode() {
		return SkyscannerCode;
	}
	public void setSkyscannerCode(String skyscannerCode) {
		SkyscannerCode = skyscannerCode;
	}
	
	

	
	
	

}
