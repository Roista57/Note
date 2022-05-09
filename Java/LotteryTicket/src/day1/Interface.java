package day1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Interface extends JFrame{
	Container c = getContentPane();
	JLabel label[] = new JLabel[45];
	JPanel panel = new JPanel();
	public Interface(int num[]) {
		setTitle("Lottery Ticket");
		c.setLayout(new BorderLayout());
		setSize(300,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Reset(num);
		JPanel northPanel = new JPanel();
		JButton button = new JButton("More");
		button.addActionListener(new ButtonAction());
		northPanel.add(button);
		c.add(northPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	class Reset{
		public Reset(int num[]) {
			panel.removeAll();
			panel.setLayout(new GridLayout(7,7));
			for(int i=0;i<45;i++) {
				label[i] = new JLabel(""+(i+1));
				label[i].setHorizontalAlignment(JLabel.CENTER);
				label[i].setOpaque(true); 
				panel.add(label[i]);
			}
			for(int i=0;i<num.length;i++) {
				label[(num[i]-1)].setBackground(Color.lightGray);
			}
			c.add(panel, BorderLayout.CENTER);
		}
	}
	
	class ButtonAction implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JButton button  = (JButton)e.getSource();
	        //new Reset();
	    }
	}
}
