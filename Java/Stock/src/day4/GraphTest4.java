package day4;

import java.awt.*;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.demo.charts.area.AreaChart01;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class GraphTest4 extends JFrame{
	public GraphTest4() {
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		Container c = getContentPane();
		JPanel panel = new XChartPanel(new AreaChart01().getChart());
	}

	public static void main(String[] args) {
		new GraphTest4();

	}

}
