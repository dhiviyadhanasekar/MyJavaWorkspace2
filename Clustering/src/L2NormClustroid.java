import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//2.  	Suppose the clustroid of a cluster is taken to be the point in the set that has the minimum sum of the squares of the distances to all other points in the cluster. We are given the following points in two-dimensional Euclidean space: a = (1,6); b = (3,7); c = (4,3); d = (7,7), e = (8,2); f = (9,5). Assuming the usual L2-norm as our distance measure, compute the clustroids of the following sets: {a,b,c,d}, {b,c,d,e}, {c,d,e,f}, and {a,b,e,f}. Then, identify one of the correct clustroids from the list below.	 
// 	a) 	The clustroid of {a,b,c,d} is d.
// 	b) 	The clustroid of {a,b,e,f} is b.
// 	c) 	The clustroid of {a,b,e,f} is e.
// 	d) 	The clustroid of {c,d,e,f} is d.
// 
// 	
//Answer submitted:   b)
// 
//You have answered the question correctly.

public class L2NormClustroid {

	ArrayList<Point> pointList = new ArrayList<Point>();
	float[][] distanceBetPoints;// = new float[pointList.size()][];
	ArrayList<Cluster> clusterList = new ArrayList<Cluster>();
	
	public static void main(String[] args) {
		
		L2NormClustroid obj = new L2NormClustroid();
		
		Point A = new Point(1, 6, 0, 'A');
		Point B = new Point(3, 7, 1, 'B');
		Point C = new Point(4, 3, 2, 'C');
		Point D = new Point(7, 7,3, 'D');
		Point E = new Point(8, 2, 4, 'E');
		Point F = new Point(9, 5, 5, 'F');
		
		obj.pointList.add(A);
		obj.pointList.add(B);
		obj.pointList.add(C);
		obj.pointList.add(D);
		obj.pointList.add(E);
		obj.pointList.add(F);
		obj.distanceBetPoints = Utils.findSquareOfDistanceBetweenPoints(obj.pointList);
		
		//{a,b,c,d}, {b,c,d,e}, {c,d,e,f}, and {a,b,e,f}
		Cluster cluster1 = new Cluster(Arrays.asList(A, B, C, D));
		Cluster cluster2 = new Cluster(Arrays.asList(B, C, D, E));
		Cluster cluster3 = new Cluster(Arrays.asList(C, D, E, F));
		Cluster cluster4 = new Cluster(Arrays.asList(A, B, E, F));
		obj.clusterList.add(cluster1);
		obj.clusterList.add(cluster2);
		obj.clusterList.add(cluster3);
		obj.clusterList.add(cluster4);
		obj.calcCentroidForClusters();
	}
	
	public void calcCentroidForClusters(){
		for(Cluster c : clusterList){
			calcCentroidInCluster(c);
		}
	}
	
	public void calcCentroidInCluster(Cluster c){
		List<Point> points = (List<Point>) c.clusterPoints;
		float minCentroidDist = -1;
		Point minCentroidPoint = null;
		for(int i=0; i<points.size(); i++){
			float dist = 0;
			for(int j=0; j<points.size(); j++){
				if(i==j) continue;
				dist += distanceBetPoints[points.get(i).pointIndex][points.get(j).pointIndex];
			}
			
			System.out.println("Point " + points.get(i).pointName + " dist = " + dist);
			if(minCentroidDist == -1 || dist < minCentroidDist){
				minCentroidDist = dist;
				minCentroidPoint = points.get(i);
			}
		}
		
		System.out.println("Centroid for cluster " + c.printClusterPoints() + ": " + minCentroidPoint.pointName);
		System.out.println("********************************");
	}

}
