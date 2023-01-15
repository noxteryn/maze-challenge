package mazeSolution;

public class Position
{
	private int x;
	private int y;
	
	public Position(int x, int y) // Constructor for a given Position.
	{
		this.x = x;
		this.y = y;
	}
	
	public String positionToString() // Returns the coordinates in String form. 
	{
		return ("[" + String.valueOf(this.x) + ", " + String.valueOf(this.y) + "]"); // [x, y]
	}
	
	public int getX() // Returns the X coordinate of the Position.
	{
		return (this.x);
	}
	
	public int getY() // Returns the Y coordinate of the Position.
	{
		return (this.y);
	}
	
	public void plusX() // Increases the X coordinate by 1.
	{
		this.x += 1;
	}
	
	public void minusX() // Decreases the X coordinate by 1.
	{
		this.x -= 1;
	}
	
	public void plusY() // Increases the Y coordinate by 1.
	{
		this.y += 1;
	}
	
	public void minusY() // Decreases the Y coordinate by 1.
	{
		this.y -= 1;
	}
}
