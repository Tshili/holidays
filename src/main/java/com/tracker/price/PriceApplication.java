package com.tracker.price;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tracker.price.bean.DestinationDate;
import com.tracker.price.bean.RetrieveCheapestFromquotes;
import com.tracker.price.controller.PriceTrackerController;

@SpringBootApplication
public class PriceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(PriceApplication.class, args);
		
		
		List<DestinationDate> destinationDates = new ArrayList<DestinationDate>();
		destinationDates.add(new DestinationDate("2020-04-10","2020-04-13"));
		destinationDates.add(new DestinationDate("2020-04-11","2020-04-13"));
		for (DestinationDate destinationDate : destinationDates) {
			
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				
					try {
						PriceTrackerController   priceTrackerController = new PriceTrackerController();
						priceTrackerController.listOfTripByMail(priceTrackerController.QuotesInbound("FR", "EUR", "NTE", "ISO", "anywhere", destinationDate.getReturnDate(), destinationDate.getDepartureDate(), true, 250, null, null));
						//priceTrackerController.QuotesInbound("FR", "EUR", "NTE", "ISO", "anywhere", destinationDate.getReturnDate(), destinationDate.getDepartureDate(), true, 250, null, null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}; 
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.HOURS);
		
		TimeUnit.MINUTES.sleep(1);
		}
	
	}

}