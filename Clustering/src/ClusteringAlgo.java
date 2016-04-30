import java.util.ArrayList;

//Perform a hierarchical clustering of the following six points:
//(0, 0) (10, 10) (21,21)
//(33,33) (5, 27) (28, 6)

//using the group-average proximity measure (the distance between two clusters is the average distance 
//	between points, one chosen from each cluster) and the min distance measure. Which of the following is a cluster at some stage of the 
//	agglomeration?
//	a) 	{A,B,C,D}
// 	b) 	{A,B,C}
// 	c) 	{C,D}
// 	d) 	{C,F}
// 
// 	
//Answer submitted:   d)
// 
//You have answered the question correctly.

//	the single-link proximity measure (the distance between clusters is the shortest distance between any pair of points, one from each cluster)
//	a) 	{C,D,F}
// 	b) 	{A,B,D,E}
// 	c) 	{A,B,C,D,E}
// 	d) 	{A,B,C,F}
// 
// 	
//Answer submitted:   d)
// 
//You have answered the question correctly.
	
public class ClusteringAlgo {
	
	ArrayList<Cluster> clustersList = new ArrayList<Cluster>();
	ArrayList<Point> pointList = new ArrayList<Point>();
	float[][] distanceBetPoints;// = new float[pointList.size()][];
	
	public static void main(String[] args) {
		
		
		ClusteringAlgo obj1 = createClusteringAlgoObj();
		System.out.println("****** MIN CLUSTERING ***********");
		while(obj1.clustersList.size() > 1) obj1.cluster("MIN");
		
		ClusteringAlgo obj2 = createClusteringAlgoObj();
		System.out.println("****** AVG CLUSTERING ***********");
		while(obj2.clustersList.size() > 1) obj2.cluster("AVG");
	}
	
	public static ClusteringAlgo createClusteringAlgoObj(){
		
		ClusteringAlgo obj = new ClusteringAlgo();
		
		Point A = new Point(0, 0, 0, 'A');
		Point B = new Point(10, 10, 1, 'B');
		Point C = new Point(21,21,2, 'C');
		Point D = new Point(33,33,3, 'D');
		Point E = new Point(5, 27, 4, 'E');
		Point F = new Point(28, 6, 5, 'F');
		
		obj.pointList.add(A);
		obj.pointList.add(B);
		obj.pointList.add(C);
		obj.pointList.add(D);
		obj.pointList.add(E);
		obj.pointList.add(F);
//		obj.distanceBetPoints = new float[obj.pointList.size()][obj.pointList.size()];
				
		obj.distanceBetPoints = Utils.findDistanceBetweenPoints(obj.pointList);
		
		obj.clustersList.add(new Cluster(A));
		obj.clustersList.add(new Cluster(B));
		obj.clustersList.add(new Cluster(C));
		obj.clustersList.add(new Cluster(D));
		obj.clustersList.add(new Cluster(E));
		obj.clustersList.add(new Cluster(F));
		
		return obj;
		
	}
	
	

	private void cluster(String clusteringAlgo) {
		
		float[][] distBetClusters = new float[clustersList.size()][clustersList.size()];
		int clusterIndex1 = -1, clusterIndex2 = -1;
		float minDist = -1;
		for(int i=0; i<clustersList.size(); i++){
			distBetClusters[i][i] = -1;
			for(int j=i+1; j<clustersList.size(); j++){
				float dist = getDistanceBetweenClusters(clusteringAlgo, clustersList.get(i), clustersList.get(j));
				distBetClusters[i][j] = dist;
				distBetClusters[j][i] = dist;
				if(minDist == -1){
					minDist = dist;
					clusterIndex1 = i;
					clusterIndex2 = j;
				} else if(dist < minDist){
					minDist = dist;
					clusterIndex1 = i;
					clusterIndex2 = j;
				}
			}
		}
		
		mergeClusters(clusterIndex1, clusterIndex2);
		
	}
	
	private float getDistanceBetweenClusters(String clusteringAlgo, Cluster c1, Cluster c2) {
		switch(clusteringAlgo){
			case "MIN":
				return getMinDistBetClusters(c1, c2);
				
		case "AVG":
				return getAvgDistBetClusters(c1, c2);
				
			default:
				return 0;
		}
	}



	public void mergeClusters(int clusterIndex1, int clusterIndex2){
		Cluster cluster1 = clustersList.get(clusterIndex1);
		Cluster cluster2 = clustersList.get(clusterIndex2);
		System.out.println("Clustering " + cluster1.printClusterPoints()
				+ " and " +  cluster2.printClusterPoints());
		cluster1.mergePoints((ArrayList<Point>) cluster2.clusterPoints);
		clustersList.remove(clusterIndex2);
		System.out.print("CurrentClusters =====> ");
		for(int i=0; i<clustersList.size(); i++){
			System.out.print(clustersList.get(i).printClusterPoints() + " , ");
		}
		System.out.println();
		System.out.println("*****************************************");
	}
	
	public float getAvgDistBetClusters(Cluster c1, Cluster c2){
		ArrayList<Point> c1Points =  (ArrayList<Point>) c1.clusterPoints;
		ArrayList<Point> c2Points = (ArrayList<Point>) c2.clusterPoints;
		
		float avgDist = 0;
		for(int i=0; i<c1Points.size(); i++){
			for(int j=0; j<c2Points.size(); j++){
				float dist = distanceBetPoints[c1Points.get(i).pointIndex][c2Points.get(j).pointIndex];
				avgDist += dist;
			}
		}
		return (avgDist/(c1Points.size()*c2Points.size()));
	}

	public float getMinDistBetClusters(Cluster c1, Cluster c2) {
		ArrayList<Point> c1Points =  (ArrayList<Point>) c1.clusterPoints;
		ArrayList<Point> c2Points = (ArrayList<Point>) c2.clusterPoints;
		float minDist = -1;
		for(int i=0; i<c1Points.size(); i++){
			for(int j=0; j<c2Points.size(); j++){
				float dist = distanceBetPoints[c1Points.get(i).pointIndex][c2Points.get(j).pointIndex];
				if(minDist == -1) minDist = dist;
				else if(dist < minDist) minDist = dist;
			}
		}
//		System.out.println();
//		System.out.println("*****************************************");
		return minDist;
	}

}