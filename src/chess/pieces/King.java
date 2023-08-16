package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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
	
	//metodo auxiliar que verifica se a peça rei pode se mover
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] possibleMoves() {
		// movimentos possiveis da peça Torre!!
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		// loop que verifica as posições no  board e atribui true
		
		// MOVIMENTO VERTICAL PRA CIMA DA PEÇA KING
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// MOVIMENTO VERTICAL PRA BAIXO DA PEÇA KING
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// MOVIMENTO LATERAL DIREITO DA PEÇA KING
		p.setValues(position.getRow(), position.getColumn()  + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// MOVIMENTO LATERAL ESQUERDO DA PEÇA KING
		p.setValues(position.getRow(), position.getColumn()  - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// MOVIMENTO DIAGONAL SUPERIOR ESQUERDO DA PEÇA KING
		p.setValues(position.getRow() - 1, position.getColumn()  - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// MOVIMENTO DIAGONAL SUPERIOR DIREITO DA PEÇA KING
		p.setValues(position.getRow() - 1, position.getColumn()  + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// MOVIMENTO DIAGONAL INFERIOR ESQUERDO DA PEÇA KING
		p.setValues(position.getRow() + 1, position.getColumn()  - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// MOVIMENTO DIAGONAL INFERIOR DIREITO DA PEÇA KING
		p.setValues(position.getRow() + 1, position.getColumn()  + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
	
}
