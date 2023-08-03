package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chessEntities.ChessPiece;
import chessEntities.Color;

public class Rook extends ChessPiece{
	
	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	@Override
	public boolean[][] possibleMoves() {
		// movimentos possiveis da peça Torre!!
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
		//variavel que recebe o valor da posição da peça rook!! o position vem da classe PIECE!!
		Position p = new Position(0, 0);
		p.setValues(position.getRow() - 1, position.getColumn());
		// loop que verifica as posições no  board e atribui true
		return mat;
	}
}
