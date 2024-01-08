package chess.pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chessEntities.ChessMatch;
import chessEntities.ChessPiece;
import chessEntities.Color;

public class King extends ChessPiece{
	private ChessMatch chessmatch;
	
	public King(Board  board, Color color, ChessMatch chessmatch) {
		super(board, color);
		this.chessmatch = chessmatch;
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
	private boolean testRookCastling(Position position) {
		ChessPiece rook = (ChessPiece)getBoard().piece(position);
		return rook != null && rook instanceof Rook && rook.getMoveCount() == 0;
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
		
		//MOVIMENTO ROQUE  DO REI
		if(!chessmatch.getCheck() && moveCount == 0) {
			//movimento roque da direita
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
				}
			//movimento roque da esquerda
			Position post2 = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastling(post2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null 
						&& getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
				}
		}

		
		return mat;
	}
	
}
