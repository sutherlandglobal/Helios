package helios.report.parameters.parameter.test;


import helios.data.granularity.time.TimeGrains;
import helios.report.parameters.parameter.AgentNamesParameter;
import helios.report.parameters.parameter.Parameter;
import helios.report.parameters.parameter.TimeGrainParameter;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class ParameterTest extends TestCase 
{

	private Parameter timeGrainParam;
	private AgentNamesParameter agentNamesParam;
	private String agentName1;
	private String agentName2;
	private String agentName3;
	private String agentName4;
	private String agentName5;

	@Before
	protected void setUp() 
	{
		timeGrainParam = new TimeGrainParameter();
		agentNamesParam = new AgentNamesParameter();
		
		agentName1 = "Archer, Sterling";
		agentName2 = "Kane, Lana";
		agentName3 = "Krieger, Doctor";
		agentName4 = "Povey, Pam";
		agentName5 = "Reilly, Rip";
	}

	@Test
	public final void testIsRequired() 
	{
		assertFalse("Test default requirement", timeGrainParam.isRequired());
		
		timeGrainParam.setIsRequired(true);
		assertTrue("Test setting requirement to true", timeGrainParam.isRequired());
		
		timeGrainParam.setIsRequired(false);
		assertFalse("Test further toggling requirement", timeGrainParam.isRequired());
	}

	@Test
	public final void testSingleValueData() 
	{
		assertTrue("Add single value", timeGrainParam.addValue(TimeGrains.DAILY_GRANULARITY));
		assertEquals("Get single Value", "" + TimeGrains.DAILY_GRANULARITY, timeGrainParam.getValues().get(0));
		
		assertTrue("Add another single value", timeGrainParam.addValue(TimeGrains.YEARLY_GRANULARITY));
		assertEquals("Test only one value is returned", 1, timeGrainParam.getValues().size());
		assertEquals("Test last added value is retained",  "" +TimeGrains.YEARLY_GRANULARITY, timeGrainParam.getValues().get(0));
	}
	
	@Test
	public final void testMultipleValueData() 
	{		
		agentNamesParam.addValue(agentName1);
		agentNamesParam.addValue(agentName2);
		agentNamesParam.addValue(agentName3);
		agentNamesParam.addValue(agentName4);
		agentNamesParam.addValue(agentName5);
		
		assertEquals("Test adding several names", 5, agentNamesParam.getValues().size());
		
		assertTrue("Test retrieving agentName1", agentNamesParam.getValues().contains(agentName1));
		assertTrue("Test retrieving agentName2", agentNamesParam.getValues().contains(agentName2));
		assertTrue("Test retrieving agentName3", agentNamesParam.getValues().contains(agentName3));
		assertTrue("Test retrieving agentName4", agentNamesParam.getValues().contains(agentName4));
		assertTrue("Test retrieving agentName5", agentNamesParam.getValues().contains(agentName5));
	}

	@Test
	public final void testClearValues() 
	{
		assertTrue("Add single value", timeGrainParam.addValue(TimeGrains.DAILY_GRANULARITY));
		timeGrainParam.clearValues();
		
		assertEquals("Test simple value clear", 0, timeGrainParam.getValues().size());
		
		agentNamesParam.addValue(agentName1);
		agentNamesParam.addValue(agentName2);
		agentNamesParam.addValue(agentName3);
		agentNamesParam.addValue(agentName4);
		agentNamesParam.addValue(agentName5);
		
		agentNamesParam.clearValues();
		assertEquals("Test multiple value clear", 0, agentNamesParam.getValues().size());
	}

}
