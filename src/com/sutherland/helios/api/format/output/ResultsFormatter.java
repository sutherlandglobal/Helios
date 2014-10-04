/**
 * 
 */
package com.sutherland.helios.api.format.output;

import java.util.ArrayList;


/**
 * @author Jason Diamond
 * 
 * A simple formatter for formatting a single line of Helios report results
 *
 */
public abstract class ResultsFormatter 
{
	protected ResultsFormatter() 
	{}

	public abstract String formatLine(String[] rowFields);
	
	public abstract ArrayList<String> formatResults(ArrayList<String[]> results);	
	
	public abstract ArrayList<String> formatResults(ArrayList<String> schema, ArrayList<String[]> results);
}
