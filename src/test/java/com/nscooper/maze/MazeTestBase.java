package com.nscooper.maze;

import java.net.URL;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.nscooper.maze.Explorer;
import com.nscooper.maze.Maze;

public class MazeTestBase {
	
	protected URL url = Thread.currentThread().getClass().getResource("/Maze1.txt");
	
	protected static Maze myMaze = null;
	protected static Explorer mazeExplorer = new Explorer();
	
	protected Deque<MazeCell> moves = new LinkedList<>();
	protected Set<MazeCell> badMoves = new HashSet<>();

}
