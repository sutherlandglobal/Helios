/**
 * 
 */
package helios.api.format.output;

import java.util.ArrayList;


/**
 * @author Jason Diamond
 * 
 * A simple formatter for formatting a single line of Helios report results
 *
 */
public abstract class ResultsFormatter 
{
	public static final int CSV = 0;
	public static final int HTML = 1;
	public static final int XML = 2;
	public static final int JSON = 3;
	public static final int JFreeChart = 6;
	
	protected ResultsFormatter() 
	{}

	public abstract String formatLine(String[] rowFields);
	
	public abstract ArrayList<String> formatResults(ArrayList<String[]> results);	
	
	public abstract ArrayList<String> formatResults(ArrayList<String> schema, ArrayList<String[]> results);
}
