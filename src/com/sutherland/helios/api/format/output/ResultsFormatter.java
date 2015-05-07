/**
 * 
 */
package com.sutherland.helios.api.format.output;

import java.util.ArrayList;

import com.sutherland.helios.report.Report;


/**
 * @author Jason Diamond
 * 
 *
 */
public abstract class ResultsFormatter 
{
	protected boolean enquote;
	protected boolean enableHeaders;
	protected String delimiter;
	protected boolean autoRefresh;
	protected final static int REFRESH_INTERVAL = 10;
	protected final static String DEFAULT_DELIM = ",";

	protected ResultsFormatter() 
	{
		enableHeaders(true);
		setEnquote(false);
		setDelimiter(DEFAULT_DELIM);
	}
	
	public abstract String formatResults(Report report);
	public abstract String formatResults(ArrayList<Report> reports);
	
	public void setEnquote(boolean enquote) 
	{
		this.enquote = enquote;
	}
	
	public void enableHeaders(boolean enableHeaders)
	{
		this.enableHeaders = enableHeaders;
	}
	
	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}
	
	public String getDelimiter()
	{
		return delimiter;
	}

	public void setAutoRefresh(boolean autoRefresh) 
	{
		this.autoRefresh = autoRefresh;
	}
	
	public boolean isAutoRefresh() 
	{
		return autoRefresh;
	}
}
