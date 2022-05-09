package day5;

import javax.swing.*;
import java.awt.*;

public class Test1 extends JFrame{
	JTextField jtf = new JTextField();
	public Test1 () {
		Container c = getContentPane();
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Test1();

	}

}
