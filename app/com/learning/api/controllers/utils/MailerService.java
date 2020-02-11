package com.learning.api.controllers.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailerService {
	
	private String actLink;
	private String actvEmail;
	
	
	public String getActLink() {
		return actLink;
	}
	public void setActLink(String actLink) {
		this.actLink = actLink;
	}
	public String getActvEmail() {
		return actvEmail;
	}
	public void setActvEmail(String actvEmail) {
		this.actvEmail = actvEmail;
	}
	public static void send(String seller,String productName,String productPrice,String email, String phone){  
		  
		final String user="harfrank2@gmail";//change accordingly  
		final String pass="modeste2006";  
		  
		//1st step) Get the session object    
		Properties props = new Properties();  
		props.put("mail.smtp.host", "mail.yahoo.com");//change accordingly  
		props.put("mail.smtp.auth", "true");  
		  
		Session session = Session.getDefaultInstance(props,  
		 new javax.mail.Authenticator() {  
		  protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(user,pass);  
		   }  
		});  
		//2nd step)compose message  
		try {  
		 MimeMessage message = new MimeMessage(session);  
		 message.setFrom(new InternetAddress(user));  
		 message.addRecipient(Message.RecipientType.TO,new InternetAddress(seller));  
		 message.setText(productName);  
		 message.setSubject(productPrice);  
		 message.setText(email);   
		 message.setText(phone);  
		   
		 //3rd step)send message  
		 Transport.send(message);  
		  
		 System.out.println("Done");  
		  
		 } catch (MessagingException e) {  
		    throw new RuntimeException(e);  
		 }  
		      
		}  
	public static  void  sendMail(String userEmail,String messageBody) {

			final String username = "harfrank2@gmail.com";
			final String password = "Modeste2006";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
			

			try {
				
				String subject ="E-Dealing Activation Link";

				String body="Dear "+userEmail
						+ "\n\n You have a registered" 
						+ ", Please Click the link bellow to activate your acoount"
						+ "\n "+messageBody;
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("E-Dealing"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(userEmail));
				message.setSubject(subject);
				//message.setText();
				//message.setContent(body,"text/html;charset=utf-8");
				message.setContent(
			              "<h1>Take The next Step </h1><br>"+body+"<br> <a href="+messageBody+"><h2>Activation Link</h2></a>",
			             "text/html;charset=utf-8");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			return ;    		
	}

}
