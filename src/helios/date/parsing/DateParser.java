/**
 * 
 */
package helios.date.parsing;

import helios.report.parameters.sanitize.StringSanitizer;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

/**
 * Library for any date manipulation, conversion, and calculation. Any database work with a lot of reporting on date granularity will have plenty of this.
 * 
 * @author Jason Diamond
 */
public class DateParser 
{

	private LinkedHashMap<String,Integer> monthNameToValue;
	private LinkedHashMap<Integer,String> valueToMonthName;

	private LinkedHashMap<Integer,String> valueToDOW;
	
	public static final String NOW_DATE_KEYWORD = "NOW";

	/**
	 * 	Build the date parser, and load up hashes for the easy Month <-> Month number associations.
	 */
	public DateParser() 
	{
		monthNameToValue = new LinkedHashMap<String,Integer>();
		valueToMonthName = new LinkedHashMap<Integer,String>();
		valueToDOW = new LinkedHashMap<Integer,String>();

		monthNameToValue.put("Jan", 1);
		monthNameToValue.put("Feb", 2);
		monthNameToValue.put("Mar", 3);
		monthNameToValue.put("Apr", 4);
		monthNameToValue.put("May", 5);
		monthNameToValue.put("Jun", 6);
		monthNameToValue.put("Jul", 7);
		monthNameToValue.put("Aug", 8);
		monthNameToValue.put("Sep", 9);
		monthNameToValue.put("Oct", 10);
		monthNameToValue.put("Nov", 11);
		monthNameToValue.put("Dec", 12);

		valueToDOW.put(1, "Sun");
		valueToDOW.put(2, "Mon");
		valueToDOW.put(3, "Tue");
		valueToDOW.put(4, "Wed");
		valueToDOW.put(5, "Thu");
		valueToDOW.put(6, "Fri");
		valueToDOW.put(7, "Sat");

		for(String monthName : monthNameToValue.keySet())
		{
			valueToMonthName.put(monthNameToValue.get(monthName), monthName);
		}
	}

	/**
	 * Retrieve the Month name <-> Month index bindings.
	 * 
	 * @param month	Either an integer to lookup the month with, or a string to lookup the index with.
	 * 
	 * @return	The month index corresponding to the month name, or vice versa depending on the actual type of the month util.parameter. 
	 */
	public String monthLookup(String month)
	{
		month = StringSanitizer.sanitize(month, 4);
		
		String retval = "";
		try
		{
			retval = valueToMonthName.get(Integer.parseInt(month));
		}
		catch (NumberFormatException e)
		{
			retval = monthNameToValue.get(month).toString();
		}
		finally
		{
			if (retval.equals(""))
			{
				System.err.println("Error looking up " + month + " in month to index mappings");
			}
		}

		return retval;
	}

	/**
	 * Determine the distance in minutes between two dates. Start and end are interchangable since this is essentially a distance measurement.	
	 * 
	 * @param startDate	Demarcation 1 of the interval.
	 * @param endDate		Demarcation 2 of the interval.
	 * 
	 * @return 	The distance in minutes between two dates.	
	 */
	public static double getMinutesBetween(GregorianCalendar startDate, GregorianCalendar endDate)
	{

		double retval = -1L;

		if(startDate != null && endDate != null)
		{
			if(startDate.equals(endDate))
			{
				retval = 0L;
			}
			else if(startDate.before(endDate))
			{
				retval = (endDate.getTime().getTime() - startDate.getTime().getTime());
			}
			else
			{
				retval = ( startDate.getTime().getTime() - endDate.getTime().getTime() );

			}
			retval /= 60000; //ms -> s, s-> m
		}

		return retval;
	}

