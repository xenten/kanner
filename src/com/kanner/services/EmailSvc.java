package com.kanner.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/email")
public class EmailSvc {
	
	@GET
	public String sendEmail() {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message message = null;
		
		try {
			
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress("me@me.com", "ME"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("you@you.com", "YOU"));
			
			message.setSubject("This is a test");
			
			message.setText("This is the body of the text");
			
			Transport.send(message);
			
		} catch (Exception e) {
			
			System.out.println("Exception");
			return "false";
		}
		
		System.out.println(message.toString());
		return "true";
		
	}

}
