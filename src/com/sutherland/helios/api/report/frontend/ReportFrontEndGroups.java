package com.sutherland.helios.api.report.frontend;

import java.util.LinkedHashMap;

public interface ReportFrontEndGroups 
{
	public final static LinkedHashMap<String, String> STACK_RANK_FRONTENDS = new LinkedHashMap<String, String>()
	{
		private static final long serialVersionUID = 8981150749471588078L;
		{
			put(ReportFrontEnds.CSV_NAME,"" +ReportFrontEnds.CSV);
			put(ReportFrontEnds.HTML_NAME,"" +ReportFrontEnds.HTML);
			put(ReportFrontEnds.EXCEL_NAME,"" +ReportFrontEnds.EXCEL);
			put(ReportFrontEnds.XML_NAME,"" +ReportFrontEnds.XML);
			put(ReportFrontEnds.JSON_NAME,"" +ReportFrontEnds.JSON);
		}
	};
	
	public final static LinkedHashMap<String, String> BASIC_METRIC_FRONTENDS = new LinkedHashMap<String, String>()
	{
		private static final long serialVersionUID = -2093351638483326816L;
		{
			put(ReportFrontEnds.CSV_NAME,"" +ReportFrontEnds.CSV);
			put(ReportFrontEnds.HTML_NAME,"" +ReportFrontEnds.HTML);
			put(ReportFrontEnds.EXCEL_NAME,"" +ReportFrontEnds.EXCEL);
			put(ReportFrontEnds.XML_NAME,"" +ReportFrontEnds.XML);
			put(ReportFrontEnds.JSON_NAME,"" +ReportFrontEnds.JSON);
			put(ReportFrontEnds.CHART_NAME,"" +ReportFrontEnds.CHART);
		}
	};
}
