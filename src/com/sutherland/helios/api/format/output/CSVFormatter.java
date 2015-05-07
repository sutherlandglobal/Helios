/**
 * 
 */
package com.sutherland.helios.api.format.output;

import java.util.ArrayList;

import com.sutherland.helios.report.Report;


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
	public CSVFormatter() 
	{
		super();
		enableHeaders(true);
		setEnquote(false);
		setDelimiter(DEFAULT_DELIM);
	}
	
	@Override
	public String formatResults(Report report) 
	{
		StringBuilder retval = new StringBuilder();
		
		if(enableHeaders)
		{
			StringBuilder headerString = new StringBuilder();
			for(String attribute : report.getReportSchema())
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
				
				headerString.append(delimiter);
			}
			
			retval.append(headerString.toString());
		}
		
		for(String[] row : report.getData())
		{
			retval.append(formatLine(row));
		}
		
		return retval.toString();
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
			
			formattedLine.append(delimiter);
		}
		
		return formattedLine.toString();
	}

	@Override
	public String formatResults(ArrayList<Report> reports) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
