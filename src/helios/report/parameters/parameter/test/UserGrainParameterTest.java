package helios.report.parameters.parameter.test;


import helios.data.granularity.user.UserGrains;
import helios.report.parameters.parameter.UserGrainParameter;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class UserGrainParameterTest extends TestCase 
{
	private UserGrainParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new UserGrainParameter();
	}

	@Test
	public final void testLikelyIUserGrains() 
	{
		assertTrue(parameter.addValue(UserGrains.AGENT_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(UserGrains.TEAM_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(UserGrains.PROGRAM_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(UserGrains.ORGUNIT_GRANULARITY));
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
		assertTrue(parameter.addValue(UserGrains.TEAM_GRANULARITY));
		assertTrue(parameter.addValue(UserGrains.ORGUNIT_GRANULARITY));
		assertEquals(1, parameter.getValues().size());
		
		assertEquals("" + UserGrains.ORGUNIT_GRANULARITY, parameter.getValues().get(0));
	}
}
