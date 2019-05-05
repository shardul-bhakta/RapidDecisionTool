/**
 * this file contains all the data sets required for the tool.
 * each hash map would consist of keys as strings and array of objects as the values.
 * the keys will represent earthquakes with specific first motion and range depth.
 */
import java.util.*;
import com.esri.mo2.cs.geom.Point;

public class HistoricDataSet {
	public static HashMap<String,ArrayList<QuakeDataObject>> dataset = new HashMap<String,ArrayList<QuakeDataObject>>(20);
	
	
	public HistoricDataSet() {
		createA0();createB0();createC0();createD0();createF0();
		createA1();createB1();createC1();createD1();createF1();
	}
	
	public ArrayList<QuakeDataObject> ExtractSimilarPoints (String key){
			
		if(dataset.containsKey(key)){
			return dataset.get(key);
		} 
				
		return (new ArrayList<QuakeDataObject>());
	}
	/* ------------------------------------------------------------------------------------------
	 * 					FUNCTION DECLARATION FOR COMPRESSIONAL WITH VARIABLE DEPTHS
	 * ------------------------------------------------------------------------------------------
	 */
	
	public void createA0() { //A=0-10km depth
		double lati[] = new double[]  {3.4   , -2.22 , -1.3};
		double longi[] = new double[] {96.28 , 92.23 , 89.01};
		double depth[] = new double[] {1     , 8.8   , 10};
		double magni[] = new double[] {6.3   , 6.8   , 6.4};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("a0", hashValue);
		
	}
	
	public void createB0() { //B=11-25km depth
		double lati[] = new double[]  {-3.46};
		double longi[] = new double[] {100.08};
		double depth[] = new double[] {13};
		double magni[] = new double[] {7.7};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("b0", hashValue);
		
	}
	
	public void createC0() { //C=26-50km depth
		double lati[]  = new double[] {3.3   , 2.08 , 2.82  , 2.36  , -4.72  , -4.61 , 0.2   , -4.43  , 3.76 , -1.34};
		double longi[] = new double[] {95.98 , 97.1 , 96.08 , 97.13 , 102.08 , 101.9 , 98.03 , 101.36 , 6.06 , 99.47};
		double depth[] = new double[] {30    , 30   , 30    , 31    , 33     , 33    , 33    , 34     , 45   , 45};
		double magni[] = new double[] {9	 , 8.7  , 7.3   , 7.8   , 7.9    , 6.7   , 7.2   , 8.4    ,	7.2  , 6.7};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("c0", hashValue);
		
	}
	
	public void createD0() { //D=51-100km depth
		double lati[]  = new double[] {-0.72 , -0.7};
		double longi[] = new double[] {99.86 , 99.86};
		double depth[] = new double[] {81    , 87};
		double magni[] = new double[] {7.5   , 7.9};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("d0", hashValue);
		
	}
	
	public void createF0() { //F=101km + depth
		double lati[]  = new double[] {};
		double longi[] = new double[] {};
		double depth[] = new double[] {};
		double magni[] = new double[] {};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		//Since this array is empty, do not put the key in hash for now.
		//dataset.put("f0", hashValue);
		
	}
	
	
	/* ------------------------------------------------------------------------------------------
	 * 					FUNCTION DECLARATION FOR SLIDE-SLIP WITH VARIABLE DEPTHS
	 * ------------------------------------------------------------------------------------------
	 */
	
	public void createA1() { //A=0-10km depth
		double lati[] = new double[]  {-0.47  , -2.51 , 4.7};
		double longi[] = new double[] {100.38 , 101.5 , 96.69};
		double depth[] = new double[] {3      , 10    , 10};
		double magni[] = new double[] {6.5    , 6.6   , 6.2};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("a1", hashValue);
		
	}
	
	public void createB1() { //B=11-25km depth
		double lati[]  = new double[] {2.24  , -4.96 , -4.97 , 2.3};
		double longi[] = new double[] {98.86 , 104.3 , 104.3 , 93.06};
		double depth[] = new double[] {11    , 23    , 23    , 23};
		double magni[] = new double[] {6.6   , 6.9   , 7     , 8.6};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("b1", hashValue);
		
	}
	
	public void createC1() { //C=26-50km depth
		double lati[] = new double[]  {2.76  , -2.04  , -3.29  , -1};
		double longi[] = new double[] {95.96 , 101.43 , 102.71 , 101};
		double depth[] = new double[] {26    , 33     , 33     , 50};
		double magni[] = new double[] {7.4   , 6.8    , 6.6    , 7.2};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("c1", hashValue);
		
	}
	
	public void createD1() { //D=51-100km depth
		double lati[] = new double[]  {2.81};
		double longi[] = new double[] {97.85};
		double depth[] = new double[] {78};
		double magni[] = new double[] {6.6};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("d1", hashValue);
		
	}
	
	public void createF1() { //F=101km+ depth
		double lati[] = new double[]  {2.97  , 4.31};
		double longi[] = new double[] {97.99 , 97.63};
		double depth[] = new double[] {110   , 160};
		double magni[] = new double[] {6.6   , 6.1};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		dataset.put("f1", hashValue);
		
	}
	
	
	/* ------------------------------------------------------------------------------------------
	 * 					FUNCTION DECLARATION FOR EXTENSIONAL WITH VARIABLE DEPTHS
	 * ------------------------------------------------------------------------------------------
	 */
	
	public void createA2() { //A=0-10km depth
		double lati[] = new double[]  {};
		double longi[] = new double[] {};
		double depth[] = new double[] {};
		double magni[] = new double[] {};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		//Since this array is empty, do not put the key in hash for now.
		//dataset.put("a2", hashValue);
		
	}
	
	public void createB2() { //B=11-25km depth
		double lati[] = new double[]  {};
		double longi[] = new double[] {};
		double depth[] = new double[] {};
		double magni[] = new double[] {};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		//Since this array is empty, do not put the key in hash for now.
		//dataset.put("b2", hashValue);
		
	}
	
	public void createC2() { //C=26-50km depth
		double lati[] = new double[]  {};
		double longi[] = new double[] {};
		double depth[] = new double[] {};
		double magni[] = new double[] {};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		//Since this array is empty, do not put the key in hash for now.
		//dataset.put("c2", hashValue);
		
	}
	
	public void createD2() { //D=51-100km depth
		double lati[] = new double[]  {};
		double longi[] = new double[] {};
		double depth[] = new double[] {};
		double magni[] = new double[] {};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		//Since this array is empty, do not put the key in hash for now.
		//dataset.put("d2", hashValue);
		
	}
	
	public void createF2() { //F=100km+ depth
		double lati[] = new double[]  {};
		double longi[] = new double[] {};
		double depth[] = new double[] {};
		double magni[] = new double[] {};
		
		ArrayList<QuakeDataObject> hashValue = new ArrayList<QuakeDataObject>();
		
		for(int i=0 ; i<lati.length ; i++) {
			Point p = new Point();
			p.setLocation(longi[i], lati[i]);
			QuakeDataObject obj = new QuakeDataObject(p,depth[i],magni[i]);
			hashValue.add(obj);
		}
		
		//Since this array is empty, do not put the key in hash for now.
		//dataset.put("f2", hashValue);
		
	}
	
}
