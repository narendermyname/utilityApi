/**
 * 
 */
package com.naren.util;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationFieldType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author nstanwar
 *
 */
public class DateUtil {
	private static final int DATE_STR_LENGTH = 10;

	private static final DateTimeFormatter AV_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");

	private static final DateTimeFormatter AV_FORMATTER_WITH_TIME = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	private static final DateTimeFormatter UDP_FORMATTER = DateTimeFormat.forPattern("MMM dd, yyyy");

	private static final DateTimeFormatter UDP_FORMATTER_WITH_TIME = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

	private static final DateTimeFormatter UDP_TRIP_DATE_FORMATTER = DateTimeFormat.forPattern("MM-dd-yy");

	private static final String DDMMYY_FORMAT = "ddMMyy";

	private static final String YYMMDD_FORMAT = "yyMMdd";

	private static final String MONTH_FORMAT = "M";

	private static final String DAY_FORMAT = "d";

	private static final String YEAR_FORMAT = "yy";

	private static final String DATE_FORMAT = "%s%s%s%s%s";

	private static final Pattern AV_DATE_PATTERN = Pattern
			.compile("((20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])");

	private static final Pattern AV_TIME_PATTERN = Pattern.compile("([0-2]\\d):([0-5]\\d)");
	
	private static final Comparator<DateTime> TIME_SORT = (dateTime1, dateTime2)->{
		return Integer.compare(dateTime1.getMillisOfDay(), dateTime2.getMillisOfDay());
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//jodaDate();
		//locaDateTime();
		updateDate();
	}
	
	public static void updateDate() {
		System.out.println(new DateTime());
		System.out.println(new DateTime().withField(DateTimeFieldType.hourOfDay(), 12)); //2018-12-06T20:46:57.154+05:30
		System.out.println(new DateTime().withHourOfDay(1).withMinuteOfHour(45));
	}
	
	private static void locaDateTime() {
		System.out.println(new DateTime().plusDays(3).plusHours(2).plusMinutes(1));
		System.out.println(new DateTime().plusDays(1).plusHours(3).plusMinutes(1));
		System.out.println(new DateTime().plusDays(2).plusHours(1).plusMinutes(1));
		List<DateTime> list= Arrays.asList(new DateTime().plusDays(3).plusHours(2).plusMinutes(1),new DateTime().plusDays(1).plusHours(3).plusMinutes(1), new DateTime().plusDays(2).plusHours(1).plusMinutes(1));
		
		Collections.sort(list, TIME_SORT);
		list.stream().forEach(dt->{
			System.out.println(dt);
		});
	}

	private static void jodaDate() {
		DateTime dateTime1 = fromAVFormat("2019-01-15 01:12:22");//new DateTime();
		DateTime dateTime11 = fromAVFormat("2019-01-15 12:00:22");//new DateTime();
		
		DateTime dateTime2 = fromAVFormat("2019-01-14 17:09:22");//new DateTime();
		DateTime dateTime22 = fromAVFormat("2019-01-14 01:12:22");//new DateTime();
		
		DateTime dateTime3 = fromAVFormat("2019-01-13 01:12:22");//new DateTime();
		DateTime dateTime33 = fromAVFormat("2019-01-13 23:59:59");//new DateTime();
		DateTime dateTime331 = fromAVFormat("2019-01-19");//new DateTime();
		//DateTime dateTime332 = fromAVFormat("2018-12-11T00:00:00.000-06:00");//new DateTime();
		List<DateTime> dateTimeList = Arrays.asList(dateTime1,dateTime11,dateTime2,dateTime22,dateTime3,dateTime33,dateTime331);
		Collections.sort(dateTimeList, TIME_SORT);
		
		dateTimeList.stream().forEach(dt->{
			System.out.println(dt);
		});
	}

	/**
	 * Prints all 12 months names
	 */
	public static void printAllMonths() {
		// printStartEndDateOfMonth("END");
		System.out.println(Calendar.WEEK_OF_MONTH);
	}

	// get weeks list from currrent date
	public static void getWeeksList() {
		// get 15 weeks from current date
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		for (int i = 0; i < 15; i++) {
			System.out.print("Start Date : " + c.getTime() + ", ");
			c.add(Calendar.DAY_OF_WEEK, 6);
			System.out.println("End Date : " + c.getTime());
			c.add(Calendar.DAY_OF_WEEK, 1);
		}
	}

	public static void printStartEndDateOfMonth(String filter) {
		String MMM_DD_COMMA_YYYY = "MMM dd, yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(MMM_DD_COMMA_YYYY);
		sdf.setTimeZone(TimeZone.getTimeZone("PST"));
		sdf.format(GregorianCalendar.getInstance().getTime());

		Calendar cal = GregorianCalendar.getInstance();
		int date = cal.getActualMinimum(Calendar.DATE);
		if ("END".equalsIgnoreCase(filter)) {
			date = cal.getActualMaximum(Calendar.DATE);
		}
		cal.set(Calendar.DATE, date);
		String result = sdf.format(cal.getTime());
		System.out.println(" " + result);
	}

	public static void printMonths() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date("01/01/2013"));
		while (cal.getTime().getTime() <= (new Date("12/01/2013")).getTime()) {
			System.out.println(
					new SimpleDateFormat("MMM-YYYY").format(cal.getTime()) + " long val " + cal.getTime().getTime());
			// long date =cal.getTime().getTime();
			// System.out.println(date);

			cal.add(Calendar.MONTH, 1);
		}
	}

	public static DateTime fromAVFormat(String dstring) {
		return fromAVFormat(dstring, DateTimeZone.UTC);
	}

	public static DateTime fromAVFormat(String dstring, DateTimeZone timeZone) {
		if (dstring == null) {
			return null;
		}
		if (dstring.length() == DATE_STR_LENGTH) {
			return AV_FORMATTER.parseLocalDate(dstring).toDateTimeAtStartOfDay(timeZone);
		} else {
			return AV_FORMATTER_WITH_TIME.withZone(timeZone).parseDateTime(dstring);
		}
	}

	static {
		/*
		 * //printAllMonths(); Date date=new Date("10/03/2011"); Calendar
		 * cal=Calendar.getInstance(); cal.setTime(date);
		 * //cal.setTimeInMillis(1404066600); //cal.set(Calendar.DAY_OF_MONTH, value);
		 * String
		 * dateS=cal.get(Calendar.DATE)+"-"+cal.get(Calendar.MONTH)+""+cal.get(Calendar.
		 * YEAR); System.out.println(dateS); System.out.println(cal.getTime());
		 * LocalTime locaTime = LocalTime.parse("2018-07-17T09:59:51.312Z", new
		 * DateTimeFormatterBuilder().toFormatter()); System.out.println(locaTime);
		 */
	}
}
