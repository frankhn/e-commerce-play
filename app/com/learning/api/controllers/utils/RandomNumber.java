package com.learning.api.controllers.utils;

import java.util.Random;

public class RandomNumber {
	
	public static int randomNumber() {
	

	Random rand = new Random();

	int  n = rand.nextInt(100000000) + 1;
	//50 is the maximum and the 1 is our minimum 
	
	
	return n;
	}
}
