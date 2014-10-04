package com.sutherland.helios.report.parameters.parameter.test;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.sutherland.helios.data.granularity.time.TimeGrains;
import com.sutherland.helios.report.parameters.parameter.TimeGrainParameter;

public class TimeGrainParameterTest extends TestCase 
{
	private TimeGrainParameter parameter;
	
	
	@Before
	protected void setUp() 
	{
		parameter = new TimeGrainParameter();
	}

	@Test
	public final void testLikelyITimeGrains() 
	{
		assertTrue(parameter.addValue(TimeGrains.HOURLY_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(TimeGrains.DAILY_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(TimeGrains.WEEKLY_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(TimeGrains.MONTHLY_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(TimeGrains.QUARTERLY_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(TimeGrains.FISCAL_QUARTERLY_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(TimeGrains.FISCAL_YEARLY_GRANULARITY));
		assertTrue(parameter.validate());
		
		assertTrue(parameter.addValue(TimeGrains.YEARLY_GRANULARITY));
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
		assertTrue(parameter.addValue(TimeGrains.HOURLY_GRANULARITY));
		assertTrue(parameter.addValue(TimeGrains.QUARTERLY_GRANULARITY));
		assertEquals(1, parameter.getValues().size());
		
		assertEquals("" + TimeGrains.QUARTERLY_GRANULARITY, parameter.getValues().get(0));
	}
}
