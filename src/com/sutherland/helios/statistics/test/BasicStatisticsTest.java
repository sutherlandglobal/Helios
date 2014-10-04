/**
 * 
 */
package com.sutherland.helios.statistics.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.statistics.Statistics;


/**
 * @author jdiamond
 *
 */
public class BasicStatisticsTest extends TestCase
{
	protected void setUp()
	{

	}

	@Test
	public void testSum()
	{
		ArrayList<String> actual = new ArrayList<String>();

			actual.add("-5");
			actual.add("-4");
			actual.add("-3");
			actual.add( "-2");
			actual.add("-1");
			actual.add("0");
			actual.add("1");
			actual.add("2");
			actual.add("3");
			actual.add("4");
			actual.add("5");
			actual.add("6");

			assertEquals("Sum test", Statistics.getTotal(actual), 6.0);
	}
	
	@Test
	public void testAverage()
	{
		ArrayList<String> actual = new ArrayList<String>();

			actual.add("-5");
			actual.add("-4");
			actual.add("-3");
			actual.add( "-2");
			actual.add("-1");
			actual.add("0");
			actual.add("1");
			actual.add("2");
			actual.add("3");
			actual.add("4");
			actual.add("5");
			actual.add("6");

			assertEquals("Average test", Statistics.getAverage(actual), .5);
	}
}
