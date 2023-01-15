package mazeSolution;

public class Main
{
	public static void main(String[] args)
	{
		//Window window = new Window();
		//Solver solver = new Solver();
		Maze maze = new Maze();
		Player player = new Player(maze.getStartX(), maze.getStartY());
		maze.printStats();
		if (maze.isSolvable() == true)
		{
			solveRandom(maze, player);
			System.out.println("The Goal has been reached! The path of Player was:");
			player.printPath();
		}
		else
		{
			System.out.println("Error: Maze is not solvable.");
		}
	}
	
	private static void solveRandom(Maze maze, Player player) // The Player moves randomly until it finds the Goal.
	{
		while (maze.getBlock(player.getX(), player.getY()) != "GOAL")
		{
			switch ((int)(Math.random() * 4))
			{
				case 0:
					move(maze, player, "north");
					break;
				case 1:
					move(maze, player, "west");
					break;
				case 2:
					move(maze, player, "south");
					break;
				case 3:
					move(maze, player, "east");
					break;
			}
		}
	}
	
	private static void move(Maze maze, Player player, String direction) // Checks the block in the given direction and moves the player to it if it's empty.
	{
		switch (direction.toLowerCase())
		{
			default:
				System.out.println("Error: Invalid move for player.");
				break;
			case "north":
			case "up":
				if (maze.getBlock(player.getX(), player.getY() - 1) != "WALL")
				{
					player.move("north");
				}
				break;
			case "south":
			case "down":
				if (maze.getBlock(player.getX(), player.getY() + 1) != "WALL")
				{
					player.move("south");
				}
				break;
			case "west":
			case "left":
				if (maze.getBlock(player.getX() - 1, player.getY()) != "WALL")
				{
					player.move("west");
				}
				break;
			case "east":
			case "right":
				if (maze.getBlock(player.getX() + 1, player.getY()) != "WALL")
				{
					player.move("east");
				}
				break;
		}
	}
}
