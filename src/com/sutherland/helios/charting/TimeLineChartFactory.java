package com.sutherland.helios.charting;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.jfree.ui.RectangleInsets;

import com.sutherland.helios.data.granularity.time.TimeGrains;
import com.sutherland.helios.date.formatting.DateFormatter;
import com.sutherland.helios.report.parameters.ReportParameters;

public class TimeLineChartFactory 
{
	protected String chartTitle;
	protected String timeAxisLabel;
	protected String valueAxisLabel;
	protected String seriesName;

	public TimeLineChartFactory(String chartTitle, String timeAxisLabel, String valueAxisLabel, String seriesName)
	{
		this.chartTitle = chartTitle;
		this.timeAxisLabel = timeAxisLabel;
		this.valueAxisLabel = valueAxisLabel;
		this.seriesName = seriesName;
	}

	public JFreeChart buildChart(ArrayList<String[]> data, ReportParameters parameters)
	{
		//expect dates to be in first column for now
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		TimeSeries timeSeries = new TimeSeries(seriesName);
		
		int timeGranularity = Integer.parseInt(parameters.getTimeGrain());
		int dateFormat = Integer.parseInt(parameters.getDateFormat());
		
		String dateDescriptor = "";
		RegularTimePeriod dateGrain = null;

		if(dateFormat == DateFormatter.SQL_FORMAT)
		{
			switch(timeGranularity)
			{
			case TimeGrains.FISCAL_YEARLY_GRANULARITY:
			case TimeGrains.YEARLY_GRANULARITY:
				dateDescriptor = "yyyy";
				break;
			case TimeGrains.WEEKLY_GRANULARITY:
				dateDescriptor = "yyyy-MM-ww";
				break;
			case TimeGrains.DAILY_GRANULARITY:
				dateDescriptor = "yyyy-MM-dd";
				break;
			case TimeGrains.HOURLY_GRANULARITY:
				dateDescriptor = "yyyy-MM-dd HH:mm:ss";
				break;
			case TimeGrains.MONTHLY_GRANULARITY:
			case TimeGrains.QUARTERLY_GRANULARITY:
			case TimeGrains.FISCAL_QUARTERLY_GRANULARITY:
			default:
				dateDescriptor = "yyyy-MM";
				break;
			}
		}
		else if(dateFormat == DateFormatter.EXCEL_FORMAT)
		{
			switch(timeGranularity)
			{
			case TimeGrains.FISCAL_YEARLY_GRANULARITY:
			case TimeGrains.YEARLY_GRANULARITY:
				dateDescriptor = "yyyy";
				break;
			case TimeGrains.WEEKLY_GRANULARITY:
				dateDescriptor = "ww/MM/yyyy";
				break;
			case TimeGrains.DAILY_GRANULARITY:
				dateDescriptor = "MM/dd/yyyy";
				break;
			case TimeGrains.HOURLY_GRANULARITY:
				dateDescriptor = "MM/dd/yyyy hh:mm:ss a";
				break;
			case TimeGrains.MONTHLY_GRANULARITY:
			case TimeGrains.QUARTERLY_GRANULARITY:
			case TimeGrains.FISCAL_QUARTERLY_GRANULARITY:
			default:
				dateDescriptor = "MM/yyyy";
				break;
			}
		}
		
		String year, quarter, month, week, day, hour, period;
		String[] dateFields, timeFields;
		for(String[] row : data)
		{
			//assume row[0] has the date
			//input date can be in any format, convert it to a form regular time period can understand
			
			String inputDate = row[0];

			switch(timeGranularity)
			{
			case TimeGrains.FISCAL_YEARLY_GRANULARITY:
			case TimeGrains.YEARLY_GRANULARITY:
				
//				if(dateFormat == DateFormatter.EXCEL_FORMAT)
//				{
//					year = inputDate.split("\\/")[2];
//				}
//				else //if(dateFormat == DateFormatter.SQL_FORMAT)
//				{
//					year = inputDate.split("\\-")[0];
//				}
				year = inputDate;
				
				dateGrain = new Year(Integer.parseInt(year));
				break;
			case TimeGrains.QUARTERLY_GRANULARITY:
			case TimeGrains.FISCAL_QUARTERLY_GRANULARITY:
				
				if(dateFormat == DateFormatter.EXCEL_FORMAT)
				{
					dateFields = inputDate.split("\\/");
					year = dateFields[1];
					quarter = dateFields[0];
				}
				else //if(dateFormat == DateFormatter.SQL_FORMAT)
				{
					dateFields = inputDate.split("\\-");
					year = dateFields[0];
					quarter = dateFields[1];
				}
				
				dateGrain = new Quarter(Integer.parseInt(quarter), Integer.parseInt(year));
				break;
			case TimeGrains.WEEKLY_GRANULARITY:
				
				if(dateFormat == DateFormatter.EXCEL_FORMAT)
				{
					dateFields = inputDate.split("\\/");
					year = dateFields[2];
					week = dateFields[1];
				}
				else //if(dateFormat == DateFormatter.SQL_FORMAT)
				{
					dateFields = inputDate.split("\\-");
					year = dateFields[0];
					week = dateFields[2];
				}
				
				dateGrain = new Week(Integer.parseInt(week), Integer.parseInt(year));
				break;
			case TimeGrains.DAILY_GRANULARITY:
				
				if(dateFormat == DateFormatter.EXCEL_FORMAT)
				{
					dateFields = inputDate.split("\\/");
					year = dateFields[2];
					day = dateFields[1];
					month = dateFields[0];
				}
				else //if(dateFormat == DateFormatter.SQL_FORMAT)
				{
					dateFields = inputDate.split("\\-");
					year = dateFields[0];
					month = dateFields[1];
					day = dateFields[2];
				}
				
				dateGrain = new Day(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
				break;
			case TimeGrains.HOURLY_GRANULARITY:
				//MM/DD/YYYY hh:MM:SS AP
				
				if(dateFormat == DateFormatter.EXCEL_FORMAT)
				{
					timeFields = inputDate.split(" ")[1].split("\\:");
					
					dateFields = inputDate.substring(0, inputDate.indexOf(" ")).split("\\/");
					year = dateFields[2];
					day = dateFields[1];
					month = dateFields[0];
					
					//convert to 24 hour value, simple date format will retrieve the 12-hour value
					hour = timeFields[0];
					period = inputDate.substring(inputDate.lastIndexOf(' ') +1 );
					

					
	        		if(period.equals("PM") && !hour.equals("12"))
	        		{
	        			hour = "" + (Integer.parseInt(hour) + 12);
	        		}
	        		else if(hour.equals("AM") && hour.equals("12"))
	        		{
	        			hour = "0";
	        		}
	        		
				}
				else //if(dateFormat == DateFormatter.SQL_FORMAT)
				{
					timeFields = inputDate.substring(inputDate.lastIndexOf(' ') +1 ).split("\\:");
					
					dateFields = inputDate.substring(0, inputDate.indexOf(" ")).split("\\-");
					year = dateFields[0];
					month = dateFields[1];
					day = dateFields[2];
					hour = timeFields[0];
					
					
				}
				
				dateGrain = new Hour(Integer.parseInt(hour), Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
				break;
			case TimeGrains.MONTHLY_GRANULARITY:
			default:
				
				if(dateFormat == DateFormatter.EXCEL_FORMAT)
				{
					dateFields = inputDate.split("\\/");
					year = dateFields[1];
					month = dateFields[0];
				}
				else //if(dateFormat == DateFormatter.SQL_FORMAT)
				{
					dateFields = inputDate.split("\\-");
					year = dateFields[0];
					month = dateFields[1];
				}
				
				dateGrain = new Month(Integer.parseInt(month), Integer.parseInt(year));
				break;
			}

			timeSeries.addOrUpdate(dateGrain, Double.parseDouble(row[1]));
		}

		dataset.addSeries(timeSeries);

		JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, timeAxisLabel, valueAxisLabel, dataset);
		
		chart.getXYPlot().getDomainAxis().setVerticalTickLabels(true);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) 
		{
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
			renderer.setDrawSeriesLineAsPath(true);
			renderer.setSeriesPaint(0, Color.BLUE);
		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat(dateDescriptor));

		return chart;
	}
}
