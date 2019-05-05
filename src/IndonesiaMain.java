import javax.swing.*;

import java.io.IOException;
import java.awt.event.*;
import java.awt.*;

import com.esri.mo2.ui.bean.*;
import com.esri.mo2.ui.tb.ZoomPanToolBar;
import com.esri.mo2.ui.tb.SelectionToolBar;
import com.esri.mo2.ui.ren.LayerProperties;
import com.esri.mo2.cs.geom.Envelope;

@SuppressWarnings("serial")
public class IndonesiaMain extends JFrame{
	
	static Map map = new Map();
	static boolean fullMap = true;
	
	Legend legend;
	Legend legend2;
	
	Layer layer = new Layer();
	Layer layer2 = new Layer();
	Layer layer3 = new Layer();
	static com.esri.mo2.map.dpy.Layer layer4;
	static com.esri.mo2.map.dpy.Layer activeLayer;
	int activeLayerIndex = -999;
	
	Toc toc = new Toc();
	JMenuBar mbar = new JMenuBar();
	//FILE menu and its Items
	JMenu file = new JMenu("File");
	JMenuItem addlyritem = new JMenuItem("add layer",new ImageIcon("Images/Icons/addtheme.gif"));
	JMenuItem remlyritem = new JMenuItem("remove layer",new ImageIcon("Images/Icons/delete.gif"));
	JMenuItem propsitem = new JMenuItem("Legend Editor",new ImageIcon("Images/Icons/properties.gif"));
	//SHAPE FILE and it's menu items 
	JMenu theme = new JMenu("Theme");
	JMenuItem attribitem = new JMenuItem("Open Attribute Table",new ImageIcon("Images/tableview.gif"));
	//LAYER CONTROL menu and its items
	JMenu layercontrol = new JMenu("Layer Control");
	JMenuItem promoteitem = new JMenuItem("Promote Selected Layer",new ImageIcon("Images/icons/promote.jpg"));
	JMenuItem demoteitem = new JMenuItem("Demote Selected Layer",new ImageIcon("Images/icons/demote.jpg"));
	//HELP menu and its Items
	JMenu help = new JMenu("Help");
	JMenuItem aboutitem = new JMenuItem("Project Information",new ImageIcon("Images/water_icon.jpg"));
	JMenuItem contactitem = new JMenuItem("Contact Me",new ImageIcon("Images/emailicon.jpg"));
	//Rapid Data Analyzer, opens a text box with tweet information
	JMenu rapid = new JMenu("Rapid-Decision-Maker");
	JMenuItem info = new JMenuItem("Enter Data",new ImageIcon("Images/icons/analyzer.jpg"));
		
	String s1 = "ShapeFiles/province-map_Indonesia/province_map.shp";
	String s2 = "Shapefile-from-csv/subduction.shp";
	String s3 = "Shapefile-from-csv/faults.shp";
	//String s1 = "ShapeFiles/world/country.shp";
	String datapathname = "";
	String legendname = "";
	
	static SelectionToolBar stb = new SelectionToolBar();
	ZoomPanToolBar zptb = new ZoomPanToolBar();
	JToolBar jtb = new JToolBar();
	
	ComponentListener complistener;
	JLabel statusLabel = new JLabel("status bar    LOC");
	java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");
	
	JPanel myjp = new JPanel();
	JPanel myjp2 = new JPanel();
	
	JButton print = new JButton(new ImageIcon("Images/Icons/print.gif"));
	JButton addlyrjb = new JButton(new ImageIcon("Images/Icons/addtheme.gif"));
		
		
	JButton pointer = new JButton(new ImageIcon("Images/pointer.gif"));
	JButton hotjb = new JButton(new ImageIcon("Images/hotlink.gif"));
	
	Arrow arrow = new Arrow();
	DrawPoint drawPt = new DrawPoint();
	
	TocAdapter mytocadapter;
	ActionListener lis;
	ActionListener layerlis;
	ActionListener layercontrollis;
	ActionListener helplis;
	ActionListener rda;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image bolt = tk.getImage("Images/hotlink.gif");  // 16x16 gif file
	java.awt.Cursor boltCursor = tk.createCustomCursor(bolt,new Point(6,10),"bolt");
	
	MyPickAdapter picklis = new MyPickAdapter();
	Identify hotlink = new Identify(); //the Identify class implements a PickListener,
	
