package com.sutherland.helios.api.report.frontend;

public interface ReportFrontEnds 
{
	public final static int CSV = 0;
	public final static String CSV_NAME = "CSV";
	
	public final static int HTML = 1;
	public final static String HTML_NAME = "HTML";
		
	public final static int XML = 2;
	public final static String XML_NAME = "XML";
	
	public final static int JSON = 3;
	public final static String JSON_NAME = "JSON";
	
	public final static int BIRT = 4;
	public final static String BIRT_NAME = "BIRT";
	
	public final static int XCHART = 5;
	public final static String XCHART_NAME = "xChart";
	
	public final static int JFREECHART = 6;
	public final static String JFCHART_NAME = "JFreeChart";
	
	public final static int EXCEL = 7;
	public final static String EXCEL_NAME = "Excel";
}
