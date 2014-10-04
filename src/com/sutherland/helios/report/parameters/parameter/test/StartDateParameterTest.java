package com.sutherland.helios.report.parameters.parameter.test;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.sutherland.helios.report.parameters.parameter.StartDateParameter;

public class StartDateParameterTest extends TestCase 
{
	private StartDateParameter parameter;
	private String testDate;
	
	@Before
	protected void setUp() 
	{
		parameter = new StartDateParameter();
	}
	
	@Test
	public final void testMultipleValues() 
	{
		testDate = "2010-09-01 02:03:03";
		assertTrue(parameter.addValue(testDate));
		
		testDate = "2011-01-01 00:00:00";
		assertTrue(parameter.addValue(testDate));
		
		assertEquals(1, parameter.getValues().size());
		
		assertEquals(testDate, parameter.getValues().get(0));
	}

	@Test
	public final void testViableDates() 
	{
		String testDate = "2010-10-31 00:00:00";
		
		assertTrue(parameter.addSingleValue(testDate));
		
		assertTrue(testDate, parameter.validate());

		testDate = "2010-09-01 02:03:03";
		assertTrue(parameter.addSingleValue(testDate));
		assertTrue(testDate, parameter.validate());

		testDate = "2011-01-01 00:00:00";
		assertTrue(parameter.addSingleValue(testDate));
		assertTrue(testDate, parameter.validate());

		testDate = "2012-02-29 23:59:59";
		assertTrue(parameter.addSingleValue(testDate));
		assertTrue(testDate, parameter.validate());

		testDate = "2011-02-28 23:59:59";
		assertTrue(parameter.addSingleValue(testDate));
		assertTrue(testDate, parameter.validate());

		testDate = "2011-02-29 23:59:59";
		assertTrue(parameter.addSingleValue(testDate));
		assertTrue("Impossible date: 2011-03-01 23:59:59", parameter.validate());
	}
	
	@Test
	public void testLongGarbageString()
	{
		testDate = "a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a ";
		assertTrue(parameter.addSingleValue(testDate));
		assertFalse("Long array of text", parameter.validate());
	}
	
	@Test
	public void testEmptyString()
	{
		testDate = ""; 
		assertTrue(parameter.addSingleValue(testDate));
		assertFalse("Empty String", parameter.validate());
	}
	
	
	@Test
	public void testAnotherGarbageString()
	{
		testDate = ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";
		assertTrue(parameter.addSingleValue(testDate));
		assertFalse("Semicolon String", parameter.validate());
	}
	
	@Test
	public void testVeryImpossibleDate()
	{
		testDate = "3333-20-60 34:99:99";
		assertTrue(parameter.addSingleValue(testDate));
		assertFalse("Really impossible date", parameter.validate());
	}
	
	@Test
	public void testGoodFormatAbsurdValues()
	{
		
		testDate = "abcd-10-ef gh:34:ji";
		assertTrue(parameter.addSingleValue(testDate));
		assertFalse("Proper format - bad values", parameter.validate());
	}


}
