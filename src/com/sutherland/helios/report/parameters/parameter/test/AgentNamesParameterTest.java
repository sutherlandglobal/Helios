package com.sutherland.helios.report.parameters.parameter.test;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.sutherland.helios.report.parameters.parameter.AgentNamesParameter;

public class AgentNamesParameterTest extends TestCase 
{
	private AgentNamesParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new AgentNamesParameter();
	}

	@Test
	public final void testSingleLikelyName() 
	{
		assertTrue(parameter.addSingleValue("Diamond, Jason"));
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
		assertTrue(parameter.addValue("Diamond, Jason"));
		assertTrue(parameter.addValue("Simpson, Homer"));
		assertTrue(parameter.addValue("Stark, Anthony"));
		
		assertTrue(parameter.validate());
	}
	
	@Test
	public final void testMultipleNamesWithANullNameToo() 
	{
		assertTrue(parameter.addValue("Diamond, Jason"));
		assertTrue(parameter.addValue(null));
		assertTrue(parameter.addValue("Simpson, Homer"));
		assertTrue(parameter.addValue("Stark, Anthony"));
		
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleNamesWithAnEmptyNameToo() 
	{
		assertTrue(parameter.addValue("Diamond, Jason"));
		assertTrue(parameter.addValue(""));
		assertTrue(parameter.addValue("Simpson, Homer"));
		assertTrue(parameter.addValue("Stark, Anthony"));
		
		assertFalse(parameter.validate());
	}
	
	@Test
	public final void testMultipleNamesWithAnBlankNameToo() 
	{
		assertTrue(parameter.addValue("Diamond, Jason"));
		assertTrue(parameter.addValue("                  "));
		assertTrue(parameter.addValue("Simpson, Homer"));
		assertTrue(parameter.addValue("Stark, Anthony"));
		
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
		assertTrue(parameter.addSingleValue("_B-I66ER_"));
		assertFalse(parameter.validate());
	}
}
