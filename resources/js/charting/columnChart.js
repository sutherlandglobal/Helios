function drawColumnChart(chartTitle, data, dataLabel, valueLabel, labelFunction)
{
	require([
	"dojox/charting/Chart", 
	"dojox/charting/plot2d/Columns", 
	"dojox/charting/themes/PrimaryColors",
	"dojox/charting/plot2d/Markers",
	"dojox/charting/widget/SelectableLegend", 
	"dojox/charting/action2d/Tooltip", 
	"dojox/charting/action2d/Highlight",
	"dojox/charting/plot2d/Grid", 
	"dojox/charting/axis2d/Default", 
	"dojo/domReady!"
	], function (
	    Chart, 
	    ChartType, 
	    pColorsTheme, 
	    Markers, 
	    Legend, 
	    Tooltip, 
	    Highlight,
	    Grid
	) {
	    var thisChart = new Chart("chartNode", {
	        title: chartTitle,
	        titlePos: "top",
	        titleGap: 25,
	        titleFont: "normal normal normal 15pt Arial",
	        titleFontColor: "black"
	    });
	    thisChart.setTheme(pColorsTheme);
	    thisChart.addPlot("default", {
	        type: ChartType,
	        markers: true,
	        dropLabels: false,
	        shadows: {dx: 2, dy: 2, dw: 2} 
	    });
	
		thisChart.addAxis("x", {
		        labelFunc: labelFunction,
		        title: dataLabel,
		        titleOrientation: 180,
		        rotation: 45,
		        dropLabels: false,
		});
		
	    thisChart.addAxis("y", {
	        vertical: true,
	        fixLower: "major",
	        fixUpper: "major",
	        title: valueLabel,
	        titleOrientation: 180
	    });
		for( var series in data)
		{
		    thisChart.addSeries(series, data[series]);
		}
	                      
	    var tip = new Tooltip(thisChart, "default");
	    var highlight = new Highlight(thisChart, "default");          
	    thisChart.render();
	    var legend = new Legend({ chart: thisChart, horizontal: false }, "legend");
	});
}