package com.learning.api.controllers.utils;


public class RandomWord {
	
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String randomAlphaNumeric(int count) {

	StringBuilder builder = new StringBuilder();

	while (count-- != 0) {

	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());

	builder.append(ALPHA_NUMERIC_STRING.charAt(character));

	}

	return builder.toString();

	}
}
