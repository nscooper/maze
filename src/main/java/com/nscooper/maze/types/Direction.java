package com.nscooper.maze.types;

public enum Direction {
	NORTH(-1, Orientation.ROW), 
	SOUTH(1, Orientation.ROW), 
	EAST(1, Orientation.COLUMN), 
	WEST(-1, Orientation.COLUMN);
	
	Direction(int cellMoveAmountIn, Orientation rowOrColumnIn) {
		cellMoveAmount = cellMoveAmountIn;
		rowOrColumn = rowOrColumnIn;
	}

	private int cellMoveAmount;
	private Orientation rowOrColumn;
	
	public final int getCellMoveAmount() {
		return cellMoveAmount;
	}	
	public final Orientation getOrientation() {
		return rowOrColumn;
	}
	
}
