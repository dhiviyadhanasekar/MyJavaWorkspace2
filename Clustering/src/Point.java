
public class Point {
	int x, y;
	int pointIndex;
	char pointName;
	public Point(int x, int y, int pointIndex, char pointName){
		this.x = x;
		this.y = y;
		this.pointIndex = pointIndex;
		this.pointName = pointName;
		ProjConstants.pointToNameMap.put(pointIndex, pointName);
	}
}
