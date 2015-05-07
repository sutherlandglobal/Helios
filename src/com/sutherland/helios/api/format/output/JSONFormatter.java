/**
 * 
 */
package com.sutherland.helios.api.format.output;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.google.gson.Gson;
import com.sutherland.helios.report.Report;


/**
 *	Format a Helios report's results into JSON format.
 * 
 * 
 * @author Jason Diamond
 *
 */
public class JSONFormatter extends ResultsFormatter 
{
	public String formatResults(Report report)
	{		
		//grain -> [array of values]
		
		LinkedHashMap<String, ArrayList<String>> results = new LinkedHashMap<String, ArrayList<String>>();
		
		for(String[] row : report.getData())
		{
			results.put(row[0], new ArrayList<String>());
			
			for(int i = 1; i<row.length; i++)
			{
				results.get(row[0]).add(row[i]);
			}
		}
				
		return new Gson().toJson(results);
	}

	@Override
	public String formatResults(ArrayList<Report> reports) {
		// TODO Auto-generated method stub
		return null;
	}
}
