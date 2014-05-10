package helios.data.test;

import helios.data.Datum;
import helios.schedule.Schedule;
import helios.schedule.Shift;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

import org.junit.Test;

public class DatumTest extends TestCase 
{
	private Datum datum;
	private final static String TEST_DATUM_ID = "Jason";
	
	public void setUp()
	{
		 datum = new Datum(TEST_DATUM_ID);
	}
	
	@Test
	public void testDatumCreation()
	{
		assertNotNull("Creation of empty datum",datum);
		assertEquals("Datum ID check", TEST_DATUM_ID, datum.getDatumID());
	}
	
	@Test
	public void testAttributes()
	{
		String testAttrName1 = "attrib1";
		String testAttrName2 = "attrib2";
		String testAttrName3 = "attrib3";
		
		datum.addAttribute(testAttrName1);
		datum.addAttribute(testAttrName2);
		datum.addAttribute(testAttrName3);

		ArrayList<String> expectedAttributes = new ArrayList<String>();
		expectedAttributes.add(testAttrName1);
		expectedAttributes.add(testAttrName2);
		expectedAttributes.add(testAttrName3);
		
		ArrayList<String> attributeNameList = datum.getAttributeNameList();
		Collections.sort(attributeNameList);
		Collections.sort(expectedAttributes);
		
		assertEquals("Add attributes test", expectedAttributes,  attributeNameList);
		
		expectedAttributes.remove(testAttrName2);
		
		datum.deleteAttributeData(testAttrName2);
		
		attributeNameList = datum.getAttributeNameList();
		
		Collections.sort(attributeNameList);
		Collections.sort(expectedAttributes);
		
		assertEquals("Remove attributes test", expectedAttributes, attributeNameList);
	}

	@Test
	public void testDataRetrieval()
	{
		String testAttrName1 = "attrib1";
		String testAttrName2 = "attrib2";
		String testAttrName3 = "attrib3";
		
		String data1 = "101";
		String data2 = "fart";
		String data3 = "nedm";
		
		ArrayList<String> expectedAttributes = new ArrayList<String>();
		ArrayList<String> expectedData = new ArrayList<String>();
		
		expectedData.add(data1);
		expectedData.add(data2);
		expectedData.add(data3);
		
		expectedAttributes.add(testAttrName1);
		expectedAttributes.add(testAttrName2);
		expectedAttributes.add(testAttrName3);
		
		datum.addAttribute(testAttrName1);
		datum.addAttribute(testAttrName2);
		datum.addAttribute(testAttrName3);
		
		datum.addData(testAttrName1, data1);
		datum.addData(testAttrName1, data2);
		datum.addData(testAttrName1, data3);
		
		assertEquals("Add data to datum attribute", expectedData, datum.getAttributeData(testAttrName1));
	}

	@Test
	public void testDataScheduleObjectRetrieval()
	{
		String schAttr = "schedules";
		
		//add schedules, retrieve them
		
		String startDate1 = "2012-07-03 00:00:00";
		String startDate2 = "2012-07-04 00:00:00";
		String startDate3 = "2012-07-05 00:00:00";
		
		Schedule sch1 = new Schedule(startDate1, "2012-07-03 08:00:00");
		Schedule sch2 = new Schedule(startDate2, "2012-07-04 08:00:00");
		Schedule sch3 = new Schedule(startDate3, "2012-07-05 08:00:00");
		
		datum.addObject(schAttr, sch1);
		datum.addObject(schAttr, sch2);
		datum.addObject(schAttr, sch3);
		
		ArrayList<Schedule> actualSchedules = new ArrayList<Schedule>();
		ArrayList<Schedule> expectedSchedules = new ArrayList<Schedule>();
		
		expectedSchedules.add(sch1);
		expectedSchedules.add(sch2);
		expectedSchedules.add(sch3);
		
		for(Object o : datum.getDatumObjects(schAttr))
		{
			if(o != null && o instanceof Schedule)
			{
				actualSchedules.add((Schedule)o);
			}
		}
		Collections.sort(actualSchedules);
		Collections.sort(expectedSchedules);
		
		assertEquals("Test custom object retrieval", expectedSchedules, actualSchedules);
		
		
		//modify the schedules, then retrieve them
		Shift testShift1 = new Shift("2012-07-04 00:01:00", "2012-07-04 00:01:00");
		Shift testShift2 = new Shift("2012-07-04 00:01:00", "2012-07-04 00:01:00");
		Shift testShift3 = new Shift("2012-07-04 00:01:00", "2012-07-04 00:01:00");
		
		((Schedule)datum.getDatumObjects(schAttr).get(0)).addShift(testShift1);
		((Schedule)datum.getDatumObjects(schAttr).get(0)).addShift(testShift2);
		((Schedule)datum.getDatumObjects(schAttr).get(0)).addShift(testShift3);

		sch1.addShift(testShift1);
		sch1.addShift(testShift2);
		sch1.addShift(testShift3);
		
		assertEquals("Test custom object modification", sch1, ((Schedule)datum.getDatumObjects(schAttr).get(0)));
	}

	@Test
	public void testDatumSetters()
	{
		
	}

}