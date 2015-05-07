/**
 * 
 */
package com.sutherland.helios.api.format.output;

import java.util.ArrayList;

import com.sutherland.helios.report.Report;
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

	public HTMLFormatter() 
	{
		super();
	}
	
	public String formatResults(Report report)
	{
		StringBuilder retval = new StringBuilder();
		
		retval.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
		retval.append("<HTML><HEAD><TITLE>Helios Data Analysis</TITLE>\n");
		retval.append("<META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n");
		
		if(isAutoRefresh())
		{
			retval.append("<META http-equiv=\"refresh\" content=\""+REFRESH_INTERVAL*60+"\">\n");
		}
		
		retval.append("<link rel=\"shortcut icon\" href=\"images/favicon.ico?v=" + (1+Math.random())*1000 + "\" type=\"image/x-icon\">\n");
		retval.append("<link rel=\"icon\" href=\"images/favicon.ico?v=" + (1+Math.random())*1000 +"\" type=\"image/x-icon\">\n");
		retval.append("<STYLE>.warningMessage { color:red; } html body, td { font-family: arial,verdana,helvetica; font-size:10pt;	}</STYLE></HEAD>\n");
		
		
		retval.append("<body bgcolor=\"#ededed\"><link rel=\"stylesheet\" href=\"css/tablesorter.css\" type=\"text/css\" media=\"print, projection, screen\" />\n");
		
		
		retval.append("<TABLE cellSpacing=0 border=0 cellpadding=\"2\" width=\"100%\">	<TBODY>	<TR><td align=\"left\" valign=\"top\"><img height=\"93\" width=\"400\" src=\"images/helios_banner.png\"/></a>	</td>\n");
		retval.append("</tr></TBODY></TABLE>\n");
		
		retval.append("<script type=\"text/javascript\" src=\"js/jquery-1.10.2.min.js\"></script>\n");
		retval.append("<script type=\"text/javascript\" src= \"js/jquery.tablesorter.min.js\"></script>\n");
	    retval.append("<script type=\"text/javascript\" src=\"js/excelDateSorter.js\"></script>\n");
		
		retval.append("<div id=\"data_table_div\"><table id=\"data\" class=\"tablesorter\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\">\n");
		retval.append("<thead>\n");
		retval.append("<tr>\n"); 
		
		for(String header : report.getReportSchema())
		{
			retval.append("<th>" + header +"</th>\n"); 
		}
		
		retval.append("</tr>\n");
		retval.append("</thead>\n");
		retval.append("<tbody>\n");
		
		ArrayList<String[]> results = report.getData();
		for(String[] row : results)
		{
			retval.append(formatLine(row));
		}
		retval.append("</tbody>\n");
		retval.append("</table></div>\n");
		//retval.add("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ 0: {sorter: 'text'} }    }); }); </script>\n");
		
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

		//retval.add("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ 0: {sorter: 'excelDateSorter'} }    }); }); </script>\n");
		//retval.add("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ 0: {sorter: 'sqlDateSorter'} }    }); }); </script>\n");
	    
	    if(sorterString.length() > 0)
	    {
	    	//remove the last character (comma
	    	sorterString.setLength(sorterString.length() - 1);
	    }
	    else
	    {
	    	sorterString.append("0: {sorter: 'text'}" );
	    }
	    
		retval.append("<script type=\"text/javascript\" >$(document).ready(function() {  $(\"#data\").tablesorter({      sortList: [[0,0]], widgets: ['zebra'],headers:{ " + sorterString.toString() + " }    }); }); </script>\n");
	    
		
		retval.append("</body></html>\n");
		
		return retval.toString();
	}
	
	public String formatLine(String[] rowFields)
	{
		StringBuilder formattedLine = new StringBuilder();
		
		formattedLine.append("<tr>\n");
		for(String col : rowFields)
		{
			formattedLine.append("<td>" + col + "</td>\n");
		}
		formattedLine.append("</tr>\n");
		
		return formattedLine.toString();
	}

	@Override
	public String formatResults(ArrayList<Report> reports) {
		// TODO Auto-generated method stub
		return null;
	}
}
