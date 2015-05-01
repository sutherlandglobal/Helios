package com.sutherland.helios.util.results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.sutherland.helios.data.Datum;


public class DriverFilter 
{

	//not simply "other" because that itself could be a driver name
	public final static String OTHER_DRIVER_KEYWORD = "Other Drivers";
	
	/**
	 * Putting the "Top" in "Top X Drivers." Reduce the total Top X Drivers data to the top n for each date grain.
	 * 
	 * @param aggPoint	The greater result set to reduce.
	 * @param	numDrivers	The max driver size
	 * 
	 * @return	The final result set.
	 */
	public static ArrayList<String[]> filterTopDrivers(Datum aggPoint, int numDrivers)
	{
		//retval is the numdrivers-sized list of the most popular drivers contained in aggPoint's data, plus an "others"
		ArrayList<String[]> retval = null;
		//descending, for use in graphs

		int driverCount;
		
		/*
		 * Hardware-Network/Communications-NIC: [1085]
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
name: [2010-10]
Hardware-Other-Other: [1085]
Hardware-Printer-Printer: [1085]
		 * 
		 */
		
		//for each datum, update the driver count 
		//name is the time grain
		
		if(aggPoint != null)
		{
			retval = new ArrayList<String[]>();
			for(String category : aggPoint.getAttributeNameList())
			{
				if(!category.equals("name"))
				{
					driverCount = aggPoint.getAttributeData(category).size();
					retval.add(new String[]{category, "" + driverCount});
				}
			}
			
			//sort by most populous driver, then sort by name
			Collections.sort(retval, new Comparator<String[]>()
			{
				@Override
				public int compare(String[] arg0, String[] arg1)
				{
					//for 2 elements, compare their driver counts:
					//
					//[2010-10, Software-Microsoft(non-os), 61]
					//[2010-10, Hardware-Printer-Other, 4]
					double result = Double.parseDouble(arg0[1]) - (Double.parseDouble(arg1[1]));
					if( result == 0 )
					{
						//then sort by driver name, ought to be unique
						return arg0[0].compareTo(arg1[0]);
					}
					else if(result < 0)
					{
						return 1;
					}
					else
					{
						return -1;
					}
				}
			});
			
			if(numDrivers > 0 && retval.size() > 0)
			{
				//reduce to top N drivers, ties are included retval.size may be greater than numdrivers
				int minDriverCount = Integer.parseInt(retval.get(0)[1]);
				int transitions = 0;
				int removalIndex = -1;
				
				for(int i =0; i< retval.size(); i++)
				{
					driverCount = Integer.parseInt(retval.get(i)[1]);
					
					if(driverCount != minDriverCount)
					{
						transitions++;
						minDriverCount = driverCount;
					}
					
					if(transitions >= numDrivers)
					{
						removalIndex = i;
						break;
					}
				}
				
				if(removalIndex > 0)
				{
					retval = new ArrayList<String[]>(retval.subList(0, removalIndex));
				}
			}
		}
		
		return retval;
	}
}
