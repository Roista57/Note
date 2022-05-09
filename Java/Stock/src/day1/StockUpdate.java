package day1;
// 상장법인목록 엑셀 다운로드
// http://kind.krx.co.kr/corpgeneral/corpList.do?method=download&searchType=13

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class StockUpdate {
	ArrayList<String> code = new ArrayList<String>();
	ArrayList<String> company = new ArrayList<String>();
	public StockUpdate() {
		String url = "http://kind.krx.co.kr/corpgeneral/corpList.do?method=download&searchType=13";
		String downloadPath = "‪E:\\Download\\상장법인목록.xls";
		String path = "E:\\Download\\상장법인목록.xls";
		//download(downloadPath, url);
		new CorporationUpdate(path);
	}
	
	class CorporationUpdate{
		public CorporationUpdate(String path) {
			try {
				FileInputStream file = new FileInputStream(path);
				HSSFWorkbook workbook = new HSSFWorkbook(file);
				HSSFSheet sheet = workbook.getSheetAt(0);
				
				// 사용자가 입력한 엑셀 Row수를 가져온다
				int rows = sheet.getPhysicalNumberOfRows();
				for(int rowIndex = 1; rowIndex < rows; rowIndex++) {
					HSSFRow row = sheet.getRow(rowIndex);
					if(row != null) {
						// 해당 Row에 사용자가 입력한 셀의 수를 가져온다
						int cells = row.getPhysicalNumberOfCells();
						for(int cellIndex = 0; cellIndex < 2; cellIndex++) {
							HSSFCell cell = row.getCell(cellIndex);
							String value = "";
							switch(cell.getCellType()) {
								case FORMULA:
									value = cell.getCellFormula();
									break;
								case NUMERIC:
									value = String.valueOf(cell.getNumericCellValue());
									break;
								case STRING:
									value = cell.getStringCellValue() + "";
									break;
								case BLANK:
									value = cell.getBooleanCellValue() + "";
									break;
								case ERROR:
									value = cell.getErrorCellValue() + "";
									break;
							}
							if(cellIndex == 0) {
								company.add(value);
							}
							else if(cellIndex == 1) {
								code.add(value);
							}
						}
					}
				}
				file.close();
				dataBase();
				}catch(FileNotFoundException e) {
					System.out.println("파일이 없습니다.");
				}catch(IOException e){
					e.printStackTrace();
				}
		}
		// 데이터 베이스에 입력
		private void dataBase(){
			String URL = "jdbc:mariadb://localhost:3306/stock";
			Connection conn = null;
			PreparedStatement pstmt = null;
			Date date = new Date();
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection(URL, "root", "kiajw119");
				if (conn != null) {
					// 데이터 테이블에 추가
					String sqlCommand = "INSERT IGNORE INTO company_update (code, company) VALUES (?, ?)";
					for(int i=0;i<code.size();i++) {
						pstmt = conn.prepareStatement(sqlCommand);
						pstmt.setString(1, code.get(i));
						pstmt.setString(2, company.get(i));
						pstmt.executeUpdate();
					}
					System.out.println("완료");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로드 실패");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("DB 접속 실패");
				e.printStackTrace();
			}
		}
	}
	
	void download(String download, String url) {
		File file = new File(download);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
			}else {
				System.out.println("파일 삭제 실패");
			}
		}else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		try {
			Desktop.getDesktop().browse(new URI(url));
		}catch(IOException er) {
			System.out.println("입출력 오류");
		}catch (URISyntaxException er) {
			System.out.println("URISyntax 오류");
		}
	}
	

	public static void main(String[] args) {
		new StockUpdate();
	}
}
