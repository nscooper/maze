package com.nscooper.maze;

import com.google.common.collect.Table.Cell;

public final class MazeCell implements Cell<Integer, Integer, String> {

	private Integer rowKey = null;
	private Integer columnKey = null;
	private String value = null;
	
	public MazeCell(Integer rowKey, Integer columnKey, String value) {
		super();
		this.rowKey = rowKey;
		this.columnKey = columnKey;
		this.value = value;
	}

	/**
	 * Clone a copy of this cell
	 * @return
	 */
	public final MazeCell copy() {
		return new MazeCell(this.getRowKey(), this.getColumnKey(), this.getValue());
	}

	public Integer getRowKey() {
		return rowKey;
	}

	public void setRowKey(Integer rowKey) {
		this.rowKey = rowKey;
	}

	public Integer getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(Integer columnKey) {
		this.columnKey = columnKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnKey == null) ? 0 : columnKey.hashCode());
		result = prime * result + ((rowKey == null) ? 0 : rowKey.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MazeCell other = (MazeCell) obj;
		if (columnKey == null) {
			if (other.columnKey != null)
				return false;
		} else if (!columnKey.equals(other.columnKey))
			return false;
		if (rowKey == null) {
			if (other.rowKey != null)
				return false;
		} else if (!rowKey.equals(other.rowKey))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
