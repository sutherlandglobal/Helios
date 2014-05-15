package helios.report.parameters.parameter.test;


import helios.report.parameters.parameter.NumDriversParameter;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class NumDriversParameterTest extends TestCase 
{
	private NumDriversParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new NumDriversParameter();
	}
	
	@Test
	public final void testLikelyIntegers() 
	{
		assertTrue(parameter.addValue("5"));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue("10"));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue("15"));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue("20"));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue("25"));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue("30"));
		assertTrue(parameter.validate());
	}

	@Test
	public final void testZero() 
	{
		assertTrue(parameter.addSingleValue("0"));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testNegative() 
	{
		assertTrue(parameter.addSingleValue("-2"));
		assertFalse(parameter.validate());
		
	}
	
	@Test
	public final void testMax() 
	{
		assertTrue(parameter.addSingleValue( "" + NumDriversParameter.MAX_DRIVERS + 100));
		assertFalse(parameter.validate());
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
		assertTrue(parameter.addValue("2ll Y0UR_8@$E"));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleValues() 
	{
		assertTrue(parameter.addValue("1"));
		assertTrue(parameter.addValue("2"));
		assertEquals(1, parameter.getValues().size());
		
		assertEquals("2", parameter.getValues().get(0));
	}
}
