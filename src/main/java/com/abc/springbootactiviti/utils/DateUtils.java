package com.abc.springbootactiviti.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Operations on Date
 * @author JerryLi
 *
 */
public class DateUtils {

	public DateUtils(){
		super();
	}
	
	/**
	 * whether the year is a leap year
	 * @param year the year to check
	 * @return <code>true</code> if the year is a leap year
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0)
				|| (year % 400 == 0)) {
			return true;
		}
		return false;
	}


	/**
	 * whether the string date with the format pattern is a valid date  
	 * @param date the date to check
	 * @param pattern the date's pattern
	 * @return <code>true</code> if the date is a valid date
	 */
	public static boolean isValidDate(String date, String pattern) {
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
		synchronized (df) {
			df.applyPattern(pattern);
			df.setLenient(false);
			try {
				df.parse(date);
				return true;
			} catch (Exception e) {
			}
		}
		return false;
	}
	
	/**
	 * check whether the string date in format yyyyMMdd is a valid Date 
	 * @param date
	 * @return <code>true</code> if the date is a valid date
	 */
	public static boolean isValidDate(String date) {
		String pattern = "yyyyMMdd";
		return isValidDate(date, pattern);
	}	
	
	/**
	 * change the string type from String to Date<BR>
	 * by dafault it can manage the string with the pattern:yyyyMMdd,yyyy MM dd,yyyy.MM.dd,yyyy-MM-dd,yyyy/MM/dd,yyyy\\MM\\dd
	 * @param dateStr the string to change
	 * @return the Date has changed
	 * @throws ParseException
	 */
	public static Date changeStringToDate(String dateStr) throws ParseException{
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
		String pattern = "";
		if (isValidDate(dateStr,"yyyyMMdd")){
			pattern = "yyyyMMdd";
		}if (isValidDate(dateStr,"yyyy MM dd")){
			pattern = "yyyy MM dd";
		}else if(isValidDate(dateStr,"yyyy.MM.dd")){
			pattern = "yyyy.MM.dd";
		}else if(isValidDate(dateStr,"yyyy-MM-dd")){
			pattern = "yyyy-MM-dd";
		}else if(isValidDate(dateStr,"yyyy/MM/dd")){
			pattern = "yyyy/MM/dd";
		}else if(isValidDate(dateStr,"yyyy\\MM\\dd")){
			pattern = "yyyy\\MM\\dd";
		}		
		synchronized (df) {
			df.applyPattern(pattern);
			df.setLenient(false);
			Date	date = df.parse(dateStr);
			return date;
		}		
	}
	
	/**
	 * change the string type from String to Date<BR>
	 * the string's Date pattern is pattern
	 * @param dateStr the string to change
	 * @param pattern the string's Date pattern
	 * @return the Date has changed
	 * @throws ParseException
	 */
	public static Date changeStringToDate(String dateStr,String pattern) throws ParseException{
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
		synchronized (df) {
			df.applyPattern(pattern);
			df.setLenient(false);
			Date	date = df.parse(dateStr);
			return date;
		}		
	}	
	
	/**
	 * change the Date to String<BR>
	 * Applies the given localized pattern string to this date format 
	 * @param date the Date to change
	 * @param pattern the given localized pattern string  
	 * @return the changed string
	 * @throws ParseException
	 */
	public static String changeDateToString(Date date,String pattern) throws ParseException{
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
		synchronized (df) {
			df.applyPattern(pattern);
			df.setLenient(false);
			return df.format(date);
		}
	}	

	/**
	 * Compare two Date in type String<BR> 
	 * @param strDate1 the Date to compare 
	 * @param strDate2 the Date to compare
	 * @return
	 * the value = 0 if the argument strDate1 is equal to this strDate2;<BR> 
	 * a value &lt; 0 if strDate1 is before the Date strDate2; <Br>
	 * and a value &gt; 0 if strDate1 is after the Date strDate2.<BR>
	 * @throws ParseException
	 */
	public static int compareTo(String strDate1,String strDate2) throws ParseException{
		Date date1 = changeStringToDate(strDate1);
		Date date2 = changeStringToDate(strDate2);		
		return date1.compareTo(date2);
	}

	/**
	 * Adds a number of years,months,days to a date returning a new object.
     * The original date object is unchanged.
	 * @param date the date, not null
	 * @param year the amount of years to add, may be negative
	 * @param month the amount of months to add, may be negative
	 * @param day the amount of days to add, may be negative
	 * @return the new date object with the year,month,day added
	 */
	public static Date addTime(Date date,int year,int month,int day){
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
		Calendar   calendar   =   Calendar.getInstance();  
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.YEAR, year);		
		return calendar.getTime();
	}
	
	/**
	 * Adds a number of years to a date returning a new object.
	 * @param date the date, not null
	 * @param year the amount of years to add, may be negative
	 * @return the new date object with the year added
	 */
	public static Date addYear(Date date,int year){
		return addTime(date,year,0,0);
	}
	
	/**
	 * Adds a number of months to a date returning a new object.
	 * @param date the date, not null
	 * @param month the amount of months to add, may be negative
	 * @return the new date object with the month added
	 */
	public static Date addMonth(Date date,int month){
		return addTime(date,0,month,0);
	}
	
	/**
	 * Adds a number of days to a date returning a new object.
	 * @param date the date, not null
	 * @param day the amount of days to add, may be negative
	 * @return the new date object with the day added
	 */
	public static Date addDay(Date date,int day){
		return addTime(date,0,0,day);
	}
	

	/**
	 * Get the year of the Date
	 * @param date the Date
	 * @return the year of the Date,e.g. the Date 2008/08/10 return 2008
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Get the month of the Date
	 * @param date the Date
	 * @return the month of the Date,not minus 1,e.g. the Date 2008/08/10 return 8
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * Get the day of the Date
	 * @param date the Date
	 * @return the day of the Date,not minus 1,e.g. the Date 2008/08/10 return 10
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}	
	
	/**
	 * With the birthday and the new Date,calculate when reach the new Date,how old one person is 
	 * @param nowDate the Date to calculate 
	 * @param birthDate the birthday
	 * @return the year between the two Date  
	 */
	public static int getAge(Date nowDate,Date birthDate) {
		int age = 0;
		GregorianCalendar birthday = new GregorianCalendar();
		GregorianCalendar nowTime = new GregorianCalendar();

		birthday.setTime(birthDate);
		nowTime.setTime(nowDate);
		
		age = nowTime.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
		
		if(age<=0){
			return 0;
		}
		if (nowTime.get(Calendar.MONTH) > birthday.get(Calendar.MONTH)) {
			return age;
		} else if (nowTime.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
			return --age;
		} else {
			if (nowTime.get(Calendar.DAY_OF_MONTH) >= birthday.get(Calendar.DAY_OF_MONTH)) {
				return age;
			} else {
				return --age;
			}
		}
	}
	
	/**
	 * Calculate how many years between birthDate and now  
	 * @param birthDate the birthday
	 * @return the years between birthDate and now
	 */
	public static int getAge(Date birthDate) {
		return getAge(new Date(),birthDate);
	}	

}
