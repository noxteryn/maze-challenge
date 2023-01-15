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
	
	JMenuBar menu = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenu newMaze = new JMenu("New Maze");
	JMenuItem fromFile = new JMenuItem("From txt File");
	JMenuItem fromRandom = new JMenuItem("From Random Generation");
	JMenuItem exit = new JMenuItem("Exit");
	JMenu helpMenu = new JMenu("Help");
	JMenuItem about = new JMenuItem("About...");
	JPanel panel = new JPanel();
	JPanel control = new JPanel();
	JButton solve = new JButton("Solve Maze");
	JButton reset = new JButton("Reset Maze");
	
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
			JOptionPane.showMessageDialog(this, "This opens a maze from a txt file.");
			final JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();
				Maze maze = new Maze(file);
				Player player = new Player(maze.getStartX(), maze.getStartY());
				maze.printStats();
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
        solve.addActionListener(this);
        control.add(reset);
        reset.addActionListener(this);
        this.getContentPane().add(BorderLayout.SOUTH, control);
	}
}
