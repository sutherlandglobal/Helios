/**
 * 
 */
package com.sutherland.helios.api.format.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import com.sutherland.helios.data.Datum;
import com.sutherland.helios.data.units.DataUnits;
import com.sutherland.helios.report.Report;
import com.sutherland.helios.report.ReportTypes;


/**
 *	Format a Helios report's results into Chart format.
 * 
 * 
 * @author Jason Diamond
 *
 *
 *
 */
public class ChartFormatter extends ResultsFormatter 
{
	private final static String LABEL_FUNC_NAME = "labelFunction";
	private final static String CHART_DATA_ONAME = "chartData";
	
	public ChartFormatter() 
	{
		super();
	}
	
	public String formatResults(Report report)
	{
		
		//JSONFormatter formatter = new JSONFormatter();
		//String jsonResults = formatter.formatResults(report);
		
		StringBuilder retval = new StringBuilder();
		
		retval.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
		retval.append("<HTML><HEAD><TITLE>Helios Data Analysis - " + report.getReportName() + "</TITLE>\n");
		retval.append("<META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n");
		
		retval.append("<link rel=\"shortcut icon\" href=\"images/favicon.ico?v=" + (1+Math.random())*1000 + "\" type=\"image/x-icon\">\n");
		retval.append("<link rel=\"icon\" href=\"images/favicon.ico?v=" + (1+Math.random())*1000 +"\" type=\"image/x-icon\">\n");
		retval.append("<STYLE>.warningMessage { color:red; } html body, td { font-family: arial,verdana,helvetica; font-size:10pt;	}</STYLE></HEAD>\n");
		
		retval.append("<body bgcolor=\"#ededed\">\n");	
		
		retval.append("<TABLE cellSpacing=0 border=0 cellpadding=\"2\" width=\"1280\"><TBODY>	<TR><td width=\"400\" align=\"left\" valign=\"top\"><img height=\"93\" width=\"400\" src=\"images/helios_banner.png\"/></a></td>\n");
		retval.append("<td width=\"880\" align=\"left\" valign=\"top\"><b>Report:&nbsp;</b>" + report.getReportName() +"<br><b>Start:&nbsp;</b>" +report.getParameters().getStartDate()+ "<br><b>End:&nbsp;</b>" + 
		report.getParameters().getEndDate()+"</b></td>\n");
		retval.append("</tr></TBODY></TABLE>\n");
		
		retval.append("<style type=\"text/css\">@import url(\"js/dojo-release-1.10.3/dijit/themes/claro/claro.css\");</style><div class=\"claro\">\n");
		retval.append("<div id=\"chartNode\" style=\"width: 1280px; height: 800px;\"></div>\n");
		retval.append("<div id=\"legend\"></div>\n");
		retval.append("</div>\n");
		
		retval.append("<script type=\"text/javascript\" src=\"js/jquery-1.10.2.min.js\"></script>\n");
		retval.append("<script> dojoConfig = {  parseOnLoad: true, gfxRenderer: \"svg,silverlight,vml\" }; </script>\n");
		retval.append("<script type=\"text/javascript\" src= \"js/dojo-release-1.10.3/dojo/dojo.js\"></script>\n");

		ArrayList<String> schema = report.getReportSchema();
	
		//determine which graph to draw from report parameters
		switch(Integer.parseInt(report.getParameters().getReportType()))
		{
		case ReportTypes.TIME_TREND_REPORT:
			//time trend line chart or stacked bars for drivers
			
			if(report.isDriversReport())
			{
				retval.append("<script type=\"text/javascript\" src= \"js/charting/stackedColumnChart.js\"></script>\n");	
				retval.append("<script type=\"text/javascript\" >\n");
				
				retval.append("var valueLabel = \"" + DataUnits.PERCENTAGE  + "\";\n");
				retval.append("var dataLabel = \""+ schema.get(0) +"\";\n");
				
				retval.append(getDriverSeries(report));
				
				retval.append("drawStackedColumnsChart(chartTitle, " + CHART_DATA_ONAME + ", dataLabel, valueLabel, " + LABEL_FUNC_NAME + ");\n</script>\n");
				retval.append("</script>\n");
			}
			else
			{
				retval.append("<script type=\"text/javascript\" src= \"js/charting/lineChart.js\"></script>\n");		
				retval.append("<script type=\"text/javascript\" >\n");
				
				retval.append("function " + LABEL_FUNC_NAME + "(value)		{ 		    return labels[value]; 		}\n");
				retval.append(getLabelFunction(report));
				
				retval.append("var chartTitle = \"" + report.getReportName() + "\";\n");
				retval.append("var valueLabel  = \"" + report.getUnits() + "\";\n");   
				retval.append("var dataLabel = \""+ schema.get(0) + "\";\n");

				retval.append(getSimpleSeries(report));
				
				retval.append("drawLineChart(chartTitle, " + CHART_DATA_ONAME + ", dataLabel, valueLabel, " + LABEL_FUNC_NAME + ");\n");
				retval.append("</script>\n");
			}

			break;
		case ReportTypes.STACK_REPORT:
		default:
			//stack chart
			
			retval.append("<script type=\"text/javascript\" src= \"js/charting/columnChart.js\"></script>\n");
			
			retval.append("<script type=\"text/javascript\">\n");
			
			retval.append("function " + LABEL_FUNC_NAME + "(value)		{ 		    return labels[value]; 		}\n");
			retval.append(getLabelFunction(report));
			
			retval.append("var chartTitle = \"" + report.getReportName() + "\";\n");
			retval.append("var valueLabel  = \"" + report.getUnits() + "\";\n");   
			retval.append("var dataLabel = \""+ schema.get(0) + "\";\n");
			
			retval.append(getSimpleSeries(report));

			retval.append("drawColumnChart(chartTitle, " + CHART_DATA_ONAME + ", dataLabel, valueLabel, " + LABEL_FUNC_NAME + ");\n");
			retval.append("</script>\n");
			break;
		}
		
		retval.append("</body></html>\n");
		
		return retval.toString();
	}
	
