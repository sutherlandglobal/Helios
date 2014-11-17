/**
 * 
 */
package com.sutherland.helios.api.format.output;

import java.util.ArrayList;

import com.sutherland.helios.report.parameters.sanitize.StringSanitizer;


/**
 *	Format a Helios report's results into HTML format. A single table with one html row per result row, with cells for each datum. For the excel users. Delimiter doesn't matter much.
 * 
 * 
 * @author Jason Diamond
 *
 *
 *
 */
public class HTMLFormatter extends ResultsFormatter 
{
	private final static int REFRESH_INTERVAL = 10;
	private boolean autoRefresh;

	public HTMLFormatter() 
	{
		super();
		autoRefresh=false;
	}
	
	/**
	 * @return the autoRefresh setting
	 */
	public boolean isAutoRefresh() 
	{
		return autoRefresh;
	}

	/**
	 * @param autoRefresh the autoRefresh to set
	 */
	public void setAutoRefresh(boolean autoRefresh) 
	{
		this.autoRefresh = autoRefresh;
	}
	
	public ArrayList<String> formatResults(ArrayList<String> schema, ArrayList<String[]> results)
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		retval.add("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
		retval.add("<HTML><HEAD><TITLE>Helios Data Analysis</TITLE>");
		retval.add("<META http-equiv=Content-Type content=\"text/html; charset=utf-8\">");
		
		if(isAutoRefresh())
		{
			retval.add("<META http-equiv=\"refresh\" content=\""+REFRESH_INTERVAL*60+"\">");
		}
		
		retval.add("<link rel=\"shortcut icon\" href=\"images/favicon.ico?v=" + (1+Math.random())*1000 + "\" type=\"image/x-icon\">");
		retval.add("<link rel=\"icon\" href=\"images/favicon.ico?v=" + (1+Math.random())*1000 +"\" type=\"image/x-icon\">");
		retval.add("<STYLE>.warningMessage { color:red; } html body, td { font-family: arial,verdana,helvetica; font-size:10pt;	}</STYLE></HEAD>");
		
		
		retval.add("<body bgcolor=\"#ededed\"><link rel=\"stylesheet\" href=\"css/tablesorter.css\" type=\"text/css\" media=\"print, projection, screen\" />");
		
		
		retval.add("<TABLE cellSpacing=0 border=0 cellpadding=\"2\" width=\"100%\">	<TBODY>	<TR><td align=\"left\" valign=\"top\"><img height=\"93\" width=\"400\" src=\"images/helios_banner.png\"/></a>	</td>");
		retval.add("</tr></TBODY></TABLE>");
		
		retval.add("<script type=\"text/javascript\" src=\"js/jquery-1.10.2.min.js\"></script>");
		retval.add("<script type=\"text/javascript\" src= \"js/jquery.tablesorter.min.js\"></script>");
	    retval.add("<script type=\"text/javascript\" src=\"js/excelDateSorter.js\"></script>");
		
		retval.add("<div id=\"data_table_div\"><table id=\"data\" class=\"tablesorter\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\">");
		retval.add("<thead>");
		retval.add("<tr>"); 
		
		for(String header : schema)
		{
			retval.add("<th>" + header +"</th>"); 
		}
		
		retval.add("</tr>");
		retval.add("</thead>");
		retval.add("<tbody>");
		for(String[] row : results)
		{
			retval.add(formatLine(row));
		}
		retval.add("</tbody>");
		retval.add("</table></div>");
		//retval.add("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ 0: {sorter: 'text'} }    }); }); </script>");
		
		
		
		//if we have a date field, normalize the date to sql
		StringBuilder sorterString = new StringBuilder();
		
		if(results != null && results.size() > 0)
		{
			for(int i = 0; i< results.get(0).length; i++)
			{
				//if we have a regex match, note the index
				if( StringSanitizer.isValidExcelDate(results.get(0)[i]))
				{
					sorterString.append( i + ": {sorter: 'excelDateSorter'}," );
				}
			}
		}

		//retval.add("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ 0: {sorter: 'excelDateSorter'} }    }); }); </script>");
		//retval.add("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ 0: {sorter: 'sqlDateSorter'} }    }); }); </script>");
	    
	    if(sorterString.length() > 0)
	    {
	    	//remove the last character (comma
	    	sorterString.setLength(sorterString.length() - 1);
	    }
	    else
	    {
	    	sorterString.append("0: {sorter: 'text'}" );
	    }
	    
		retval.add("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ " + sorterString.toString() + " }    }); }); </script>");
	    
		
		retval.add("</body></html>");
		
		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		StringBuilder formattedLine = new StringBuilder();
		
		formattedLine.append("<tr>");
		for(String col : rowFields)
		{
			formattedLine.append("<td>" + col + "</td>");
		}
		formattedLine.append("</tr>");
		
		return formattedLine.toString();
	}

	@Override
	public ArrayList<String> formatResults(ArrayList<String[]> results) 
	{
		return formatResults(new ArrayList<String>(), results);
	}
}
