package chessEntities;

import boardgame.Board;

public class ChessMatch {
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private Board board;
	
	public ChessMatch(Board board) {
		board = new Board(8,8);
	}
	
	
}
