import javax.swing.*;

import java.awt.event.*;
import java.awt.*;


@SuppressWarnings("serial")
public class AboutMe extends JFrame implements ActionListener {
	
	public AboutMe() {
		JButton ok = new JButton("OK");
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel centerlabel = new JLabel();
		setBounds(200,100,400,300);
		setTitle("Project Information");
		
		ok.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent ae) {
			
			setVisible(false);
			
		}});
		
		String s = "<HTML> <H1>*** Project Information ***</H1><BR>" +
		"Analysis of earthquake epicenter in Indonesia<BR>" +
		"Copyright(c) 2013-2014 <BR>" +
		"Version 1.0<BR><BR>" +
		"Created By: Shardul S Bhakta<BR>" +
		"San Diego State University<BR><BR>" +
		"All Rights Reserved.<BR>" ;
		
		
		centerlabel.setHorizontalAlignment(JLabel.CENTER);
		centerlabel.setText(s);
		panel1.add(centerlabel);
		panel2.add(ok);
		getContentPane().add(panel2,BorderLayout.SOUTH);
		getContentPane().add(panel1,BorderLayout.CENTER);
		
	}
	
	public void actionPerformed(ActionEvent e) {this.setVisible(false);
	}
}

