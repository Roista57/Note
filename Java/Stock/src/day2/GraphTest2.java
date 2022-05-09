package day2;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class GraphTest2 {
	public static void main(String[] args) {
		int numCharts = 6;
		List charts = new ArrayList();
		
		for(int i=0;i<numCharts;i++) {
			XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(300).height(200).build();
			// 차트의 크기를 지정하고 x, y의 이름을 설정
			chart.getStyler().setYAxisMin(-10.0); // y축의 최소값
			chart.getStyler().setYAxisMax(10.0); // y축의 최대값
			XYSeries series = chart.addSeries(""+i, null, getRandomWalk(100));
			// addSeries(String SeriesName, Double[] xData, Double[] yData)
			series.setMarker(SeriesMarkers.NONE);
			charts.add(chart);
		}
		new SwingWrapper(charts).displayChartMatrix();
	}
	private static double[] getRandomWalk(int numPoints) {
		double[] y = new double[numPoints];
		y[0] = 0;
		for(int i = 1;i<y.length;i++) {
			y[i] = y[i-1] + Math.random() - .5;
		}
		return y;
	}
}
