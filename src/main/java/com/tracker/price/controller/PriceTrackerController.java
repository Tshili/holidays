package com.tracker.price.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tracker.price.bean.Criteria;
import com.tracker.price.bean.DestinationDate;
import com.tracker.price.bean.Places;
import com.tracker.price.bean.Quotes;
import com.tracker.price.bean.RetrieveCheapestFromquotes;
import com.tracker.price.service.Service;
import com.tracker.price.service.ServiceImpl;
import com.tracker.price.util.JavaMailUtil;



import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PriceTrackerController {
	

	
	
	
	RetrieveCheapestFromquotes criteriaJson=null;
	List <Quotes> newQuote = new ArrayList<>();
	
	Service service = new ServiceImpl();
	
	
		
	
	@GetMapping(value="/launch")
	public void launch() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.skyscanner.fr");
		WebElement destination = driver.findElement(By.xpath("//*[@id=\"fsc-destination-search\"]"));
		destination.sendKeys("Partout");
		
		
		// selected day of departure 
		WebElement departure = driver.findElement(By.xpath("//*[@id=\"depart-fsc-datepicker-button\"]/span"));
		departure.click();
		
		Select chooseMonthDeparture = new Select(driver.findElement(By.name("months")));
		chooseMonthDeparture.selectByVisibleText("décembre 2019");
		
		WebElement selectedDayDeparture = driver.findElement(By.xpath("//*[@id=\"depart-fsc-datepicker-popover\"]/div/div/div[2]/div/table/tbody/tr[5]/td[5]/button"));
		selectedDayDeparture.click();
		
		
		// selected day of departure
		WebElement arrival = driver.findElement(By.xpath("//*[@id=\"return-fsc-datepicker-button\"]"));
		arrival.click();
		
		Select chooseMonthArrival = new Select(driver.findElement(By.name("months")));
		chooseMonthArrival.selectByVisibleText("janvier 2020");
		
		WebElement selectedDayArrival = driver.findElement(By.xpath("//*[@id=\"return-fsc-datepicker-popover\"]/div/div/div[2]/div/table/tbody/tr[1]/td[6]/button"));
		selectedDayArrival.click();
		
		
		// click to fond a flight
		WebElement findAFlight = driver.findElement(By.xpath("//*[@id=\"flights-search-controls-root\"]/div/div/form/div[3]/button"));
		findAFlight.click();
		
	}
	
	
	// Retrieve the cheapest quotes from our cache prices. Must include inboundpartialdate
	 
	@GetMapping(value = "/search")
	public RetrieveCheapestFromquotes  QuotesInbound (
			@RequestBody Criteria criteria
			) throws Exception{
		
	
		
		HttpResponse<String> response = null;
		
		RetrieveCheapestFromquotes newcriteriaJson=new RetrieveCheapestFromquotes();
		
		 
		try {
			
			response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/{country}/{currency}/{locale}/{originplace}/{destinationplace}/{inboundpartialdate}/{outboundpartialdate}")
					.routeParam("country", criteria.getCountry())
					.routeParam("currency", criteria.getCurrency())
					.routeParam("originplace", criteria.getOriginplace())
					.routeParam("locale", criteria.getLocale())
					.routeParam("destinationplace", criteria.getDestinationplace())
					.routeParam("inboundpartialdate", criteria.getOutboundpartialdate() )
					.routeParam("outboundpartialdate", criteria.getInboundpartialdate() )	
					.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
					.header("x-rapidapi-key", "c1658f5d0dmsh026228b9892adddp1fded6jsn653906320566")
					.asString();
			
			 criteriaJson = new ObjectMapper().readValue(response.getBody(), RetrieveCheapestFromquotes.class);
			 	
			
			newcriteriaJson.setCarriers(criteriaJson.getCarriers());
         	newcriteriaJson.setCurrencies(criteriaJson.getCurrencies());
         	newcriteriaJson.setPlaces(criteriaJson.getPlaces());
         	newcriteriaJson.setQuotes(criteriaJson.getQuotes());
         	
         	
         	List <Quotes> newQuoteDirectWithPrice = new ArrayList<>();
         	
         	if (criteria.isDirect()) {
         		 newQuoteDirectWithPrice= newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				 .filter(quote -> quote.getMinPrice() < criteria.getMaxPrice() &&  quote.isDirect())
         				 .collect(Collectors.toList());
         		newcriteriaJson.setQuotes(newQuoteDirectWithPrice);
			}
         	
         	if (criteria.isDirect() == false) {
         		 newQuoteDirectWithPrice= newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				 .filter(quote -> quote.getMinPrice() < criteria.getMaxPrice() && quote.isDirect()   ||  quote.getMinPrice() < criteria.getMaxPrice() && !quote.isDirect())
         				 .collect(Collectors.toList());
         		newcriteriaJson.setQuotes(newQuoteDirectWithPrice);
			}
         	
         	
         	Optional<String> departureDate = Optional.ofNullable(criteria.getFilterBydepartureDate());
         	Optional<String> returnDate = Optional.ofNullable(criteria.getFilterByreturnDate());
         	List <Quotes> newQuoteWithDepartureDate = new ArrayList<>();
         		 	
         	if (departureDate.isPresent() && !returnDate.isPresent() ) {
         		LocalDate formattedCriteriaDate = LocalDate.parse(criteria.getFilterBydepartureDate());
         		newQuoteWithDepartureDate = newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				.filter(quote -> 
         				
         				formattedCriteriaDate.isBefore(LocalDateTime.parse(quote.getOutboundLeg().getDepartureDate()).toLocalDate())).collect(Collectors.toList());
         		
         		newcriteriaJson.setQuotes(newQuoteWithDepartureDate);
			}
         	
         	List <Quotes> newQuoteWithReturnDate = new ArrayList<>();
         	
         	if (returnDate.isPresent() && !departureDate.isPresent()) {
         		LocalDate formattedCriteriaReturnDate = LocalDate.parse(criteria.getFilterByreturnDate());
         		newQuoteWithReturnDate = newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				.filter(quote -> 
         				
         				formattedCriteriaReturnDate.isAfter(LocalDateTime.parse(quote.getInboundLeg().getDepartureDate()).toLocalDate())).collect(Collectors.toList());
         		
         		newcriteriaJson.setQuotes(newQuoteWithReturnDate);
			}
         	
         	List <Quotes> newQuoteWithDepartureAndReturnDate = new ArrayList<>();
         	
         	if (returnDate.isPresent() && departureDate.isPresent()) {
         		LocalDate formattedCriteriaReturnDate = LocalDate.parse(criteria.getFilterByreturnDate());
         		LocalDate formattedCriteriaDepartureDate = LocalDate.parse(criteria.getFilterBydepartureDate());
         		
         		newQuoteWithDepartureAndReturnDate = newcriteriaJson.getQuotes().stream()
         				.sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				.filter(quote ->  formattedCriteriaDepartureDate.isBefore(LocalDateTime.parse(quote.getOutboundLeg().getDepartureDate()).toLocalDate()))
         				.filter(quote ->  formattedCriteriaReturnDate.isAfter(LocalDateTime.parse(quote.getInboundLeg().getDepartureDate()).toLocalDate()))
         				.collect(Collectors.toList());
         		
         		newcriteriaJson.setQuotes(newQuoteWithDepartureAndReturnDate);
				
			}
         	
    List<String> mailContent= service.mailStructure(newcriteriaJson);
    

         	JavaMailUtil.sendMail("mailtopo2121@gmail.com", mailContent.toString());
         	

			
		} catch (UnirestException e) {
			
			e.printStackTrace();
		}
		return newcriteriaJson;
	}
	
	
	public RetrieveCheapestFromquotes  QuotesInbound (
			String country, String currency, String originplace, String locale, String destinationplace,  String inboundpartialdate,
			String outboundpartialdate, Boolean direct, int maxPrice, String filterBydepartureDate, String filterByreturnDate
			) throws Exception{
		
	
		
		HttpResponse<String> response = null;
		
		RetrieveCheapestFromquotes newcriteriaJson=new RetrieveCheapestFromquotes();
		
		 
		try {
			
			response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/{country}/{currency}/{locale}/{originplace}/{destinationplace}/{inboundpartialdate}/{outboundpartialdate}")
					.routeParam("country", country)
					.routeParam("currency", currency)
					.routeParam("originplace", originplace)
					.routeParam("locale", locale)
					.routeParam("destinationplace", destinationplace)
					.routeParam("inboundpartialdate", outboundpartialdate )
					.routeParam("outboundpartialdate", inboundpartialdate)	
					.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
					.header("x-rapidapi-key", "c1658f5d0dmsh026228b9892adddp1fded6jsn653906320566")
					.asString();
			
			TimeUnit.MINUTES.sleep(2);
			
			 criteriaJson = new ObjectMapper().readValue(response.getBody(), RetrieveCheapestFromquotes.class);
			 	
			
			newcriteriaJson.setCarriers(criteriaJson.getCarriers());
         	newcriteriaJson.setCurrencies(criteriaJson.getCurrencies());
         	newcriteriaJson.setPlaces(criteriaJson.getPlaces());
         	newcriteriaJson.setQuotes(criteriaJson.getQuotes());
         	
         	
         	List <Quotes> newQuoteDirectWithPrice = new ArrayList<>();
         	
         	if (direct) {
         		 newQuoteDirectWithPrice= newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				 .filter(quote -> quote.getMinPrice() < maxPrice &&  quote.isDirect())
         				 .collect(Collectors.toList());
         		newcriteriaJson.setQuotes(newQuoteDirectWithPrice);
			}
         	
         	if (direct == false) {
         		 newQuoteDirectWithPrice= newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				 .filter(quote -> quote.getMinPrice() < maxPrice && quote.isDirect()   ||  quote.getMinPrice() < maxPrice && !quote.isDirect())
         				 .collect(Collectors.toList());
         		newcriteriaJson.setQuotes(newQuoteDirectWithPrice);
			}
         	
         	
         	Optional<String> departureDate = Optional.ofNullable(filterBydepartureDate);
         	Optional<String> returnDate = Optional.ofNullable(filterByreturnDate);
         	List <Quotes> newQuoteWithDepartureDate = new ArrayList<>();
         		 	
         	if (departureDate.isPresent() && !returnDate.isPresent() ) {
         		LocalDate formattedCriteriaDate = LocalDate.parse(filterBydepartureDate);
         		newQuoteWithDepartureDate = newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				.filter(quote -> 
         				
         				formattedCriteriaDate.isBefore(LocalDateTime.parse(quote.getOutboundLeg().getDepartureDate()).toLocalDate())).collect(Collectors.toList());
         		
         		newcriteriaJson.setQuotes(newQuoteWithDepartureDate);
			}
         	
         	List <Quotes> newQuoteWithReturnDate = new ArrayList<>();
         	
         	if (returnDate.isPresent() && !departureDate.isPresent()) {
         		LocalDate formattedCriteriaReturnDate = LocalDate.parse(filterByreturnDate);
         		newQuoteWithReturnDate = newcriteriaJson.getQuotes().stream().sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				.filter(quote -> 
         				
         				formattedCriteriaReturnDate.isAfter(LocalDateTime.parse(quote.getInboundLeg().getDepartureDate()).toLocalDate())).collect(Collectors.toList());
         		
         		newcriteriaJson.setQuotes(newQuoteWithReturnDate);
			}
         	
         	List <Quotes> newQuoteWithDepartureAndReturnDate = new ArrayList<>();
         	
         	if (returnDate.isPresent() && departureDate.isPresent()) {
         		LocalDate formattedCriteriaReturnDate = LocalDate.parse(filterByreturnDate);
         		LocalDate formattedCriteriaDepartureDate = LocalDate.parse(filterBydepartureDate);
         		
         		newQuoteWithDepartureAndReturnDate = newcriteriaJson.getQuotes().stream()
         				.sorted(Comparator.comparingInt(Quotes::getMinPrice))
         				.filter(quote ->  formattedCriteriaDepartureDate.isBefore(LocalDateTime.parse(quote.getOutboundLeg().getDepartureDate()).toLocalDate()))
         				.filter(quote ->  formattedCriteriaReturnDate.isAfter(LocalDateTime.parse(quote.getInboundLeg().getDepartureDate()).toLocalDate()))
         				.collect(Collectors.toList());
         		
         		
         		
         		newcriteriaJson.setQuotes(newQuoteWithDepartureAndReturnDate);
         		//newcriteriaJson.setQuotes(newQuoteWithDepartureAndReturnDates);
         		
         		
         		
        		
				
			}
         	
    //List<String> mailContent= service.mailStructure(newcriteriaJson);
    

         	//JavaMailUtil.sendMail("mailtopo2121@gmail.com", mailContent.toString());
         	

			
		} catch (UnirestException e) {
			
			e.printStackTrace();
		}
		return newcriteriaJson;
	
	
	}
	

	
	public  void  listOfTripByMail(RetrieveCheapestFromquotes newCriteriaJson) throws Exception {
		
	
		
		List <Quotes> newQuoteWithDepartureAndReturnDates = new ArrayList<>(Arrays.asList());
 		for (Quotes quotes : newCriteriaJson.getQuotes()) {
 			
 			newQuoteWithDepartureAndReturnDates= JavaMailUtil.addToList(newQuoteWithDepartureAndReturnDates, Stream.of(quotes));
		}
 		
 		newCriteriaJson.setQuotes(newQuoteWithDepartureAndReturnDates);
		
		
		List<String> mailContent= service.mailStructure(newCriteriaJson);
	    

     	JavaMailUtil.sendMail("mailtopo2121@gmail.com", mailContent.toString());
	}
	
	
	

	
	

}
