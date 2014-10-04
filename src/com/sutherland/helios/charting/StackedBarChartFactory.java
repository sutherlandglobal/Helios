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
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

public class StackedBarChartFactory 
{
	protected String chartTitle;
	protected String categoryAxisLabel;
	protected String valueAxisLabel;

	public StackedBarChartFactory(String chartTitle, String categoryAxisLabel, String valueAxisLabel)
	{
		this.chartTitle = chartTitle;
		this.categoryAxisLabel = categoryAxisLabel;
		this.valueAxisLabel = valueAxisLabel;
	}

	public JFreeChart buildChart(ArrayList<String[]> data)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(String[] row : data)
		{
			dataset.addValue(Double.parseDouble(row[2]), row[1], row[0]);
		}

		JFreeChart chart = ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);
		CategoryAxis axis = chart.getCategoryPlot().getDomainAxis();
		axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		chart.setBackgroundPaint(Color.white);
		
		CategoryPlot plot = chart.getCategoryPlot();
		
		
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.black);
		
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);
		
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        //rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
		
        BarRenderer.setDefaultBarPainter(new StandardBarPainter());
        
		GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
		//StackedBarRenderer renderer = new StackedBarRenderer();
		
		//renderer.setRenderAsPercentages(true);

		plot.setRenderer(renderer);
		



		return chart;
	}
}
