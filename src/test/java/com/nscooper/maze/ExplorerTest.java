package com.nscooper.maze;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nscooper.maze.capture.MazeFileLoader;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;
import com.nscooper.maze.types.Direction;

public class ExplorerTest extends MazeTestBase {

	@Before
	public void setUp() {
		try {
			myMaze = MazeFileLoader.loadMazeIntoTable(url.getPath());
			mazeExplorer = new Explorer();
		} catch (IOException | InvalidMazeConfigException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
		myMaze = null;
		mazeExplorer = null;
	}
	
	@Test
	public void getBadMovesTest() {
		MazeCell newCell =null;
		try {
			newCell = mazeExplorer.move(myMaze.getStartPoint(), myMaze);
		} catch (InvalidMazeConfigException e) {
			fail(e.getLocalizedMessage());
		}
		assertThat(newCell.getValue()).isEqualTo(" ");
		assertThat(mazeExplorer.getBadMoves()).hasSizeGreaterThan(0);
	}
	
	@Test
	public void getNewCellDetailsTest() {
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze).getValue()).isEqualTo("X");
	}
	
	@Test
	public void invalidCellTest() {
		try {
			mazeExplorer.move(myMaze.getStartPoint(), myMaze);
		} catch (InvalidMazeConfigException e) {
			fail(e.getLocalizedMessage());
		}
		assertThat(mazeExplorer.getBadMoves()).hasSizeGreaterThan(0);
		MazeCell invalidCell = mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze);
		assertThat(mazeExplorer.getBadMoves()).containsAnyOf(invalidCell);
	}
	
	@Test
	public void moveTest() {
		MazeCell newCell =null;
		try {
			newCell = mazeExplorer.move(myMaze.getStartPoint(), myMaze);
		} catch (InvalidMazeConfigException e) {
			fail(e.getLocalizedMessage());
		}
		assertThat(newCell.getValue()).isEqualTo(" ");
		assertThat(mazeExplorer.getMoves()).hasSizeGreaterThan(0);
	}

}