	private String getSimpleSeries(Report report)
	{
//				var data = 
//				{
//				    "Realtime Sales1" : [1, 2, 3,5,7],
//				    "Realtime Sales2" : [10, 20, 30,50,70],
//				};
		
		StringBuilder sb = new StringBuilder();

		sb.append("var " +CHART_DATA_ONAME+ "=\n{\n");
		
		ArrayList<String> schema = report.getReportSchema();
		
		for(int i = 1; i < schema.size(); i++)
		{
			sb.append("\"" + schema.get(i) + "\": [");
			
			for(String[] row : report.getData())
			{
				sb.append(  "" + row[i] + "," );
			}
			
			sb.append("],\n");
		}
		
		sb.append("};\n");

		return sb.toString();
	}
	
	private String getDriverSeries(Report report)
	{
		
		//foreach driver in resultset, add to hash series
			//
		
		//load the data
//		StringBuilder chartDataBuilder = new StringBuilder();
//		
//		chartDataBuilder.append( "\nvar chartData=[\n");
//		StringBuilder rowBuilder = new StringBuilder();
//		StringBuilder dataBuilder = new StringBuilder();
//		
//		for( String[] row : report.getData())
//		{
//			rowBuilder.setLength(0);
//			dataBuilder.setLength(0);
//			
//			rowBuilder.append("{");
//			//rowBuilder.append( "\"" + schema.get(0) + "\": \"" + row[0] +"\",");
//			rowBuilder.append( "\"x\": \"" + row[0] +"\","); 
//			
//			
//			for(int i =1; i< schema.size(); i++)
//			{
//				//dataBuilder.append( "\"" + schema.get(i) + "\": " + row[i]); 
//				dataBuilder.append( "\"y\": " + row[i]); 
//			}
//			rowBuilder.append(dataBuilder.toString() + "},\n");
//			chartDataBuilder.append(rowBuilder.toString());
//		}
//		chartDataBuilder.setLength(chartDataBuilder.length() - 2);
//		chartDataBuilder.append("\n];\n\n");
		
		//convert report results into map of metric -> [values]
		//worry about grain matchup later
//		LinkedHashMap<String, ArrayList<String>> serii = new LinkedHashMap<String, ArrayList<String>>();
//		ArrayList<String> grains = new ArrayList<String>();
//		
//		ArrayList<String[]> results = report.getData();
//		
//		for( int i = 0; i<results.size(); i++)
//		{
//			grains.add(results.get(i)[0]);
//			for(int j =1; j< schema.size(); j++)
//			{
//				String metricName = schema.get(j).replaceAll("\\s", "");
//				
//				if(!serii.containsKey(metricName))
//				{
//					serii.put(metricName, new ArrayList<String>());
//				}
//				
//				serii.get(metricName).add(results.get(i)[j]);
//			}
//		}
		
//		var total1 = 2723;
//		var total2 = 2739;
//		var total3 = 2502;
//
		
		
//		2013-03,Operating System-Windows Boot Issues,870,
//		2013-03,Malware-PC infected / infection suspected,395,
//		2013-03,Networking-Connectivity,361,
//		2013-03,Applications-Other Application,355,
//		2013-03,Browser-Unable to browse / Browsing maladies,266,
//		2013-03,Operating System-OS Reload,255,
		
//		//flatten drivers to make series
//		//NO java layer handles percentage calc
		
		//2013-01, 2013-02, 2013-03
//		var data =
//		{
//		    "Operating System-Windows Boot Issues" : [getDriverPercent(683,total1),getDriverPercent(731, total2),getDriverPercent(870, total3)],
//		    "Windows usability / performance-Performance": [getDriverPercent(594,total1),getDriverPercent(542, total2),getDriverPercent(0, total3)],
//		    "Applications-Other Application":[getDriverPercent(440,total1),getDriverPercent(391, total2),getDriverPercent(355, total3)],
//		    "Malware-PC infected / infection suspected":[getDriverPercent(375,total1),getDriverPercent(445, total2),getDriverPercent(395, total3)],
//		    "Networking-Connectivity":[getDriverPercent(367,total1),getDriverPercent(407, total2),getDriverPercent(361, total3)],
//		    "Operating System-OS Reload":[getDriverPercent(264,total1),getDriverPercent(0, total2),getDriverPercent(255, total3)],
//		    "Browser-Unable to browse / Browsing maladies":[getDriverPercent(0,total1),getDriverPercent(223, total2),getDriverPercent(266, total3)]
//		};
		
		TreeSet<String> dateGrains = new TreeSet<String>();
		
		for(String[] row : report.getData())
		{
			dateGrains.add(row[0]);
		}
		
		Datum stackSeries = new Datum("Drivers");
		for(String[] row : report.getData())
		{
			stackSeries.addAttribute(row[1]);
			
			for(String dateGrain : dateGrains)
			{
				stackSeries.getAttributeData(dateGrain).add( "" + getDriverGrain(dateGrain, report.getData()));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("var " +CHART_DATA_ONAME+ "=\n{\n");
		
		
		
		sb.append("};\n");
		return sb.toString();
	}
	
	private int getDriverGrain(String grain, ArrayList<String[]> resultSet)
	{
//		2013-03,Operating System-Windows Boot Issues,870,
//		2013-03,Malware-PC infected / infection suspected,395,
//		2013-03,Networking-Connectivity,361,
//		2013-03,Applications-Other Application,355,
//		2013-03,Browser-Unable to browse / Browsing maladies,266,
//		2013-03,Operating System-OS Reload,255,
		
		int retval = 0;
		
		for(String[] row : resultSet)
		{
			if(row[0].equals(grain))
			{
				retval = Integer.parseInt(row[2]);
				break;
			}
		}
		
		return retval;
	}
	
	private String getLabelFunction(Report report)
	{
//		var labels = 
//			{
//			    1: "2013-01",
//			    2: "2013-02", 
//			    3: "2013-03", 
//			    4: "2013-04", 
//			    5: "2013-05", 
//			    6: "2013-06", 
//			    7: "2013-07", 
//			    8: "2013-08", 
//			    9: "2013-09", 
//			    10: "2013-10", 
//			    11: "2013-11",
//			    12: "2013-12"
//			};
		
		StringBuilder sb = new StringBuilder();

		sb.append("var labels=\n{\n");
		
		int key = 1;
		for(String[] row : report.getData())
		{
			sb.append(key + ": \"" + row[0] + "\",\n" );
			key++;
		}
		
		sb.append("};\n");

		return sb.toString();
	}

	@Override
	public String formatResults(ArrayList<Report> reports) 
	{
		//for each report, combine series if units and report type are the same
		
		
		return null;
	}
}
