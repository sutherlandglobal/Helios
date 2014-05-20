package helios.report.parameters.test;


import helios.report.parameters.ParameterInfo;
import junit.framework.TestCase;

import org.junit.Test;

public class ParameterInfoTest extends TestCase 
{

	@Test
	public void testAvailableParameters()
	{
		assertEquals("Parameter count", 11, ParameterInfo.availableParameters.size());
		
		assertTrue("Report Name parameter", ParameterInfo.availableParameters.contains(ParameterInfo.REPORT_NAME_PARAM));
		assertTrue("Agent Names parameter",ParameterInfo.availableParameters.contains(ParameterInfo.AGENT_NAMES_PARAM));
		assertTrue("Team Names parameter",ParameterInfo.availableParameters.contains(ParameterInfo.TEAM_NAMES_PARAM));
		assertTrue("Time Grain parameter",ParameterInfo.availableParameters.contains(ParameterInfo.TIME_GRAIN_PARAM));
		assertTrue("User Grain parameter",ParameterInfo.availableParameters.contains(ParameterInfo.USER_GRAIN_PARAM));
		assertTrue("Start Date parameter",ParameterInfo.availableParameters.contains(ParameterInfo.START_DATE_PARAM));
		assertTrue("End Date parameter",ParameterInfo.availableParameters.contains(ParameterInfo.END_DATE_PARAM));
		assertTrue("Source parameter",ParameterInfo.availableParameters.contains(ParameterInfo.SOURCE_PARAM));
		assertTrue("Report Class Name parameter",ParameterInfo.availableParameters.contains(ParameterInfo.REPORT_CLASSNAME_PARAM));
		assertTrue("Report Type parameter",ParameterInfo.availableParameters.contains(ParameterInfo.REPORT_TYPE_PARAM));
		assertTrue("Num Drivers parameter",ParameterInfo.availableParameters.contains(ParameterInfo.NUM_DRIVERS_PARAM));
	}

}
