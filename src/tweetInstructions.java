/**
 * pops up a dialog box which gives clear instructions about how to initialize a tweet
 * it shows up a static string of instructions which can be changes by changing the String s below  
 */
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class tweetInstructions extends JFrame implements ActionListener {
	
	public tweetInstructions() {
		JButton ok = new JButton("OK");
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel centerlabel = new JLabel();
		setBounds(400,50,800,600);
		setTitle("Instructions");
		
		ok.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent ae) {
			
			setVisible(false);
			
		}});
		
		String s = "<HTML> <H1>*** Instructions to input tweet ***</H1><BR><BR>" +
		"FORMAT: (lat) , (long) , (depth grade) , (first motion survey code) , (magnitude)<BR><BR>" +
		"lat = latitude (Eg: 223.46)<BR>" +
		"lat = longitude (Eg: 28.91)<BR><BR>" +
		"---Depth grades---<BR>a -> 0-10km<BR>b -> 11-25km<BR>c -> 26-50km<BR>d -> 51-100km<BR>f -> 100km+<BR><BR>" +
		"---First Motion survey code---<BR>0 -> Compressional<BR>1 -> Sliding<BR>2 -> Extensional<BR><BR>" +
		"---Magnitude---<BR>If the range of magnitude is for example 6.5-7.5, input the lowest number '6.5'<BR>" +
		"<BR>---EXAMPLES---<BR><BR>1-Sumatra tsunami 2004 lat:3.30,long:95.98,depth:30km,first motion survey:compressional,magnitude:9.1<BR>tweet:\"3.30,95.98,c,0,9.1\"<BR>" +
		"2-Java earthquake 1943 lat:-9.5,long:110.00,depth:90km,first motion survey:compressional,magnitude:8.5<BR><B>tweet:\"-9.5,110.00,d,0,8.5\"<BR><BR>";
		
		
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