package helios.report.parameters.parameter.test;


import helios.report.parameters.parameter.ReportClassNameParameter;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class ReportClassNameParameterTest extends TestCase 
{
	private ReportClassNameParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new ReportClassNameParameter();
	}

	@Test
	public final void testSingleLikelySource() 
	{
		assertTrue(parameter.addSingleValue("privatelabel.report.RealtimeSales"));
		assertTrue(parameter.validate());
	}
	
	@Test
	public final void testNullSource() 
	{
		assertTrue(parameter.addSingleValue(null));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleLikelySources() 
	{
		assertTrue(parameter.addValue("privatelabel.report.RealtimeSales"));
		assertTrue(parameter.addValue("hughes.report.CreatedCases"));
		assertTrue(parameter.addValue("privatelabel.report.AverageOrderValue"));
		
		assertTrue(parameter.validate());
	}
	
	@Test
	public final void testMultipleSourcesWithANullSourceToo() 
	{
		assertTrue(parameter.addValue("privatelabel.report.RealtimeSales"));
		assertTrue(parameter.addValue(null));
		assertTrue(parameter.addValue("hughes.report.CreatedCases"));
		assertTrue(parameter.addValue("privatelabel.report.AverageOrderValue"));
		
		assertTrue("Validate multiple sources with a null", parameter.validate());
		assertEquals("privatelabel.report.AverageOrderValue", parameter.getValues().get(0));
	}
	
	@Test
	public final void testEmptySource() 
	{
		assertTrue(parameter.addSingleValue(""));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testBlankSource() 
	{
		assertTrue(parameter.addSingleValue("                        "));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testSourceWithBadSymbols() 
	{
		assertTrue(parameter.addSingleValue("_B-I66ER_"));
		assertFalse(parameter.validate());
	}
}
