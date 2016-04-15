/**
 * 
 */
package com.naren.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author nstanwar
 *
 */
public class DateUtil {

	/**
	 * 
	 */
	public DateUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//printAllMonths();
		Date date=new Date("10/03/2011");
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		//cal.setTimeInMillis(1404066600);
		//cal.set(Calendar.DAY_OF_MONTH, value);
		String dateS=cal.get(Calendar.DATE)+"-"+cal.get(Calendar.MONTH)+""+cal.get(Calendar.YEAR);
		System.out.println(dateS);
		System.out.println(cal.getTime());

	}
	/**
	 * Prints all 12 months names
	 */
	public static void printAllMonths(){
		//printStartEndDateOfMonth("END");
		System.out.println(Calendar.WEEK_OF_MONTH);
	}
	//get weeks list from currrent date 
	public static void getWeeksList()
	{
		//get 15 weeks from current date
				Calendar c = Calendar.getInstance();
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				for(int i=0; i<15; i++)
				{
				    System.out.print("Start Date : " + c.getTime() + ", ");
				    c.add(Calendar.DAY_OF_WEEK, 6);
				    System.out.println("End Date : " + c.getTime());
				    c.add(Calendar.DAY_OF_WEEK, 1);
				}
	}
	
	public static void printStartEndDateOfMonth(String filter)
	{
		 String MMM_DD_COMMA_YYYY       = "MMM dd, yyyy";
		    SimpleDateFormat sdf = new SimpleDateFormat(MMM_DD_COMMA_YYYY);
		    sdf.setTimeZone(TimeZone.getTimeZone("PST"));
		    sdf.format(GregorianCalendar.getInstance().getTime());

		    Calendar cal =  GregorianCalendar.getInstance();
		    int date = cal.getActualMinimum(Calendar.DATE);
		    if("END".equalsIgnoreCase(filter)){
		        date = cal.getActualMaximum(Calendar.DATE);
		    }
		    cal.set(Calendar.DATE, date);
		    String result =  sdf.format(cal.getTime());
		    System.out.println(" " + result  );
	}
	
	public static void printMonths(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date("01/01/2013"));
		while(cal.getTime().getTime() <= (new Date("12/01/2013")).getTime())
		{
			System.out.println(new SimpleDateFormat("MMM-YYYY").format(cal.getTime())+" long val "+cal.getTime().getTime());
			//long date =cal.getTime().getTime();
			//System.out.println(date);
			
			cal.add(Calendar.MONTH,1);
		}
	}

}
