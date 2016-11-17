public class ClosestPairNaive {
    
	
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
   
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "NAIVE (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    public static void findClosestPair(XYPoint points[], boolean print) {
    	double minDist = INF;
    	int nPoints = points.length;
    	int j = 0;
    	print  = true;
    	XYPoint p1 = points[0];
    	XYPoint p2 = points[0];
    	
    	while(j<nPoints-1){
    		int k = j+1;
    		while(k<nPoints){
    			double d = points[j].dist(points[k]);
    			if (d < minDist) {
    				minDist = d;
    				p1 = points[j];
    				p2 = points[k];
    			}k++;
    		}j++;
    	}
    	
        if(print == true){	
            System.out.println("NAIVE ("+p1.x+", "+p1.y+") "+"("+p2.x+", "+p2.y+") "+minDist);
        }
    }
    
 
}
