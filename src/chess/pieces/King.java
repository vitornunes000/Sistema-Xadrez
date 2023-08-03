package chess.pieces;

import boardgame.Board;
import chessEntities.ChessPiece;
import chessEntities.Color;

public class King extends ChessPiece{
	
	public King(Board  board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}
	@Override
	public boolean[][] possibleMoves() {
		// movimentos possiveis da peça Torre!!
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
}
