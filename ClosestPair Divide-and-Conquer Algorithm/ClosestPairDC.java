import java.util.ArrayList;



public class ClosestPairDC {

	public final static double INF = java.lang.Double.POSITIVE_INFINITY;

	
	//
	// findClosestPair()
	//
	// Given a collection of nPoints points, find and ***print***
	//  * the closest pair of points
	//  * the distance between them
	// in the form "(x1, y1) (x2, y2) distance"
	//

	// INPUTS:
	//  - points sorted in nondecreasing order by X coordinate
	//  - points sorted in nondecreasing order by Y coordinate
	//

	public static void findClosestPair(XYPoint pointsByX[], 
			XYPoint pointsByY[], boolean print)
	{	
		XYPoint[] dcDist = ClosestPair(pointsByX, pointsByY);
		System.out.println("DC "+dcDist[0]+" "+dcDist[1]+" "+dcDist[0].dist(dcDist[1]));

		//ClosestPairNaive.findClosestPair(pointsByX);



	}

	//Return type as XYPoint[] so I can retrieve XY Coords
	public static XYPoint[] ClosestPair(XYPoint pointsByX[], XYPoint pointsByY[]){
		int nPoints = pointsByX.length;

		int mid = nPoints/2;
		XYPoint xL[] = new XYPoint[mid];
		XYPoint xR[] = new XYPoint[nPoints-mid];
		XYPoint yL[] = new XYPoint[mid];
		XYPoint yR[] = new XYPoint[nPoints-mid];


		if( nPoints == 1 ) {
			XYPoint[] a = new XYPoint[2];
			a[0] = pointsByX[0];
			XYPoint dummy = new XYPoint(9999999, 9999999);
			a[1] = dummy;
			return a;
		}
		else if( nPoints == 2) {
			XYPoint[] a = new XYPoint[2];
			a[0] = pointsByX[0];
			a[1] = pointsByX[1];
			return a;
		}
		else {
			for(int i=0; i< mid; i++) {
				xL[i] = pointsByX[i];
			}
			for(int j=0; j<nPoints-mid; j++) {
				xR[j] = pointsByX[j+mid];
			}
			//TODO: sort YL, YR in nondecreasing order
			int count = 0;
			for (int k = 0; k < nPoints; k++){
				if (count < yL.length){
					if (pointsByY[k].isLeftOf(pointsByX[mid])){
						yL[count] = pointsByY[k];
						count++;
					}
				}
				else{
					break;
				}
			}

			count = 0;
			for (int k = 0; k < nPoints; k++){
				if (count < yR.length){
					if (!pointsByY[k].isLeftOf(pointsByX[mid])){
						yR[count] = pointsByY[k];
						count++;
					}
				}
				else{
					break;
				}
			}

			
			
		}

		XYPoint[] aL = ClosestPair(xL, yL);
		XYPoint[] aR = ClosestPair(xR, yR);
		
		double distL = aL[0].dist(aL[1]);
		double distR = aR[0].dist(aR[1]);
		
		XYPoint[] lrPair = aL;
		double lrDist = distL;
		
		if(distR < distL){
			lrPair = aR;
			lrDist = distR;
		}

		//call combine
		XYPoint[] result = combine(pointsByY, pointsByX, lrDist, lrPair);

		return result;
	}

	//Combine 
	public static XYPoint[] combine(XYPoint ptsByY[], XYPoint pointsByX[], double lrDist, XYPoint[] lrPair) {
		ArrayList<XYPoint>yStrip = new ArrayList<XYPoint>();
		int mid = pointsByX.length/2;
		int x = pointsByX[mid].x;
		for(int i=0; i< ptsByY.length; i++) {
			if(abs(x - ptsByY[i].x) <= lrDist) {
				yStrip.add(ptsByY[i]);
			}
		}
		double minDist = lrDist;
		XYPoint[] minPair = lrPair;
		for(int j=0; j<yStrip.size()-1; j++) {
			int k = j+1;
			while(k<=yStrip.size()-1 && yStrip.get(k).y - yStrip.get(j).y < lrDist) {
				double d = yStrip.get(k).dist(yStrip.get(j));
				if( d < minDist ) {
					// Save the coords into minPair, and the minimum distance to minDist
					minDist = d;
					minPair[0] = yStrip.get(k);
					minPair[1] = yStrip.get(j);	
				}
				k++;
			}
		}return minPair;
	}


	// Could use Math functions, but just wanted to make my own
	static int abs(int x){
		if( x< 0){
			return -x;
		}
		else{
			return x;
		}
	}
	static double min(double a, double b) {
		if(a< b) {
			return a;
		}
		else {
			return b;
		}
	}

}
