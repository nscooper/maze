package com.nscooper.maze.types;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class OrientationTest {

	@Test
	public void testEnsureOrientationsArePresent() {
		
		Orientation[] orientations = new Orientation[] {Orientation.ROW, Orientation.COLUMN};
		assertThat(Orientation.values()).containsOnly(orientations);
	}

}
