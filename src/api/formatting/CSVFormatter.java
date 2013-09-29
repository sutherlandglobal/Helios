/**
 * 
 */
package api.formatting;

import java.util.ArrayList;


/**
 *	Format a Helios report's results into CSV format. No header or footer required.
 * 
 * 
 * @author Jason Diamond
 *
 *
 *
 */
public class CSVFormatter extends ResultsFormatter {

	/**
	 * 
	 */
	public CSVFormatter() 
	{
		super();
	}
	
	public ArrayList<String> formatResults(ArrayList<String[]> results)
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		for(String[] row : results)
		{
			retval.add(formatLine(row));
		}
		
		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		StringBuilder formattedLine = new StringBuilder();
		
		for(String col : rowFields)
		{
			if(enquote)
			{
				col = "\"" + col;
				col += "\"";
			}
			
			formattedLine.append(col);
			
			formattedLine.append(delim);
		}
		
		return formattedLine.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
			ResultsFormatter rf = new CSVFormatter();
			
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
