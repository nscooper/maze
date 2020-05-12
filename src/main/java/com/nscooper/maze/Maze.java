package com.nscooper.maze;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;

public final class Maze {

	private static final String START = "S";
	private static final String END = "F";
	private static final String WALL = "X";
	private static final String EMPTY_SPACE = " ";

	private Table<Integer, Integer, String> maze;
	private MazeCell startPoint = null;
	private int nrOfStartPoints = 0;
	private MazeCell endPoint = null;
	private int nrOfEndPoints = 0;
	private int nrOfWalls = 0;
	private int nrOfEmptySpaces = 0;
	private int maxCol = 0;
	private int maxRow = 0;

	public Maze() {
		super();
		maze = HashBasedTable.create();
	}

	/**
	 * Store cell into maze table and check if it's either the start or finish,
	 * noting coordinates when either discovered.
	 * 
	 * @param row
	 * @param column
	 * @param value
	 * @return
	 * @throws InvalidMazeConfigException 
	 */
	public String put(int row, int column, String value) throws InvalidMazeConfigException {
		if (value.equalsIgnoreCase(START)) {
			startPoint = new MazeCell(row, column, value + "");
			nrOfStartPoints++;
			if(nrOfStartPoints>1) {
				throw new InvalidMazeConfigException("Only permitted ONE starting position");
			}
		} else if (value.equalsIgnoreCase(END)) {
			endPoint = new MazeCell(row, column, value + "");
			nrOfEndPoints++;
			if(nrOfEndPoints>1) {
				throw new InvalidMazeConfigException("Only permitted ONE finishing position");
			}
		} else if (value.equalsIgnoreCase(WALL)) {
			nrOfWalls++;
		} else if (value.equalsIgnoreCase(EMPTY_SPACE)) {
			nrOfEmptySpaces++;
		}
		if(getMaxRow()<row){
			setMaxRow(row);
		}
		if(getMaxCol()<column){
			setMaxCol(column);
		}
		return maze.put(row, column, value + "");
	}
	
	/**
	 * Return value sitting in cell referenced by row and column coordinates
	 * @param row
	 * @param col
	 * @return
	 */
	public String getCellValue(Integer row, Integer col) {
		return maze.get(row, col);
	}
	
	public Table<Integer, Integer, String> getMaze() {
		return maze;
	}

	public void setMaze(Table<Integer, Integer, String> maze) {
		this.maze = maze;
	}

	public MazeCell getStartPoint() {
		return startPoint;
	}

	public MazeCell getEndPoint() {
		return endPoint;
	}
	
	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getNrOfWalls() {
		return nrOfWalls;
	}

	public int getNrOfEmptySpaces() {
		return nrOfEmptySpaces;
	}
	
	public int getNrOfEndPoints() {
		return nrOfEndPoints;
	}
	
	public int getNrOfStartPoints() {
		return nrOfStartPoints;
	}

}
