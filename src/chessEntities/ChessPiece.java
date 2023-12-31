package chessEntities;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
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
	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}
	public int getMoveCount() {
		return moveCount;
	}
	//aumenta a contagem de movimento
	protected void increaseMoveCount() {
		moveCount++;
	}
	//diminui contagem de movimento
	protected void decreaseMoveCount() {
		moveCount--;
	}
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece chesspiece = (ChessPiece) getBoard().piece(position);
		return chesspiece != null && chesspiece.getColor() !=color;
		
	}
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	

	
	
	
	
}
