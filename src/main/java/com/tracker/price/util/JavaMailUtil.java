package com.tracker.price.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailUtil {

	
	public static void sendMail(String recepient,String msg) throws Exception  {
		
		// Properties for the mail server 
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		

		// Email address and Password
		String myAccountEmail = "mailtopo2121@gmail.com";
		String password = "KxzjkQ?fJ)A98bRL@=8DUzKzuVxa*a7BC3X";
		

		Session session = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(myAccountEmail, password);
		    }
		});
		
		
		Message message = prepareMessage(session,myAccountEmail,recepient, msg);
		Transport.send(message);
		
		
	}
	
		
		private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String msg) {
			try {
				Message message = new MimeMessage(session);	
				message.setFrom(new InternetAddress("from@gmail.com"));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
				message.setSubject("My next trip");
				message.setText(msg);
				return message;
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return null;
			
		}
		
		
		public static LocalDate convertDate(String date ){

			return LocalDateTime.parse(date).toLocalDate();		
		}
		
		
		public static<T> List<T> addToList(List<T> target, Stream<T> source)
		{
			return Stream.concat(target.stream(), source)
						.collect(Collectors.toList());
		}
		
		
	
		
	}
	
	
	

