package helios.report.parameters.sanitize;

import helios.date.parsing.DateParser;

/**
 * The framework tosses a lot of strings around. Here, we provide the means to sanitize those strings.
 * 
 * @author Jason Diamond
 *
 */
public abstract class StringSanitizer 
{
	private static final int SQL_DATE_LEN = 19;
	private static final String SQL_DATE_REGEX = "[01234][0-9][0-9][0-9]\\-[01][0-9]\\-[0123][0-9]\\ [012][0-9]\\:[012345][0-9]\\:[012345][0-9]";
	
	public static String truncate(String str, int maxLen)
	{
		String retval = null;
		
		if(str != null)
		{
			str = str.trim();
			if(maxLen < 0)
			{
				maxLen = str.length();
			}
			
			retval = str.substring(0, Math.min(str.length(), maxLen));
		}
		
		return retval;
	}
	
	public static String stripNonAscii(String str)
	{
		String retval = null;
		if(str != null)
		{
			retval = str.trim().replaceAll("[^\\x00-\\x7F]", "");
		}
		
		return retval;
	}
	
	public static String sanitize(String str, int maxLen)
	{
		String retval = null;
		
		if(str != null)
		{
			retval = stripNonAscii(truncate(str.trim(), maxLen));
		}
		
		return retval;
	}
	
	public static boolean isValidSQLDate(String date)
	{
		//date expected in format YYYY-MM-DD HH:MM:SS
		boolean retval = false;
		if(date != null)
		{
			try
			{
				date = StringSanitizer.sanitize(date.trim(), SQL_DATE_LEN);
				
				retval = date.equalsIgnoreCase(DateParser.NOW_DATE_KEYWORD) || date.matches(SQL_DATE_REGEX);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return retval;
	}
}
