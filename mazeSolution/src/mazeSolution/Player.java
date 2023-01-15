package mazeSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Player
{
	private Position position;
	private List<int[]> path;

	public Player(int x, int y) // Constructor for the Player.
	{
		position = new Position(x, y);
		path = new ArrayList<int[]>();
		path.add(IntStream.of(position.getX(), position.getY()).toArray());
	}
	
	public int getX() // Returns the X coordinate of the Player's position.
	{
		return (position.getX());
	}
	
	public int getY() // Returns the Y coordinate of the Player's position.
	{
		return (position.getY());
	}
	
	public Position getPosition() // Returns the Position of the Player.
	{
		return (position);
	}
	
	public List<int[]> getPath() // Returns the path of blocks the Player has been through.
	{
		return path;
	}
	
	public void printPath() // Prints the path of the Player from the Start position to its current position.
	{
		System.out.println(Arrays.deepToString(path.toArray()));
	}
	
	public void move(String direction) // Moves the player in a given direction. Does NOT check if the move is possible.
	{
		switch (direction.toLowerCase())
		{
			default:
				System.out.println("Error: Invalid move for player.");
				break;
			case "north":
			case "up":
				position.minusY();
				path.add(IntStream.of(position.getX(), position.getY()).toArray());
				break;
			case "south":
			case "down":
				position.plusY();
				path.add(IntStream.of(position.getX(), position.getY()).toArray());
				break;
			case "west":
			case "left":
				position.minusX();
				path.add(IntStream.of(position.getX(), position.getY()).toArray());
				break;
			case "east":
			case "right":
				position.plusX();
				path.add(IntStream.of(position.getX(), position.getY()).toArray());
				break;
		}
	}
}
