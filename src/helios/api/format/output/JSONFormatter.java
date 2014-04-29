/**
 * 
 */
package helios.api.format.output;

import java.util.ArrayList;

import com.google.gson.Gson;


/**
 *	Format a Helios report's results into JSON format.
 * 
 * 
 * @author Jason Diamond
 *
 *
 *
 */
public class JSONFormatter extends ResultsFormatter {

	/**
	 * 
	 */
	public JSONFormatter() 
	{
		super();
	}
	
	public ArrayList<String> formatResults(ArrayList<String[]> results)
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		Gson gson = new Gson();
		
		for(String[] row : results)
		{
			retval.add(gson.toJson(row));
		}

		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		return null;
	}
	
	@Override
	public ArrayList<String> formatResults(ArrayList<String> schema, ArrayList<String[]> results) 
	{
		//no schema for JSON, yet
		return formatResults(new ArrayList<String>(), results);
	}

}
