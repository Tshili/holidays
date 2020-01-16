package com.tracker.price.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.tracker.price.bean.RetrieveCheapestFromquotes;
import com.tracker.price.util.JavaMailUtil;

public class ServiceImpl implements Service {

	@Override
	public List<String> mailStructure(RetrieveCheapestFromquotes retrieveCheapestFromquotes) {
		
		 List <String> stringMail = new ArrayList<>();
		 String departure = null;
		 
		 for (int i = 0; i < retrieveCheapestFromquotes.getQuotes().size(); i++) {
			 Integer   destinationId = retrieveCheapestFromquotes.getQuotes().get(i).getOutboundLeg().getDestinationId();
			 Integer originId = retrieveCheapestFromquotes.getQuotes().get(i).getOutboundLeg().getOriginId();
			   
			   for (int j = 0; j < retrieveCheapestFromquotes.getPlaces().size(); j++) {
				   Integer   placeId = retrieveCheapestFromquotes.getPlaces().get(j).getPlaceId();
				   
				   if ( originId.equals(placeId)) {
					     departure = retrieveCheapestFromquotes.getPlaces().get(j).getCityName();
					   
				}
				   
				   if ( destinationId.equals(placeId)) {
					   stringMail.add( " Departure : " + departure + 
							   		" Arrival : " + retrieveCheapestFromquotes.getPlaces().get(j).getCityName() 
							   		+ " du : " + JavaMailUtil.convertDate( retrieveCheapestFromquotes.getQuotes().get(i).getOutboundLeg().getDepartureDate()).format(DateTimeFormatter.ofPattern("dd-MMM-yy"))          
							   		+ " au " +  JavaMailUtil.convertDate(retrieveCheapestFromquotes.getQuotes().get(i).getInboundLeg().getDepartureDate()).format(DateTimeFormatter.ofPattern("dd-MMM-yy"))     
							   		+ " Prix " + retrieveCheapestFromquotes.getQuotes().get(i).getMinPrice()
							   		+ "\n"
							   );
				}
				   
				  
			}
			   
			  	   
		}
		return stringMail;
	}

}
