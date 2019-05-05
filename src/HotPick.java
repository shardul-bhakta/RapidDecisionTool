import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class HotPick extends JDialog{
	
	  String selected_country = MyPickAdapter.province;
	  String region = MyPickAdapter.shape;
	  String capital_city = MyPickAdapter.provCode;
	  String continent = null;
	  String flagpic = null;
	  String curr = null;

	  JPanel goes_south = new JPanel();
	  JPanel goes_center = new JPanel();
	  JPanel goes_west = new JPanel();

	  String[][]countries={
			  
			{"India",			"Asia",			"Images/indiaflag.gif",		"Rupees"},
			{"China",			"Asia",			"Images/chinaflag.jpg",		"Yuan"},
			{"Japan",			"Asia",			"Images/japanflag.jpg",		"Yen"},
			{"Iran",			"Asia",			"Images/iranflag.gif",			"Rial"},
			{"Saudi Arabia",	"Asia",			"Images/saudiflag.gif",		"Riyal"},
			{"Russia",			"Europe",		"Images/russiaflag.gif",		"Ruble"},
			{"France",			"Europe",		"Images/franceflag.jpg",		"Euro"},
			{"Spain",			"Europe",		"Images/spainflag.gif",		"Euro"},
			{"Italy",			"Europe",		"Images/italyflag.jpg",		"Euro"},
			{"United Kingdom",	"Europe",		"Images/ukflag.jpg",			"Pound sterling"},
			{"United States",	"North America","Images/usaflag.gif",			"Dollar"},
			{"Canada",			"North America","Images/canadaflag.gif",		"Canadian dollar"},
			{"Mexico",			"North America","Images/mexicoflag.gif",		"Mexican peso"},
			{"Brazil",			"South America","Images/brazilflag.gif",		"Real"},
			{"Colombia",		"South America","Images/colombiaflag.jpg",		"Colombian Peso"},
			{"Argentina",		"South America","Images/argentinaflag.jpg",	"Peso"},
			{"Peru",			"South America","Images/peruflag.jpg",			"Nuevo sol"},
			{"Ecuador",			"South America","Images/ecuadorflag.jpg",		"U.S. dollar"},
			{"South Africa",	"Africa",		"Images/southafricaflag.jpg",	"Rand"},
			{"Egypt",			"Africa",		"Images/egyptflag.jpg",		"Egyptian pound"},
			{"Ethiopia",		"Africa",		"Images/ethiopiaflag.jpg",		"Birr"},
			{"Zimbabwe",		"Africa",		"Images/zimbabweflag.jpg",		"Zimbabwean dollar"},
			{"Australia",		"Australia",	"Images/australiaflag.jpg",	"Australian dollar"},
			{"New Zealand",		"Australia",	"Images/newzealandflag.jpg",	"New Zealand dollar"},
		};
	  
	  
	  public HotPick() throws IOException  {
		  
		  setTitle("Country Flag");
		  setBounds(50,50,1000,500);

		  addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e)  {
				  setVisible(false);
			  }
		  }
		  );
		
		  for (int i=0 ; i<24 ; i++) {
			if (countries[i][0].equals(selected_country)){
				continent = countries[i][2];
				flagpic = countries[i][2];
				curr = countries[i][3];
				break;
			}
		  }


		  Font labelfont = new Font("",1,24);
		  Font f = new Font("",1,30);

		  JLabel stars = new JLabel("****************" + selected_country + "*****************");

		  JLabel label_welcome = new JLabel("WELCOME TO "+ selected_country);
		  JLabel label_curr = new JLabel("CURRENCY: "+curr);
		  JLabel label_region = new JLabel("REGION: "+ region);
		  JLabel label_capital = new JLabel("CAPITAL: "+ capital_city);


		  stars.setFont(f);
		  label_welcome.setFont(labelfont);
		  label_curr.setFont(labelfont);
		  label_region.setFont(labelfont);
		  label_capital.setFont(labelfont);


		  ImageIcon flagIcon = new ImageIcon(flagpic);
		  JLabel flagLabel = new JLabel(flagIcon);

		  goes_west.setLayout(new GridLayout(8,1));
		  goes_west.add(label_welcome);
		  goes_west.add(label_curr);
		  goes_west.add(label_region);
		  goes_west.add(label_capital);
		  
		  goes_west.setBackground(Color.WHITE);
		  goes_south.setBackground(Color.CYAN);

		  goes_center.add(flagLabel);
		  goes_south.add(stars);

		  getContentPane().add(goes_center,BorderLayout.CENTER);
		  getContentPane().add(goes_south,BorderLayout.SOUTH);
		  getContentPane().add(goes_west,BorderLayout.WEST);

		  System.out.println("Country:" + selected_country);
		  System.out.println("Continent:"+ continent);
		  System.out.println("Currency:"+ curr+ "\n");

	  }

}
