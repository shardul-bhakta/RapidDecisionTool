import com.esri.mo2.data.feat.Feature;
import com.esri.mo2.data.feat.Fields;
import com.esri.mo2.ui.bean.PickEvent;
import com.esri.mo2.ui.bean.PickListener;
 

public class MyPickAdapter implements PickListener {  //implements hotlink
	
	static String shape = null;
	static String province = null;
	static String provCode = null;
	boolean isfound;

	public void beginPick(PickEvent pe){;
		// this fires even when you click outside the states layer
		isfound = false;
		System.out.println("\nI am in beginPick- isfound is " + isfound);
	}
	public void endPick(PickEvent pe){
		if(isfound)
			System.out.println("\npoint is on land");
		else
			System.out.println("\npoint is on water body");
		System.out.println("\nI am in endPICK\n");
	}
	
	public void foundData(PickEvent pe){
		isfound = true;
		
		//fires only when a layer feature is clicked
		//FeatureLayer flayer2 = (FeatureLayer) pe.getLayer();
		com.esri.mo2.data.feat.Cursor c = pe.getCursor();
		Feature f = null;
		Fields fields = null;
		if (c != null)
			f = (Feature)c.next();
		fields = f.getFields();
	
		System.out.println("All Fields = " + fields);
		shape = f.getDisplayValue(0);
		province = f.getDisplayValue(1);
		provCode = f.getDisplayValue(2);
	 	   
		c.dispose();
	  
		
	 }
}