package com.nscooper.maze.capture;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nscooper.maze.MazeTestBase;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;

public class MazeFileLoaderTest extends MazeTestBase {

	@Before
	public void setUp() throws Exception {
		try {
			myMaze = MazeFileLoader.loadMazeIntoTable(url.getPath());
		} catch (Exception | InvalidMazeConfigException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
		myMaze = null;
	}
	
	@Test
	public void testMazeLoading() {
		assertNotNull("No maze returned", myMaze);
	}

}
