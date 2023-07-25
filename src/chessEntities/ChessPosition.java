package chessEntities;

import boardgame.Position;

public class ChessPosition {
	private char column;
	private int row;
	private Position position;
	
	public ChessPosition() {
		
	}

	public ChessPosition(char column, int row) {
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public void setColumn(char column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	protected Position toPosition() {
		return new Position(8 -row, column - 'a');
	}
	protected ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row; //macete para forçar a concatenação
	}
}
