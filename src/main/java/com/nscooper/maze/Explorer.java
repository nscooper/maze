package com.nscooper.maze;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nscooper.maze.exceptions.InvalidMazeConfigException;
import com.nscooper.maze.types.Direction;
import com.nscooper.maze.types.Orientation;

public final class Explorer {
	
	private static final Logger log = LoggerFactory.getLogger(Explorer.class);

	private Deque<MazeCell> moves = new LinkedList<>();
	private Set<MazeCell> badMoves = new HashSet<>();
	
	/**
	 * Beginning at the starting position, we explore the maze in search of the
	 * declared ending position
	 * 
	 * @param maze
	 * @throws InvalidMazeConfigException 
	 */
	public List<String> exploreMaze(final Maze maze) throws InvalidMazeConfigException {

		MazeCell currentPosition = maze.getStartPoint();
		moves.push(currentPosition);
		
		log.info("Commencing exploration.");
		// keep moving around the maze until we reach the finish point
		while(!currentPosition.equals( maze.getEndPoint() )) {
			currentPosition = move( moves.peek(), maze );
		}
		log.info("Completed exploration.");

		List<String> listOfMoves = new LinkedList<>();
		// increment each coordinate to be reported, as origins start at zero
		moves.descendingIterator().forEachRemaining(
				m -> listOfMoves.add( m.getRowKey().intValue()+1 + "," + (m.getColumnKey().intValue()+1) ) );
		return listOfMoves;
	}
	
	/**
	 * Here, we try to move in all four directions of the compass, adjusting our
	 * coordinates accordingly and backtracking where we discover a dead-end
	 * @param currentCell
	 * @param maze
	 * @return
	 * @throws InvalidMazeConfigException 
	 */
	public final MazeCell move(final MazeCell currentCell, final Maze maze) throws InvalidMazeConfigException {
		if (currentCell==null) {
			throw new InvalidMazeConfigException("Either no Finish point exists or it is unreachable from the Start point");
		}
		MazeCell newCell = null;

		// if North by 1 is present,open and valid or have we already been here?
		newCell = getNewCellDetails(currentCell, Direction.NORTH, maze);
		if ( newCell.getRowKey().intValue()<0 || invalidCell(newCell,maze)) {
			badMoves.add(newCell);  //cannot move NORTH
		}else{
			moves.push(newCell);
			return newCell;
		}
		
		// if South by 1 is present,open and valid or have we already been here?
		newCell = getNewCellDetails(currentCell, Direction.SOUTH, maze);
		if ( newCell.getRowKey().intValue()>maze.getMaxRow() || invalidCell(newCell,maze)) {
			badMoves.add(newCell);  //cannot move SOUTH
		}else{
			moves.push(newCell);
			return newCell;
		}		
		
		// if West by 1 is present,open and valid or have we already been here?
		newCell = getNewCellDetails(currentCell, Direction.WEST, maze);
		if ( newCell.getColumnKey().intValue()<0 || invalidCell(newCell,maze)) {
			badMoves.add(newCell);  //cannot move WEST
		}else{
			moves.push(newCell);
			return newCell;
		}		

		// if East by 1 is present,open and valid or have we already been here?
		newCell = getNewCellDetails(currentCell, Direction.EAST, maze);
		if ( newCell.getColumnKey().intValue()>maze.getMaxCol() || invalidCell(newCell,maze)) {
			badMoves.add(newCell);  //cannot move EAST
		}else{	
			moves.push(newCell);
			return newCell;
		}			
		
		/*
		 * If we've arrived here, it means we can't go in any NEW direction and we need
		 * to backtrack one move and try again via recursive call.  So, we pop the last
		 * successful entry off the moved stack (this stores valid moves since the
		 * starting position) and add that cell to the badMoves collection, so that we
		 * don't try to store it as a valid move instead.
		 */
		badMoves.add(moves.pop());
		return move(moves.peek(), maze);
	}

	public final MazeCell getNewCellDetails(final MazeCell currentCell,
			final Direction direction, final Maze maze) {
		MazeCell newCell = currentCell.copy();
		
		if (direction.getOrientation().equals(Orientation.ROW)) {
			newCell.setRowKey(newCell.getRowKey().intValue() + direction.getCellMoveAmount());
		} else {
			newCell.setColumnKey(newCell.getColumnKey().intValue() + direction.getCellMoveAmount());
		}

		newCell.setValue(maze.getCellValue(newCell.getRowKey(),newCell.getColumnKey()));
		return newCell;
	}
	
	/**
	 * return true if been found to be bad cell before or has an X in the cell or
	 * was the previous valid move left on the stack
	 * 
	 * @param cell
	 * @param maze
	 * @return
	 */
	private boolean invalidCell(MazeCell cell, Maze maze) {
		return (badMoves.contains(cell)||cell.getValue().equalsIgnoreCase("X")||moves.contains(cell)) ;
	}

	public Deque<MazeCell> getMoves() {
		return moves;
	}

	public Set<MazeCell> getBadMoves() {
		return badMoves;
	}

}
