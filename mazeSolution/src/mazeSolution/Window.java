package mazeSolution;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

import mazeSolution.Window;

public class Window extends JFrame implements ActionListener
{
	
	/**
	 * No idea what this is. IDE wanted me to add this.
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu newMaze = new JMenu("New Maze");
	private JMenuItem fromFile = new JMenuItem("From txt File");
	private JMenuItem fromRandom = new JMenuItem("From Random Generation");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenu helpMenu = new JMenu("Help");
	private JMenuItem about = new JMenuItem("About...");
	private JPanel panel = new JPanel();
	private JPanel control = new JPanel();
	private JButton solve = new JButton("Solve Maze");
	private JButton reset = new JButton("Reset Maze");
	Maze maze;
	Player player;
	
	public Window()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Maze Challenge");
		this.setSize(800, 600);
		buildMenu();
		buildPanel();
		buildControl();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == fromFile)
		{
			final JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();
				this.maze = new Maze(file);
				this.player = new Player(maze.getStartX(), maze.getStartY());
				solve.setEnabled(true);
			}
		}
		else if (event.getSource() == fromRandom)
		{
			JOptionPane.showMessageDialog(this, "This randomly generates a new maze.");
		}
		else if (event.getSource() == exit)
		{
			System.exit(0);
		}
		else if (event.getSource() == about)
		{
			JOptionPane.showMessageDialog(this, "Created by noxteryn, January 2023.");
		}
		else if (event.getSource() == solve)
		{
			JOptionPane.showMessageDialog(this, "This solves the maze.");
			new Solver(maze, player);
		}
		else if (event.getSource() == reset)
		{
			JOptionPane.showMessageDialog(this, "This resets the maze.");
		}
		
	}
	
	private void buildMenu()
	{
        menu.add(fileMenu);
        fileMenu.add(newMaze);
        newMaze.add(fromFile);
        fromFile.addActionListener(this);
        newMaze.add(fromRandom);
        fromRandom.addActionListener(this);
        fileMenu.add(exit);
        exit.addActionListener(this);
        menu.add(helpMenu);
        helpMenu.add(about);
        about.addActionListener(this);
        this.getContentPane().add(BorderLayout.NORTH, menu);
	}
	
	private void buildPanel()
	{
        this.getContentPane().add(BorderLayout.CENTER, panel);
	}
	
	private void buildControl()
	{
        control.add(solve);
        solve.setEnabled(false);
        solve.addActionListener(this);
        control.add(reset);
        reset.setEnabled(false);
        reset.addActionListener(this);
        this.getContentPane().add(BorderLayout.SOUTH, control);
	}
}
