package com.sutherland.helios.util.results.test;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

import com.sutherland.helios.data.Aggregation;
import com.sutherland.helios.data.Datum;
import com.sutherland.helios.util.results.DriverFilter;

public class DriverFilterTest extends TestCase
{
	private Aggregation testDataAggregation;
	
	public void setUp()
	{
		testDataAggregation = new Aggregation();
		
		//populate aggregation
		//name holds time grain
		ArrayList<String[]> testData = new ArrayList<String[]>();
		
		//date grain, user id, driver
		
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Software-OS"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-NIC"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-NIC"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-NIC"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Input Device-Mouse"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Input Device-Mouse"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Input Device-Mouse"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-09", "1085", "Hardware-Network/Communications-Broadband Modem"});
		
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-NIC"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Input Device-Mouse"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-10", "1085", "Software-Other"});
		testData.add(new String[]{"2010-10", "1085", "Software-Browser"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-OS"});
		testData.add(new String[]{"2010-10", "1085", "Software-Malware Protection"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Motherboard-Motherboard"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Other-Other"});
		testData.add(new String[]{"2010-10", "1085", "Hardware-Printer-Printer"});
		
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-NIC"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Input Device-Mouse"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-11", "1085", "Software-Other"});
		testData.add(new String[]{"2010-11", "1085", "Software-Browser"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-OS"});
		testData.add(new String[]{"2010-11", "1085", "Software-Malware Protection"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Motherboard-Motherboard"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Other-Other"});
		testData.add(new String[]{"2010-11", "1085", "Hardware-Printer-Printer"});
		
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-NIC"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Video-Video Card"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Input Device-Mouse"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Router"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Network/Communications-Broadband Modem"});
		testData.add(new String[]{"2010-12", "1085", "Software-Other"});
		testData.add(new String[]{"2010-12", "1085", "Software-Browser"});
		testData.add(new String[]{"2010-12", "1085", "Software-OS"});
		testData.add(new String[]{"2010-12", "1085", "Software-OS"});
		testData.add(new String[]{"2010-12", "1085", "Software-OS"});
		testData.add(new String[]{"2010-12", "1085", "Software-OS"});
		testData.add(new String[]{"2010-12", "1085", "Software-OS"});
		testData.add(new String[]{"2010-12", "1085", "Software-Malware Protection"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Storage-HDD"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Motherboard-Motherboard"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Other-Other"});
		testData.add(new String[]{"2010-12", "1085", "Hardware-Printer-Printer"});
		
		/*
		 * 
		 	name: [2010-10]
		 	Hardware-Network/Communications-NIC: [1085]
			Hardware-Video-Video Card: [1085, 1085]
			Hardware-Input Device-Mouse: [1085]
			Hardware-Network/Communications-Router: [1085, 1085]
			Hardware-Network/Communications-Broadband Modem: [1085, 1085]
			Software-Other: [1085, 1085, 1085]
			Software-Browser: [1085, 1085, 1085]
			Software-OS: [1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085, 1085]
			Software-Malware Protection: [1085, 1085]
			Hardware-Storage-HDD: [1085, 1085, 1085]
			Hardware-Motherboard-Motherboard: [1085]
			Hardware-Other-Other: [1085]
			Hardware-Printer-Printer: [1085]
		 * 
		 */
		
		for(String[] data : testData)
		{
			testDataAggregation.addDatum(data[0]);
			testDataAggregation.getDatum(data[0]).addAttribute(data[2]);
			testDataAggregation.getDatum(data[0]).addData(data[2], data[1]);
		}
	}
	
	private boolean compareDriverSets(ArrayList<String[]> results1, ArrayList<String[]> results2)
	{
		boolean retval = true;
		
		if(results1.size() == results2.size())
		{
			for(int i = 0; i<results1.size(); i++)
			{
				if(!retval)
				{
					retval = false;
					break;
				}
				else if(results1.get(i).length == results2.get(i).length)
				{
					for(int j = 0; j < results1.get(i).length; j++)
					{
						if( !results1.get(i)[j].equals(results2.get(i)[j]) )
						{
							retval = false;
							break;
						}
					}
				}
				else
				{
					retval = false;
					break;
				}
			}
		}
		else
		{
			retval = false;
		}
		
		return retval;
	}
	
	private String printResultSet(ArrayList<String[]> results)
	{
		StringBuilder rows = new StringBuilder();
		StringBuilder row = new StringBuilder();
		
		for( String[] aRow : results)
		{
			row.setLength(0);
			
			for( String datum : aRow)
			{
				row.append("\"" + datum + "\"");
				row.append(",");
			}
			rows.append(row);
			rows.append("\n");
		}
		
		return rows.toString();
	}
	
	@Test
	public void testNullAggregation()
	{
		assertEquals("Null aggregation", null, DriverFilter.filterTopDrivers(null, 4));
	}
	
	@Test
	public void testEmptyAggregation()
	{		
		Datum testDatum = new Datum("test");
		assertEquals("Empty aggregation", new ArrayList<String>(), DriverFilter.filterTopDrivers(testDatum, 4));
	}
	
	@Test
	public void testBadDriverCount()
	{
		ArrayList<String[]> expected = new ArrayList<String[]>();
		expected.add(new String[]{ "Software-OS", "8" });
		expected.add(new String[]{ "Hardware-Storage-HDD", "6" });
		expected.add(new String[]{ "Hardware-Network/Communications-Router", "4" });
		expected.add(new String[]{ "Hardware-Network/Communications-Broadband Modem", "3" });
		expected.add(new String[]{ "Hardware-Video-Video Card", "2" });
		expected.add(new String[]{ "Hardware-Input Device-Mouse", "1" });
		expected.add(new String[]{ "Hardware-Motherboard-Motherboard", "1" });
		expected.add(new String[]{ "Hardware-Network/Communications-NIC", "1" });
		expected.add(new String[]{ "Hardware-Other-Other", "1" });
		expected.add(new String[]{ "Hardware-Printer-Printer", "1" });
		expected.add(new String[]{ "Software-Browser", "1" });
		expected.add(new String[]{ "Software-Malware Protection", "1" });
		expected.add(new String[]{ "Software-Other", "1" });
		
		ArrayList<String[]> actual = DriverFilter.filterTopDrivers(testDataAggregation.getDatum("2010-10"), 0);
		
		if(compareDriverSets(expected, actual))
		{
			assertTrue("Zero driver count filter for 2010-10", true);
		}
		else
		{
			System.out.println("Expected:\n" + printResultSet(expected) + "\n========\nActual:\n" + printResultSet(actual));
			assertFalse("Zero driver count filter for 2010-10", true);
		}
				
		actual = DriverFilter.filterTopDrivers(testDataAggregation.getDatum("2010-10"), -4);
		
		if(compareDriverSets(expected, actual))
		{
			assertTrue("Negative driver count filter for 2010-10", true);
		}
		else
		{
			System.out.println("Expected:\n" + printResultSet(expected) + "\n========\nActual:\n" + printResultSet(actual));
			assertFalse("Negative driver count filter for 2010-10", true);
		}
	}
	
	@Test
	public void testTooLargeDriverCount()
	{
		ArrayList<String[]> expected = new ArrayList<String[]>();
		expected.add(new String[]{ "Software-OS", "8" });
		expected.add(new String[]{ "Hardware-Storage-HDD", "6" });
		expected.add(new String[]{ "Hardware-Network/Communications-Router", "4" });
		expected.add(new String[]{ "Hardware-Network/Communications-Broadband Modem", "3" });
		expected.add(new String[]{ "Hardware-Video-Video Card", "2" });
		expected.add(new String[]{ "Hardware-Input Device-Mouse", "1" });
		expected.add(new String[]{ "Hardware-Motherboard-Motherboard", "1" });
		expected.add(new String[]{ "Hardware-Network/Communications-NIC", "1" });
		expected.add(new String[]{ "Hardware-Other-Other", "1" });
		expected.add(new String[]{ "Hardware-Printer-Printer", "1" });
		expected.add(new String[]{ "Software-Browser", "1" });
		expected.add(new String[]{ "Software-Malware Protection", "1" });
		expected.add(new String[]{ "Software-Other", "1" });

		
		ArrayList<String[]> actual = DriverFilter.filterTopDrivers(testDataAggregation.getDatum("2010-10"), 400);
		
		if(compareDriverSets(expected, actual))
		{
			assertTrue("Too large driver count for grain 2010-10", true);
		}
		else
		{
			System.out.println("Expected:\n" + printResultSet(expected) + "\n========\nActual:\n" + printResultSet(actual));
			assertFalse("Too large driver count for grain 2010-10", true);
		}	
	}
	
	@Test
	public void testNormalDriverFilter()
	{
		ArrayList<String[]> expected = new ArrayList<String[]>();
		expected.add(new String[]{ "Software-OS", "16" });
		expected.add(new String[]{ "Hardware-Network/Communications-Router", "5" });
		expected.add(new String[]{ "Hardware-Storage-HDD", "4" });
		expected.add(new String[]{ "Hardware-Network/Communications-Broadband Modem", "3" });
		
		ArrayList<String[]> actual = DriverFilter.filterTopDrivers(testDataAggregation.getDatum("2010-11"), 4);
				
		if(compareDriverSets(expected, actual))
		{
			assertTrue("Top 4 drivers for grain 2010-11", true);
		}
		else
		{
			System.out.println("Expected:\n" + printResultSet(expected) + "\n========\nActual:\n" + printResultSet(actual));
			assertFalse("Top 4 drivers for grain 2010-11", true);
		}	
	}
	
	@Test
	public void testDriverCountTies()
	{
		ArrayList<String[]> expected = new ArrayList<String[]>();
		expected.add(new String[]{ "Software-OS", "16" });
		expected.add(new String[]{ "Hardware-Input Device-Mouse", "3"});
		expected.add(new String[]{ "Hardware-Network/Communications-Broadband Modem", "3"});
		expected.add(new String[]{ "Hardware-Network/Communications-NIC", "3"});
		expected.add(new String[]{ "Hardware-Network/Communications-Router", "3"});
		expected.add(new String[]{ "Hardware-Video-Video Card", "3"});
		
		ArrayList<String[]> actual = DriverFilter.filterTopDrivers(testDataAggregation.getDatum("2010-09"), 2);
				
		if(compareDriverSets(expected, actual))
		{
			assertTrue("Top 4 drivers for grain 2010-09", true);
		}
		else
		{
			System.out.println("Expected:\n" + printResultSet(expected) + "\n========\nActual:\n" + printResultSet(actual));
			assertFalse("Top 4 drivers for grain 2010-09", true);
		}	
	}
	

}