	static Envelope env;
	public IndonesiaMain() { // CONSTRUCTOR
		
		super("Earthquake prediction in Sumatra, Indonesia - by Shardul Bhakta");
		this.setSize(1000,500);
		zptb.setMap(map);
		stb.setMap(map);
		setJMenuBar(mbar);
		
		ActionListener lisZoom = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fullMap = false;
			}
		}; // can change a boolean here

		ActionListener lisFullExt = new ActionListener() {
			public void actionPerformed(ActionEvent ae)	{
				fullMap = true;
			}
		};
		
		// next line gets a hold of a reference to the zoomin button
		JButton zoomInButton = (JButton)zptb.getActionComponent("ZoomIn");
		JButton zoomFullExtentButton = (JButton)zptb.getActionComponent("ZoomToFullExtent");
		JButton zoomToSelectedLayerButton = (JButton)zptb.getActionComponent("ZoomToSelectedLayer");
		zoomInButton.addActionListener(lisZoom);
		zoomFullExtentButton.addActionListener(lisFullExt);
		zoomToSelectedLayerButton.addActionListener(lisZoom);

		complistener = new ComponentAdapter () {
			public void componentResized(ComponentEvent ce)	{
				if(fullMap)	{
					map.setExtent(env);
					map.zoom(1.0);    //scale is scale factor in pixels
					map.redraw();
				}
			}
		};
		
		addComponentListener(complistener);
		lis = new ActionListener() {
			public void actionPerformed(ActionEvent ae)	{
				Object source = ae.getSource();

				if (source == hotjb) {
					//BoltOn = true;
					hotlink.setCursor(boltCursor);
					map.setSelectedTool(hotlink);
					
				
				}else
				if(source == pointer) {
					//BoltOn = false;
					map.setSelectedTool(arrow);
				
				}else if(source == print) {
					com.esri.mo2.ui.bean.Print mapPrint = new com.esri.mo2.ui.bean.Print();
        			mapPrint.setMap(map);
        			mapPrint.doPrint();
        		
				}else {
        			try {
        				AddLyrDialog aldlg=  new AddLyrDialog();
        				aldlg.setMap(map);
        				aldlg.setVisible(true);
        			} catch(IOException e){}
        		}
				
			}
		};

		layerlis = new ActionListener(){
			
			public void actionPerformed(ActionEvent ae)	{
				
				Object source = ae.getSource();
				if (source instanceof JMenuItem) {
					String arg = ae.getActionCommand();
					
					if(arg == "add layer")	{
						try {
							AddLyrDialog aldlg = new AddLyrDialog();
							aldlg.setMap(map);
							aldlg.setVisible(true);
						} catch(IOException e){}
					
					} else if(arg == "remove layer"){
						try {
							com.esri.mo2.map.dpy.Layer dpylayer =
							legend.getLayer();
							
							map.getLayerset().removeLayer(dpylayer);
							map.redraw();
							remlyritem.setEnabled(false);
							propsitem.setEnabled(false);
							attribitem.setEnabled(false);
													
							stb.setSelectedLayer(null);
						
						} catch(Exception e) {}
					
					} else if(arg == "Legend Editor") {
						LayerProperties lp = new LayerProperties();
						lp.setLegend(legend);
						lp.setSelectedTabIndex(0);
						lp.setVisible(true);
					
					} else if (arg == "Open Attribute Table") {
	    				try {
	    					layer4 = legend.getLayer();
	    					AttrTab attrtab = new AttrTab();
	    					attrtab.setVisible(true);
	    				} catch(Exception ioe){
	    					RapidDecision.displayError("No layer selected. Please select a layer and try again", "error");
	    				}
	    				
	    			}

				}
			}
		};
		
		layercontrollis = new ActionListener() {
			public void	actionPerformed(ActionEvent ae){
				String source = ae.getActionCommand();
				System.out.println(activeLayerIndex+" active index");
				if (source .equals("Promote Selected Layer"))
					map.getLayerset().moveLayer(activeLayerIndex,++activeLayerIndex);
				else
					map.getLayerset().moveLayer(activeLayerIndex,--activeLayerIndex);
				enableDisableButtons();
        	map.redraw();
        	
			}
		};

		helplis = new ActionListener() {
			public void	actionPerformed(ActionEvent ae) {
				String source = ae.getActionCommand();
				if (source .equals("Project Information") ) {
					AboutMe abu = new AboutMe();
					abu.setVisible(true);
				} else 	if (source .equals("Contact Me"))	{
					ContactMe cu = new ContactMe();
					cu.setVisible(true);
				}
	    	}
		};
		
		rda = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String source = ae.getActionCommand();
				if(source == "Enter Data"){
					RapidDecision obj = new RapidDecision();
					obj.setVisible(true);
				}
			}
		};
		
		toc.setMap(map);
		mytocadapter = new TocAdapter()	{
		  public void click(TocEvent e)  {
			  
				legend = e.getLegend();
				activeLayer = legend.getLayer();
				stb.setSelectedLayer(activeLayer);
				zptb.setSelectedLayer(activeLayer);
				
				// get active layer index for promote and demote
				activeLayerIndex = map.getLayerset().indexOf(activeLayer);
				
				// layer indices are in order added, not toc order.
				
				remlyritem.setEnabled(true);
	    		propsitem.setEnabled(true);
	    		attribitem.setEnabled(true);
				enableDisableButtons();
		  }
		};
		
		map.addMouseMotionListener(new MouseMotionAdapter()	{
			public void mouseMoved(MouseEvent me) {
				
				com.esri.mo2.cs.geom.Point worldPoint = null;
				if (map.getLayerCount() > 0) {
					worldPoint = map.transformPixelToWorld(me.getX(),me.getY());
					String s = "X:"+df.format(worldPoint.getX())+" "+
								"Y:"+df.format(worldPoint.getY());
					statusLabel.setText(s);
				} else
					statusLabel.setText("X:0.000 Y:0.000");
			}
		}
		);

		toc.addTocListener(mytocadapter);
		
		// assume no layer initially selected
		remlyritem.setEnabled(false);
		propsitem.setEnabled(false);
		
		//Listeners of FILE menu items
		addlyritem.addActionListener(layerlis);
		remlyritem.addActionListener(layerlis);
		propsitem.addActionListener(layerlis);
		
		//Listener of THEME menu items
		attribitem.addActionListener(layerlis);
		
		//Listeners of LAYER CONTROL menu items
		promoteitem.addActionListener(layercontrollis);
	    demoteitem.addActionListener(layercontrollis);
	    
	    //Listeners of HELP menu items
	    aboutitem.addActionListener(helplis);
	    contactitem.addActionListener(helplis);
	    
	    //Listeners of Rapid menu item
	    info.addActionListener(rda);
	    
	   	//Add FILE and its attributes to mbar         
		file.add(addlyritem);
		file.add(remlyritem);
		file.add(propsitem);
		mbar.add(file);
		//Add THEME and its attributes to mbar
		theme.add(attribitem);
		mbar.add(theme);
		//Add LAYER CONTROL and its attributes to mbar
		layercontrol.add(promoteitem);
		layercontrol.add(demoteitem);
		mbar.add(layercontrol);
		//Add HELP to its attributes to mbar
		help.add(aboutitem);
		help.add(contactitem);
		mbar.add(help);
		//Add rapid and its attributes to mbar
		rapid.add(info);
		mbar.add(rapid);
		
		
		hotlink.addPickListener(picklis);
		hotlink.setPickWidth(5);// sets tolerance for hotlink clicks
		
		hotjb.addActionListener(lis);
		hotjb.setToolTipText("hotlink tool- click on any point for more information --- NOT IMPLEMENTED");
		
		pointer.addActionListener(lis);
		pointer.setToolTipText("Return back to the pointer");
		
		print.addActionListener(lis);
		print.setToolTipText("Print Map");
		
		addlyrjb.addActionListener(lis);
		addlyrjb.setToolTipText("Add new Layer");
		
				
		jtb.add(print);
		jtb.add(addlyrjb);
		jtb.add(hotjb);
		jtb.add(pointer);
		
		myjp.add(jtb);
		myjp.add(zptb);
		myjp.add(stb);
		
		myjp2.add(statusLabel);
		
		getContentPane().add(map, BorderLayout.CENTER);
		getContentPane().add(myjp,BorderLayout.NORTH);
		getContentPane().add(myjp2,BorderLayout.SOUTH);
		
		//Add all the layers
		addShapefileToMap(layer,s1);
		addShapefileToMap(layer2,s2);
		addShapefileToMap(layer3,s3);
			
		getContentPane().add(toc, BorderLayout.WEST);
		
		 
	  }//COnstructor over

	   private void addShapefileToMap(Layer layer,String s)  {
		String datapath = s;
		layer.setDataset("0;" + datapath);
		
		map.add(layer);
	   }
	   
	  public static void main(String[] args)  {
		  
		IndonesiaMain qstart = new IndonesiaMain();
		qstart.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e)  {
			System.exit(0);
		  }
		}
		);
		qstart.setVisible(true);
		env = map.getExtent();
		
	  }
	  
	  public IndonesiaMain getCurrentObject(){
		  return (this);
	  }
	  
	  
	  private void enableDisableButtons() {
		  int layerCount = map.getLayerset().size();
		  if (layerCount < 2) {
			  promoteitem.setEnabled(false);
			  demoteitem.setEnabled(false);
		  }
		  else if (activeLayerIndex == 0) {
			  demoteitem.setEnabled(false);
			  promoteitem.setEnabled(true);
		  }
		  else if (activeLayerIndex == layerCount - 1) {
			  promoteitem.setEnabled(false);
			  demoteitem.setEnabled(true);
		  }
		  else {
			  promoteitem.setEnabled(true);
			  demoteitem.setEnabled(true);
		  }
	  }


}// The IndonesiaMain class closes