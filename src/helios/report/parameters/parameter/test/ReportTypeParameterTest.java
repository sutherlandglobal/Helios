package helios.report.parameters.parameter.test;


import helios.report.ReportTypes;
import helios.report.parameters.parameter.ReportTypeParameter;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class ReportTypeParameterTest extends TestCase 
{
	private ReportTypeParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new ReportTypeParameter();
	}

	@Test
	public final void testLikelyReportTypes() 
	{
		assertTrue(parameter.addValue(ReportTypes.TIME_TREND_REPORT));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(ReportTypes.STACK_REPORT));
		assertTrue(parameter.validate());
	}

	@Test
	public final void testNull() 
	{
		assertTrue(parameter.addSingleValue(null));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testAlphanumericStrings() 
	{
		assertTrue(parameter.addValue("N3DM"));
		assertFalse(parameter.validate());
		
		assertTrue(parameter.addValue("N3DM"));
		assertFalse(parameter.validate());
		
		assertTrue(parameter.addValue("2ll Y0UR_8@$E"));
		assertFalse(parameter.validate());
		
		assertTrue(parameter.addValue("      "));
		assertFalse(parameter.validate());
		
		assertTrue(parameter.addValue(""));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleValues() 
	{
		assertTrue(parameter.addValue(ReportTypes.TIME_TREND_REPORT));
		assertTrue(parameter.addValue(ReportTypes.STACK_REPORT));
		assertEquals(1, parameter.getValues().size());
		
		assertEquals("" + ReportTypes.STACK_REPORT, parameter.getValues().get(0));
	}
}
