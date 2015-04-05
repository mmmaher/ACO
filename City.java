
public class City {

	int id;
	double x,y;
	
	public City(int id, double x, double y) {
		
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	
	public int getID() { return this.id; }
	public double getX() { return this.x; }
	public double getY() { return this.y; }

	public void printCity() { System.out.println("City: " + this.id + " at: (" + this.x + ", " + this.y + ")"); }
}
