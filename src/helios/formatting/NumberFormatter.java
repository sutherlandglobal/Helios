/**
 * 
 */
package helios.formatting;

import java.text.DecimalFormat;

/**
 * A class to handle various number formatting conversions required by reports, especially those that use values generated from other reports.
 * 
 * @author Jason Diamond
 *
 */
public final class NumberFormatter 
{
	private NumberFormatter(){}
	
	/**
	 * Convert a double value to a currency looking value (numbers.nn). Rounding is 5 and up to the nearest cent.
	 * 
	 * @param value	Raw value to convert.
	 * 
	 * @return	The converted string.
	 */
	public static String convertToCurrency(double value)
	{
		return new DecimalFormat("#.##").format(value);
	}
	
	/**
	 * Convert a decimal value into a percentage-looking value. No % sign is asked.
	 * 
	 * @param value		The value to convert.
	 * @param places	The number of decimal places for rounding.
	 * 
	 * @return	The original value formatted as a percent.
	 */
	public static String convertToPercentage(double value, int places)
	{
		String formatDescriptor = "#.";

		for(int i = 0; i < places; i ++)
		{
			formatDescriptor += "#";
		}

		return new DecimalFormat(formatDescriptor).format(100*value);
	}
}
