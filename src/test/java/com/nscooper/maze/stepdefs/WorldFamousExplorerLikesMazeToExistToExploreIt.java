package com.nscooper.maze.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Assert;

import com.nscooper.maze.MazeTestBase;
import com.nscooper.maze.capture.MazeFileLoader;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WorldFamousExplorerLikesMazeToExistToExploreIt extends MazeTestBase{
	
	@Given("A Maze")
	public void a_Maze() {
		myMaze=null;
		assertThat(myMaze).isNull();
	}

	@When("a Maze is populated by a new MazeFile")
	public void a_Maze_is_populated_by_a_new_MazeFile() {
		
		try {
			myMaze = MazeFileLoader.loadMazeIntoTable(url.getPath());
			assertThat(new Integer(myMaze.getMaxCol())).isGreaterThan(0);
			assertThat(new Integer(myMaze.getMaxRow())).isGreaterThan(0);
		} catch (IOException | InvalidMazeConfigException e) {
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Then("the Maze should have walls of {string}")
	public void the_Maze_should_have_walls_of(String wallCharacter) {
		assertThat(myMaze.getCellValue(0,0)).isEqualTo(wallCharacter);
	}

	@Then("empty spaces of {string}")
	public void empty_spaces_of(String emptySpaceCharacter) {
		assertThat(myMaze.getCellValue(1,3)).isEqualTo(emptySpaceCharacter);
	}
	
	@Then("one {int} start point of {string}")
	public void one_start_point_of(Integer numberOfStartPoints, String startCharacter) {
		assertThat(myMaze.getNrOfStartPoints()).isEqualTo(numberOfStartPoints);
		assertThat(myMaze.getStartPoint().getValue()).isEqualTo(startCharacter);
	}

	@Then("only {int} Finish Point of {string}")
	public void only_Finish_Point_of(Integer numberOfFinishPoints, String finishCharacter) {
		assertThat(myMaze.getNrOfEndPoints()).isEqualTo(numberOfFinishPoints);
		assertThat(myMaze.getEndPoint().getValue()).isEqualTo(finishCharacter);
	}

}
