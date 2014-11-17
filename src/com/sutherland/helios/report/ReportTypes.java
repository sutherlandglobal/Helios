/**
 * 
 */
package com.sutherland.helios.report;

import java.util.LinkedHashMap;



/**
 * @author Jason Diamond
 *
 */
public interface ReportTypes 
{
	/**
	 * A stack ranking for a metric
	 */
	public static final int STACK_REPORT = 1;
	public static final String STACK_REPORT_NAME = "Stack";
	
	/**
	 * A time trending for a metric
	 */
	public static final int TIME_TREND_REPORT = 2;
	public static final String TIME_TREND_REPORT_NAME = "Time Trend";
	

	public static final LinkedHashMap<String, String> TYPE_LOOKUP = new LinkedHashMap<String, String>()
	{
		private static final long serialVersionUID = 1502789438852373258L;
		{
			put(STACK_REPORT_NAME, "" + STACK_REPORT);
			put(TIME_TREND_REPORT_NAME, "" + TIME_TREND_REPORT);
		}
	};
}
