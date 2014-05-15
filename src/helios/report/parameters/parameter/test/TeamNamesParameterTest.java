package helios.report.parameters.parameter.test;


import helios.report.parameters.parameter.TeamNamesParameter;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TeamNamesParameterTest extends TestCase 
{
	private TeamNamesParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new TeamNamesParameter();
	}

	@Test
	public final void testSingleLikelyName() 
	{
		assertTrue(parameter.addSingleValue("The A Team"));
		assertTrue(parameter.validate());
	}
	
	@Test
	public final void testSingleNullName() 
	{
		assertTrue(parameter.addSingleValue(null));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleLikelyNames() 
	{
		assertTrue(parameter.addValue("The A Team"));
		assertTrue(parameter.addValue("Team Rocket"));
		assertTrue(parameter.addValue("Team USA"));
		
		assertTrue(parameter.validate());
	}
	
	@Test
	public final void testMultipleNamesWithANullNameToo() 
	{
		assertTrue(parameter.addValue("The A Team"));
		assertTrue(parameter.addValue(null));
		assertTrue(parameter.addValue("Team Rocket"));
		assertTrue(parameter.addValue("Team USA"));
		
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleNamesWithAnEmptyNameToo() 
	{
		assertTrue(parameter.addValue("The A Team"));
		assertTrue(parameter.addValue(""));
		assertTrue(parameter.addValue("Team Rocket"));
		assertTrue(parameter.addValue("Team USA"));
		
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleNamesWithAnBlankNameToo() 
	{
		assertTrue(parameter.addValue("The A Team"));
		assertTrue(parameter.addValue("                  "));
		assertTrue(parameter.addValue("Team Rocket"));
		assertTrue(parameter.addValue("Team USA"));
		
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testSingleEmptyName() 
	{
		assertTrue(parameter.addSingleValue(""));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testSingleBlankName() 
	{
		assertTrue(parameter.addSingleValue("                        "));
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testNameWithBadSymbols() 
	{
		assertTrue(parameter.addSingleValue("The @_Team"));
		assertFalse(parameter.validate());
	}
}
