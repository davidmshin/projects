import java.util.*;


public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    
    public static XYPoint p1;
    public static XYPoint p2;
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "DC (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
    public static void findClosestPair(XYPoint pointsByX[], 
				       XYPoint pointsByY[],
				       boolean print)
    {
    	int nPoints = pointsByX.length;
    	
    	double distance= findClosestPair(pointsByX, pointsByY, nPoints);
    	
    	
    	if (print)
	    System.out.println("DC " + "(" + p1.x+", "+p1.y+") "+"("+p2.x+", "+p2.y+") "+distance);
    }
    
  
    
    public static double findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[], int n) {
    	int nPoints = pointsByX.length;
    	double minDist = INF;
    	double distL = INF;
    	double distR = INF;
    	
    	if(nPoints==1) {
    		return INF;
    	} if(nPoints==2) {
    		return pointsByX[0].dist(pointsByX[1]);
    	} else {
//    		int mid = (int)Math.ceil(nPoints/2) -1;
    		int mid = nPoints/2;
    		
    		
    		XYPoint[] XL = new XYPoint[mid];
    		XYPoint[] XR = new XYPoint[nPoints-mid];
    		
    		// Sort XL in nondecreasing order of X Coord
    		for(int i=0; i<mid; i++) {
    			XL[i] = pointsByX[i];
    		}
    		
    		for(int j=0; j<nPoints-mid; j++) {
    			XR[j] = pointsByX[j+mid];
    		}
    	
    		XYPoint [] YL = new XYPoint[mid];
    		XYPoint [] YR = new XYPoint[nPoints-mid];
    		
    		// Just need to sort YL, and YR, accordingly, each to the XL and XR
    		int count = 0;
    		for(int i=0; i<nPoints; i++){
    			if(count < YL.length){
    				if(pointsByY[i].isLeftOf(pointsByX[mid])) {
    					YL[count] = pointsByY[i];
    					count++;
    				}
    			}
    			else {
    				break;
    			}
    		}
    		
    		count = 0;
    		for(int i=0; i<nPoints; i++) {
    			if(count < YR.length) {
    				if(!pointsByY[i].isLeftOf(pointsByX[mid])) {
    					YR[count] = pointsByY[i];
    					count++;
    				}
    			}
    			else {
    				break;
    			}
    		}
    		
    		distL = findClosestPair(XL, YL, nPoints);
    		distR = findClosestPair(XR, YR, nPoints);
    		
    		
    		
    		return combine(pointsByY, pointsByX, pointsByX[mid], n, Math.min(distL, distR));
    	}
    }
    
    public static double combine(XYPoint pointsByY[], XYPoint pointsByX[], XYPoint midPoint, int n, double lrDist) {
    	
    	int nPoints = pointsByY.length;
    	int mid = pointsByY.length/2;
    	
    	// Using ArrayList to conveniently create yStrip, while not disturbing the running time
    	// It supports constant time access and amortized constant time append!
    	ArrayList<XYPoint> yS = new ArrayList<XYPoint>();
    	int x = pointsByX[mid].x;
    	
    	//XYPoint [] yStrip = new XYPoint[0];
    	//Construct yStrip, in increasing y order, of all points p in ptsByY, s.t |p.x - midPoint.x| < lrDist
    	
    	//Get all such p points in yStrip
    	for(int i=0; i<nPoints; i++) {
    		if(Math.abs(x - pointsByY[mid].x ) <= lrDist ) {
    			yS.add(pointsByY[i]);
    		}  		
    	}
    	
    	
    	
    	//Convert yS ArrayList to array yStrip
    	XYPoint[] yStrip = new XYPoint[yS.size()];
    	yS.toArray(yStrip);
    	
  	
    	// Now, store Closest Points Pairs in the global variables, and get the minDist
    	double minDist = lrDist;
    	for(int j=0; j<yStrip.length-1; j++) {
    		int k = j+1;
    		while(k<=yStrip.length-1 && yStrip[k].y-yStrip[j].y < lrDist) {
    			double d = yStrip[j].dist(yStrip[k]);
    			if(d < minDist){
    				p1 = yStrip[j];
        			p2 = yStrip[k];
        			minDist = Math.min(minDist,d);	
    			}
    			
    			k++;
    		} 
    		
    	}
    	
    	
    	
    	return minDist;
    }
    
    
}
