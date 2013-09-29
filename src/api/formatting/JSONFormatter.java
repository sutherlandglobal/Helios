/**
 * 
 */
package api.formatting;

import java.util.ArrayList;

import com.google.gson.Gson;


/**
 *	Format a Helios report's results into HTML format. A single table with one html row per result row, with cells for each datum. For the excel users. Delimiter doesn't matter much.
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
		
		//int rowNum = 0;
		//int colNum;
		Gson gson = new Gson();
		
		for(String[] row : results)
		{
			//rowMap= new LinkedHashMap<String,String>();
			
			
//			colNum =0;
//			for(String col : row)
//			{
//
//
//				colNum++;
//			}
			
			//Type collectionType = new TypeToken<String[](){}.getType();
			
			retval.add(gson.toJson(row));
			//retval.add(gson.fromJson(gson.toJson(row), TypeToken.get(new String[]{}.getClass()).getType()));
			//retval.add(gson.fromJson(gson.toJson(row), new TypeToken<String[]>.getType());
			//rowNum++;
		}


		
		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
			ResultsFormatter rf = new JSONFormatter();
			
			rf.setDelim(",");
			//rf.setEnquote(true);
			
			ArrayList<String[]> data = new ArrayList<String[]>();
			data.add(new String[]{"a","b","c"});
			data.add(new String[]{"d","e","f"});
			data.add(new String[]{"g","h","i"});
			data.add(new String[]{"j","k","l"});
			data.add(new String[]{"m","n","o"});
			
			for(String row : rf.formatResults(data))
			{
				System.out.println(row);
			}

	}

}
