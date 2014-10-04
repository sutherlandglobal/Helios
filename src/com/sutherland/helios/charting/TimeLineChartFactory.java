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

	public JFreeChart buildChart(ArrayList<String[]> data, int timeGranularity)
	{
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		TimeSeries timeSeries = new TimeSeries(seriesName);
		
		String dateFormat = "";
		RegularTimePeriod dateGrain = null;
		
		switch(timeGranularity)
		{
		case TimeGrains.FISCAL_YEARLY_GRANULARITY:
		case TimeGrains.YEARLY_GRANULARITY:
			dateFormat = "yyyy";
			break;
		case TimeGrains.MONTHLY_GRANULARITY:
		case TimeGrains.QUARTERLY_GRANULARITY:
		case TimeGrains.FISCAL_QUARTERLY_GRANULARITY:
			dateFormat = "yyyy-MM";
			break;
		case TimeGrains.WEEKLY_GRANULARITY:
			dateFormat = "yyyy-MM-ww";
			break;
		case TimeGrains.DAILY_GRANULARITY:
			dateFormat = "yyyy-MM-dd";
			break;
		case TimeGrains.HOURLY_GRANULARITY:
			dateFormat = "yyyy-MM-dd HH";
			break;
		default:
			dateFormat = "yyyy-MM";
			break;
		}

		String[] dateFields, timeFields;
		for(String[] row : data)
		{
			if(row[0].contains(" "))
			{
				dateFields = row[0].substring(0, row[0].lastIndexOf(' ')).split("\\-");
			}
			else
			{
				dateFields = row[0].split("\\-");
			}
			
			switch(timeGranularity)
			{
			case TimeGrains.FISCAL_YEARLY_GRANULARITY:
			case TimeGrains.YEARLY_GRANULARITY:
				dateGrain = new Year(Integer.parseInt(row[0]));
				break;
			case TimeGrains.QUARTERLY_GRANULARITY:
			case TimeGrains.FISCAL_QUARTERLY_GRANULARITY:
				dateGrain = new Quarter(Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[0]));
				break;
			case TimeGrains.MONTHLY_GRANULARITY:
				dateGrain = new Month(Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[0]));
				break;
			case TimeGrains.WEEKLY_GRANULARITY:
				dateGrain = new Week(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[0]));
				break;
			case TimeGrains.DAILY_GRANULARITY:
				dateGrain = new Day(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[0]));
				break;
			case TimeGrains.HOURLY_GRANULARITY:
				
				timeFields = row[0].substring(row[0].lastIndexOf(' ')).split("\\:");
				
				dateGrain = new Hour(Integer.parseInt(timeFields[0]), Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[0]));
				break;
			default:
				dateGrain = new Month(Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[0]));
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
		axis.setDateFormatOverride(new SimpleDateFormat(dateFormat));

		return chart;
	}
}
