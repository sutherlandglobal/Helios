package com.sutherland.helios.data.formatting.test;

import org.junit.Test;

import com.sutherland.helios.data.formatting.NumberFormatter;

import junit.framework.TestCase;

public class NumberFormatterTest extends TestCase
{

	@Test
	public void testCurrencyFormatting()
	{
		//round down
		assertEquals("0.00", NumberFormatter.convertToCurrency(.0));
		assertEquals("0.00", NumberFormatter.convertToCurrency(.00));
		assertEquals("0.00", NumberFormatter.convertToCurrency(.000));
		assertEquals("0.00", NumberFormatter.convertToCurrency(0.0));
		assertEquals("0.00", NumberFormatter.convertToCurrency(0.00));
		assertEquals("0.00", NumberFormatter.convertToCurrency(0.000));
		
		assertEquals("0.40", NumberFormatter.convertToCurrency(.4));
		assertEquals("0.40", NumberFormatter.convertToCurrency(0.4));
		assertEquals("0.40", NumberFormatter.convertToCurrency(0.40));
		assertEquals("0.40", NumberFormatter.convertToCurrency(0.400));
		assertEquals("0.40", NumberFormatter.convertToCurrency(.400));
		assertEquals("0.40", NumberFormatter.convertToCurrency(0.400));
		
		assertEquals("-0.40", NumberFormatter.convertToCurrency(-.4));
		assertEquals("-0.40", NumberFormatter.convertToCurrency(-0.4));
		assertEquals("-0.40", NumberFormatter.convertToCurrency(-0.40));
		assertEquals("-0.40", NumberFormatter.convertToCurrency(-0.400));
		assertEquals("-0.40", NumberFormatter.convertToCurrency(-.400));
		assertEquals("-0.40", NumberFormatter.convertToCurrency(-0.400));
		
		assertEquals("9.00", NumberFormatter.convertToCurrency(9));
		assertEquals("9.02", NumberFormatter.convertToCurrency(9.02));
		assertEquals("9.02", NumberFormatter.convertToCurrency(9.022));
		assertEquals("9.00", NumberFormatter.convertToCurrency(9.0022));
		
		assertEquals("90.00", NumberFormatter.convertToCurrency(90));
		assertEquals("90.02", NumberFormatter.convertToCurrency(90.02));
		assertEquals("90.02", NumberFormatter.convertToCurrency(90.022));
		assertEquals("90.00", NumberFormatter.convertToCurrency(90.0022));
		
		assertEquals("900.02", NumberFormatter.convertToCurrency(900.02));
		assertEquals("900.02", NumberFormatter.convertToCurrency(900.022));
		assertEquals("900.00", NumberFormatter.convertToCurrency(900.0022));
		
		//round up
		assertEquals("9.06", NumberFormatter.convertToCurrency(9.055));
		assertEquals("9.06", NumberFormatter.convertToCurrency(9.056));
		assertEquals("9.01", NumberFormatter.convertToCurrency(9.005));
		assertEquals("9.01", NumberFormatter.convertToCurrency(9.006));
		
		assertEquals("90.06", NumberFormatter.convertToCurrency(90.055));
		assertEquals("90.06", NumberFormatter.convertToCurrency(90.056));
		assertEquals("91.00", NumberFormatter.convertToCurrency(90.999));
		
		assertEquals("900.06", NumberFormatter.convertToCurrency(900.055));
		assertEquals("900.06", NumberFormatter.convertToCurrency(900.056));
		assertEquals("901.00", NumberFormatter.convertToCurrency(900.999));
	}
	
	@Test
	public void testPercentageFormatting()
	{
		assertEquals("9", NumberFormatter.convertToPercentage(.09055, 0));
		assertEquals("9", NumberFormatter.convertToPercentage(.09056, 0));
		assertEquals("9", NumberFormatter.convertToPercentage(.09005, 0));
		assertEquals("9", NumberFormatter.convertToPercentage(.09006, 0));
		
		assertEquals("9.06", NumberFormatter.convertToPercentage(.09055, 2));
		assertEquals("9.06", NumberFormatter.convertToPercentage(.09056, 2));
		assertEquals("9.01", NumberFormatter.convertToPercentage(.09005, 2));
		assertEquals("9.01", NumberFormatter.convertToPercentage(.09006, 2));
	}
}