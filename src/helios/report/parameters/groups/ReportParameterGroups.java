package helios.report.parameters.groups;

import helios.report.ReportTypes;
import helios.report.parameters.ParameterInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface ReportParameterGroups 
{
	public static final LinkedHashMap<String, ArrayList<String>> BASIC_METRIC_REPORT_PARAMETERS = new LinkedHashMap<String, ArrayList<String>>()
	{
		private static final long serialVersionUID = -7857682117767610166L;
		{
			put
			(
					ReportTypes.STACK_REPORT_NAME, new ArrayList<String>()
					{
						private static final long serialVersionUID = -2743390869808358895L;
						{
							add(ParameterInfo.REPORT_TYPE_PARAM);
							add(ParameterInfo.START_DATE_PARAM);
							add(ParameterInfo.END_DATE_PARAM);
							add(ParameterInfo.AGENT_NAMES_PARAM);
							add(ParameterInfo.TEAM_NAMES_PARAM);
							add(ParameterInfo.USER_GRAIN_PARAM);
						}
					}
			);
			
			put
			(
					ReportTypes.TIME_TREND_REPORT_NAME, new ArrayList<String>()
					{
						private static final long serialVersionUID = 5865104375666794390L;
						{
							add(ParameterInfo.REPORT_TYPE_PARAM);
							add(ParameterInfo.START_DATE_PARAM);
							add(ParameterInfo.END_DATE_PARAM);
							add(ParameterInfo.AGENT_NAMES_PARAM);
							add(ParameterInfo.TEAM_NAMES_PARAM);
							add(ParameterInfo.TIME_GRAIN_PARAM);
						}
					}
			);
		}
	};
	
	public static final LinkedHashMap<String, ArrayList<String>> DRIVERS_REPORT_PARAMETERS = new LinkedHashMap<String, ArrayList<String>>()
	{
		private static final long serialVersionUID = 1201484275541766107L;
		{
			put
			(
					ReportTypes.TIME_TREND_REPORT_NAME, new ArrayList<String>()
					{
						private static final long serialVersionUID = -7901338068326971766L;
						{
							add(ParameterInfo.REPORT_TYPE_PARAM);
							add(ParameterInfo.START_DATE_PARAM);
							add(ParameterInfo.END_DATE_PARAM);
							add(ParameterInfo.AGENT_NAMES_PARAM);
							add(ParameterInfo.TEAM_NAMES_PARAM);
							add(ParameterInfo.TIME_GRAIN_PARAM);
							add(ParameterInfo.NUM_DRIVERS_PARAM);
						}
					}
			);
		}
	};
	
	//dashboard taking user/agent grain, like agent progress
	//can be a group of graphs or a big table
	//needs both user and time grains
	public static final LinkedHashMap<String, ArrayList<String>> DASHBOARD_REPORT_PARAMETERS = new LinkedHashMap<String, ArrayList<String>>()
	{
		private static final long serialVersionUID = -5894012742805833751L;
		{
			put
			(
					ReportTypes.TIME_TREND_REPORT_NAME, new ArrayList<String>()
					{
						private static final long serialVersionUID = -1587576099499641950L;
						{
							add(ParameterInfo.REPORT_TYPE_PARAM);
							add(ParameterInfo.START_DATE_PARAM);
							add(ParameterInfo.END_DATE_PARAM);
							add(ParameterInfo.AGENT_NAMES_PARAM);
							add(ParameterInfo.TEAM_NAMES_PARAM);
							add(ParameterInfo.USER_GRAIN_PARAM);
							add(ParameterInfo.TIME_GRAIN_PARAM);
						}
					}
			);
		}
	};
	
	public static final LinkedHashMap<String, ArrayList<String>> STACK_RANK_REPORT_PARAMETERS = new LinkedHashMap<String, ArrayList<String>>()
	{
		private static final long serialVersionUID = -4521439443989466098L;
		{
			put
			(
					ReportTypes.STACK_REPORT_NAME, new ArrayList<String>()
					{
						private static final long serialVersionUID = 6375820839526388572L;
						{
							add(ParameterInfo.REPORT_TYPE_PARAM);
							add(ParameterInfo.START_DATE_PARAM);
							add(ParameterInfo.END_DATE_PARAM);
							add(ParameterInfo.AGENT_NAMES_PARAM);
							add(ParameterInfo.TEAM_NAMES_PARAM);
							add(ParameterInfo.USER_GRAIN_PARAM);
						}
					}
			);
		}
	};
	
	public static final LinkedHashMap<String, ArrayList<String>> ROSTER_REPORT_PARAMETERS = new LinkedHashMap<String, ArrayList<String>>()
	{
		private static final long serialVersionUID = -8693119072209097661L;
		{
			put
			(
					ReportTypes.STACK_REPORT_NAME, new ArrayList<String>()
					{
						private static final long serialVersionUID = -466450579269054788L;

						{
							add(ParameterInfo.REPORT_TYPE_PARAM);
							add(ParameterInfo.AGENT_NAMES_PARAM);
							add(ParameterInfo.TEAM_NAMES_PARAM);
						}
					}
			);
		}
	};
}
