package StockUpdate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
	Container c = getContentPane();
	WestPanel west = new WestPanel();
	JPanel center = new JPanel();
	public MainMenu() {
		setSize(500,150);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		c.add(west, BorderLayout.WEST);
		c.add(center, BorderLayout.CENTER);
		
	}
	void screenReset(String name) {
		center.removeAll();
		center.setVisible(false);
		if(name.equals("한국 상장주식 데이터 다운로드")) {
			center.add(new KoreaStockDownload());
		}else if(name.equals("무결성 검사")){
			center.add(new JButton("1"));
		}
		else if(name.equals("데이터 개수 확인")){
			center.add(new DataCheck());
		}
		center.setVisible(true);
	}
	
	
	class WestPanel extends JPanel{
		public WestPanel() {
			this.setLayout(new GridLayout(0,1));
			JButton[] button = new JButton[3];
			JPanel panel1 = new JPanel();
			button[0] = new JButton("한국 상장주식 데이터 다운로드");
			panel1.add(button[0]);
			
			JPanel panel2 = new JPanel();
			button[1] = new JButton("무결성 검사");
			panel2.add(button[1]);
			
			JPanel panel3 = new JPanel();
			button[2] = new JButton("데이터 개수 확인");
			panel3.add(button[2]);
			
			this.add(panel1);
			this.add(panel2);
			this.add(panel3);
			
			for(int i=0;i<button.length;i++) {
				button[i].addActionListener(new MyActionListener());
			}
		}
	}

	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			screenReset(button.getText());

			
		}
	}

	public static void main(String[] args) {
		new MainMenu();
	}

}
