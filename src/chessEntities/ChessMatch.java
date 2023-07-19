package chessEntities;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	//cria a matriz das peças que é usada para printar o tabuleiro
	public ChessPiece[][] getPiece(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i = 0; i<board.getRows(); i++) {
			for(int j = 0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i , j);
			}
		}
		return mat;
	}
	//coloca as peças no tabuleiro
	private void initialSetup() {
		//usa metodo do board que coloca peças passando como argumento o construtor da peça
		// e sua posição
		board.placePiece(new Rook(board, Color.WHITE), new Position(2 ,1));
		board.placePiece(new King(board, Color.BLACK), new Position(7 , 4));
	}
}
