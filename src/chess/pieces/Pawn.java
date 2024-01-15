package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chessEntities.ChessMatch;
import chessEntities.ChessPiece;
import chessEntities.Color;

public class Pawn extends ChessPiece{
	
	private ChessMatch chessmatch;

	public Pawn(Board board, Color color, ChessMatch chessmatch) {
		super(board, color);
		this.chessmatch = chessmatch;
	}
	

	@Override
	public boolean[][] possibleMoves() {
		// movimentos possiveis da peça peão
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		// MOVIMENTO VERTICAL DA PEÇA PEÃO BRANCO
		if(getColor() == color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
	
			// MOVIMENTO VERTICAL INICIAL DA PEÇA PEÃO BRANCO
			p.setValues(position.getRow() - 2, position.getColumn());
			// nova var posição criada para testar se a posição 1 esta livre
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			// condição verifica se cada uma posição esta livre a frente
			//a primeira e a segunda posição devem estar livres bem como o contador de movimento deve ser 0
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && !getBoard().thereIsAPiece(p2) && getBoard().positionExists(p2) &&
					getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// MOVIMENTO DIAGONAL SUPERIOR ESQUERDO DA PEÇA PEÃO BRANCO
			p.setValues(position.getRow() - 1, position.getColumn()  - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// MOVIMENTO DIAGONAL SUPERIOR DIREITO DA PEÇA PEÃO BRANCO
			p.setValues(position.getRow() - 1, position.getColumn()  + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; }
			
			//movimento especial EnPassant peças brancas
			if (position.getRow() == 3) {
				Position left = new Position (position.getRow(), position.getColumn() -1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) 
						&& getBoard().piece(left) == chessmatch.getEnpassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
			}
			// mesma coisa agora para o lado direito
				Position right = new Position (position.getRow(), position.getColumn()+1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) 
						&& getBoard().piece(right) == chessmatch.getEnpassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			
    }
	
    else {
			// MOVIMENTO VERTICAL DA PEÇA PEÃO PRETO
			if(getColor() == color.BLACK) {
			p.setValues(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// MOVIMENTO VERTICAL INICIAL DA PEÇA PEÃO PRETO
			p.setValues(position.getRow() + 2, position.getColumn());
			// nova var posição criada para testar se a posição 1 esta livre
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			// condição verifica se cada uma posição esta livre a frente
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && !getBoard().thereIsAPiece(p2) && getBoard().positionExists(p2)
					&& getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;

			}
			// MOVIMENTO DIAGONAL SUPERIOR ESQUERDO DA PEÇA PEÃO PRETO
			p.setValues(position.getRow() + 1, position.getColumn()  - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// MOVIMENTO DIAGONAL SUPERIOR DIREITO DA PEÇA PEÃO PRETO
			p.setValues(position.getRow() + 1, position.getColumn()  + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//movimento especial EnPassant peças pretas
			if (position.getRow() == 4) {
				Position left = new Position (position.getRow(), position.getColumn() -1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) 
						&& getBoard().piece(left) == chessmatch.getEnpassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
			}
			// movimento enpassant lado direito
				Position right = new Position (position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) 
						&& getBoard().piece(right) == chessmatch.getEnpassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
		}
			} 
		return mat;
    }

	@Override
	public String toString() {
		return "P";
	}
}
