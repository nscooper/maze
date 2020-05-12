package com.nscooper.maze.types;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void testEnsureDirectionsArePresent() {
		Direction[] directions = new Direction[] {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};	
		assertThat(Direction.values()).containsOnly(directions);
	}
	
	@Test
	public void testOrientations() {
		assertThat(Direction.NORTH.getOrientation()).isEqualTo(Orientation.ROW);
		assertThat(Direction.SOUTH.getOrientation()).isEqualTo(Orientation.ROW);
		assertThat(Direction.EAST.getOrientation()).isEqualTo(Orientation.COLUMN);
		assertThat(Direction.WEST.getOrientation()).isEqualTo(Orientation.COLUMN);
	}

	@Test
	public void testCellMoveAmounts() {
		assertThat(Direction.NORTH.getCellMoveAmount()).isEqualTo(-1);
		assertThat(Direction.SOUTH.getCellMoveAmount()).isEqualTo(1);
		assertThat(Direction.EAST.getCellMoveAmount()).isEqualTo(1);
		assertThat(Direction.WEST.getCellMoveAmount()).isEqualTo(-1);
	}
}
