package helios.report.parameters;

import java.util.ArrayList;

public interface ParameterInfo 
{
	//internal
	public final static String REPORT_NAME_PARAM = "Report Name";
	public final static String REPORT_NAME_PARAM_DESC = "Name of the report.";
	public final static String REPORT_NAME_HTTP_PARAM_NAME = "reportName";
	
	public final static String REPORT_CLASSNAME_PARAM = "Report class name";
	public final static String REPORT_CLASSNAME_PARAM_DESC = "Java classname of the report.";
	
	public final static String SOURCE_PARAM = "Source";
	public final static String SOURCE_PARAM_DESC = "Source of the report request.";
	public final static String SOURCE_HTTP_PARAM_NAME = "source";
	
	//GUI
	public final static String REPORT_TYPE_PARAM = "Report Type";
	public final static String REPORT_TYPE_PARAM_DESC = "The archetype of report.";
	public final static String REPORT_TYPE_HTTP_PARAM_NAME = "reportType";

	public final static String START_DATE_PARAM = "Start Date";
	public final static String START_DATE_PARAM_DESC = "The beginning of the data's time interval.";
	public final static String START_DATE_HTTP_PARAM_NAME = "startDate";
	
	public final static String END_DATE_PARAM = "End Date";
	public final static String END_DATE_PARAM_DESC = "The end of the data's time interval.";
	public final static String END_DATE_HTTP_PARAM_NAME = "endDate";

	public final static String TIME_GRAIN_PARAM = "Time Grain";
	public final static String TIME_GRAIN_PARAM_DESC = "The time granularity to group the data in.";
	public final static String TIME_GRAIN_HTTP_PARAM_NAME = "timeGrain";
	
	public final static String USER_GRAIN_PARAM = "User Grain";
	public final static String USER_GRAIN_PARAM_DESC = "The user granularity to group the data in.";
	public final static String USER_GRAIN_HTTP_PARAM_NAME = "userGrain";
	
	public final static String AGENT_NAMES_PARAM = "Agent Names";
	public final static String AGENT_NAMES_PARAM_DESC = "The list of users.";
	public final static String AGENT_NAMES_HTTP_PARAM_NAME = "agentName";
	
	public final static String TEAM_NAMES_PARAM = "Team Names";
	public final static String TEAM_NAMES_PARAM_DESC = "The list of teams.";
	public final static String TEAM_NAMES_HTTP_PARAM_NAME = "teamName";
	
	public final static String NUM_DRIVERS_PARAM = "Num Drivers";
	public final static String NUM_DRIVERS_PARAM_DESC = "The top N drivers for drivers-based metrics.";
	public final static String NUM_DRIVERS_HTTP_PARAM_NAME = "numDrivers";
	
	public final static ArrayList<String> availableParameters = new ArrayList<String>() 
	{
		private static final long serialVersionUID = -7312581091474975621L;
		{
			add(REPORT_NAME_PARAM);
			add(REPORT_CLASSNAME_PARAM);
			add(REPORT_TYPE_PARAM);
			add(SOURCE_PARAM);
			add(START_DATE_PARAM);
			add(END_DATE_PARAM);
			add(TIME_GRAIN_PARAM);
			add(USER_GRAIN_PARAM);
			add(AGENT_NAMES_PARAM);
			add(TEAM_NAMES_PARAM);
			add(NUM_DRIVERS_PARAM);
		}
	};
}
