package StockUpdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataCheck extends JPanel{
	private ArrayList<String[]> list = new ArrayList<String[]>();
	private JLabel label = new JLabel("DB에 저장된 데이터 수 : "+list.size());
	public DataCheck() {
		Thread th = new Thread(new ThreadRunnable());
		this.add(label);
		th.start();
	}
	
	class ThreadRunnable implements Runnable{
		public ThreadRunnable() {
			
		}
		@Override
		public void run() {
			dataCodeOutput();
		}
	}
	
	private void dataCodeOutput(){
		String URL = "jdbc:mariadb://localhost:3306/stock";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "root", "kiajw119");
			if (conn != null) {
				System.out.println("DB 접속 성공");
				String sql = "SELECT * FROM daily_price";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String[] a = new String[3];
					a[0] = rs.getString("code");
					a[1] = rs.getString("date");
					a[2] = rs.getString("close");
					list.add(a);
					label.setText("DB에 저장된 데이터 수 : "+list.size());
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}
	}
}
