package helios.report.parameters.parameter.test;


import helios.report.parameters.parameter.SourceParameter;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class SourceParameterTest extends TestCase 
{
	private SourceParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new SourceParameter();
	}

	@Test
	public final void testSingleLikelySource() 
	{
		assertTrue(parameter.addSingleValue("API"));
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
		assertTrue(parameter.addValue("API"));
		assertTrue(parameter.addValue("UI"));
		assertTrue(parameter.addValue("BIRT Viewer"));
		
		assertTrue(parameter.validate());
	}
	
	@Test
	public final void testMultipleSourcesWithANullSourceToo() 
	{
		assertTrue(parameter.addValue("API"));
		assertTrue(parameter.addValue(null));
		assertTrue(parameter.addValue("UI"));
		assertTrue(parameter.addValue("BIRT Viewer"));
		
		assertTrue("Validate multiple sources with a null", parameter.validate());
		assertEquals("BIRT Viewer", parameter.getValues().get(0));
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
