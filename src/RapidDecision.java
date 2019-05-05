/*
 * This java file is used to create a dialog box to enter the tweet information.
 * it contains 2 buttons, namely predict and instructions
 * Predict - actual heart of the thesis. takes in tweet data and predicts the similar earthquakes.
 * Instructions - this button gives a detailed instructions of how to enter the tweet
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("serial")
public class RapidDecision extends JDialog{
	
	boolean tweetCorrect = false;
	String tweet;
	JLabel label = new JLabel("Enter tweet");
	JTextField text = new JTextField("3.30,95.98,c,0,9.1",15);
	JButton instruct = new JButton("Instructions");
	JButton predict = new JButton("Predict");
	
	JPanel panel = new JPanel();
	ActionListener buttonlis;
	
	private static Pattern stringparse = Pattern.compile("([0-9\\.-]+),([0-9\\.-]+),([abcdf]?),([0-3]?),([0-9]+\\.?[0-9]*)");
	
	double problemLati,problemLongi;
	char problemDepth;
	int problemFirstMotion;
	double problemMagni;
	
	
	public RapidDecision () {
		setTitle("Rapid Decision Window");
		setBounds(50,50,300,100);
		
		// add acionlistener to close the window.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)  {
				setVisible(false);
			  }
		  }
		  );
		
		buttonlis = new ActionListener() {
			public void actionPerformed(ActionEvent ae)	{
				String source = ae.getActionCommand();

				if (source == "Instructions") {
					tweetInstructions myobj = new tweetInstructions();
					myobj.setVisible(true);
				}else if(source == "Predict") {
					//here we need to read all data from the tweet and store in correct format.
					tweet = text.getText();
					tweetCorrect = checkTweet(tweet);
					if(tweetCorrect == true)
						Prediction.compute();
				}	
			}
		};
		
		//Add action Listeners to buttons
		predict.addActionListener(buttonlis);
		instruct.addActionListener(buttonlis);
		 
		//Set the dialog box with layouts, etc
		panel.setLayout(new FlowLayout());
		panel.add(label);
		panel.add(text);
		panel.add(predict);
		panel.add(instruct);
		getContentPane().add(panel, BorderLayout.CENTER);
	}

	
	public boolean checkTweet(String s)
    {
        Matcher m = stringparse.matcher(s);
        if (m.matches()) {
        	
            problemLati = Double.parseDouble(m.group(1));
            problemLongi = Double.parseDouble(m.group(2));
            Prediction.depth = m.group(3).charAt(0);
            Prediction.hashkey = m.group(3)+m.group(4);
            problemMagni = Double.parseDouble(m.group(5));
            Prediction.magnitude = problemMagni;
            Prediction.p1.setLocation(problemLongi, problemLati);
            
            //System.out.println("problemLati=" + problemLati + " problemLongi=" + problemLongi + " hashkey=" + Prediction.hashkey + " problemMagni=" + problemMagni + "\n");
            
            if(problemLati<-90 || problemLati>90) {
            	displayError("Latitude is not in range. it should be between [-90,90]" , "error");
            	return false;
            }
            
            if(problemLongi<-180 || problemLongi>180) {
            	displayError("Longitude is not in range. it should be between [-180,180]" , "error");
            	return false;
            }	
            
            return true;
            
            
        } else {
        	displayError("Tweet format is wrong. Please read instructions carefully", "error");
        	return false;
        }
    }
	
	public static void displayError(String s, String flavor) {
		String TypeOfDialog = null;
		String Image = null;
		if(flavor.contains("info")) {
			TypeOfDialog = "Information";
			Image = "Images/Icons/info.jpg";
		} else if(flavor.contains("error")) {
			TypeOfDialog = "ERROR";
			Image = "Images/Icons/error.jpg";
		}
		
		JOptionPane.showMessageDialog(null, s, TypeOfDialog, 
				JOptionPane.ERROR_MESSAGE, new ImageIcon(Image));
		
	}
}
