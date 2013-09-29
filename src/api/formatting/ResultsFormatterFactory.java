package api.formatting;

import java.util.ArrayList;

public class ResultsFormatterFactory 
{
	public final static int CSV_FORMAT = 1;
	public final static int XML_FORMAT = 2;
	public final static int HTML_FORMAT=3;
	public final static int JSON_FORMAT=4;
	
	public static ResultsFormatter buildFormatter(int formatterType)
	{
		ResultsFormatter retval = null;
		
		switch(formatterType)
		{
		case CSV_FORMAT:
			retval = new CSVFormatter();
			break;
		case XML_FORMAT:
			retval = new XMLFormatter();
			break;
		case HTML_FORMAT:
			retval = new HTMLFormatter();
			break;
		case JSON_FORMAT:
			retval = new JSONFormatter();
			break;
		default:
			retval = new CSVFormatter();
			break;
		}
		
		return retval;
	}
	
	public static void main(String[] args)
	{
		ResultsFormatter derp = ResultsFormatterFactory.buildFormatter(ResultsFormatterFactory.HTML_FORMAT);
		
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[]{"a","b","c"});
		data.add(new String[]{"d","e","f"});
		data.add(new String[]{"g","h","i"});
		data.add(new String[]{"j","k","l"});
		data.add(new String[]{"m","n","o"});
		
		for(String row : derp.formatResults(data))
		{
			System.out.println(row);
		}
		
	}
}
