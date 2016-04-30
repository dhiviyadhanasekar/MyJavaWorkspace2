import java.util.ArrayList;
import java.util.List;


//We can cluster in one dimension as well as in many dimensions. In this problem, we are going to cluster numbers on the real line. The particular numbers (data points) are 1, 4, 9, 16, 25, 36, 49, 64, 81, and 100, i.e., the squares of 1 through 10. We shall use a k-means algorithm, with two clusters. You can verify easily that no matter which two points we choose as the initial centroids, some prefix of the sequence of squares will go into the cluster of the smaller and the remaining suffix goes into the other cluster. As a result, there are only nine different clusterings that can be achieved, ranging from {1}{4,9,...,100} through {1,4,...,81}{100}.
//We then go through a reclustering phase, where the centroids of the two clusters are recalculated and all points are reassigned to the nearer of the two new centroids. For each of the nine possible clusterings, calculate how many points are reclassified during the reclustering phase. Identify in the list below the pair of initial centroids that results in exactly one point being reclassified.
//
// 
// 	a) 	1 and 16
// 	b) 	4 and 25
// 	c) 	9 and 64
// 	d) 	9 and 49
// 
// 	
//Answer submitted:   d)
// 
//You have answered the question correctly.

public class KMeans {

	ArrayList<Point> pointList = new ArrayList<Point>();
	float[][] distanceBetPoints;// = new float[pointList.size()][];
	
	public static void main(String[] args) {
		// 1, 4, 9, 16, 25, 36, 49, 64, 81, and 100,
		int pointIndex =0;
		Point A = new Point(1, 0, pointIndex++, 'A');
		Point B = new Point(4, 0, pointIndex++, 'B');
		Point C = new Point(9, 0, pointIndex++, 'C');
		Point D = new Point(16, 0, pointIndex++, 'D');
		Point E = new Point(25, 0, pointIndex++, 'E');
		Point F = new Point(36, 0, pointIndex++, 'F');
		Point G = new Point(49, 0, pointIndex++, 'G');
		Point H = new Point(64, 0, pointIndex++, 'H');
		Point I = new Point(81, 0, pointIndex++, 'I');
		Point J = new Point(100, 0, pointIndex++, 'J');
		
		KMeans obj = new KMeans();
		obj.pointList.add(A);
		obj.pointList.add(B);
		obj.pointList.add(C);
		obj.pointList.add(D);
		obj.pointList.add(E);
		obj.pointList.add(F);
		obj.pointList.add(G);
		obj.pointList.add(H);
		obj.pointList.add(I);
		obj.pointList.add(J);
		obj.distanceBetPoints = Utils.findDistanceBetweenPoints(obj.pointList);
		
		obj.getAllPosibleClusters();
	}
	
	//ans: 36 and 100
	public void getAllPosibleClusters(){
		for(int i=0; i<pointList.size(); i++){
			for(int j=i+1; j<pointList.size(); j++){
				Cluster c1 = new Cluster();
				Cluster c2 = new Cluster();
				System.out.println("Initial points: " + pointList.get(i).x + " and " + pointList.get(j).x  );
				initClusters(c1, c2, pointList.get(i).x, pointList.get(j).x);
				reCluster(c1, c2);
				System.out.println("********************************");
			}
		}
	}
	
	public void reCluster(Cluster c1, Cluster c2){
		float centroid1 = calcCentroidInCluster(c1);
		float centroid2 = calcCentroidInCluster(c2);
		Cluster newC1 = new Cluster();
		Cluster newC2 = new Cluster();
		initClusters(newC1, newC2, centroid1, centroid2);
		if(Math.abs(c1.clusterPoints.size() - newC1.clusterPoints.size()) == 1) 
			System.out.println("This is a candidate answer");
						
	}
	
//	public void calcCentroidForClusters(){
//		for(Cluster c : clusterList){
//			
//		}
//	}
	
	public float calcCentroidInCluster(Cluster c){
		List<Point> points = (List<Point>) c.clusterPoints;
		float centroid = 0;
		for(int i=0; i<points.size(); i++){
			centroid += points.get(i).x;
		}
		centroid /= points.size();
		
		System.out.println("Centroid for cluster " + c.printClusterPointsByXVal() + ": " + centroid);
		return centroid;
	}

	
	public void initClusters(Cluster c1, Cluster c2, float centroid1, float centroid2){
		
//		int choosenPt1Index = c1.clusterPoints.get(0).pointIndex;
//		int choosenPt2Index = c2.clusterPoints.get(0).pointIndex;
		
		for(int i=0; i<pointList.size(); i++){
			
//			if(i == choosenPt1Index || i == choosenPt2Index) continue;
			float dist1 = Math.abs(pointList.get(i).x- centroid1);//distanceBetPoints[i][choosenPt1Index];
			float dist2 = Math.abs(pointList.get(i).x- centroid2);
			if(dist1 <= dist2) c1.addPoint(pointList.get(i));
			else c2.addPoint(pointList.get(i));
			
		}
		
		System.out.println("Initial clusters" + c1.printClusterPointsByXVal() + " & " + c2.printClusterPointsByXVal() );
	}

}
