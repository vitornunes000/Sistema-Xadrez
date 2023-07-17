package chessEntities;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece {
	protected Color color;
	protected int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	
}
