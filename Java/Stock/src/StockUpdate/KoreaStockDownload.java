package StockUpdate;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.*;
import javax.swing.*;

public class KoreaStockDownload extends JPanel{
	private Thread[] thread = new Thread[20];
	private ArrayList<String> code = new ArrayList<String>();
	private int last = 0;
	private int max;
	private int listCount = 0;
	JLabel nowLabel = new JLabel("");
	JLabel countLabel = new JLabel("");
	public KoreaStockDownload() {
		if(code.size() == 0) {
			dataOutput();
			max = code.size();
		}
		JLabel totalLabel = new JLabel("");
		this.setLayout(new GridLayout(5,1));
		
		JPanel time = new JPanel();
		JLabel timeClock = new JLabel("작업 시간 : "+0);
		time.add(timeClock);
		Thread timer = new Thread(new Clock(timeClock));
		timer.start();
		this.add(time);
		
		JPanel panel1 = new JPanel();
		panel1.add(totalLabel);
		totalLabel.setText("한국 상장 주식 수 : "+code.size());
		
		this.add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.add(nowLabel);
		this.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.add(countLabel);
		this.add(panel3);
		
		Thread th = new Thread(new ThreadRunnable());
		th.start();
	}
	
	class ThreadRunnable implements Runnable{
		public ThreadRunnable() {
			
		}
		@Override
		public void run() {
			threadRun();
		}
	}
	
	class Clock implements Runnable{
		JLabel label = new JLabel("");
		public Clock(JLabel label) {
			this.label = label;
		}
		@Override
		public void run() {
			int i=0;
			while(last != -1) {
				label.setText("작동 시간 : "+i+"초");
				try {
					Thread.sleep(1000);
				}catch(Exception e) {}
				i++;
			}
		}
	}
	
	
	private void threadRun() {
		for(int i = 0;i<thread.length;i++) {
			thread[i] = new Thread();
		}
		int i = 0;
		while(i < max) {
			for(int j = 0;j<thread.length;j++) {
				if(thread[j].getState() == Thread.State.NEW || thread[j].getState() == Thread.State.TERMINATED) {
					if(i < max) {
						nowLabel.setText("현재 작업 : "+(i+1)+"/"+max);
						countLabel.setText("현재 데이터 수 : "+listCount);
						//System.out.println(j+"번 스레드 : "+i);
						thread[j] = new Thread(new CrawlingRunnable(code.get(i), nowLabel));
						thread[j].start();
						i++;
					}
					else {break;}
				}
				try {
					Thread.sleep(100);
				}catch(Exception e) {
					System.out.println(code.get(i));
				}
			}
		}
		while(true) {
			int count = 0;
			for(int j=0;j<thread.length;j++) {
				if(thread[j].getState() != Thread.State.RUNNABLE) {
					count++;
				}
			}
			if(count == thread.length) {
				break;
			}
			try {
				Thread.sleep(300);
			}catch(Exception e) {}
		}
		last = -1;
	}
	
	class CrawlingRunnable implements Runnable{
		private String code;
		public CrawlingRunnable(String code, JLabel label) {
			this.code = code;
		}
		@Override
		public void run() {
			try {
				new JsoupCrawling(code);
			}catch(ConnectException e) {
				System.out.println("time out");
			}
			
		}
	}
	class JsoupCrawling {
		private String urlFront = "https://finance.naver.com/item/sise_day.naver?code=";
		private String urlPage = "&page=";
		private String[][] strList;
		public JsoupCrawling(String code) throws ConnectException {
			int max = 100;
			strList = new String[max*10][3];
			int count = 0;
			for(int page = 1;page <= max;page++) {
				String url = urlFront+code+urlPage+page;
				Document doc = null;
			    try{
			    	doc = Jsoup.connect(url).get();
			    	Elements elementDate = doc.select("td:nth-child(1) > span");
			    	Elements elementClose = doc.select("td:nth-child(2) > span");
			    	for(int i = 0;i<elementDate.size();count++, i++) {
			    		strList[count][0] = code;
			    		strList[count][1] = elementDate.get(i).text();
			    	  	strList[count][2] = elementClose.get(i).text();
			    	}
			    } catch (Exception e) {
			    	System.out.println(code);
			    }
			}
			dataInput(strList, code);
		}
		
		private void dataInput(String[][] list, String code){
			String URL = "jdbc:mariadb://localhost:3306/stock";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection(URL, "root", "kiajw119");
				if (conn != null) {
					// 데이터 테이블에 추가
					String sqlCommand2 = "INSERT IGNORE INTO daily_price (code, date, close) VALUES (?, ?, ?)";
					for(String[] a : list) {
						if(a[1] != null && a[2] != null) {
							pstmt = conn.prepareStatement(sqlCommand2);
							pstmt.setString(1, a[0]);
							pstmt.setString(2, a[1]);
							pstmt.setString(3, a[2]);
							int rows = pstmt.executeUpdate();
							listCount += rows;
						}
					}
				}
				pstmt.close();
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로드 실패");
			} catch (SQLException e) {
				System.out.println("DB 접속 실패");
			}
		}
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
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}
	}
}