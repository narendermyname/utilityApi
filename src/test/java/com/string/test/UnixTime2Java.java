/**
 * 
 */
package com.string.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author nstanwar
 *
 */
public class UnixTime2Java {

	/**
	 * 
	 */
	public UnixTime2Java() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long unixSeconds = 1378060200;
		//Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss aa"); // the format of your date
		inputFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		String formattedDate = inputFormat.format(new Date());
		System.out.println(new Date(formattedDate));

	}

}
