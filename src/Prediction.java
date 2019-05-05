/**
 * This class file is the logic of this project.
 * This file will compare the current data with all other data sets stores in this project about First Motion Survey and Depth
 * and come up with list of similar earthquakes to the problem.
 * The similar earthquakes would be stored in an ArrayList of GIS Points, 
 */
import com.esri.mo2.cs.geom.Point;
import java.util.*;



@SuppressWarnings("serial")
public class Prediction extends IndonesiaMain {
	public static Point p1 = new Point();
	public static ArrayList<QuakeDataObject> SimilarPoints = new ArrayList<QuakeDataObject>();
	public static String hashkey;
	public static double magnitude;
	public static char depth;
	
	public static void compute() {
		
		HistoricDataSet HDSobj = new HistoricDataSet();
		SimilarPoints = HDSobj.ExtractSimilarPoints(hashkey);
		//Draw the points, similar and problem points
		DrawPoint.StartDrawing();
	}	
}
