package com.learning.api.controllers.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProcessDates {

	public static void main(String[] args) {
		System.out.println(isAcivationLinkExpired("2018/07/18 19:11:52"));

	}

	public static int compareDates(String dateTargeted) throws ParseException {

		int returnStatus = 0;
		/* Get current date */
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateToday = dateFormat.format(date);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date1 = sdf.parse(dateTargeted);
		Date date2 = sdf.parse(dateToday);

		System.out.println("date1 : " + sdf.format(date1));
		System.out.println("date2 : " + sdf.format(date2));

		if (date1.compareTo(date2) > 0) {
			returnStatus = 1;
			System.out.println("Date1 is after Date2");
		} else if (date1.compareTo(date2) < 0) {
			returnStatus = -1;
			System.out.println("Date1 is before Date2");
		} else if (date1.compareTo(date2) == 0) {
			returnStatus = 0;
			System.out.println("Date1 is equal to Date2");
		} else {
			returnStatus = -1;
			System.out.println("How to get here?");
		}

		return returnStatus;

	}

	public static int isAcivationLinkExpired(String dateTargeted) {
		Date dateNow;
		Date dateScheduled;
		int isExpired = 0;

		try {

			/* Get current date */
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String dateToday = dateFormat.format(date);

			dateNow = dateFormat.parse(dateToday);
			dateScheduled = dateFormat.parse(dateTargeted);

			Calendar regTime = Calendar.getInstance();
			regTime.setTimeInMillis(dateScheduled.getTime());
			
			Calendar currentTime = Calendar.getInstance();
			currentTime.setTimeInMillis(dateNow.getTime());
			
			int timeDifference=(int)(currentTime.getTimeInMillis() - regTime.getTimeInMillis());
			double timeDff=(timeDifference*0.001)/3600;
			System.out.println("Datenow:"+dateToday+"...time:"+currentTime.getTimeInMillis());
			System.out.println("Datetar:"+dateTargeted+"...time:"+regTime.getTimeInMillis());
			System.out.println("time difference is:"+timeDifference+"...in hours:"+timeDff);
			
			if(timeDff>24) {
				isExpired=1;
			}
			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExpired;
	}

}
