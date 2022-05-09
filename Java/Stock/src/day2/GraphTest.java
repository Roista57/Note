package day2;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;

public class GraphTest {

	public static void main(String[] args) {
		
		double[] x = new double[] {0.0, 1.0, 2.0};
		double[] y = new double[] {2.0, 1.0, 0.0};
		
		Chart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", x, y);
		// = XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", x, y);
		
		new SwingWrapper(chart).displayChart();
	}

}
