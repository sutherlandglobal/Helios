/**
 * 
 */
package api.formatting;

import java.util.ArrayList;


/**
 *	Format a Helios report's results into HTML format. A single table with one html row per result row, with cells for each datum. For the excel users. Delimiter doesn't matter much.
 * 
 * 
 * @author Jason Diamond
 *
 *
 *
 */
public class HTMLFormatter extends ResultsFormatter {

	/**
	 * 
	 */
	public HTMLFormatter() 
	{
		super();
	}
	
	public ArrayList<String> formatResults(ArrayList<String[]> results)
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		retval.add("<table>");
		for(String[] row : results)
		{
			retval.add(formatLine(row));
		}
		retval.add("</table>");
		
		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		StringBuilder formattedLine = new StringBuilder();
		
		formattedLine.append("<tr>");
		for(String col : rowFields)
		{
			formattedLine.append("<td>" + col + "</td>");
		}
		formattedLine.append("</tr>");
		
		return formattedLine.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
			ResultsFormatter rf = new HTMLFormatter();
			
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
