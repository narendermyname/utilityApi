/**
 * 
 */
package com.naren.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author nstanwar
 *
 */
public class NstanwarUtil {

	/**
	 * 
	 */
	public NstanwarUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//System.out.println("Converted Date : "+formateDate("MMf-DD-YY",new Date()));
		//FileWriterUtil.createCSV("D:/kkkk.csv", "fdgfddfs");
		//System.getProperties().list(System.out);
		//String ddc=UtilEnum.TYPE.HH.toString();
		//System.out.println(ddc);
		unixToJavaDate();
	}
	/**
	 * This method is use to formate date 
	 * @param formate
	 * @param date
	 * @return
	 */
	public static String formateDate(String formate ,Date date)
	{
		SimpleDateFormat sdf=null;
		try{
			sdf=new SimpleDateFormat(formate);
			return sdf.format(date); 
		}catch(Exception e){
			System.err.println("Error While format ,May Be Format is not proper "+e.getMessage());
		}
		return date.toString();
	}
	
	
	public static void unixToJavaDate()
	{
		long unixSeconds = 1370111400;
		Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
	}

}
