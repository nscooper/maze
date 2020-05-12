package com.nscooper.maze.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.nscooper.maze.Explorer;
import com.nscooper.maze.MazeTestBase;
import com.nscooper.maze.capture.MazeFileLoader;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;
import com.nscooper.maze.types.Direction;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class WorldFamousExplorerLikesMazeToExistToNavigateIt extends MazeTestBase {

	@Given("Given a maze the explorer should be able to drop in to the Start point \\(facing north)")
	public void given_a_maze_the_explorer_should_be_able_to_drop_in_to_the_Start_point_facing_north() {
		try {
			myMaze = MazeFileLoader.loadMazeIntoTable(url.getPath());
			assertThat(new Integer(myMaze.getMaxCol())).isGreaterThan(0);
			assertThat(new Integer(myMaze.getMaxRow())).isGreaterThan(0);
			mazeExplorer = new Explorer();
		} catch (IOException | InvalidMazeConfigException e) {
			Assert.fail(e.getLocalizedMessage());
		}

		moves.push(myMaze.getStartPoint());
		assertThat(myMaze.getStartPoint()).isEqualTo(moves.peek());
	}

	@Then("An explorer on a maze must be able to move forward")
	public void an_explorer_on_a_maze_must_be_able_to_move_forward() {
		moves.clear();  //reset maze
		moves.push(myMaze.getStartPoint()); //set to start point
		try {
			assertThat(mazeExplorer.move(moves.peek(), myMaze)).isNotEqualTo(myMaze.getStartPoint());  // check new cell differs from original one
		} catch (InvalidMazeConfigException e) {
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Then("An explorer on a maze must be able to turn left and right \\(changing direction the explorer is facing)")
	public void an_explorer_on_a_maze_must_be_able_to_turn_left_and_right_changing_direction_the_explorer_is_facing() {
		assertThat(mazeExplorer.getNewCellDetails(moves.peek(), Direction.NORTH, myMaze)).isNotEqualTo(moves.peek());
		assertThat(mazeExplorer.getNewCellDetails(moves.peek(), Direction.SOUTH, myMaze)).isNotEqualTo(moves.peek());
		assertThat(mazeExplorer.getNewCellDetails(moves.peek(), Direction.EAST, myMaze)).isNotEqualTo(moves.peek());
		assertThat(mazeExplorer.getNewCellDetails(moves.peek(), Direction.WEST, myMaze)).isNotEqualTo(moves.peek());
	}

	@Then("An explorer on a maze must be able to declare what is in front of them")
	public void an_explorer_on_a_maze_must_be_able_to_declare_what_is_in_front_of_them() {
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze).getValue()).isEqualTo("X");
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze).getValue()).isEqualTo("X");
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.EAST, myMaze).getValue()).isEqualTo(" ");
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze).getValue()).isEqualTo("X");
	}

	@Then("An explorer on a maze must be able to declare all movement options from their given location")
	public void an_explorer_on_a_maze_must_be_able_to_declare_all_movement_options_from_their_given_location() {
		List<String> validCellValuesAfterStart = new ArrayList<String>() { 
			private static final long serialVersionUID = 1L;
			{ 
                add(" "); 
                add("X"); 
                add("F"); 
            } 
        }; 
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze).getValue()).isIn(validCellValuesAfterStart);
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze).getValue()).isIn(validCellValuesAfterStart);
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.EAST, myMaze).getValue()).isIn(validCellValuesAfterStart);
		assertThat(mazeExplorer.getNewCellDetails(myMaze.getStartPoint(), Direction.NORTH, myMaze).getValue()).isIn(validCellValuesAfterStart);
	}

	@Then("An explorer on a maze must be able to report a record of where they have been in an understandable fashion")
	public void an_explorer_on_a_maze_must_be_able_to_report_a_record_of_where_they_have_been_in_an_understandable_fashion() {
		List<String> coordinates = null;
		try {
			coordinates = new Explorer().exploreMaze(MazeFileLoader.loadMazeIntoTable(url.getPath()));
			assertThat(coordinates.size()).isGreaterThan(0);
			assertThat(coordinates.toString()).isEqualTo(
					"[4,4, 4,5, 4,6, 4,7, 4,8, 4,9, 4,10, 4,11, 4,12, 5,12, 6,12, 7,12, 7,11, 7,10, 7,9, 7,8, 7,7, 8,7, 9,7, 10,7, 10,6, 10,5, 10,4, 11,4, 12,4, 13,4, 13,5, 13,6, 13,7, 13,8, 13,9, 13,10, 13,11, 13,12, 14,12, 14,13, 14,14, 13,14, 12,14, 11,14, 10,14, 9,14, 8,14, 7,14, 6,14, 5,14, 4,14, 3,14, 2,14, 2,13, 2,12, 2,11, 2,10, 2,9, 2,8, 2,7, 2,6, 2,5, 2,4, 2,3, 2,2, 3,2, 4,2, 5,2, 6,2, 7,2, 8,2, 9,2, 10,2, 11,2, 12,2, 13,2, 14,2, 15,2]");
			assertThat(coordinates.get(0)).isEqualTo("4,4");
		}catch (InvalidMazeConfigException | IOException e) {
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
