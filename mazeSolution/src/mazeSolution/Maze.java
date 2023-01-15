package mazeSolution;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Maze
{
	private static final int MAX_VALUE = 10; // The maximum possible size of the Maze.
	private static final int MIN_VALUE = 2; // The maximum possible size of the Maze.
	private int width; // The width of the Maze.
	private int height; // The height of the Maze.
	private Position start; // The coordinates of the starting position. Top-left is [0,0].
	private Position goal; // The coordinates of the goal position. Top-left is [0,0].
	private ArrayList<String> blueprint; // The blueprint for the maze.
	
	public Maze() // Constructor for the Maze from the maze.txt file in the root directory.
	{
		File txt = new File("maze.txt");
		this.blueprint = readMazeFromFile(txt);
		if (isValid(blueprint) == true)
		{
			this.width = blueprint.get(0).length(); // The width of the Maze is the length of one line.
			this.height = blueprint.size(); // The height of the Maze is the number of lines.
			this.start = findBlock(blueprint, 'S'); // Sets the Start position.
			this.goal = findBlock(blueprint, 'G'); // Sets the Goal position.
		}
	}
	
	public Maze(File file) // Constructs a maze from a given file.
	{
		this.blueprint = readMazeFromFile(file);
		if (isValid(blueprint) == true)
		{
			this.width = blueprint.get(0).length(); // The width of the Maze is the length of one line.
			this.height = blueprint.size(); // The height of the Maze is the number of lines.
			this.start = findBlock(blueprint, 'S'); // Sets the Start position.
			this.goal = findBlock(blueprint, 'G'); // Sets the Goal position.
		}
	}
	
	public Maze(int seed) // Constructs a maze randomly from a given seed.
	{
		Random generator = new Random(seed);
		this.blueprint = new ArrayList<String>();
		this.width = generator.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE; // Randomizes the width.
		this.height = generator.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE; // Randomizes the height.
		this.start = randomBlock(width, height);
		this.goal = randomBlock(width, height);
		int walls = width * height / 4;
		char[] array = new char[width];
		do
		{
			// Generates an empty Maze.
			for (int i = 0; i < width; i++)
			{
				array[i] = '_';
				}
			for (int j = 0; j < height; j++)
			{
				this.blueprint.add(String.valueOf(array));
				}
			// Generates Walls for the Maze.
			while (walls > 0)
			{
				for (int j = 0; j < height; j++)
				{
					for (int i = 0; i < width; i++)
					{
						if (((int)(Math.random() * width * height) == 0) && (walls > 0) && (blueprint.get(j).charAt(i) != 'X'))
						{
							char[] temp = blueprint.get(j).toCharArray();
							temp[i] = 'X';
							blueprint.set(j, String.valueOf(temp));
							walls -= 1;
						}
					}
				}
			}
			// Sets the Start position.
			boolean done = false;
			while (done == false)
			{
				for (int j = 0; j < height; j++)
				{
					for (int i = 0; i < width; i++)
					{
						if (((int)(Math.random() * width * height) == 0) && (done == false) && (blueprint.get(j).charAt(i) != 'X'))
						{
							char[] temp = blueprint.get(j).toCharArray();
							temp[i] = 'S';
							blueprint.set(j, String.valueOf(temp));
							this.start = new Position(i, j);
							done = true;
							break;
							}
						}
					}
				}
			// Sets the Goal position.
			done = false;
			while (done == false)
			{
				for (int j = 0; j < height; j++)
				{
					for (int i = 0; i < width; i++)
					{
						if (((int)(Math.random() * width * height) == 0) && (done == false) && (blueprint.get(j).charAt(i) != 'X') && (blueprint.get(j).charAt(i) != 'S'))
						{
							char[] temp = blueprint.get(j).toCharArray();
							temp[i] = 'G';
							blueprint.set(j, String.valueOf(temp));
							this.goal = new Position(i, j);
							done = true;
							break;
						}
					}
				}
			}
		}
		while (isValid(blueprint) == false);
	}
	
	public void printStats()
	{
		System.out.printf("Width: %d%n", width);
		System.out.printf("Height: %d%n", height);
		System.out.printf("Start: [%d, %d]%n", start.getX(), start.getY());
		System.out.printf("Goal: [%d, %d]%n", goal.getX(), goal.getY());
		for (int j = 0; j < height; j++)
		{
			System.out.println(blueprint.get(j));
		}
	}
	
	public Position randomBlock(int width, int height)
	{
		Position position = new Position((int)(Math.random() * width), (int)(Math.random() * height));
		return (position);
	}
	
	public String getBlock(int x, int y) // Returns the type of block at a given position.
	{
		if ((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height)) // Check if coordinates are out of bounds.
		{
			return "WALL";
		}
		else
		{
			switch (blueprint.get(y).charAt(x))
			{
				default:
					return "CLEAR";
				case 'X':
					return "WALL";
				case 'S':
					return "START";
				case 'G':
					return "GOAL";
			}
		}
	}
	
	public int getWidth() // Returns the width of the Maze.
	{
		return (width);
	}
	
	public int getHeight() // Returns the height of the Maze.
	{
		return (height);
	}
	
	public int getStartX() // Returns the X coordinate of the Start position.
	{
		return (start.getX());
	}
	
	public int getStartY() // Returns the Y coordinate of the Start position.
	{
		return (start.getY());
	}
	
	public int getGoalX() // Returns the X coordinate of the Goal position.
	{
		return (goal.getX());
	}
	
	public int getGoalY() // Returns the Y coordinate of the Goal position.
	{
		return (goal.getY());
	}
	
	private ArrayList<String> readMazeFromFile(File file) // Reads the maze.txt file and generates the Blueprint for the Maze.
	{
		ArrayList<String> draft = new ArrayList<String>();
		try
		{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine() == true)
			{
				draft.add(scanner.nextLine());
			}
			scanner.close();
		}
		catch (Exception exception)
		{
			System.out.println("An error occurred.");
			exception.printStackTrace();
		}
		return (draft);
	}
	
	private boolean isValid(ArrayList<String> draft) // Returns TRUE if the Maze is valid.
	{
		int length;
		int counter;
		// Checks if height is at least 2.
		if (draft.size() < 2)
		{
			System.out.println("Error: Invalid size.");
			return (false);
		}
		// Checks if width is at least 2.
		length = draft.get(0).length();
		if (length < 2)
		{
			System.out.println("Error: Invalid size.");
			return (false);
		}
		// Checks if width is the same for every line.
		for (int i = 1; i < draft.size(); i++)
		{
			if (length != draft.get(i).length())
			{
				System.out.println("Error: Maze is not a rectangle.");
				return (false);
			}
		}
		// Checks if there is one and only one Start position.
		counter = 0;
		for (int j = 0; j < draft.size(); j++)
		{ 
			if (draft.get(j).indexOf('S') != -1)
			{
				counter++;
			}
		}
		if (counter != 1)
		{
			System.out.println("Error: Maze should have one and only one starting point.");
			return (false);
		}
		// Checks if there is one and only one Goal position.
		counter = 0;
		for (int j = 0; j < draft.size(); j++)
		{ 
			if (draft.get(j).indexOf('G') != -1)
			{
				counter++;
			}
		}
		if (counter != 1)
		{
			System.out.println("Error: Maze should have one and only one ending point.");
			return (false);
		}
		return (true);
	}
	
	public boolean isSolvable() // Returns TRUE if the Maze is solvable.
	{
		ArrayList<String> testDraft = new ArrayList<String>(blueprint); // Copy the blueprint, so it doesn't get destroyed.
		Position begin = findBlock(testDraft, 'S'); // Finds the Start position.
		mazeDestroyer(testDraft, begin); // Destroys the copy of the blueprint by flooding it.
		Position check = findBlock(testDraft, 'G');
		if ((check.getX() == -1) && (check.getY() == -1)) // Checks if the Goal position survived the flood.
		{
			return (true); // Both Start and Goal have been flooded, therefore the maze is solvable.
		}
		else
		{
			return (false); // The Goal survived, therefore the maze is NOT solvable.
		}
	}
	
	private void mazeDestroyer(ArrayList<String> draft, Position blast) // Floods a maze draft, destroying it in the process.
	{
		// This is a recursive method that floods the entire draft of a maze.
		// It is meant to be used to check if two points of the maze are accessible to each other.
		// If they are, then both points will be destroyed in the process.
		// If a point survives, it means it is unreachable from the first point.
		if (draft.get(blast.getY()).charAt(blast.getX()) != 'O')
		{
			char[] temp = draft.get(blast.getY()).toCharArray();
			temp[blast.getX()] = 'O';
			draft.set(blast.getY(), String.valueOf(temp));
			if (getBlock(blast.getX(), blast.getY() - 1) != "WALL")
			{
				Position targetNorth = new Position(blast.getX(), blast.getY() - 1);
				mazeDestroyer(draft, targetNorth);
			}
			if (getBlock(blast.getX(), blast.getY() + 1) != "WALL")
			{
				Position targetSouth = new Position(blast.getX(), blast.getY() + 1);
				mazeDestroyer(draft, targetSouth);
			}
			if (getBlock(blast.getX() - 1, blast.getY()) != "WALL")
			{
				Position targetWest = new Position(blast.getX() - 1, blast.getY());
				mazeDestroyer(draft, targetWest);
			}
			if (getBlock(blast.getX() + 1, blast.getY()) != "WALL")
			{
				Position targetEast = new Position(blast.getX() + 1, blast.getY());
				mazeDestroyer(draft, targetEast);
			}
		}
	}
	
	private Position findBlock(ArrayList<String> draft, char block) // Returns the first Position of a given Block. The search starts from the top-left.
	{
		Position answer = new Position(-1, -1);
		for (int j = 0; j < draft.size(); j++)
		{ 
			if (draft.get(j).indexOf(block) != -1)
			{
				answer = new Position(draft.get(j).indexOf(block), j);
				break;
			}
		}
		return (answer);
	}
}
