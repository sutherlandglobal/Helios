package com.sutherland.helios.charting;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

public class BarChartFactory 
{
	protected String chartTitle;
	protected String categoryAxisLabel;
	protected String valueAxisLabel;
	protected String seriesName;

	public BarChartFactory(String chartTitle, String categoryAxisLabel, String valueAxisLabel, String seriesName)
	{
		this.chartTitle = chartTitle;
		this.categoryAxisLabel = categoryAxisLabel;
		this.valueAxisLabel = valueAxisLabel;
		this.seriesName = seriesName;
	}

	public JFreeChart buildChart(ArrayList<String[]> data)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(String[] row : data)
		{
			dataset.addValue(Double.parseDouble(row[1]), seriesName, row[0]);
		}

		JFreeChart chart = ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);
		CategoryAxis axis = chart.getCategoryPlot().getDomainAxis();
		axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		chart.setBackgroundPaint(Color.white);
		
		CategoryPlot plot = chart.getCategoryPlot();
		
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.black);
		
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);
		
		
		
		CategoryItemRenderer r = plot.getRenderer();
		if (r instanceof CategoryItemRenderer) 
		{
			BarRenderer renderer = (BarRenderer) r;
			renderer.setSeriesPaint(0, Color.BLUE);
		}
		
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		return chart;
	}
}