	/**
	 * Format a GregorianCalendar into something more readable, specifically in the form "YYYY-MM-DD HH:MM:SS".
	 * 
	 * @param date	The date to convert.
	 * 
	 * @return	A string representing a GregorianCalendar.
	 */
	public static String toSQLDateFormat(GregorianCalendar date)
	{
		StringBuilder retval = new StringBuilder();

		retval.append(date.get(Calendar.YEAR));
		retval.append("-");

		if(date.get(Calendar.MONTH)+1 < 10)
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.MONTH)+1);
		retval.append("-");

		if(date.get(Calendar.DAY_OF_MONTH) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.DAY_OF_MONTH));
		retval.append(" ");

		if(date.get(Calendar.HOUR_OF_DAY) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.HOUR_OF_DAY));
		retval.append(":");

		if(date.get(Calendar.MINUTE) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.MINUTE));
		retval.append(":");


		if(date.get(Calendar.SECOND) < 10) 
		{
			retval.append("0");
		}
		retval.append(date.get(Calendar.SECOND));

		return retval.toString();
	}

	/**
	 * Convert an SQL date (2010-06-27 00:00:00) to a GregorianCalendar date.
	 * 
	 * @param date	The date string to convert.
	 * 
	 * @return	The converted date, or null if the conversion failed.
	 */
	public static GregorianCalendar convertSQLDateToGregorian(String date)
	{
		//dates in YYYY-MM-DD HH:MM:SS
		
		GregorianCalendar retval = null;

		if( StringSanitizer.isValidSQLDate(date))
		{
			if(date.equalsIgnoreCase("now"))
			{
				retval = new GregorianCalendar();
			}
			else
			{
				try
				{
					//cheers to dishonest javadocs
					//0-based stupid
					String[] dateComponents = date.split(" ");
					String[] dateFields = dateComponents[0].split("-");
					String[] timeFields = dateComponents[1].split(":");
	
					retval = new GregorianCalendar
					(
									Integer.parseInt(dateFields[0]),
									Integer.parseInt(dateFields[1]) -1,
									Integer.parseInt(dateFields[2]),
									Integer.parseInt(timeFields[0]),
									Integer.parseInt(timeFields[1]),
									(int) Double.parseDouble(timeFields[2])	//truncate tenths of a second in mysql timestamps
					);
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.err.println("Error converting " + date);
				}
				catch(NullPointerException e)
				{
					System.err.println("Error converting " + date);
				}
			}
	
		}

		return retval;
	}
	
    /**
     * Convert an MS Access date (Mon Oct 25 14:36:00 EDT 2010) to a GregorianCalendar date.
     * 
     * @param date  The date string to convert.
     * 
     * @return      The converted date, or null if the conversion failed.
     */
	public GregorianCalendar convertMSAccessDateToGregorian(String date)
	{
		GregorianCalendar retval = null;

		date = StringSanitizer.sanitize(date,30);

		if(date.equalsIgnoreCase("now"))
		{
			retval = new GregorianCalendar();
		}
		else
		{

			//dates in YYYY-MM-DD HH:MM:SS
			//Wed Nov 03 21:35:00 EDT 2010
			//
			//date is [1]-[2]-[5] [3] from splt

			String[] dateComponents= null;
			String[] dateFields = null;
			String[] timeFields= null;

			try
			{
				dateComponents = date.split(" ");
				dateFields = new String[]{dateComponents[5], monthLookup(dateComponents[1]), dateComponents[2]};
				timeFields = dateComponents[3].split(":");

				//cheers to dishonest javadocs
				//0-based stupid


				retval = new GregorianCalendar
						(
								Integer.parseInt(dateFields[0]),
								Integer.parseInt(dateFields[1]) -1,
								Integer.parseInt(dateFields[2]),
								Integer.parseInt(timeFields[0]),
								Integer.parseInt(timeFields[1]),
								(int) Double.parseDouble(timeFields[2]) //truncate tenths of a second in mysql timestamps
								);
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.err.println("Error converting " + date);
			}
			catch(NullPointerException e)
			{
				System.err.println("Error converting " + date);
			}
		}


		return retval;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((monthNameToValue == null) ? 0 : monthNameToValue.hashCode());
		result = prime * result
				+ ((valueToDOW == null) ? 0 : valueToDOW.hashCode());
		result = prime
				* result
				+ ((valueToMonthName == null) ? 0 : valueToMonthName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DateParser)) {
			return false;
		}
		DateParser other = (DateParser) obj;
		if (monthNameToValue == null) {
			if (other.monthNameToValue != null) {
				return false;
			}
		} else if (!monthNameToValue.equals(other.monthNameToValue)) {
			return false;
		}
		if (valueToDOW == null) {
			if (other.valueToDOW != null) {
				return false;
			}
		} else if (!valueToDOW.equals(other.valueToDOW)) {
			return false;
		}
		if (valueToMonthName == null) {
			if (other.valueToMonthName != null) {
				return false;
			}
		} else if (!valueToMonthName.equals(other.valueToMonthName)) {
			return false;
		}
		return true;
	}
}
