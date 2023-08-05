package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chessEntities.ChessException;
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
	public boolean[][] possibleMoves() throws ChessException{
		// movimentos possiveis da peça Torre!!
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
		//variavel que recebe o valor da posição da peça rook!! o position vem da classe PIECE!!
		Position p = new Position(0, 0);
		// loop que verifica as posições no  board e atribui true
		
		// MOVIMENTO VERTICAL DA PEÇA TORRE
		p.setValues(position.getRow() - 1, position.getColumn());
		while(!getBoard().thereIsAPiece(p) && getBoard().positionExists(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(position.getRow() - 1);
		}
		if(!getBoard().thereIsAPiece(p) && isThereOpponentPiece(p)) {
			 mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// MOVIMENTO LATERAL ESQUERDO DA TORRE
		p.setValues(position.getRow() , position.getColumn() - 1);
		while(!getBoard().thereIsAPiece(p) && getBoard().positionExists(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(position.getColumn() - 1);
		}
		if(!getBoard().thereIsAPiece(p) && isThereOpponentPiece(p)) {
			 mat[p.getRow()][p.getColumn()] = true;
		}
		
		//MOVIMENTO LATERAL DIREITO DA TORRE
		p.setValues(position.getRow() , position.getColumn() + 1);
		while(!getBoard().thereIsAPiece(p) && getBoard().positionExists(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(position.getColumn() + 1);
		}
		if(!getBoard().thereIsAPiece(p) && isThereOpponentPiece(p)) {
			 mat[p.getRow()][p.getColumn()] = true;
		}
		
		//MOVIMENTO VERTICAL PARA BAIXO TORRE
		p.setValues(position.getRow() + 1 , position.getColumn());
		while(!getBoard().thereIsAPiece(p) && getBoard().positionExists(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(position.getRow() + 1);
		}
		if(!getBoard().thereIsAPiece(p) && isThereOpponentPiece(p)) {
			 mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
	
}
