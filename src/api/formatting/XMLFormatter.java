/**
 * 
 */
package api.formatting;

import java.util.ArrayList;
import java.util.Arrays;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 *	Format a Helios report's results into HTML format. A single table with one html row per result row, with cells for each datum. For the excel users. Delimiter doesn't matter much.
 * 
 * 
 * @author Jason Diamond
 *
 *
 *
 */
public class XMLFormatter extends ResultsFormatter {

	/**
	 * 
	 */
	public XMLFormatter() 
	{
		super();
	}
	
	public ArrayList<String> formatResults(ArrayList<String[]> results)
	{
		ArrayList<String> retval = new ArrayList<String>();
		
		//typcially the convention is one row per line, but xml isn't newline-conscious
		
		Element rootElement = new Element("report");
		
		//doc.setRootElement(rootElement);
		
		int rowNum = 0;
		int colNum;
		for(String[] row : results)
		{
			Element rowElement = new Element("row");

			rowElement.setAttribute(new Attribute("id", "" + rowNum));
			
			colNum =0;
			for(String col : row)
			{
				rowElement.addContent(new Element("col"+colNum).setText(col));
				
				colNum++;
			}
			
			rootElement.addContent(rowElement);
			//doc.addContent(rowElement);
			rowNum++;
		}
		
		Document doc = new Document(rootElement);
		
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		
		String rawXML = xmlOutput.outputString(doc);
		retval.addAll(Arrays.asList(rawXML.split("\\n")));
		
		return retval;
	}
	
	public String formatLine(String[] rowFields)
	{
		//invalid to just print an xml row. context is needed.
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
			ResultsFormatter rf = new XMLFormatter();
			
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
