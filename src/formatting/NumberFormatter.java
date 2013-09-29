/**
 * 
 */
package formatting;

import java.text.DecimalFormat;

/**
 * @author Jason Diamond
 *
 */
public class NumberFormatter {

	private DecimalFormat currencyFormatter;
	private DecimalFormat percentageFormatter;

	/**
	 * A class to handle various number formatting conversions required by reports, especially those that use values generated from other reports.
	 */
	public NumberFormatter() 
	{
	}
	
	/**
	 * Convert a double value to a currency looking value (numbers.nn). Rounding is 5 and up to the nearest cent.
	 * 
	 * @param value	Raw value to convert.
	 * 
	 * @return	The converted string.
	 */
	public String convertToCurrency(double value)
	{
		if(currencyFormatter == null)
		{
			currencyFormatter = new DecimalFormat("#.##");
		}
		
		return currencyFormatter.format(value);
	}
	
	/**
	 * Convert a decimal value into a percentage-looking value. No % sign is asked.
	 * 
	 * @param value		The value to convert.
	 * @param places	The number of decimal places for rounding.
	 * 
	 * @return	The original value formatted as a percent.
	 */
	public String convertToPercentage(double value, int places)
	{
		if(percentageFormatter == null)
		{
			String formatDescriptor = "#.";
			
			for(int i = 0; i < places; i ++)
			{
				formatDescriptor += "#";
			}
			
			percentageFormatter = new DecimalFormat(formatDescriptor);
		}
		
		return percentageFormatter.format(100*value);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
