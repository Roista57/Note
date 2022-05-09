package day3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class GraphTest3 {
	private ArrayList<String> code = new ArrayList<String>();
	private double[] price = new double[1000];
	public GraphTest3() {
		dataOutput();
		XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(1200).height(600).build();
		// 차트의 크기를 지정하고 x, y의 이름을 설정
		chart.getStyler().setYAxisMin(3000.0); // y축의 최소값
		chart.getStyler().setYAxisMax(40000.0); // y축의 최대값
		XYSeries series = chart.addSeries("1", null, price);
		series.setMarker(SeriesMarkers.NONE);
		new SwingWrapper(chart).displayChartMatrix();
	}
	
	private void dataOutput(){
		String URL = "jdbc:mariadb://localhost:3306/stock";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "root", "kiajw119");
			if (conn != null) {
				System.out.println("DB 접속 성공");
				String sql = "SELECT * FROM company_update";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					code.add(rs.getString("code"));
				}
				sql = "SELECT * FROM daily_price";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int count = 0;
				while(rs.next()) {
					if(rs.getString("code").equals(code.get(0))) {
						price[count] = Double.parseDouble(rs.getString("close").replace(",", ""));
						count++;
					}
				}
				pstmt.close();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}
	}
	
	public static void main(String[] args) {
		new GraphTest3();
	}
}
