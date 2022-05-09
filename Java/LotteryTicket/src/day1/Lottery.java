package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Lottery {
	int num[][] = new int[45][3];
	int view[][] = new int[45][2];
	int range[] = new int[45];
	int lottery[] = new int[6];
	int max = 0;
	int sum = 0;
	int c = 0;
	int overlap = 0;
	public Lottery(){
		try { 
			read();
		}catch(Exception e) { e.printStackTrace();}		
		for(int i=0;i<45;i++) {
			sum += num[i][1];
			range[i] = sum;
			view[i][0] = i+1;
			view[i][1] = 0;
		}
		while(c < 5555) { 
			roll2();
			c++;
		}
		sort(view);
		for(int i=0;i<6;i++) {
			lottery[i] = view[i][0];
		}
		Arrays.sort(lottery);
		System.out.print("ÃßÃ· ¹øÈ£ >> ");
		for(int i=0;i<6;i++) {
			System.out.print(lottery[i]+" ");
		}
		System.out.println();
	}
	
	
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
	
	void firstStart() {
		new Interface(lottery);
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
	
	void roll() {
		Random random = new Random();
		int rand = random.nextInt(1, max);
		int sum = 0;
		for(int i=0;i<45;i++) {
			sum += num[i][1];
			if(rand < sum) {
				select(i);
				break;
			}
		}
	}
	
	void roll2() {
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
		new Lottery();
		new Lottery().firstStart();
	}
}
