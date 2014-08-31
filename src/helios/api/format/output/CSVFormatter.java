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
	protected boolean enableHeaders;
	
	public CSVFormatter() 
	{
		super();
		enableHeaders(true);
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
	
	@Override
	public ArrayList<String> formatResults(ArrayList<String> schema, ArrayList<String[]> results) 
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		if(enableHeaders)
		{
			StringBuilder headerString = new StringBuilder();
			for(String attribute : schema)
			{
				if(enquote)
				{
					headerString.append("\"");
					headerString.append(attribute);
					headerString.append("\"");
				}
				else
				{
					headerString.append(attribute);
				}
				
				headerString.append(delim);
			}
			
			retval.add(headerString.toString());
		}
		
		retval.addAll(formatResults(results));
		
		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		StringBuilder formattedLine = new StringBuilder();
		
		for(String col : rowFields)
		{
			if(enquote)
			{
				col = "\"" + col + "\"";
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
	
	public void enableHeaders(boolean enableHeaders)
	{
		this.enableHeaders = enableHeaders;
	}
	
	public void setDelim(String delim)
	{
		this.delim = delim;
	}
	
	public String getDelim()
	{
		return delim;
	}


}
