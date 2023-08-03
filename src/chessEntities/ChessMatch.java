package chessEntities;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
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
	// metodo que coloca uma peça no tabuleiro de acordo com sua cordenada no xadrez
	private void placeNewPiece(char column, int row , ChessPiece chessPiece) {
		board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());
	}
	//coloca as peças no tabuleiro
	private void initialSetup() {
		//usa metodo do board que coloca peças passando como argumento o construtor da peça
		// e sua posição
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	//movimenta a peça e efetua uma captura
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
		Position source = sourcePosition.toPosition();
		validateSourcePosition(source);
		Position target = targetPosition.toPosition();
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
		
	}
	// validar se a posição de origem informada é valida
	private void validateSourcePosition(Position source) {
	    if (!board.thereIsAPiece(source)) {
	        throw new ChessException("There is no piece on source position!!");
	    }
	    //validar se existem movimentos que a peça possa realizar
	    if (!board.piece(source).isThereAnyPossibleMove()) {
	    	throw new ChessException("there is not possible moves for the chosen piece!!");
	    }
	}
	//remover a peça da origem e uma possivel peça do destino
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
}
