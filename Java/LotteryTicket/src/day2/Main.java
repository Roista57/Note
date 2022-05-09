package day2;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Main {
	int num[][] = new int[45][3];
	int view[][] = new int[45][2];
	int range[] = new int[45];
	int lottery[] = new int[6];
	private int max = 0;
	private int sum = 0;
	private int c = 0;
	private Percent percent = new Percent();
	public Main() {
		Scanner scanner = new Scanner(System.in);
		try {
			read();
		}catch(Exception e) {
			e.printStackTrace();
		}
		while(true) {
			int n = scanner.nextInt();
			new Lottery(n);
			for(int a : lottery) {
				System.out.print(a + " ");
			}
			System.out.println();
		}		
	}
	
	class Lottery{
		public Lottery(int max) {
			int i=0;
			for(i=0;i<view.length;i++) {
				sum += num[i][1];
				range[i] = sum;
				view[i][0] = i+1;
				view[i][1] = 0;
			}
			i=0;
			while(i < max) {
				roll2();
				i++;
			}
			percent.setLabel(view, max);
			sort(view);
			for(i=0;i<6;i++) {
				lottery[i] = view[i][0];
			}
			Arrays.sort(lottery);
		}
		
		void roll2() {
			for(int j=0;j<6;j++) {
				Random random = new Random();
				int rand = random.nextInt(1, max);
				int sum = 0;
				for(int i=0;i<45;i++) {
					sum += num[i][1];
					if(rand < sum) {
						view[i][1]++;
						break;
					}
				}
			}
		}
		
		// 랜덤으로 돌렸을 때 가장 많이 나오는 수를 내림차순으로 정렬
		void sort(int a[][]) {
			int temp[] = new int[2];
			for(int i=0;i<45;i++) {
				for(int j=i+1;j<45;j++) {
					if(a[i][1] < a[j][1]) {
						temp = a[i];
						a[i] = a[j];
						a[j] = temp;
					}
				}
			}
		}
		
		void select(int i) {
			int check = 0;
			for(int j=0;j<6;j++) {
				if(lottery[j] == i+1) {
					check = -1;
					break;
				}
			}
			if(check == 0) {
				lottery[c] = i+1;
				c++;
			}
		}
		
		
	}
	
	class Percent extends JFrame{
		private JLabel label[] = new JLabel[45];
		private JLabel per[] = new JLabel[45];
		private JLabel per2[] = new JLabel[45];
		public Percent() {
			JPanel mainPanel = new JPanel();
			Container c = getContentPane();
			mainPanel.setLayout(new GridLayout(45, 1));
			setSize(400, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			for(int i=0;i<45;i++) {
				JPanel panel = new JPanel();
				JLabel num = new JLabel((i+1)+"");
				num.setHorizontalAlignment(JLabel.CENTER);
				panel.setLayout(new GridLayout(0, 4));
				panel.add(num);
				label[i] = new JLabel();
				label[i].setHorizontalAlignment(JLabel.CENTER);
				panel.add(label[i]);
				per[i] = new JLabel();
				per[i].setHorizontalAlignment(JLabel.CENTER);
				panel.add(per[i]);
				per2[i] = new JLabel();
				per2[i].setHorizontalAlignment(JLabel.CENTER);
				panel.add(per2[i]);
				mainPanel.add(panel);
			}
			c.add(new JScrollPane(mainPanel));
			setVisible(true);
		}
		
		void setLabel(int view[][], int n) {
			int MAX = 0;
			int location = 0;
			for(int i=0;i<view.length;i++) {
				label[i].setOpaque(true);
				label[i].setBackground(Color.LIGHT_GRAY);
				label[i].setText(view[i][1]+"");
				if(MAX < view[i][1]) {
					MAX = view[i][1];
				}
			}
			for(int i=0;i<view.length;i++) {
				if(view[i][1] == MAX) {
					label[i].setBackground(Color.magenta);
				}
			}
			MAX = 0;
			for(int i=0;i<view.length;i++) {
				MAX += num[i][1];
				double a = (double)view[i][1]/n;
				String b = String.format("%.3f", a);
				per[i].setText(b);
			}
			for(int i=0;i<num.length;i++) {
				double a = (double)num[i][1]/MAX;
				String b = String.format("%.3f", a);
				per2[i].setText(b);
			}
		}
	}
	
	
	
	
	void read() throws IOException{
		BufferedReader reader = new BufferedReader(
			new FileReader("setting.txt")
		);
		String str;
		int i = 0;
		while ((str = reader.readLine()) != null) {
			str.split("\t");
			num[i][0] = Integer.parseInt(str.split("\t")[0]);
			num[i][1] = Integer.parseInt(str.split("\t")[1]);
			max += num[i][1];
			num[i][2] = max;
			i++;
		}
		reader.close();
	}

	public static void main(String[] args) {
		new Main();
	}
}
