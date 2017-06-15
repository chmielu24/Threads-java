
public class Point {
	private float x;
	private float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}	

	public float Lenght()
	{
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	
	public static float Distance(Point a, Point b)
	{
		return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	
	public static Point NormalizedVector(Point a, Point b)
	{
		Point newP = new Point(a.x - b.x, a.y - b.y);
		float lenght = Distance(a,b);
		newP.x /= lenght;
		newP.y /= lenght;

		return newP;
	}
}
