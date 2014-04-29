/**
 * 
 */
package helios.api.format.output;

import java.util.ArrayList;


/**
 *	Format a Helios report's results into CSV format. No header or footer required.
 * 
 * 
 * @author Jason Diamond
 *
 *
 *
 */
public class CSVFormatter extends ResultsFormatter 
{
	protected boolean enquote = false;

	protected String delim;
	protected final static String DEFAULT_DELIM = ","; 
	
	public CSVFormatter() 
	{
		super();
		setEnquote(false);
		setDelim(DEFAULT_DELIM);
	}
	
	public ArrayList<String> formatResults(ArrayList<String[]> results)
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		for(String[] row : results)
		{
			retval.add(formatLine(row));
		}
		
		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		StringBuilder formattedLine = new StringBuilder();
		
		for(String col : rowFields)
		{
			if(enquote)
			{
				col = "\"" + col;
				col += "\"";
			}
			
			formattedLine.append(col);
			
			formattedLine.append(delim);
		}
		
		return formattedLine.toString();
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

	@Override
	public ArrayList<String> formatResults(ArrayList<String> schema, ArrayList<String[]> results) 
	{
		//no schema for csv
		return formatResults(new ArrayList<String>(), results);
	}
}
