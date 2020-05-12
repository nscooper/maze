package com.nscooper.maze;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nscooper.maze.capture.MazeFileLoader;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;

public class MazeTest extends MazeTestBase {

	@Before
	public void setUp() {
		try {
			myMaze = MazeFileLoader.loadMazeIntoTable(url.getPath());
		} catch (IOException | InvalidMazeConfigException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
		myMaze = null;
	}
	
	@Test
	public void testMazeConfig() {
		assertTrue("No walls found in maze",myMaze.getNrOfWalls()>0);
		assertTrue("No spaces found in maze",myMaze.getNrOfEmptySpaces()>0);
		assertTrue("Wrong number of start points",myMaze.getNrOfStartPoints()==1);
		assertTrue("Wrong number of end points",myMaze.getNrOfEndPoints()==1);
	}
	
	@Test
	public void testMazeContent() {
		assertTrue("Cell should contain an S",myMaze.getCellValue(3, 3).equalsIgnoreCase("S"));
	}
	
	@Test
	public void testMazeHasStartingPosition() {
		try {
			assertNotNull("No starting position detected", myMaze.getStartPoint());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testMazeHasEndPosition() {
		try {
			assertNotNull("No end position detected", myMaze.getEndPoint());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testMazeKnowsItsRowLimits() {
		try {
			assertTrue("Maze does not know its max row size", myMaze.getMaxRow() > 0);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testMazeKnowsItsColumnLimits() {
		try {
			assertTrue("Maze does not know its max column size", myMaze.getMaxCol() > 0);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

}
