package com.tracker.price.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quotes {
	
	public Quotes() {
		
	}

	private int QuoteId;
	private int MinPrice;
	private boolean Direct;
	private String QuoteDateTime;
	private InboundLeg InboundLeg;
	private OutboundLeg OutboundLeg;
	
	

	
	@JsonProperty("QuoteId")
	public int getQuoteId() {
		return QuoteId;
	}
	public void setQuoteId(int quoteId) {
		QuoteId = quoteId;
	}
	
	@JsonProperty("MinPrice")
	public int getMinPrice() {
		return MinPrice;
	}
	
	public void setMinPrice(int minPrice) {
		MinPrice = minPrice;
	}
	
	
	@JsonProperty("Direct")
	public boolean isDirect() {
		return Direct;
	}
	public void setDirect(boolean direct) {
		Direct = direct;
	}
	
	
	@JsonProperty("QuoteDateTime")
	public String getQuoteDateTime() {
		return QuoteDateTime;
	}
	public void setQuoteDateTime(String quoteDateTime) {
		QuoteDateTime = quoteDateTime;
	}
	
	
	@JsonProperty("InboundLeg")
	public InboundLeg getInboundLeg() {
		return InboundLeg;
	}
	public void setInboundLeg(InboundLeg inboundLeg) {
		InboundLeg = inboundLeg;
	}
	
	@JsonProperty("OutboundLeg")
	public OutboundLeg getOutboundLeg() {
		return OutboundLeg;
	}
	
	public void setOutboundLeg(OutboundLeg outboundLeg) {
		OutboundLeg = outboundLeg;
	}
	
	
	
	@Override
	public String toString () {
		
	
	return  " Departure : " + OutboundLeg.getOriginId()  + 
				
			" Prix : " + MinPrice + "\n" ; 
		
		
	}
	
	
	
	
	
	
	
	
}
