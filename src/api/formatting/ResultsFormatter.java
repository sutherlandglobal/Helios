/**
 * 
 */
package api.formatting;

import java.util.ArrayList;


/**
 * @author Jason Diamond
 * 
 * A simple formatter for formatting a single line of Helios report results
 *
 */
public abstract class ResultsFormatter 
{

	protected boolean enquote = false;

	protected String delim;
	protected final static String DEFAULT_DELIM = ","; 

	/**
	 * 
	 */
	protected ResultsFormatter() 
	{
		setEnquote(false);
		setDelim(DEFAULT_DELIM);
	}
	
	public void setEnquote(boolean enquote) 
	{
		this.enquote = enquote;
	}
	
	public void setDelim(String delim)
	{
		this.delim = delim;
	}
	
	public String getDelim()
	{
		return delim;
	}
	
	public abstract String formatLine(String[] rowFields);
	
	public abstract ArrayList<String> formatResults(ArrayList<String[]> results);	
}
