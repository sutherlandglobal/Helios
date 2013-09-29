package statistics.forecast;

import java.awt.geom.Line2D;
import java.util.ArrayList;

//collect modern data
//collect historic data
//establish trendline from historic data
//apply trend to modern data and extrapolate
public class Historic 
{
	private ArrayList<String[]> historicData;
	private ArrayList<String[]> currentData;

	public Historic(ArrayList<String[]> currentData, ArrayList<String[]> historicData)
	{
		this.currentData = currentData;
		this.historicData = historicData;
		
	}
	
	protected Line2D calcTrend()
	{
		return null;
		
	}
	
	public ArrayList<String[]> runForecast()
	{
		ArrayList<String[]> forecastedData = null;
		
		
		
		forecastedData = new ArrayList<String[]>();
		
		return forecastedData;
	}
}
