import com.esri.mo2.cs.geom.Point;

public class QuakeDataObject {
	Point p;
	double magnitude;
	double depth;
		
	public QuakeDataObject(Point p,double depth,double magnitude) {
		this.p = (Point) p.clone();
		this.depth = depth;
		this.magnitude = magnitude;
	}
}
