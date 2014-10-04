package com.sutherland.helios.data.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import com.sutherland.helios.data.Aggregation;

public class AggregationTest extends TestCase 
{
	private Aggregation agg;
	private String datumDate1;
	private String datumDate2;
	
	private String datumData1;
	private String datumData2;
	private String datumData3;
	private String datumData4;
	private String datumData5;
	private String testAttributeName1;
	
	public void setUp()
	{
		agg = new Aggregation();
		
		datumDate1 = "2012-10-31 00:01:00";
		datumDate1 = "2010-03-27 12:30:54";
		
		datumData1 = "alpha";
		datumData2 = "beta";
		datumData3 = "gamma";
		datumData4 = "epsilon";
		datumData5 = "upsilon";
		
		testAttributeName1 = "greek letters";
	}
	
	@Test
	public void testDatumAdd()
	{
		assertTrue("", agg.addDatum(datumDate1));
		assertTrue("",agg.getDatum(datumDate1).addData(testAttributeName1, datumData1));
	}
	
	@Test
	public void testDatumRetrieval()
	{
		//Datum d1 = new Datum(datumDate1);
		//Datum d2 = new Datum(datumDate2);
		
		assertTrue("", agg.addDatum(datumDate1));
		assertTrue("",agg.getDatum(datumDate1).addData(testAttributeName1, datumData1));
		
		assertTrue("",agg.addDatum(datumDate2));
		assertTrue("",agg.getDatum(datumDate2).addData(testAttributeName1, datumData2));
		
		assertFalse("",agg.addDatum(datumDate1));
		assertTrue("",agg.getDatum(datumDate1).addData(testAttributeName1, datumData3));
		
		assertFalse("",agg.addDatum(datumDate2));
		assertTrue("",agg.getDatum(datumDate2).addData(testAttributeName1, datumData4));
		
		assertFalse("",agg.addDatum(datumDate1));
		assertTrue("",agg.getDatum(datumDate1).addData(testAttributeName1, datumData5));
		
		ArrayList<String> datum1Expected = new ArrayList<String>();
		datum1Expected.add(datumData1);
		datum1Expected.add(datumData3);
		datum1Expected.add(datumData5);
		
		assertEquals("Datum data retrieval 1", datum1Expected, agg.getDatum(datumDate1).getAttributeData(testAttributeName1));
		
		ArrayList<String> datum2Expected = new ArrayList<String>();
		datum2Expected.add(datumData2);
		datum2Expected.add(datumData4);
		
		assertEquals("Datum data retrieval 2", datum2Expected, agg.getDatum(datumDate2).getAttributeData(testAttributeName1));
	}
	
}
