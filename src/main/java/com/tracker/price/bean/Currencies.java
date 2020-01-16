package com.tracker.price.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currencies {
	
	private String Code;
	private String Symbol;
	private String ThousandsSeparator;
	private String DecimalSeparator;
	private Boolean SymbolOnLeft;
	private Boolean SpaceBetweenAmountAndSymbol;
	private int RoundingCoefficient;
	private int DecimalDigits;
	
	
	
	public Currencies() {
		
	}
	
	@JsonProperty("Code")
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	
	@JsonProperty("Symbol")
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	
	@JsonProperty("ThousandsSeparator")
	public String getThousandsSeparator() {
		return ThousandsSeparator;
	}
	
	public void setThousandsSeparator(String thousandsSeparator) {
		ThousandsSeparator = thousandsSeparator;
	}
	
	@JsonProperty("DecimalSeparator")
	public String getDecimalSeparator() {
		return DecimalSeparator;
	}
	public void setDecimalSeparator(String decimalSeparator) {
		DecimalSeparator = decimalSeparator;
	}
	
	@JsonProperty("SymbolOnLeft")
	public Boolean getSymbolOnLeft() {
		return SymbolOnLeft;
	}
	
	public void setSymbolOnLeft(Boolean symbolOnLeft) {
		SymbolOnLeft = symbolOnLeft;
	}
	
	@JsonProperty("SpaceBetweenAmountAndSymbol")
	public Boolean getSpaceBetweenAmountAndSymbol() {
		return SpaceBetweenAmountAndSymbol;
	}
	
	public void setSpaceBetweenAmountAndSymbol(Boolean spaceBetweenAmountAndSymbol) {
		SpaceBetweenAmountAndSymbol = spaceBetweenAmountAndSymbol;
	}
	
	@JsonProperty("RoundingCoefficient")
	public int getRoundingCoefficient() {
		return RoundingCoefficient;
	}
	public void setRoundingCoefficient(int roundingCoefficient) {
		RoundingCoefficient = roundingCoefficient;
	}
	
	@JsonProperty("DecimalDigits")
	public int getDecimalDigits() {
		return DecimalDigits;
	}
	public void setDecimalDigits(int decimalDigits) {
		DecimalDigits = decimalDigits;
	}
	
	
	
	

}
