import java.awt.Color;
import com.esri.mo2.map.draw.SimpleMarkerSymbol;
import com.esri.mo2.map.elt.TextElement;
import com.esri.mo2.ui.bean.AcetateLayer;
import com.esri.mo2.ui.bean.Map;

@SuppressWarnings("serial")
class DrawPoint {
	static Map map = IndonesiaMain.map;
	static SimpleMarkerSymbol sms = new SimpleMarkerSymbol();
	static TextElement magnitext = new TextElement();
	static java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");
	static boolean firstcall = false;
	static boolean arrayempty = false;
	

	static AcetateLayer acetLayer = new AcetateLayer() {
		public void paintComponent(java.awt.Graphics g) {
				java.awt.Graphics2D g2d = (java.awt.Graphics2D)g;
				g2d.setTransform(map.getWorldToPixelTransform().toAffine());
				g2d.setClip(map.getExtent());
				
				//draw the problem earthquake point on map
				sms.setType(SimpleMarkerSymbol.STAR_MARKER);
				sms.setWidth(decidePointSize(Prediction.depth));
				sms.setSymbolColor(Color.red);
				sms.draw(Prediction.p1,g2d,"");
				
				//draw the text displaying the magnitude
				magnitext.setText(Double.toString(Prediction.magnitude));
				magnitext.setX(Prediction.p1.getX());
				magnitext.setY(Prediction.p1.getY()+0.2);
				magnitext.setTextSize(decideTextSize(Prediction.magnitude));
				magnitext.setTextColor(decideTextColor(Prediction.magnitude));
				magnitext.draw(g2d);
				magnitext.setTextColor(decideTextColor(Prediction.magnitude));
				
				if(arrayempty == false) {
					for(QuakeDataObject obj : Prediction.SimilarPoints) {
						
						//draw the similar earthquake points
						sms.setType(SimpleMarkerSymbol.CIRCLE_MARKER);
						sms.setWidth(decideTextSize(obj.depth));
						sms.setSymbolColor(Color.BLUE);
						sms.draw(obj.p, g2d, "");
						
						//draw the text displaying the magnitude
						magnitext.setText(Double.toString(obj.magnitude));
						magnitext.setX(obj.p.getX());
						magnitext.setY(obj.p.getY()+0.2);
						magnitext.setTextSize(decideTextSize(obj.magnitude));
						magnitext.setTextColor(decideTextColor(obj.magnitude));
						magnitext.draw(g2d);
						
					}
				}
		}
	};

	public static void StartDrawing() {
		//System.out.println("\nfirstcall? " + firstcall + "\n");
		if(firstcall == false){
			firstcall = true;
			map.add(acetLayer);
		}
		if (Prediction.SimilarPoints.isEmpty()) {
			RapidDecision.displayError("No similar earthquakes found. This earthquake is a new kind" , "info");
			arrayempty = true;
		} else {
			arrayempty = false;
		}	
		acetLayer.repaint();
		
			
	}
	
	public static int decideTextSize(double magnitude) {
		
		if(magnitude <=6)
			return 12;
		if(magnitude >6 && magnitude <=7)
			return 14;
		if(magnitude>7)
			return 20;
		return 0;
	}
	
	public static Color decideTextColor(double magnitude) {
		if(magnitude <=6)
			return Color.BLUE;
		if(magnitude >6 && magnitude <=7)
			return Color.GREEN;
		if(magnitude>7)
			return Color.RED;
		
		return Color.WHITE;
	}
	
	public static double decidePointSize(char depth) {
		
		if(depth == 'a')
			return 10;
		if(depth == 'b')
			return 15;
		if(depth == 'c')
			return 20;
		if(depth == 'd')
			return 25;
		if(depth == 'f')
			return 30;
		
		return 0;
	}
	
}

