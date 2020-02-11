package com.learning.api.controllers.utils;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TokenGenerator {
	
	 protected static SecureRandom random = new SecureRandom();
     
     public static synchronized String generateToken() {
    	 
             long longToken = Math.abs( random.nextLong() );
             String random = Long.toString( longToken, 5 );
             
             DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
             
     		 Date date = new Date();
     			
     		 UUID uuid = UUID.randomUUID();
             String randomUUIDString = uuid.toString();
     		
             return randomUUIDString+dateFormat.format(date)+random ;
     }
}
